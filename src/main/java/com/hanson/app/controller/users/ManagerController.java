package com.hanson.app.controller.users;

import com.hanson.app.enums.Gender;
import com.hanson.app.interfaces.UsersControllerI;
import com.hanson.app.model.users.Manager;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManagerController extends DatabaseUtilities implements UsersControllerI<Manager> {
    @Override
    public String create(Manager manager) {
        try {
            if ((searchManagerId(manager.getIdNo()))==-1){
                System.out.println(execUpdate((manager.createInsertSql()))?"inserted successfully":"Failed to insert");
            }
            else {
                System.out.println("User already exists");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void edit(Manager manager) {
        try {
            if (searchManagerId(manager.getIdNo()) == -1){
                System.out.println("manager not found please enter the correct manager Id");
                return;
            }
            else {
                System.out.println((execUpdate(manager.createUpdateSql()))?"manager details updated successfully":"manager details failed to update");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (searchManagerId(id)==-1){
                System.out.println("Manager doesn't exist please enter correct banker id");
                return;
            }
            else {
                if (execUpdate("delete from managers where id="+ id)){
                    System.out.println("Deleted successfully");
                }
                else {
                    System.out.println("failed to delete");
                }
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    private int searchManagerId(int idNo) throws SQLException {
        Utilities utilities = new Utilities();
        ArrayList<Integer> bankersId = new ArrayList<>();
        ResultSet resultSet = execQuery("select id from bankers");
        while (resultSet.next()){
            bankersId.add(resultSet.getInt(1));
        }
        int[] a = new int[bankersId.size()];
        for (int i = 0; i < bankersId.size(); i++) {
            a[i] = bankersId.get(i);
        }
        return utilities.search(a,idNo);
    }

    @Override
    public List<Manager> displayAll() {
        Manager manager = new Manager();
        List<Manager> managers = new ArrayList<>();
        ResultSet resultSet = execQuery("select * from managers");
        try {
            while (resultSet.next()){
                manager.setIdNo(resultSet.getInt(1));
                manager.setName(resultSet.getString(2));
                manager.setGender(Gender.valueOf(resultSet.getString(3)));
                manager.setEmail(resultSet.getString(4));
                manager.setPhone(resultSet.getString(5));
                manager.setBranch(resultSet.getString(6));
                managers.add(manager);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return managers;
    }
}
