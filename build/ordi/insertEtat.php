<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Enregistrer l'état d'un ordinateur</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f6;
            padding: 40px;
            display: flex;
            justify-content: center;
        }
        .form-container {
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.1);
            width: 100%;
            max-width: 500px;
        }
        h2 {
            margin-top: 0;
            color: #333;
            border-bottom: 2px solid #3498db;
            padding-bottom: 10px;
        }
        .form-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 8px;
            font-weight: bold;
            color: #555;
        }
        select, input[type="date"], textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            font-size: 14px;
        }
        textarea {
            resize: vertical;
            height: 100px;
        }
        button {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 12px 20px;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            width: 100%;
            transition: background-color 0.2s;
        }
        button:hover {
            background-color: #2980b9;
        }
        .loading {
            color: #888;
            font-style: italic;
        }
    </style>
</head>
<body>

<div class="form-container">
    <h2>Enregistrer l'état de l'ordinateur</h2>
    
    <form action="" method="GET">
        
        <div class="form-group">
            <label for="id_ordinateur">Sélectionner l'ordinateur :</label>
            <select name="id_ordinateur" id="id_ordinateur" required>
                <option value="" disabled selected>-- Choisir l'ordinateur --</option>
            </select>
        </div>

        <div class="form-group">
            <label for="etat">État de l'appareil :</label>
            <select name="etat" id="etat" required>
                <option value="" disabled selected>-- Choisir l'état --</option>
                <option value="1">Fonctionnel</option>
                <option value="2">En panne</option>
            </select>
        </div>

        <div class="form-group">
            <label for="date">Date de constatation :</label>
            <input type="date" name="date" id="date" required>
        </div>

        <div class="form-group">
            <label for="observations">Observation / Remarques :</label>
            <textarea name="observations" id="observations" placeholder="Ex: Écran scintille, besoin d'un dépoussiérage..." required></textarea>
        </div>

        <button type="submit">
            OK
        </button>
    </form>
</div>

<script>
    document.getElementById('date').valueAsDate = new Date();

    const urlServlet = 'http://localhost:8080/gestionParc_1/ordi/liste';
    fetch(urlServlet)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erreur de récupération des données");
            }
            return response.json();
        })
        .then(ordinateurs => {
            const selectOrdi = document.getElementById('id_ordinateur');
                        
            ordinateurs.forEach(ordi => {
                const option = document.createElement('option');
                option.value = ordi.id;
                option.textContent = `${ordi.processeur} (${ordi.ram} Go RAM / ${ordi.disqueDur} Go DD)`;
                selectOrdi.appendChild(option);
            });
        })
        .catch(error => {
            console.error('Erreur:', error);
            const selectOrdi = document.getElementById('id_ordinateur');
            selectOrdi.innerHTML = '<option value="">Erreur de chargement des ordinateurs</option>';
        });
</script>

<script>
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        const formData = new FormData(form);
        const params = new URLSearchParams(formData);

        fetch(`http://localhost:8080/gestionParc_1/ordietat?${params.toString()}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erreur HTTP : ${response.status}`);
                }

                return response.json();
            })
            .then(resultat => {
                console.log(resultat.message);

                if (resultat.success) {
                    alert(resultat.message);
                    form.reset();
                    document.getElementById('date').valueAsDate = new Date();
                }
            })
            .catch(error => {
                console.error("Erreur :", error);
                alert("Erreur lors de l'enregistrement de l'état.");
            });
    });
</script>

</body>
</html>