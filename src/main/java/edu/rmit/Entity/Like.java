package edu.rmit.Entity;

public class Like {
    private String email;
    private Integer postID;     //Primary Key

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Integer getPostID() {
        return postID;
    }
    public void setPostID(Integer postID) {
        this.postID = postID;
    }
}
