<%@ page import="com.greennit.CS3141.entities.Post" %>
<%@ page import="java.util.List" %>
<%@ page import="com.greennit.CS3141.entities.Thread"%>
<%@ page import="com.greennit.CS3141.managers.ThreadManager" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Greennit</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/blog-home.css" rel="stylesheet">

</head>

    <body>

        <!-- Navigation -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
            <div class="container">
                <a class="navbar-brand" href="index.jsp">GREENNIT</a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                        aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="form-row">
                    <form action="search" method="get" class="">
                        <input class="form-control" name="search" type="search" placeholder="Search for..."/>
                    </form>
                </div>

                <div class="collapse navbar-collapse" id="navbarResponsive" style="padding: 0 0 0 5pt">
                    <ul class="navbar-nav ml-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp">Home
                            </a>
                        </li>
                        <c:if test="${empty user}">
                            <li class="nav-item">
                                <a class="nav-link" href="login.jsp">Login</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="signup.jsp">Register</a>
                            </li>
                        </c:if>
                        <c:if test="${not empty user}">
                            <li class="nav-item">
                                <a class="nav-link" href="create_thread.jsp">Create Thread</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="create_subgreennit.jsp">Create Subgreennit</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="account_details.jsp">${user.username}</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="logout">Logout</a>
                            </li>
                        </c:if>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container" style="padding-top:15pt">
            <div class="row">
                <div class="col">
                    <h3 class="my-auto">Viewing profile of: ${pageContext.request.getParameter("u")}</h3>
                    <br>
                    <div class="card">
                        <c:if test="${empty failedUserMessage}">
                            <div class="card-header">
                                <h6 class="my-auto">${viewedUser.username}
                                    <c:if test="${user.permission > 2 and viewedUser.permission == 1}">
                                        <a href="mod_user?u=${viewedUser.username}" class="float-right card-link">Mod User</a>
                                    </c:if>
                                </h6>
                            </div>
                            <div class="card-body">
                                <p class="my-auto">Karma: ${viewedUser.karma}</p>
                                <p class="my-auto">Role:
                                    <c:if test="${viewedUser.permission == 1}">
                                        User
                                    </c:if>
                                    <c:if test="${viewedUser.permission == 2}">
                                        Moderator
                                    </c:if>
                                    <c:if test="${viewedUser.permission == 3}">
                                        Owner
                                    </c:if></p>
                            </div>
                        </c:if>
                        <c:if test="${not empty failedUserMessage}">
                            <div class="card-body">
                                <h6 class="my-auto text-warning">${failedUserMessage}</h6>
                            </div>
                        </c:if>
                    </div>

                    <c:if test="${!empty userThreads}">
                        <br>
                        <h6 class="my-auto">Threads: </h6>
                        <br>
                    </c:if>
                    <c:forEach items="${userThreads}" var="thread">
                        <div class="card">
                            <div class="card-header">
                                <a href="thread?id=${thread.id}" class="card-link">${thread.title}</a>
                            </div>
                            <div class="card-body">
                                <p class="my-auto text-wrap">${thread.content}</p>
                            </div>
                            <div class="card-footer">
                                <p class="my-auto">Posted ${thread.timeAgo}</p>
                                <p class="my-auto">Likes: ${thread.likes}</p>
                            </div>
                        </div>
                        <br>
                    </c:forEach>

                    <c:if test="${!empty userPosts}">
                        <br>
                        <h6 class="my-auto">Comments: </h6>
                        <br>
                    </c:if>
                    <%
                        List<Post> userPosts = (List<Post>) request.getAttribute("userPosts");
                        ThreadManager threadManager = new ThreadManager();
                        Thread thread;
                        for (Post post : userPosts) {
                            thread = threadManager.getThread(post.getHost_thread());
                            out.print(" <div class=\"card\">\n" +
                                    "       <div class=\"card-header\">\n" +
                                    "           <a href=\"thread?id="+ post.getHost_thread() +"\" class=\"card-link\">" + thread.getTitle() +"</a>\n" +
                                    "       </div>\n" +
                                    "       <div class=\"card-body\">\n" +
                                    "           <h6 class=\"my-auto tex-wrap\">Comment:</h6>" +
                                    "           <p class=\"my-auto text-wrap\">" + post.getContent() + "</p>\n" +
                                    "       </div>\n" +
                                    "       <div class=\"card-footer\">\n" +
                                    "           <p class=\"my-auto\">Posted " + post.getTimeAgo() + "</p>\n" +
                                    "           <p class=\"my-auto\">Likes: " + post.getLikes() + "</p>\n" +
                                    "        </div>\n" +
                                    "   </div>\n" +
                                    "   <br>");
                        }
                    %>
                </div>

            </div>

        </div>

    </body>
</html>