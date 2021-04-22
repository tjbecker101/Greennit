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

<div class="container" style="padding-top:15pt">
    <div class="row">
        <div class="col">
            <h3 class="my-auto">Search results for: <em>${search}</em></h3>
            <c:if test="${!empty subgreennits}">
                <br>
                <h6 class="my-auto">Subgreenits: </h6>
                <br>
            </c:if>
            <c:forEach items="${subgreennits}" var="subgreennit">
                <div class="card">
                    <div class="card-header">
                        <a href="subgreennit?id=${subgreennit.id}" class="card-link">${subgreennit.name}</a>
                    </div>
                    <div class="card-body">
                        <p class="my-auto">${subgreennit.description}</p>
                    </div>
                </div>
                <br>
            </c:forEach>
            <c:if test="${!empty threads}">
                <br>
                <h6 class="my-auto">Threads: </h6>
                <br>
            </c:if>
            <c:forEach items="${threads}" var="thread">
                <div class="card">
                    <div class="card-header">
                        <a href="thread?id=${thread.id}" class="card-link">${thread.title}</a>
                    </div>
                    <div class="card-body">
                        <p class="my-auto text-wrap">${thread.content}</p>
                    </div>
                    <div class="card-footer">
                        Author: <a href="view_profile?u=${thread.author}" class="card-link">${thread.author} </a>
                        <p class="my-auto">Posted ${thread.timeAgo}</p>
                    </div>
                </div>
                <br>
            </c:forEach>
            <c:if test="${empty threads and empty subgreennits}">
                <br>
                <p class="my-auto">No results.</p>
            </c:if>
        </div>
    </div>
</div>


<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
