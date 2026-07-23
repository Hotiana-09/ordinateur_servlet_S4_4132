<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<html>
<head>
    <title>4132</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>

    <main>
    <h1>4132</h1>
    <h2>Nombre d'Ordinateur par Etat</h2>
    
    <%
        int count_fonctionel = (int) request.getAttribute("count_fonctionnel");
        int count_en_panne = (int) request.getAttribute("count_en_panne");
    %>
    <table>
        <thead>
            <tr>
                <th>Fonctionnel</th>
                <th>En Panne</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><%= count_fonctionel %></td>
                <td><%= count_en_panne %></td>
            </tr>
        </tbody>
    </table>

    <h2>Liste Nombre d'Ordinateur par Type de Panne</h2>
    <%
        int count_alimentation = (int) request.getAttribute("count_alimentation");
        int count_disque = (int) request.getAttribute("count_disque");
        int count_carteM = (int) request.getAttribute("count_carteM");
    %>
    <table>
        <thead>
            <tr>
                <th>Alimentation</th>
                <th>Disque Dur</th>
                <th>Carte Mere</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><%= count_alimentation %></td>
                <td><%= count_disque %></td>
                <td><%= count_carteM %></td>
            </tr>

        </tbody>
    </table>
    </main>
</body>
</html>