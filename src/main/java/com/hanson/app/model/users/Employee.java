package com.hanson.app.model.users;

import com.hanson.app.enums.Gender;

public class Employee extends Person {
    private String employeeId;
    private String branch;

    public Employee(int idNo, String name, String gender, String phone, String email, String employeeId, String branch) {
        super(idNo, name, gender, phone, email);
        this.employeeId = employeeId;
        this.branch = branch;
    }

    public Employee() {
        super();
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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
        sql = "insert into employees(idNo,name,gender,email,phone,employeeId,branch) values(";
        sql += "'" + getIdNo() + "',";
        sql += "'" + getName() + "',";
        sql += "'" + getGender() + "',";
        sql += "'" + getEmail() + "',";
        sql += "'" + getPhone() + "',";
        sql += "'" + getEmployeeId() + "',";
        sql += "'" + getBranch() + "')";
        return sql;
    }

    public String createUpdateSql(){

        String sql = "update employees set ";

        String setColumns = "";

        if (getName() != null && !getName().trim().equals(""))
            setColumns += "name='" + getName();
        if (!setColumns.equals("") && getGender() != null)
            setColumns += "',gender='" + getGender() + "'";
        setColumns += ",email='" + getEmail() + "'";
        setColumns += ",employeeId='" + getEmployeeId() + "'";
        setColumns += ",branch='" + getBranch() + "'";
        sql += setColumns;
        sql += " where idNo='" + getIdNo()+"'";
        return sql;
    }
}
