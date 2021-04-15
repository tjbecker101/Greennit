package com.greennit.CS3141.webpage;

import com.greennit.CS3141.managers.UserManager;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Pattern;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/signup")
public class UserSignupServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserSignupServlet() {
        super();
    }

    private static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    private static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";

        Pattern pat = Pattern.compile(regex);
        if (password == null || password.length() < 8) {
            return false;
        }

        return pat.matcher(password).matches();
    }

    private static boolean passwordsMatch(String password1, String password2) {
        if (password1 == null || password2 == null) {
            return false;
        }
        return password1.equals(password2);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        UserDAO userDAO = new UserDAO();

        String destPage = "signup.jsp";
        boolean isValid = true;

        UserManager manager = new UserManager();

        if (username.contains(" ")) {
            String messageUsername = "Username cannot contain spaces";
            request.setAttribute("messageUsername", messageUsername);
            isValid = false;
        }
        try{
            manager.getUser(username);
            String messageUsername = "User already exists";
            request.setAttribute("messageUsername", messageUsername);
            isValid = false;
        } catch (IllegalArgumentException ignored) {
        }
        if (!isValidEmail(email) && isValid) {
            String messageEmail = "Invalid email address";
            request.setAttribute("messageEmail", messageEmail);
            isValid = false;
        }
        if (!isValidPassword(password1) && isValid) {
            String messageBadPassword = "Invalid password parameters";
            request.setAttribute("messageBadPassword", messageBadPassword);
            isValid = false;
        }
        if (!passwordsMatch(password1, password2) && isValid) {
            String messagePasswordMismatch = "Passwords do not match";
            request.setAttribute("messagePasswordMismatch", messagePasswordMismatch);
            isValid = false;
        }

        if(isValid) {
            destPage = "index.jsp";
            HttpSession session = request.getSession();
            try {
                session.setAttribute("user", manager.createUser(username, userDAO.SHA3_256(password1), email));
            } catch (NoSuchAlgorithmException e) {
                String messageFail = "Account creation failed. Try again";
                request.setAttribute("messageFail", messageFail);
                destPage = "signup.jsp";
            }
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        manager.exit();
    }
}
