package com.greennit.CS3141.webpage;

import com.greennit.CS3141.managers.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/account_details")
public class AccountDetailsServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public AccountDetailsServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String type = request.getParameter("type");


        UserManager userManager = new UserManager();
        String destPage = "account_details.jsp";
        if(type.equals("email")) {
            String message = "";
            String username = request.getParameter("username");
            String newEmail1 = request.getParameter("new_email_1");
            String newEmail2 = request.getParameter("new_email_2");
            if(username.equals("")){
                message = "Please Sign In";
            }else if(!newEmail1.equals(newEmail2)){
                message = "Emails do not Match";
            } else{
                userManager.updateEmail(username,newEmail1);
                message = "Email Change Successful";
            }
            request.setAttribute("message", message);
        }
        else if(type.equals("username")){
            String message = "";
            request.setAttribute("message", message);
        }
        else if(type.equals("password")){
            String message = "";
            request.setAttribute("message", message);
        }else{
            String message = "Invalid Change";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        userManager.exit();
    }


}
