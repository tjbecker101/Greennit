package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.SubgreennitManager;
import com.greennit.CS3141.managers.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete_subgreennit")
public class SubgreennitDeletionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SubgreennitDeletionServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("g"));
        User user = (User) request.getSession().getAttribute("user");
        SubgreennitManager subgreennitManager = new SubgreennitManager();
        String destPage = "index.jsp";

        try {
            Subgreennit subgreennit = subgreennitManager.getSubgreennit(id);
            if (user == null) {
                throw new Exception();
            }
            if (user.getPermission() == 3) {
                subgreennitManager.deleteSubgreennit(id);
            }
        } catch (Exception e) {
            String failedMessage = "Deletion unsuccessful.";
            request.setAttribute("failedMessage", failedMessage);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        subgreennitManager.exit();
    }
}
