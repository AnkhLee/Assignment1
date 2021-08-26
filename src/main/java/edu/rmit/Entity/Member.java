package edu.rmit.Entity;

import java.util.Date;

public class Member {
    private String email;
    private String password;
    private String fullName;
    private String screenName;
    private Date dateOfBirth;
    private String gender;
    private String status;
    private String location;
    private String visibility;
    
    public Member(){
        
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public Member(String email, String password, String fullName, String screenName, Date dateOfBirth, String gender,
            String status, String location, String visibility) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
        this.screenName = screenName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.status = status;
        this.location = location;
        this.visibility = visibility;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public String getScreenName() {
        return screenName;
    }
    public void setScreenName(String screenName) {
        this.screenName = screenName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getLocation() {
        return location;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public String getVisibility() {
        return visibility;
    }
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
