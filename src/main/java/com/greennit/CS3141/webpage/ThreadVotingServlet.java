package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Subgreennit;
import com.greennit.CS3141.entities.Thread;
import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.SubgreennitManager;
import com.greennit.CS3141.managers.ThreadManager;
import com.greennit.CS3141.managers.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

@WebServlet("/thread_voting")
public class ThreadVotingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public ThreadVotingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ThreadManager threadManager = new ThreadManager();
        UserManager userManager = new UserManager();

        String id = request.getParameter("id"); //Gets the thread id for redirection
        String message; //Prints a message back to the user
        String destPage = "index.jsp"; //Used to redirect the user
        String strUser = request.getParameter("username");
        int amount = Integer.parseInt(request.getParameter("amount"));

        if(id.equals("")){ //Checks if the thread has an id
            message = "Invalid Thread ID";
        }else{
            try{
                Thread thread = threadManager.getThread(Integer.parseInt(id)); //Gets the thread the post is in
                destPage = "thread?id=" + thread.getId(); //Assigns thread to the destPage

                if (strUser.equals("")) { //check if signed in
                    message = "Not Signed In!";
                } else if (1 == 2) {// change to check if user hasnt already liked
                    message = "Already Voted!";
                } else {
                    message = "";
                    User author = userManager.getUser(thread.getAuthor());
                    threadManager.updateThreadLikes(thread.getId(), thread.getLikes() + amount);
                    userManager.updateKarma(author.getUsername(), author.getKarma() + amount);
                }

            }catch (IllegalArgumentException ex){
                message = "Vote Failed";
            }
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);
        threadManager.exit();
    }
}

