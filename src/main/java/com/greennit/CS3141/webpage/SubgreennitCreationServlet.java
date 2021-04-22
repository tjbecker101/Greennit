package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.SubgreennitManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create_subgreennit")
public class SubgreennitCreationServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SubgreennitCreationServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SubgreennitManager subgreennitManager = new SubgreennitManager();

        String name = request.getParameter("name");
        String description = request.getParameter("description");
        User user = (User) request.getSession().getAttribute("user");


        String destPage = "create_subgreennit.jsp";
        if(name.equals("")) {
            String message = "Please include a name.";
            request.setAttribute("message", message);
        }
        else if (name.contains(" ")) {
            String message = "Subgreennit names cannot contain spaces.";
            request.setAttribute("message", message);
        }
        else if(description.equals("")){
            String message = "Please include the description of the subgreennit.";
            request.setAttribute("message", message);
        }
        else if(user == null) {
            String message = "Please Sign in";
            request.setAttribute("message", message);
        }else{
            Subgreennit subgreennit = subgreennitManager.createSubgreennit(name, description);
            destPage = "subgreennit?id=" + subgreennit.getId();
            response.sendRedirect(destPage);
            subgreennitManager.exit();
            return;
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        subgreennitManager.exit();
    }
}
