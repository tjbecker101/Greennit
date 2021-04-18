package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.managers.SubgreennitManager;
import com.greennit.CS3141.managers.ThreadManager;
import com.greennit.CS3141.entities.Thread;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/subgreennit")
public class SubgreennitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SubgreennitServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SubgreennitManager subgreennitManager = new SubgreennitManager();
        ThreadManager threadManager = new ThreadManager();
        Subgreennit subgreennit;
        String destPage ;
        int id = -1;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (Exception ignored) {
        }

        if (id == -1) {
            destPage = "index.jsp";
        } else {
            subgreennit = subgreennitManager.getSubgreennit(id);
            List<Thread> threadsInSubgreennit = threadManager.getThreadsBySubgreennit(subgreennit.getId());
            Collections.reverse(threadsInSubgreennit);
            HttpSession session = request.getSession();
            session.setAttribute("currentSubgreennit", subgreennit);
            session.setAttribute("threadsInSubgreennit", threadsInSubgreennit);
            destPage = "subgreennit.jsp?id=" + subgreennit.getId();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        subgreennitManager.exit();
    }
}
