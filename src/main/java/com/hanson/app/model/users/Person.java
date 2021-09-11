package com.hanson.app.model.users;


import com.hanson.app.enums.Gender;

public class Person {
    private int idNo;
    private String name;
    private Gender gender;
    private String phone;
    private String email;

    public Person() {
    }

    public Person(int idNo, String name, String gender, String phone, String email) {
        super();
        this.name = name;
        this.gender = Gender.valueOf(gender);
        this.phone = phone;
        this.idNo = idNo;
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getIdNo() {
        return idNo;
    }

    public void setIdNo(int idNo) {
        this.idNo = idNo;
    }
}
