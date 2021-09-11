package com.hanson.app.actions.accounts;

import com.hanson.app.controller.accounts.CurrentAccountController;
import com.hanson.app.model.accounts.CurrentAccount;
import com.hanson.app.model.items.Item;
import com.hanson.app.utils.Utilities;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class CreateAccountAction extends HttpServlet {

    /**
     * called on server/web container start up or on the first time a Servlet is created
     */
    public void init(){
        System.out.println("Item Action Servlet is Loaded & Instatiated");
    }

    /**
     * Handles GET request, called when the page is loaded first, because the loading a page is a get request on http
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintWriter display = res.getWriter();
        display.print("<html>");
        display.print("<head>"
                + "<style>"
                + "table, th, td {"
                + "  border: 1px solid black;"
                + "  width: 100%;"
                + "  border-collapse: collapse;"
                + "  background-color: #96D4D4;"
                + "}"
                + "</style>"
                + "</head>");
        display.print("<body>");
        display.print("<h1>" + req.getServletContext().getInitParameter("Application Name") + "</h1></br>");
        display.print("Version " + req.getServletContext().getInitParameter("Application Version") + "</br>");
        display.print("&nbsp");
        display.print("</br>");
        display.print("</br>");
        display.print(getServletConfig().getInitParameter("Page Name") + "</br>");

        display.print("<h3>Open new Account</h3>\n"
                + "\n"
                + "<form action=\"./open-account\" method=\"POST\">\n");

            display.print("<hr/>");
            display.print("  <label for=\"customerId\">Cusomer ID/Passport"  + ":</label><br>\n"
                    + "  <input type=\"number\" id=\"customerId" +  "\" name=\"customerId" + "\"><br>\n" +
                    "\n");


        display.print("<input type=\"submit\" value=\"Submit\">\n"
                + "</form> ");

    }

    /**
     * Handles POST request, called/executed when submitting a form through post method
     * @param req
     * @param res
     * @throws ServletException
     * @throws IOException
     */
    @SuppressWarnings("rawtypes")
    public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        CurrentAccount currentAccount = new CurrentAccount(Integer.parseInt(req.getParameter("customerId")), new Utilities().generateAccountNumber());
        CurrentAccountController currentAccountController = new CurrentAccountController();


        PrintWriter display = res.getWriter();
        display.print("<html>");
        display.print("<head></head>");
        display.print("<body>");
        display.print("<h1>" + req.getServletContext().getInitParameter("Application Name") + "</h1></br>");
        display.print("Version " + req.getServletContext().getInitParameter("Application Version") + "</br>");
        display.print("&nbsp");
        display.print("</br>");
        display.print("</br>");
        display.print(getServletConfig().getInitParameter("Page Name") + "</br>");
        display.print("<p>"+currentAccountController.createAccount(currentAccount)+"</p>");
        display.print("</table>");
        display.print("</body>");
        display.print("</html>");

    }

    /**
     * called when the server/web container is shutdown or a application is undeployed from the server/web container
     */
    @Override
    public void destroy() {
        System.out.println("Killing servlet....");
    }
}
