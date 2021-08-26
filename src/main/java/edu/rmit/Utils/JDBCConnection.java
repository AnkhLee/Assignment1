package edu.rmit.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import edu.rmit.Entity.Member;
import edu.rmit.Entity.Post;

/**
 * Class for Managing the JDBC Connection to a SQLLite Database. Allows SQL
 * queries to be used with the SQLLite Databse in Java.
 * 
 * This is an example JDBC Connection that has a single query for the Movies
 * Database This is similar to the project workshop JDBC examples.
 *
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 * @author Timothy Wiley, 2021. email: timothy.wiley@rmit.edu.au
 * @author Halil Ali, 2021. email halil.ali@rmit.edu.au
 */
public class JDBCConnection {

   // the default oracle account uses the the read only MOVIES database
   // once you create a set of tables in your own account, update this to your RMIT
   // Oracle account details
   private static final String DATABASE_USERNAME = "S3706335";
   private static final String DATABASE_PASSWORD = "Ankh456852";

   private static final String DATABASE_URL = "jdbc:oracle:thin:@//localhost:9922/CSAMPR1.its.rmit.edu.au";
   // private static final String DATABASE_URL =
   // "jdbc:oracle:thin:@//talsprddb01.int.its.rmit.edu.au:1521/CSAMPR1.ITS.RMIT.EDU.AU";
   private static JDBCConnection jdbc = null;
   private static Connection connection;

   /**
    * Singleton function to return single copy of this class to other classes
    **/
   public static JDBCConnection getConnection() {

      // check that ssh session is still open (if not reopen)
      // SSHTunnel.getSession();

      // check that JDBCconnection is available (if not establish)
      if (jdbc == null) {
         jdbc = new JDBCConnection();
      }
      return jdbc;
   }

   /**
    * Hidden constructor to establish Database connection (once)
    **/
   private JDBCConnection() {
      System.out.println("Created JDBC Connection Object");

      try {
         // Connect to JDBC data base
         connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USERNAME, DATABASE_PASSWORD);
      } catch (SQLException e) {
         // If there is an error, lets just print the error
         System.err.println(e.getMessage());
      }
   }

   /**
    * Closes the database connection - called only when server shutdown
    **/
   public static void closeConnection() {
      try {
         if (connection != null) {
            connection.close();
            System.out.println("Database Connection closed");
         }
      } catch (SQLException e) {
         // connection close failed.
         System.err.println(e.getMessage());
      }
   }

   public ArrayList<Member> queryUserByEmail(String email) {
      ArrayList<Member> members = null;
      try {
         members = new ArrayList<Member>();
         Statement statement = connection.createStatement();
         statement.setQueryTimeout(30);

         String query = "SELECT *" + "\n" + "FROM MEMBER" + "\n" + "WHERE EMAIL='" + email + "'";

         ResultSet results = statement.executeQuery(query);
         while (results.next()) {
            Member member = new Member();
            member.setEmail(results.getString("EMAIL"));
            member.setFullName(results.getString("FULLNAME"));
            member.setScreenName(results.getString("SCREENNAME"));
            member.setDateOfBirth(results.getDate("DATEOFBIRTH"));
            member.setGender(results.getString("GENDER"));
            member.setStatus(results.getString("STATUS"));
            member.setLocation(results.getString("LOCATION"));
            member.setVisibility(results.getString("VISIBILITY"));
            member.setPassword(results.getString("PASSWORD"));
            members.add(member);
         }

      } catch (SQLException e) {
         System.err.println(e.getMessage());
      }
      return members;
   }

   public Integer insertMember(Member member) {
      int result = 0;
      try {
         String sql = "INSERT INTO MEMBER(EMAIL,PASSWORD,FULLNAME,SCREENNAME,DATEOFBIRTH,GENDER,STATUS,LOCATION,VISIBILITY) values(?,?,?,?,?,?,?,?,?)";
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setString(1, member.getEmail());
         ps.setString(2, member.getPassword());
         ps.setString(3, member.getFullName());
         ps.setString(4, member.getScreenName());
         ps.setObject(5, member.getDateOfBirth());
         ps.setString(6, member.getGender());
         ps.setString(7, member.getStatus());
         ps.setString(8, member.getLocation());
         ps.setString(9, member.getVisibility());
         result = ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }

   public Integer insertPost(Post post) {
      int result = 0;
      try {
         String sql = "INSERT INTO POST(POSTID,POSTTIMESTAMP,BODY,PARENTPOSTID,EMAIL) values(?,?,?,?,?)";
         PreparedStatement ps = connection.prepareStatement(sql);
         ps.setObject(1, post.getPostID());
         ps.setTimestamp(2, new Timestamp(post.getPostTimestamp().getTime()));
         ps.setString(3, post.getBody());
         ps.setObject(4, post.getParentPostId());
         ps.setString(5, post.getEmail());
         result = ps.executeUpdate();
      } catch (SQLException e) {
         e.printStackTrace();
      }
      return result;
   }
}
