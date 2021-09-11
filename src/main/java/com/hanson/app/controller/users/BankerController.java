package com.hanson.app.controller.users;

import com.hanson.app.enums.Gender;
import com.hanson.app.interfaces.UsersControllerI;
import com.hanson.app.model.users.Banker;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BankerController extends DatabaseUtilities implements UsersControllerI<Banker> {
    @Override
    public String create(Banker banker) {
        try {
            if ((searchbankerId(banker.getIdNo()))==-1){
                System.out.println(execUpdate((banker.createInsertSql()))?"inserted successfully":"Failed to insert");
            }
            else {
                System.out.println("banker already exists");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    private int searchbankerId(int idNo) throws SQLException {
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
    public void edit(Banker banker) {
        try {
            if (searchbankerId(banker.getIdNo()) == -1){
                System.out.println("customer not found please enter the correct banker Id");
                return;
            }
            else {
                System.out.println((execUpdate(banker.createUpdateSql()))?"banker updated successfully":"banker details failed to update");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    @Override
    public void delete(int id) {
        try {
            if (searchbankerId(id)==-1){
                System.out.println("Banker doesn't exist please enter correct banker id");
                return;
            }
            else {
                if (execUpdate("delete from bankers where id="+ id)){
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

    @Override
    public List<Banker> displayAll() {
        Banker banker = new Banker();
        List<Banker> bankers = new ArrayList<>();
        ResultSet resultSet = execQuery("select * from bankers");
        try {
            while (resultSet.next()){
                banker.setIdNo(resultSet.getInt(1));
                banker.setName(resultSet.getString(2));
                banker.setGender(Gender.valueOf(resultSet.getString(3)));
                banker.setEmail(resultSet.getString(4));
                banker.setPhone(resultSet.getString(5));
                banker.setBankerId(resultSet.getString(6));
                banker.setBranch(resultSet.getString(7));
                bankers.add(banker);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return bankers;
    }
}
