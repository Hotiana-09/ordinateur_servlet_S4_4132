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

    <table>
        <thead>
            <tr>
                <th>Fonctionnel</th>
                <th>En Panne</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td id = "count_fonct" ></td>
                <td id = "count_panne" ></td>
            </tr>
        </tbody>
    </table>

    <h2>Liste Nombre d'Ordinateur par Type de Panne</h2>
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
                <td id =" count_alim" ></td>
                <td id = "count_disque"></td>
                <td id = "count_carte"></td>
            </tr>

        </tbody>
    </table>
    </main>

    <script>
    document.getElementById('date').valueAsDate = new Date();

    const urlServlet = 'http://localhost:8080/gestionParc_1/info.php';
    fetch(urlServlet)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erreur de récupération des données");
            }
            return response.json();
        })
        .then(count_fonct => {
            const selectcount_fonct = document.getElementById('count_fonct');
                selectcount_fonct.textContent = count_fonctionnel;
            });

            const count_panne = document.getElementById('count_panne');
                count_panne.textContent = count_en_panne;
            });

            const count_alim = document.getElementById('count_alim');
                count_alim.textContent = count_alimentation;
            });

            const count_disque = document.getElementById('count_disque');
                count_disque.textContent = count_disque;
            });

            const count_carte = document.getElementById('count_carte');
                count_carte.textContent = count_carteM;
            });
        
        .catch(error => {
            console.error('Erreur:', error);
            const selectOrdi = document.getElementById('id_ordinateur');
            selectOrdi.innerHTML = '<option value="">Erreur de chargement des ordinateurs</option>';
        });
</script>

</body>
</html>