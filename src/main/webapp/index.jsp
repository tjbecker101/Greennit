<%@ page import="com.greennit.CS3141.entities.User" %>
<%@ page import="com.greennit.CS3141.managers.PostManager" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.greennit.CS3141.entities.Subgreennit" %>
<%@ page import="com.greennit.CS3141.managers.SubgreennitManager" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="com.greennit.CS3141.webpage.SubgreennitComparator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

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
                    <a class="nav-link active" href="index.jsp">Home
                        <span class="sr-only">(current)</span>
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

<!-- Page Content -->
<div class="container">

    <div class="row">

        <!-- Blog Entries Column -->
        <div class="col-md-8">

            <h1 class="my-4">GREENNIT
                <small><br>Your HUB for forums that were meant to be green, but aren't.</small>
            </h1>


            <!-- Blog Post -->
            <%
                SubgreennitManager subreennitManager = new SubgreennitManager();
                List<Subgreennit> subgreennitList = subreennitManager.getSubgreennits("");
                Collections.sort(subgreennitList, new SubgreennitComparator());
                for (Subgreennit subgreennit : subgreennitList) {
                    out.print(
                            "<div class=\"card mb-4\">\n" +
                                    "    <div class=\"card-body\">\n" +
                                    "        <h2 class=\"card-title\">" + subgreennit.getName() + "</h2>\n" +
                                    "        <p class=\"card-text\">" + subgreennit.getDescription() + "</p>\n" +
                                    "        <a href=\"subgreennit?id=" + subgreennit.getId() + "\" class=\"btn btn-primary\">Go To &rarr;</a>\n" +
                                    "    </div>\n" +
                                    "</div>");
                }
            %>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->
</div>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
        integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
</body>

</html>
