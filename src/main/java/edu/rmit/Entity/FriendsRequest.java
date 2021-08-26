package edu.rmit.Entity;

import java.util.Date;

public class FriendsRequest {
    private String memberRequesterEmail;
    private String memberRecepieEmail;
    private Date RequestDate;
    private String RequestStatus;
    public String getMemberRequesterEmail() {
        return memberRequesterEmail;
    }
    public void setMemberRequesterEmail(String memberRequesterEmail) {
        this.memberRequesterEmail = memberRequesterEmail;
    }
    public String getMemberRecepieEmail() {
        return memberRecepieEmail;
    }
    public void setMemberRecepieEmail(String memberRecepieEmail) {
        this.memberRecepieEmail = memberRecepieEmail;
    }
    public Date getRequestDate() {
        return RequestDate;
    }
    public void setRequestDate(Date requestDate) {
        RequestDate = requestDate;
    }
    public String getRequestStatus() {
        return RequestStatus;
    }
    public void setRequestStatus(String requestStatus) {
        RequestStatus = requestStatus;
    }
    
}
