package edu.rmit;

//import java.io.IOException;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

import java.io.IOException;

import edu.rmit.Controller.PostController;
import edu.rmit.Controller.UserController;
import edu.rmit.Utils.*;

public class App {

  public static final int SERVER_PORT = 8080;
  public static final String CSS_DIR = "css/";
  public static final String IMAGES_DIR = "images/";
  public static final String JAVASCRIPT_DIR = "js/";

  public static final String RMIT_USERNAME = "s3706335";
  public static char[] password = null;

  public static void main(String[] args) throws Exception {
    // get general RMIT password
    try {
      password = PasswordField.getPassword(System.in,
          "Click here and enter your RMIT password for account \"" + RMIT_USERNAME + "\": ");
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }

    // //establish ssh tunnel through firewall to RMIT Oracle server
    SSHTunnel.open();

    // Establish database connection
    if (JDBCConnection.getConnection() == null) {
      throw new Exception("Could not establish connection to database");
    }

    // Create our HTTP server and listen in port 7000
    Javalin app = Javalin.create(config -> {
      config.registerPlugin(new RouteOverviewPlugin("/help/routes"));

      // Uncomment this if you have files in the CSS Directory
      config.addStaticFiles(CSS_DIR);

      // Uncomment this if you have files in the Images Directory
      config.addStaticFiles(IMAGES_DIR);

      config.addStaticFiles(JAVASCRIPT_DIR);
    }).start(SERVER_PORT);

    // capture ctrl-c signal so we can shutdown server safely
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      app.stop();
    }));

    // handle shutdown events by closing database and ssh tunnel connections
    app.events(event -> {
      event.serverStopping(() -> {
        System.out.println("server stopping");
      });
      event.serverStopped(() -> {
        System.out.println("server stopped");

        // Close Database connection
        JDBCConnection.closeConnection();
        // close SSH Tunnel
        // SSHTunnel.close();
      });
    });

    configureRotes(app);
  }

  public static void configureRotes(Javalin app) {
    app.get("/", ctx -> ctx.redirect("/login"));
    app.get("/login", ctx -> ctx.render("login.html"));
    app.post("/login", UserController.handleLoginPost);
    app.get("/homepage", ctx -> ctx.render("homepage.html"));
    app.get("/error", ctx -> ctx.render("error.html"));
    app.get("/register", ctx -> ctx.render("register.html"));
    app.post("/register", UserController.handlerRegisterPost);
    app.post("/newpost", PostController.handleNewPostPost);
  }
}
