package com.hanson.app.actions.users;

import com.hanson.app.controller.users.CustomerController;
import com.hanson.app.enums.Gender;
import com.hanson.app.model.users.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RegisterCustomerAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>" +
                "<body>" +
                "<form action=\"./customer-register\" method=\"post\">\n" +
                "    <p>Enter ID/Passport number <input type=\"number\" name=\"id\" required></p>\n" +
                "    <p>Enter your name <input type=\"text\" name=\"name\" required></p>\n" +
                "    <p>gender (MALE/FEMALE) <input type=\"text\" name=\"gender\" required></p>\n" +
                "    <p>Enter email <input type=\"text\" name=\"email\" required></p>\n" +
                "    <p>Enter phone <input type=\"text\" name=\"phone\" required></p>\n" +
                "    <p>Enter kra pin <input type=\"text\" name=\"kra\" required></p>\n" +
                "    <p><input type=\"submit\" value=\"register\"></p>\n" +
                "</form>" +
                "</body>" +
                "</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = new Customer();
        customer.setIdNo(Integer.parseInt(req.getParameter("id")));
        customer.setName(req.getParameter("name"));
        customer.setGender(Gender.valueOf(req.getParameter("gender").toUpperCase()));
        customer.setEmail(req.getParameter("phone"));
        customer.setKraPin(req.getParameter("kra"));
        CustomerController customerController = new CustomerController();
        PrintWriter printWriter = resp.getWriter();
        printWriter.print("<html>" +
                "<body>" +
                "<p>"+customerController.create(customer)+"</p>" +
                "</body>" +
                "</html>");
    }
}
