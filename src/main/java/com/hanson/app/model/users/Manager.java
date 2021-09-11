package com.hanson.app.model.users;

import com.hanson.app.enums.Gender;

public class Manager extends Person{
    private String branch;

    public Manager(int idNo, String name, String gender, String phone, String email, String branch) {
        super(idNo, name, gender, phone, email);
        this.branch = branch;
    }

    public Manager(){
        super();
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public Gender getGender() {
        return super.getGender();
    }

    @Override
    public void setGender(Gender gender) {
        super.setGender(gender);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getPhone() {
        return super.getPhone();
    }

    @Override
    public void setPhone(String phone) {
        super.setPhone(phone);
    }

    @Override
    public int getIdNo() {
        return super.getIdNo();
    }

    @Override
    public void setIdNo(int idNo) {
        super.setIdNo(idNo);
    }

    public String createInsertSql(){
        String sql;
        sql = "insert into managers(id,name,gender,email,phone,branch) values(";
        sql += "'" + getIdNo() + "',";
        sql += "'" + getName() + "',";
        sql += "'" + getGender() + "',";
        sql += "'" + getEmail() + "',";
        sql += "'" + getPhone() + "',";
        sql += "'" + getBranch() + "')";
        return sql;
    }
    public String createUpdateSql(){

        String sql = "update managers set ";

        String setColumns = "";

        if (getName() != null && !getName().trim().equals(""))
            setColumns += "name='" + getName();
        if (!setColumns.equals("") && getGender() != null)
            setColumns += "',gender='" + getGender() + "'";
        setColumns += ",email='" + getEmail() + "'";
        setColumns += ",phone='" + getPhone() + "'";
        setColumns += ",branch='" + getBranch() + "'";
        sql += setColumns;
        sql += " where id='" + getIdNo()+"'";
        System.out.println(sql);
        return sql;
    }
}
