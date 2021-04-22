package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
        if (type.equals("email")) { //Checks if the type of change is email based
            String message = "";
            String username = request.getParameter("username"); //Gets the username of the user
            String newEmail1 = request.getParameter("new_email_1"); //Gets the new email
            String newEmail2 = request.getParameter("new_email_2"); //Used to check if the email entered is the same
            if (username.equals("")) { //Ensures there is a username
                message = "Please Sign In";
            } else if (newEmail1.equals("") || newEmail2.equals("")) { //Checks to make sure both fields were filled out
                message = "Missing Inputs";
            } else if (!newEmail1.equals(newEmail2)) { //Checks if the emails match
                message = "Emails do not Match";
            } else {
                try {
                    userManager.updateEmail(username, newEmail1); //Updates the email
                    User user = userManager.getUser(username); //Retrieves the user
                    request.setAttribute("user", user); //Updates the user as their email was changed
                    message = "Email Change Successful";
                } catch (IllegalArgumentException ex) { //Username wasn't found in the database
                    message = "Account does not Exist";
                }

            }
            request.setAttribute("message", message);
        } else if (type.equals("username")) {
            String message = "";
            String oldUsername = request.getParameter("old_username");
            String newUsername = request.getParameter("new_username");
            if (oldUsername.equals("")) {
                message = "Please Sign in";
            } else if (oldUsername.equals(newUsername)) { //Causes bug where the username on the site hasn't been updated to the new one yet
                message = "New Username is Same as Old Username";
            } else {
                try {
                    userManager.getUser(newUsername);
                    message = "Username already taken";
                } catch (IllegalArgumentException ex) {
                    userManager.updateUsername(oldUsername, newUsername);
                    User user = userManager.getUser(newUsername);
                    request.setAttribute("user", user);
                    message = "Username change Successful";
                }

            }
            request.setAttribute("message", message);
        } else if (type.equals("password")) {
            String message = "";
            String username = request.getParameter("username");
            String oldPassword = request.getParameter("old_pass");
            String newPassword1 = request.getParameter("new_pass_1");
            String newPassword2 = request.getParameter("new_pass_2");
            if (username.equals("")) {
                message = "Please Sign in";
            } else if (oldPassword.equals("") || newPassword1.equals("") || newPassword2.equals("")) {
                message = "Field left blank";
            } else if (!newPassword1.equals(newPassword2)) {
                message = "Passwords do not match";
            } else {
                try {
                    UserDAO userDAO = new UserDAO();
                    if (userDAO.checkLogin(username, userDAO.SHA_256(oldPassword)) != null) {
                        userManager.updatePassword(username, userDAO.SHA_256(newPassword1));
                        User user = userManager.getUser(username);
                        request.setAttribute("user", user);
                        message = "Password Change Successful";
                    }else{
                        message = "Incorrect Password Given";
                    }

                } catch (IllegalArgumentException | NoSuchAlgorithmException ex) {
                    message = "User does not exist";
                }
            }
            request.setAttribute("message", message);
        } else {
            String message = "Invalid Change";
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        userManager.exit();
    }


}
