package com.hanson.app.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseUtilities {

    private final String url = "jdbc:mysql://localhost:3306/bank";
    private final String username = "root";
    private final String password = "";
    private Connection getDbConnection() throws Exception{
        return DriverManager.getConnection(url, username, password);
    }

    public ResultSet execQuery(String sql){
        if  (sql == null || sql.trim().equals(""))
            return null;

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
            return preparedStatement.executeQuery();

        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }

        return null;

    }

    public boolean execUpdate(String sql){
        if  (sql == null || sql.trim().equals(""))
            return false;

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
           return (preparedStatement.executeUpdate() ==0)?false:true;

        }catch (Exception ex){
            System.out.println("Error from executeUpdate Query "+ex.getMessage());

        }
        return false;
    }

}
