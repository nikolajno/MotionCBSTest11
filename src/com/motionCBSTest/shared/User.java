package com.motionCBSTest.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private String mobilenr;
    private String education;
    private String experience;
    private int hoursPrWeek;
    private String password;
    private String teamtype;
    private int type;
    private boolean isApproved;
    private String teamName;


    public User(int id, String fname, String lname, String email, String address, String mobilenr,
                String education, String experience, int hoursPrWeek, String password, String teamtype, int type, boolean isApproved, String teamName) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.address = address;
        this.mobilenr = mobilenr;
        this.education = education;
        this.experience = experience;
        this.hoursPrWeek = hoursPrWeek;
        this.password = password;
        this.teamtype = teamtype;
        this.type = type;
        this.isApproved = isApproved;
        this.teamName = teamName;
    }

    public User(){}


    // Getters
    public int getId() {return id;}

    public String getFname() { return fname; }

    public String getLname() { return lname; }

    public String getEmail() { return email; }

    public String getAddress() { return address; }

    public String getMobilenr() { return mobilenr; }

    public String getEducation() { return education; }

    public String getExperience() { return experience; }

    public int getHoursPrWeek() { return hoursPrWeek; }

    public String getPassword() { return password; }

    public String getTeamtype() { return teamtype; }

    public int getType() { return type; }

    public boolean getIsApproved() { return isApproved; }

    public String getTeamName() {
        return teamName;
    }

    // Setters
    public void setId(int id) { this.id = id; }

    public void setFname(String fname) { this.fname = fname; }

    public void setLname(String lname) { this.lname = lname; }

    public void setEmail(String email) { this.email = email; }

    public void setAddress(String address) { this.address = address; }

    public void setMobilenr(String mobilenr) { this.mobilenr = mobilenr; }

    public void setEducation(String education) { this.education = education; }

    public void setExperience(String experience) { this.experience = experience; }

    public void setHoursPrWeek(int hoursPrWeek) { this.hoursPrWeek = hoursPrWeek; }

    public void setPassword(String password) { this.password = password; }

    public void setTeamtype(String teamtype) { this.teamtype = teamtype; }

    public void setType(int type) {this.type = type;}

    public void setIsApproved (Boolean isApproved) {this.isApproved = isApproved; }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }



}

