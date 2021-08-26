package edu.rmit.Entity;

import java.util.Date;

public class Post {
    private Long postID;
    private Date postTimestamp;
    private String body;
    private Long parentPostId;
    private String email;
    
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    
    public Long getPostID() {
        return postID;
    }
    public void setPostID(Long postID) {
        this.postID = postID;
    }
    public Long getParentPostId() {
        return parentPostId;
    }
    public void setParentPostId(Long parentPostId) {
        this.parentPostId = parentPostId;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Date getPostTimestamp() {
        return postTimestamp;
    }
    public void setPostTimestamp(Date postTimestamp) {
        this.postTimestamp = postTimestamp;
    }
}
