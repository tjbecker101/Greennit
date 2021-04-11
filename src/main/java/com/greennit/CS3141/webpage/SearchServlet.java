package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.entities.Thread;

import com.greennit.CS3141.managers.SubgreennitManager;
import com.greennit.CS3141.managers.ThreadManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public SearchServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String search = request.getParameter("search");
        request.setAttribute("user", request.getAttribute("user"));
        request.setAttribute("search", search);
        SubgreennitManager subgreennitManager = new SubgreennitManager();
        ThreadManager threadManager = new ThreadManager();
        String destPage = "search.jsp";


        if (search != null && search.length() > 2) {
            List<Thread> threads = threadManager.getThreadsByTitle(search);
            List<Subgreennit> subgreennits = subgreennitManager.getSubgreennits(search);
            HttpSession session = request.getSession();
            session.setAttribute("threads", threads);
            session.setAttribute("subgreennits", subgreennits);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);

        subgreennitManager.exit();
        threadManager.exit();
    }
}
