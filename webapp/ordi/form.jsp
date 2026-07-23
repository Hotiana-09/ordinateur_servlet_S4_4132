<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<%@ page import="model.*" %>

<%
    Ordinateur ordi = (Ordinateur) request.getAttribute("ordinateur");
    boolean isEdit = (ordi != null);
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
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="ordi">Liste des ordinateurs</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="form_ordi">Ajouter un ordinateur</a>
            </li>
        </ul>
    </nav>

    <main class="container">
        <h1><%= isEdit ? "Modifier l'ordinateur" : "Ajouter un ordinateur" %></h1>
    
        <form action="ordi" method="post">   
            <% if(isEdit) { %>
                <input type="hidden" name="id" value="<%= ordi.getId() %>">
            <% } %>

            <label for="idModele">Modèle d'ordinateur :</label>
            <select id="idModele" name="idModele" required>
                <option value="">-- Choisir un modèle --</option>
                <%
                    List<Modele> modeles = (List<Modele>) request.getAttribute("listeModeles");                    
                    if (modeles != null) {
                        for (Modele m : modeles) {
                            String selected = (isEdit && ordi.getIdModele() == m.getId()) ? "selected" : "";
                %>
                            <option value="<%= m.getId() %>" <%= selected %>>
                                <%= m.getLibelle() %> (<%= m.getReference() %>)
                            </option>
                <%
                        }
                    }
                %>
            </select>
            
            <label for="ram">RAM (Go) :</label>
            <input type="number" id="ram" name="ram" 
                   value="<%= isEdit ? ordi.getRAM() : "" %>" required>
            
            <label for="processeur">Processeur :</label>
            <input type="text" id="processeur" name="processeur" 
                   value="<%= isEdit ? ordi.getProcesseur() : "" %>" required>
            
            <label for="disqueDur">Disque Dur (Go) :</label>
            <input type="number" id="disqueDur" name="disqueDur" 
                   value="<%= isEdit ? ordi.getDisqueDur() : "" %>" required>
            
            <input type="submit" value="<%= isEdit ? "Enregistrer les modifications" : "Ajouter" %>">
        </form>
    </main>

</body>
</html>