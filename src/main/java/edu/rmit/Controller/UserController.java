package edu.rmit.Controller;

import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import edu.rmit.Entity.Member;
import edu.rmit.Utils.JDBCConnection;
import io.javalin.http.Handler;

import static edu.rmit.Utils.RequestUtil.*;

public class UserController {

    /**
     * 处理用户登陆的POST请求
     */
    public static Handler handleLoginPost = ctx -> 
    {
        Map<String,Object> model = new HashMap<String,Object>();
        if(!authenticate(getFormParamEmail(ctx), getFormParamPassowrd(ctx)))
        {
            model.put("authentication",false);
            model.put("msg","invalid email or fullname");
            ctx.redirect("/login");
        }
        else
        {
            model.put("authentication",true);
            ctx.sessionAttribute("currentMemberEmail",getFormParamEmail(ctx));
            ctx.sessionAttribute("currentMemberFullName",getFormParamFullName(ctx));
            ctx.redirect("/homepage");
        }
    };

    /**
     * 处理注册新成员的POST请求
     */
    public static Handler handlerRegisterPost = ctx -> {
        JDBCConnection jdbc = JDBCConnection.getConnection();
        String email = getFormParamEmail(ctx);
        String fullName = getFormParamFullName(ctx);
        if(email == null || fullName == null)
            ctx.redirect("/register");
        ArrayList<Member> others = jdbc.queryUserByEmail(email);
        if(others.size() != 0)
            ctx.redirect("/register");
        String screenName = getFormParamScreenName(ctx);
        String gender = getFormParamGender(ctx);
        String status = getFormParamStatus(ctx);
        String password = getFormParamPassowrd(ctx);
        String location = getFormParamLocation(ctx);
        String visibility = getFormParamVisibility(ctx);
        String dateOfBirth = getFormParamDateOfBirth(ctx);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(dateOfBirth);
        Member member = new Member(email,password,fullName,screenName,new java.sql.Date(date.getTime()),gender,status,location,visibility);

        int result = jdbc.insertMember(member);
        if(result>0)
        {
            ctx.sessionAttribute("currentMemberEmail",member.getEmail());
            ctx.sessionAttribute("currentMemberScreenName",member.getScreenName());
            ctx.redirect("/homepage");
        }
        else{
            ctx.redirect("/register");
        }
    };

    public static boolean authenticate(String email, String password)
    {
        if(email == null || password == null)
            return false;
         JDBCConnection jdbc = JDBCConnection.getConnection();
         ArrayList<Member> members = jdbc.queryUserByEmail(email);
         if(members.size() > 0 && members.get(0).getPassword().equals(password))
            return true;
        return false;
    }
}
