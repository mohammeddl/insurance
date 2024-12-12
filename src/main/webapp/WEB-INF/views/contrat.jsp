<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Insurance Contract Summary</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">

<div class="max-w-3xl mx-auto my-10 p-6 bg-white rounded-lg shadow-md">
    <h2 class="text-2xl font-bold mb-6 text-center">Insurance Contract Summary</h2>
    <p>Devis ID: ${insuranceDetails.id}</p> 

    <!-- Display Insurance Details -->
    <c:choose>
        <c:when test="${insuranceType == 'Automobile'}">
            <h3 class="text-xl font-semibold mb-4">Automobile Insurance Details</h3>
            <p><span class="font-bold">Age du Conducteur:</span> ${insuranceDetails.ageConducteur}</p>
            <p><span class="font-bold">Type de Véhicule:</span> ${insuranceDetails.typeVehicule}</p>
            <p><span class="font-bold">Utilisation du Véhicule:</span> ${insuranceDetails.utilisationVehicule}</p>
            <p><span class="font-bold">Historique de Conduite:</span> ${insuranceDetails.historiqueConduite}</p>
        </c:when>
        <c:when test="${insuranceType == 'Habitation'}">
            <h3 class="text-xl font-semibold mb-4">Home Insurance Details</h3>
            <p><span class="font-bold">Valeur du Bien:</span> ${insuranceDetails.valeurBien}</p>
            <p><span class="font-bold">Type de Logement:</span> ${insuranceDetails.typeLogement}</p>
            <p><span class="font-bold">Localisation:</span> ${insuranceDetails.localisation}</p>
            <p><span class="font-bold">Système de Sécurité:</span> ${insuranceDetails.systemeSecurite ? 'Yes' : 'No'}</p>
        </c:when>
        <c:when test="${insuranceType == 'Sante'}">
            <h3 class="text-xl font-semibold mb-4">Health Insurance Details</h3>
            <p><span class="font-bold">Age Assuré:</span> ${insuranceDetails.ageAssure}</p>
            <p><span class="font-bold">Etat de Santé:</span> ${insuranceDetails.etatSante}</p>
            <p><span class="font-bold">Type de Couverture:</span> ${insuranceDetails.typeCouverture}</p>
        </c:when>
    </c:choose>

    <p class="mt-4"><span class="font-bold">Total Monthly Premium:</span> ${finalPrice}</p>

    <div class="mt-6">
        <p><span class="font-bold">Start Date:</span> <span id="startDate"></span></p>
        <p><span class="font-bold">End Date:</span> <span id="endDate"></span></p>
    </div>

    <!-- Contract Form with Document Upload -->
    <form action="/contrat/create" method="POST" enctype="multipart/form-data"   class="mt-6">
        <input type="hidden" name="devisId" value="${insuranceDetails.id}" />
        <input type="hidden" name="insuranceType" value="${insuranceType}">
        <input type="hidden" id="dateDebut" name="dateDebut">
        <input type="hidden" id="dateFin" name="dateFin">

        <!-- Document Upload -->
        <label for="document" class="font-bold">Upload Document:</label>
        <input type="file" id="document" name="document" class="border rounded px-3 py-2 mt-2 w-full"><br><br>

        <button type="submit" class="bg-blue-500 text-white py-2 px-4 rounded mt-4">Approve Contract</button>
    </form>
   <form id="deleteForm" action="/insurance/deleteInsurance" method="post" style="display: inline;">
    <input type="hidden" name="_method" value="DELETE">
    <input type="hidden" name="insuranceId" value="${insuranceDetails.id}">
    <button type="button" onclick="confirmDelete()" class="bg-red-500 text-white py-2 px-4 rounded mt-4 ml-4">Cancel</button>
</form>
</div>

<script>

function confirmDelete() {
        if (confirm("Are you sure you want to cancel this insurance ?")) {
            document.getElementById("deleteForm").submit();
        }
    }

    const today = new Date();
    const endDate = new Date();
    endDate.setDate(today.getDate() + 30);

    const formatDate = (date) => date.toISOString().split('T')[0];
    
    document.getElementById("startDate").innerText = formatDate(today);
    document.getElementById("endDate").innerText = formatDate(endDate);

    document.getElementById("dateDebut").value = formatDate(today);
    document.getElementById("dateFin").value = formatDate(endDate);
</script>

</body>
</html>
