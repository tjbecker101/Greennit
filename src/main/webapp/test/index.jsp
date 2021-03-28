<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<%@ page import = "com.greennit.CS3141.managers.*"%>
<%@ page import = "com.greennit.CS3141.entities.*"%>
<%
    UserManager userManager = new UserManager();
%>

<form>
    <label for="getName">Input name:</label><br>
    <input type="text" id="getName" name="name">
</form>

    <%
    String[] names = request.getParameterValues("name");
    if (names != null) {
    %>
    <%
        try {
            User user = userManager.getUser(names[0]);
            String username = user.getUsername();
            String password = user.getPass();
            String email = user.getEmail();
            int permission = user.getPermission();
            int karma = user.getKarma();
            out.println("<ul>");
            out.println("<li>Username:" + username + "</li>");
            out.println("<li>Password:" + password + "</li>");
            out.println("<li>Email:" + email + "</li>");
            out.println("<li>Permission:" + permission + "</li>");
            out.println("<li>Karma:" + karma + "</li>");
            out.println("</ul>");
        } catch (IllegalArgumentException e) {
            out.println(e.getMessage());
        }
    %>


<% userManager.exit(); }%>
</body>
</html>
