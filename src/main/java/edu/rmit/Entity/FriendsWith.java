package edu.rmit.Entity;

import java.util.Date;

public class FriendsWith {
    private String memberOneEmail;
    private String memberTwoEmail;
    private Date startDate;
    
    public String getMemberOneEmail() {
        return memberOneEmail;
    }
    public void setMemberOneEmail(String memberOneEmail) {
        this.memberOneEmail = memberOneEmail;
    }
    public String getMemberTwoEmail() {
        return memberTwoEmail;
    }
    public void setMemberTwoEmail(String memberTwoEmail) {
        this.memberTwoEmail = memberTwoEmail;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    
}
