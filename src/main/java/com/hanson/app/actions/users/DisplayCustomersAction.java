package com.hanson.app.actions.users;

import com.hanson.app.controller.users.CustomerController;
import com.hanson.app.model.users.Customer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class DisplayCustomersAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CustomerController customerController = new CustomerController();
        List<Customer> customers = customerController.displayAll();
        req.setAttribute("customers",customers);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        List<Customer> customers = (List<Customer>) req.getAttribute("customers");
        PrintWriter display = res.getWriter();
        display.print("<html>");
        display.print("<head>"
                + "</head>");
        display.print("<body>");

        display.print("Application Version" + req.getServletContext().getInitParameter("Application Version") + "<br>");
        display.print("Application Country" + req.getServletContext().getInitParameter("Built Country") + "<br>");
        display.print("Application Country " + getServletConfig().getInitParameter("Built Continent") + "<br>");

        display.print("Customer Id<br/>");
        display.print("<table>");
        display.print("<th>Customer Id</th>");
        display.print("<th>Name</th>");
        display.print("<th>Gender</th>");
        display.print("<th>email</th>");
        display.print("<th>phone</th>");
        display.print("<th>KRA pin</th>");

        for (Customer customer  : customers){
            display.print("<tr>");
            display.print("<td>" + customer.getIdNo() + "</td>");
            display.print("<td>" + customer.getName() + "</td>");
            display.print("<td>" + customer.getGender() + "</td>");
            display.print("<td>" + customer.getEmail() + "</td>");
            display.print("<td>" + customer.getPhone() + "</td>");
            display.print("<td>" + customer.getKraPin() + "</td>");
            display.print("</tr>");
        }

        display.print("</table>");
        display.print("</body>");
        display.print("</html>");
    }
}
