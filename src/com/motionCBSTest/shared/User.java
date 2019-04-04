package com.motionCBSTest.shared;
//Hej Christian

import com.google.gwt.user.client.rpc.IsSerializable;

public class User implements IsSerializable {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String address;
    private int mobilenr;
    private String education;
    private int experience;
    private int hoursPrWeek;
    private String password;
    private String teamtype;
    private int type;

    public User(int id, String fname, String lname, String email, String address, int mobilenr,
                String education, int experience, int hoursPrWeek, String password, String teamtype, int type) {
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
    }

    public User(){}


    // Getters
    public int getId() {return id;}

    public String getFname() { return fname; }

    public String getLname() { return lname; }

    public String getEmail() { return email; }

    public String getAddress() { return address; }

    public int getMobilenr() { return mobilenr; }

    public String getEducation() { return education; }

    public int getExperience() { return experience; }

    public int getHoursPrWeek() { return hoursPrWeek; }

    public String getPassword() { return password; }

    public String getTeamtype() { return teamtype; }

    public int getType() { return type; }

    // Setters
    public void setId(int id) { this.id = id; }

    public void setFname(String fname) { this.fname = fname; }

    public void setLname(String lname) { this.lname = lname; }

    public void setEmail(String email) { this.email = email; }

    public void setAddress(String address) { this.address = address; }

    public void setMobilenr(int mobilenr) { this.mobilenr = mobilenr; }

    public void setEducation(String education) { this.education = education; }

    public void setExperience(int experience) { this.experience = experience; }

    public void setHoursPrWeek(int hoursPrWeek) { this.hoursPrWeek = hoursPrWeek; }

    public void setPassword(String password) { this.password = password; }

    public void setTeamtype(String teamtype) { this.teamtype = teamtype; }

    public void setType(int type) {this.type = type;}
}

