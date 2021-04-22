package com.greennit.CS3141.webpage;

import com.greennit.CS3141.entities.Post;
import com.greennit.CS3141.entities.Thread;
import com.greennit.CS3141.entities.User;
import com.greennit.CS3141.managers.PostManager;
import com.greennit.CS3141.managers.ThreadManager;
import com.greennit.CS3141.managers.UserManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/post_voting")
public class PostVotingServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public PostVotingServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ThreadManager threadManager = new ThreadManager();
        PostManager postManager = new PostManager();
        UserManager userManager = new UserManager();

        String threadId = request.getParameter("threadId"); //Gets the thread id
        String postId = request.getParameter("postId"); //Gets the post id
        String message; //Prints a message back to the user
        String destPage = "index.jsp"; //Used to redirect the user
        String strUser = request.getParameter("username");
        int amount = Integer.parseInt(request.getParameter("postAmount"));

        if(threadId.equals("")) { //Checks if the thread has an id
            message = "Invalid Thread ID";
        }else if (postId.equals("")){
            message = "Invalid Post ID";
        }else{
            try{
                Thread thread = threadManager.getThread(Integer.parseInt(threadId)); //Gets the thread the post is in
                Post post = postManager.getPost(Integer.parseInt(postId));
                destPage = "thread?id=" + thread.getId(); //Assigns thread to the destPage

                if (strUser.equals("")) { //check if signed in
                    message = "Not Signed In!";
                } else if (1 == 2) {// change to check if user hasnt already liked
                    message = "Already Voted!";
                } else {
                    message = "";
                    User author = userManager.getUser(post.getAuthor());
                    postManager.updatePostLikes(post.getId(), post.getLikes() + amount);
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

