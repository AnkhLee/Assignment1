package edu.rmit.Utils;

import io.javalin.http.Context;

public class RequestUtil {

    public static String getFormParamEmail(Context ctx)
    {
        return ctx.formParam("email");
    }
    public static String getFormParamFullName(Context ctx)
    {
        return ctx.formParam("fullName");
    }
    public static String getFormParamPassowrd(Context ctx)
    {
        return ctx.formParam("password");
    }
    public static String getFormParamScreenName(Context ctx)
    {
        return ctx.formParam("screenName");
    }
    public static String getFormParamGender(Context ctx)
    {
        return ctx.formParam("gender");
    }
    public static String getFormParamStatus(Context ctx)
    {
        return ctx.formParam("status");
    }
    public static String getFormParamLocation(Context ctx)
    {
        return ctx.formParam("location");
    }
    public static String getFormParamVisibility(Context ctx)
    {
        return ctx.formParam("visibility");
    }
    public static String getFormParamDateOfBirth(Context ctx)
    {
        return ctx.formParam("dateOfBirth");
    }


    public static String getQueryPostID(Context ctx)
    {
        return ctx.pathParam("postID");
    }
    public static String getQueryPostTimeStamp(Context ctx)
    {
        return ctx.formParam("postTimeStamp");
    }
    public static String getQueryBody(Context ctx)
    {
        return ctx.formParam("body");
    }
    public static String getQueryParentPostID(Context ctx)
    {
        return ctx.formParam("patentPostID");
    }
    public static String getQueryParamEmail(Context ctx)
    {
        return ctx.formParam("email");
    }

    
    public static String getQueryMemberOneEmail(Context ctx)
    {
        return ctx.pathParam("memberOneEmail");
    }
    public static String getQueryMemberTwoEmail(Context ctx)
    {
        return ctx.pathParam("memberTwoEmail");
    }

    
    
    public static String getQueryMemberRequesterEmail(Context ctx)
    {
        return ctx.pathParam("memberRequesterEmail");
    }
    public static String getQueryMemberRecepientEmail(Context ctx)
    {
        return ctx.pathParam("memberReceipentEmail");
    }
    public static String getQueryRequestDate(Context ctx)
    {
        return ctx.pathParam("requestDate");
    }
}
