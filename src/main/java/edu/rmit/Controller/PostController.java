package edu.rmit.Controller;

import io.javalin.http.Handler;
import static edu.rmit.Utils.Utils.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

import edu.rmit.Entity.Post;
import edu.rmit.Utils.JDBCConnection;

import static edu.rmit.Utils.RequestUtil.*;
public class PostController {
    /**
     * 处理添加博文的POST请问
     */
    public static Handler handleNewPostPost = ctx -> {
        Map<String, Object> model = new HashMap<String, Object>();
        int result = 0;
        JDBCConnection jdbc = JDBCConnection.getConnection();
        String body = getQueryBody(ctx);
        String email = getQueryParamEmail(ctx);
        String timeStampStr = getQueryPostTimeStamp(ctx);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        Date date = sdf.parse(timeStampStr);
        Long postID = getUID();
        Post post = new Post();
        post.setPostID(postID);
        post.setBody(body);
        post.setPostTimestamp(date);
        post.setEmail(email);
        result = jdbc.insertPost(post);
        if(result > 0)
        {
            model.put("success",true);
            model.put("msg","add a new post successfully.");
            ctx.json(model);
        }
        else{
            model.put("success",false);
            model.put("msg","unknow error.");
            ctx.json(model);
        }
    };
}
