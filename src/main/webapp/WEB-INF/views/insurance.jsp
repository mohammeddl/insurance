<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Insert Insurance Details</title>
</head>
<body>
    <h2>Insert Insurance Details</h2>

    <form action="/insurance/contrat/create" method="POST">
        <!-- Select Insurance Type -->
        <label for="insuranceType">Insurance Type:</label>
        <select id="insuranceType" name="insuranceType" required>
            <option value="Automobile">Automobile</option>
            <option value="Habitation">Habitation</option>
            <option value="Sante">Sante</option>
        </select><br><br>

        <!-- Insurance details will be shown dynamically based on selected type -->
        <div id="insuranceDetails"></div><br>

        <button type="submit">Submit</button>
    </form>

    <script>
        document.getElementById('insuranceType').addEventListener('change', function () {
            var type = this.value;
            var detailsDiv = document.getElementById('insuranceDetails');
            detailsDiv.innerHTML = '';

            if (type === 'Automobile') {
                detailsDiv.innerHTML = `
                    <label for="ageConducteur">Age du conducteur:</label>
                    <input type="number" id="ageConducteur" name="ageConducteur" required><br><br>
                    
                    <label for="typeVehicule">Type de véhicule:</label>
                    <input type="text" id="typeVehicule" name="typeVehicule" required><br><br>

                    <label for="utilisationVehicule">Utilisation du véhicule:</label>
                    <input type="text" id="utilisationVehicule" name="utilisationVehicule" required><br><br>

                    <label for="historiqueConduite">Historique de conduite:</label>
                    <input type="text" id="historiqueConduite" name="historiqueConduite" required><br><br>
                `;
            } else if (type === 'Habitation') {
                detailsDiv.innerHTML = `
                    <label for="valeurBien">Valeur du bien:</label>
                    <input type="number" id="valeurBien" name="valeurBien" required><br><br>

                    <label for="typeLogement">Type de logement:</label>
                    <input type="text" id="typeLogement" name="typeLogement" required><br><br>

                    <label for="localisation">Localisation:</label>
                    <input type="text" id="localisation" name="localisation" required><br><br>

                    <label for="systemeSecurite">Système de sécurité:</label>
                    <input type="checkbox" id="systemeSecurite" name="systemeSecurite"><br><br>
                `;
            } else if (type === 'Sante') {
                detailsDiv.innerHTML = `
                    <label for="ageAssure">Age:</label>
                    <input type="number" id="ageAssure" name="ageAssure" required><br><br>

                    <label for="etatSante">Etat de santé:</label>
                    <input type="text" id="etatSante" name="etatSante" required><br><br>

                    <label for="typeCouverture">Type de couverture:</label>
                    <input type="text" id="typeCouverture" name="typeCouverture" required><br><br>
                `;
            }
        });
    </script>
</body>
</html>
