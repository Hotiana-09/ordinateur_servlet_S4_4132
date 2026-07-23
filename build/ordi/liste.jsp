<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<html>
<head>
    <title>4132</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/style.css">
</head>
<body>

    <nav class="navbar">
        <a class="navbar-brand" href="ordi">4132 - Gestion informatique</a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link active" href="ordi">Liste des ordinateurs</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="form_ordi">Ajouter un ordinateur</a>
            </li>
        </ul>
    </nav>

    <main>
    <h2>Liste des Ordinateurs en Parc</h2>
    
    <%
        List<Ordinateur> liste = (List<Ordinateur>) request.getAttribute("listeOrdinateurs");
        if (liste != null && !liste.isEmpty()) {
            %>
    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>ID Modèle</th>
                <th>RAM (Go)</th>
                <th>Processeur</th>
                <th>Disque Dur (Go)</th>
                <th>Actions</th> 
            </tr>
        </thead>
        <tbody>
            <% 
                    for (Ordinateur o : liste) {
            %>
                        <tr>
                            <td><%= o.getId() %></td>
                            <td><%= o.getIdModele() %></td>
                            <td><%= o.getRAM() %></td>
                            <td><%= o.getProcesseur() %></td>
                            <td><%= o.getDisqueDur() %></td>
                            <td>
                                <a href="form_ordi?id=<%= o.getId() %>" class="btn-action btn-edit">Modifier</a>
                                <a href="ordi?id=<%= o.getId() %>" class="btn-action btn-delete"
                                class="btn-action btn-delete" 
                                   onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet ordinateur ?');">
                                Supprimer</a>
                            </td>
                        </tr>
            <% 
                    } 
                    
                    %>
                    </tbody>
                </table>

            <%
                } else {
            %>
                <h3 class="text-center text-muted">Aucun ordinateur trouvé</h3>
            <% 
                } 
            %>

    </main>
</body>
</html>