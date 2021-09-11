package com.hanson.app.controller.users;

import com.hanson.app.enums.Gender;
import com.hanson.app.interfaces.UsersControllerI;
import com.hanson.app.model.users.Employee;
import com.hanson.app.utils.DatabaseUtilities;
import com.hanson.app.utils.Utilities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeController extends DatabaseUtilities implements UsersControllerI<Employee> {
    @Override
    public String create(Employee employee) {
        if ((searchEmployeeId(employee.getIdNo()))==-1){
            System.out.println(execUpdate((employee.createInsertSql()))?"inserted successfully":"Failed to insert");
        }
        else {
            System.out.println("Employee already exists");
        }
        return null;
    }

    private int searchEmployeeId(int idNo) {
        Utilities utilities = new Utilities();
        ArrayList<Integer> employeesId = new ArrayList<>();
        int[] a = new int[0];
        try {
            ResultSet resultSet = execQuery("select idNo from employees");
            while (resultSet.next()) {
                employeesId.add(resultSet.getInt(1));
            }
            a = new int[employeesId.size()];
            for (int i = 0; i < employeesId.size(); i++) {
                a[i] = employeesId.get(i);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return utilities.search(a, idNo);

    }

    @Override
    public void edit(Employee employee) {
            if (searchEmployeeId(employee.getIdNo()) == -1){
                System.out.println("customer not found please enter the correct customer Id");
                return;
            }
            else {
                System.out.println((execUpdate(employee.createUpdateSql()))?"Employee details updated successfully":"Employee details failed to update");
            }
    }

    @Override
    public void delete(int id) {
            if (searchEmployeeId(id)==-1){
                System.out.println("Customer doesn't exist please enter correct customer id");
                return;
            }
            else {
                if (execUpdate("delete from employees where idNo="+ id)){
                    System.out.println("Deleted successfully");
                }
                else {
                    System.out.println("failed to delete");
                }
            }
    }

    @Override
    public List<Employee> displayAll() {
        Employee employee = new Employee();
        List<Employee> employees = new ArrayList<>();
        ResultSet resultSet = execQuery("select * from employees");
        try {
            while (resultSet.next()){
                employee.setIdNo(resultSet.getInt(1));
                employee.setName(resultSet.getString(2));
                employee.setGender(Gender.valueOf(resultSet.getString(3)));
                employee.setEmail(resultSet.getString(4));
                employee.setPhone(resultSet.getString(5));
                employee.setEmployeeId(resultSet.getString(6));
                employee.setBranch(resultSet.getString(7));
                employees.add(employee);
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return employees;
    }
}
