<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>4132</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>

    <nav class="navbar">
        <a class="navbar-brand" href="ordi">4132 - Gestion informatique</a>
    </nav>

    <main class="container">
        <h1>Login</h1>
    
        <% if (errorMessage != null) { %>
            <div class="error-message"><%= errorMessage %></div>
        <% } %>

        <form action="login" method="post">   
            <label for="login">Login :</label>
            <input type="text" id="login" name="login" required>

            <label for="mdp">Mot de passe :</label>
            <input type="password" id="mdp" name="mdp" required>
            
            <input type="submit" value="Se connecter">
        </form>
    </main>

</body>
</html>