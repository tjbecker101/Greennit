<%@ page import="com.greennit.CS3141.entities.User" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
        <meta charset="utf-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>Greennit - Sign up</title>

        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/blog-home.css" rel="stylesheet">
        <script
                src="https://code.jquery.com/jquery-3.4.1.min.js"
                integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
                crossorigin="anonymous"></script>
        <script type="text/javascript"
                src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>
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
                <input class="form-control" name="search" type="search"  placeholder="Search for..."/>
            </form>
        </div>

        <div class="collapse navbar-collapse" id="navbarResponsive" style="padding: 0 0 0 5pt">
            <ul class="navbar-nav ml-auto">
                <c:if test="${!empty user}">
                    <li class="nav-item">
                        <a class="nav-link" href="#">${user.username}</a>
                    </li>
                </c:if>
                <li class="nav-item active">
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
                        <a class="nav-link" href="logout">Logout</a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="container" style="padding-top:15pt">
    <form action="signup" method="post">
        <div class="row" style="alignment: center">
            <div class="col">
                <label for="username">Username:</label>
                <input class="form-control" name="username" size="30" placeholder="Enter username"/>
                <p class="text-warning">${messageUsername}</p>
            </div>
            <div class="col">
                <label for="email">Email:</label>
                <input class="form-control" name="email" type="email" size="30" placeholder="Enter email"/>
                <p class="text-warning">${messageEmail}</p>
            </div>
        </div>
        <br>
        <div class="row" style="alignment: center">
            <div class="col">
                <label for="password1">Password:</label>
                <input class="form-control" name="password1" size="30" type="password" placeholder="Enter password"/>
                <p class="text-warning">${messageBadPassword}</p>
                <p>Passwords must have the following:</p>
                <ul>
                    <li>At least 8 characters in length</li>
                    <li>1 uppercase character</li>
                    <li>1 lowercase character</li>
                    <li>1 number</li>
                </ul>
            </div>
            <div class="col">
                <label for="password2">Re-type Password:</label>
                <input class="form-control" name="password2" size="30" type="password" placeholder="Re-type Password"/>
                <p class="text-warning">${messagePasswordMismatch}</p>
            </div>
        </div>
        <button type="submit" class="btn btn-dark">Sign up</button>
    </form>
</div>

<!-- Footer -->
<footer class="py-5 bg-dark" >
    <div class="container">
        <p class="m-0 text-center text-white">Copyright &copy; GREENNIT 2020</p>
    </div>
    <!-- /.container -->
</footer>

<!-- Bootstrap core JavaScript -->
<script src="vendor/jquery/jquery.min.js"></script>
<script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

</body>
</html>
