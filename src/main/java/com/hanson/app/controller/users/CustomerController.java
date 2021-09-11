package com.hanson.app.controller.users;

import com.hanson.app.interfaces.UsersControllerI;
import com.hanson.app.model.users.Customer;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author hanson kibet
 */
public class CustomerController extends DatabaseUtilities implements UsersControllerI<Customer> {
    @Override
    public String create(Customer customer) {//adding new customer to the database
        String message="";
        try {
            if ((searchCustomerId(customer.getIdNo()))==-1){
               message= (execUpdate((customer.createInsertSql()))?"inserted successfully":"Failed to insert");
            }
            else {
                message = "Customer already exists";
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
            message = e.getMessage();
        }
        return message;
    }

    private int searchCustomerId(int idNo) throws SQLException {
        Utilities utilities = new Utilities();
        ArrayList<Integer> customersId = new ArrayList<>();
        ResultSet resultSet = execQuery("select id from customers");
        while (resultSet.next()){
            customersId.add(resultSet.getInt(1));
        }
        int[] a = new int[customersId.size()];
        for (int i = 0; i < customersId.size(); i++) {
            a[i] = customersId.get(i);
        }
         return utilities.search(a,idNo);
    }

    @Override
    public void edit(Customer customer) {
        try {
            if (searchCustomerId(customer.getIdNo()) == -1){
                System.out.println("customer not found please enter the correct customer Id");
                return;
            }
            else {
                System.out.println((execUpdate(customer.createUpdateSql()))?"Customer updated successfully":"Customer details failed to update");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        try {
            if (searchCustomerId(id)==-1){
                System.out.println("Customer doesn't exist please enter correct customer id");
                return;
            }
            else {
                if (execUpdate("delete from customers where id="+ id)){
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
    public List<Customer> displayAll() {
        Utilities utilities = new Utilities();
        List<Customer> customers = new ArrayList<>();
        try {
            ResultSet resultSet = execQuery("select * from customers");
            customers = utilities.resultsetToCustomerObject(resultSet);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return customers;
    }
}
