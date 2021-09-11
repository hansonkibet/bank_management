package com.hanson.app.model.users;


import com.hanson.app.interfaces.ComparatorI;

public class Banker extends Person implements ComparatorI<Banker> {
    private String bankerId;
    private String branch;

    public Banker(int idNo, String name, String gender, String phone, String email, String employeeId, String branch) {
        super(idNo, name, gender, phone, email);
        this.bankerId = employeeId;
        this.branch = branch;
    }

    public Banker() {
        super();
    }

    public String getBankerId() {
        return bankerId;
    }

    public void setBankerId(String bankerId) {
        this.bankerId = bankerId;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
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

    @Override
    public String toString() {
        return "" +this.getName()+
                " " +this.getIdNo()+
                " " +this.getPhone()+
                " " +this.getBranch()+
                " " +this.getBankerId();
    }

    @Override
    public int compareTo(Banker banker) {
        int i = this.getName().compareTo(banker.getName());
        if (i != 0)
            return i;
        i = this.bankerId.compareTo(banker.getBankerId());
        if (i !=0)
            return i;
//        int compare = Integer.compare(this.getBranch(), banker.getBranch());
        int compare = 0;
        return compare;
    }

    public String createInsertSql(){
        String sql;
        sql = "insert into bankers(id,name,gender,email,phone,bankerId,branch) values(";
        sql += "'" + getIdNo() + "',";
        sql += "'" + getName() + "',";
        sql += "'" + getGender() + "',";
        sql += "'" + getEmail() + "',";
        sql += "'" + getPhone() + "',";
        sql += "'" + getBankerId() + "')";
        sql += "'" + getBranch() + "')";
        return sql;
    }
    public String createUpdateSql(){

        String sql = "update bankers set ";

        String setColumns = "";

        if (getName() != null && !getName().trim().equals(""))
            setColumns += "name='" + getName();
        if (!setColumns.equals("") && getGender() != null)
            setColumns += "',gender='" + getGender() + "'";
        setColumns += ",email='" + getEmail() + "'";
        setColumns += ",phone='" + getPhone() + "'";
        setColumns += ",bankerId='" + getBankerId() + "'";
        setColumns += ",branch='" + getBranch() + "'";
        sql += setColumns;
        sql += " where id='" + getIdNo()+"'";
        System.out.println(sql);
        return sql;
    }

}
