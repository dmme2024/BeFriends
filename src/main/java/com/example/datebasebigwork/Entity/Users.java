package com.example.datebasebigwork.Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Users implements Serializable{
    @Id
    @Column(name="username",columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(name = "userPassword", columnDefinition = "VARCHAR(50) NOT NULL")
    private String userPassword;

    @Column(name = "phoneNumber", columnDefinition = "VARCHAR(50) NOT NULL")
    private String phoneNumber;

    @Column(name = "email",columnDefinition = "VARCHAR(50) NOT NULL")
    private String email;

    @Column(name = "truename",columnDefinition = "VARCHAR(20)")
    private String truename;

    @Column(name = "userPic",columnDefinition = "VARCHAR(20)")
    private String userPic;

    @Column(name = "userIntroduction",columnDefinition = "VARCHAR(100)")
    private String userIntroduction;

    @Column(name = "address",columnDefinition = "VARCHAR(100)")
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTruename() {
        return truename;
    }

    public void setTruename(String truename) {
        this.truename = truename;
    }

    public String getUserPic() {
        return userPic;
    }

    public void setUserPic(String userPic) {
        this.userPic = userPic;
    }

    public String getUserIntroduction() {
        return userIntroduction;
    }

    public void setUserIntroduction(String userIntroduction) {
        this.userIntroduction = userIntroduction;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}