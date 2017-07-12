package com.isha.donation.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "volunteerdetails")
public class Volunteer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Volunteer_ID", nullable = false)
    private Long id;

    public Volunteer(Long id) {
        this.id = id;
    }

    @Column(name = "Volunteer_User_Name", nullable = false)
    private String userName;

    @Column(name = "Volunteer_Name", nullable = false)
    private String name;

    @Column(name = "Volunteer_Phone_Num", nullable = false)
    private String phoneNo;

    @Column(name = "Volunteer_Email_Id")
    private String email;

    @Column(name = "Volunteer_Password")
    private String password;

    @Column(name = "Volunteer_Status")
    private String status="Active";

    @Column(name = "Create_Dt")
    private String createDate;

    @Column(name = "Update_Dt")
    private String updateDate;

    @Column(name = "Comments")
    private String comments;

    private transient String confirmPassword;

    public Volunteer(String userName, String name, String PhoneNo, String email, String password, String status,
            String createDate, String updateDate, String comments) {
        this.userName = userName;
        this.name = name;
        this.phoneNo = PhoneNo;
        this.email = email;
        this.password = password;
        this.status = status;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.comments = comments;
    }

    public Volunteer() {
        // TODO Auto-generated constructor stub
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getStatus() {
        return status;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getupdateDate() {
        return updateDate;
    }

    public String getComments() {
        return comments;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
    
}
