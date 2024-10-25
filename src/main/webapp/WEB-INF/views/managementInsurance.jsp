<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Insurance Contract management</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body class="bg-gray-100">

<header class="relative bg-blue-400">
    <div class="flex items-center justify-between h-24 px-8 mx-auto max-w-7xl">

        <a href="/insurance/home" class="relative z-10 flex items-center w-auto  text-2xl font-extrabold leading-none text-white select-none">
            Insurance.
        </a>

        <nav class="items-center justify-center hidden space-x-8 text-gray-200 md:flex">
            <a href="/insurance/home" x-data="{ hover: false }" @mouseenter="hover = true" @mouseleave="hover = false" class="relative inline-block text-base font-bold text-white uppercase transition duration-150 ease hover:text-gray-900">
                <span class="block">Insurance</span>
                <span class="absolute bottom-0 left-0 inline-block w-full h-1 -mb-1 overflow-hidden">
                    <span x-show="hover" class="absolute inset-0 inline-block w-full h-1 h-full transform border-t-2 border-blue-600" x-transition:enter="transition ease-out duration-300" x-transition:enter-start="-translate-x-full" x-transition:enter-end="translate-x-0" x-transition:leave="transition ease-out duration-300" x-transition:leave-start="translate-x-0" x-transition:leave-end="translate-x-full"></span>
                </span>
            </a>
            <a href="/contrat/all" x-data="{ hover: false }" @mouseenter="hover = true" @mouseleave="hover = false" class="relative inline-block text-base font-bold text-gray-700 uppercase transition duration-150 ease hover:text-gray-900">
                <span class="block">Contrat</span>
                <span class="absolute bottom-0 left-0 inline-block w-full h-1 -mb-1 overflow-hidden">
                    <span x-show="hover" class="absolute inset-0 inline-block w-full h-1 h-full transform border-t-2 border-blue-600" x-transition:enter="transition ease-out duration-300" x-transition:enter-start="-translate-x-full" x-transition:enter-end="translate-x-0" x-transition:leave="transition ease-out duration-300" x-transition:leave-start="translate-x-0" x-transition:leave-end="translate-x-full"></span>
                </span>
            </a>
            <a href="#_" x-data="{ hover: false }" @mouseenter="hover = true" @mouseleave="hover = false" class="relative inline-block text-base font-bold text-white uppercase transition duration-150 ease hover:text-gray-900">
                <span class="block">About Us</span>
                <span class="absolute bottom-0 left-0 inline-block w-full h-1 -mb-1 overflow-hidden">
                    <span x-show="hover" class="absolute inset-0 inline-block w-full h-1 h-full transform border-t-2 border-blue-600" x-transition:enter="transition ease-out duration-300" x-transition:enter-start="-translate-x-full" x-transition:enter-end="translate-x-0" x-transition:leave="transition ease-out duration-300" x-transition:leave-start="translate-x-0" x-transition:leave-end="translate-x-full"></span>
                </span>
            </a>
            
            <div class="w-0 h-5 border border-r border-gray-200"></div>
            
            <a href="/insurance/utilisateur/logout" class="inline-flex items-center justify-center px-4 py-2 text-base font-medium leading-6 text-white whitespace-no-wrap bg-blue-600 border border-blue-700 rounded-md shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
                logout
            </a>
        </nav>

        <!-- Mobile Button -->
        <div class="flex items-center justify-center h-full md:hidden">
            <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 8h16M4 16h16"></path>
            </svg>
        </div>
    </div>
  </header>
<div class="container mx-auto px-4 py-10">
    <h2 class="text-2xl font-bold mb-6 text-center">Insurance Contracts</h2>

    <!-- Table for Automobile Insurances -->
    <h3 class="text-xl font-bold mb-4">Assurance Automobile</h3>
    <table class="min-w-full bg-white border mb-10">
        <thead>
            <tr>
                <th class="py-2 px-4">ID</th>
                <th class="py-2 px-4">Vehicle Model</th>
                <th class="py-2 px-4">Registration Number</th>
                <th class="py-2 px-4">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="auto" items="${assuranceAutomobiles}">
                <tr>
                    <td>${auto.ageConducteur}</td>
                    <td>${auto.typeVehicule}</td>
                    <td>${auto.historiqueConduite}</td>
                    <td>
                        <button class="bg-blue-500 text-white px-4 py-2 rounded" 
    onclick="openModal('Automobile', ${auto.id}, ${auto.ageConducteur}, '${auto.typeVehicule}', '${auto.utilisationVehicule}', '${auto.historiqueConduite}')">Update</button>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Table for Habitation Insurances -->
    <h3 class="text-xl font-bold mb-4">Assurance Habitation</h3>
    <table class="min-w-full bg-white border mb-10">
        <thead>
            <tr>
                <th class="py-2 px-4">ID</th>
                <th class="py-2 px-4">Property Address</th>
                <th class="py-2 px-4">Property Value</th>
                <th class="py-2 px-4">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="habitation" items="${assuranceHabitations}">
                <tr>
                    <td>${habitation.typeLogement}</td>
                    <td>${habitation.localisation}</td>
                    <td>${habitation.valeurBien}</td>
                    <td>
                        <button class="bg-blue-500 text-white px-4 py-2 rounded" 
    onclick="openModal('Habitation', ${habitation.id}, null, null, null, null, ${habitation.valeurBien}, '${habitation.typeLogement}', '${habitation.localisation}', ${habitation.systemeSecurite})">Update</button>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <!-- Table for Sante Insurances -->
    <h3 class="text-xl font-bold mb-4">Assurance Santé</h3>
    <table class="min-w-full bg-white border mb-10">
        <thead>
            <tr>
                <th class="py-2 px-4">ID</th>
                <th class="py-2 px-4">Coverage Type</th>
                <th class="py-2 px-4">Maximum Coverage</th>
                <th class="py-2 px-4">Actions</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="sante" items="${assuranceSantes}">
                <tr>
                    <td>${sante.etatSante}</td>
                    <td>${sante.typeCouverture}</td>
                    <td>${sante.etatSante}</td>
                    <td>
                       <button class="bg-blue-500 text-white px-4 py-2 rounded" 
    onclick="openModal('Sante', ${sante.id}, null, null, null, null, null, null, null, null, ${sante.ageAssure}, '${sante.etatSante}', '${sante.typeCouverture}')">Update</button>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<!-- Modal for Updating Insurance -->
<div id="insuranceModal" class="fixed inset-0 hidden bg-gray-600 bg-opacity-75 flex justify-center items-center z-50">
    <div class="bg-white rounded-lg p-8 max-w-lg w-full">
        <h2 class="text-2xl font-bold mb-4">Update Insurance Details</h2>
        <form action="/insurance/insurance/updateInsurance" method="post" id="insuranceForm">
            <input type="hidden" name="insuranceType" id="insuranceType">
            <input type="hidden" name="insuranceId" id="insuranceId">
            <div id="insuranceDetails"></div>
            <button type="submit" class="bg-green-500 text-white py-2 px-4 rounded">Save Changes</button>
            <button type="button" onclick="closeModal()" class="bg-red-500 text-white py-2 px-4 rounded ml-2">Cancel</button>
        </form>
    </div>
</div>

<script>
 function openModal(type, id, ageConducteur = '', typeVehicule = '', utilisationVehicule = '', historiqueConduite = '', 
                    valeurBien = '', typeLogement = '', localisation = '', systemeSecurite = false, 
                    ageAssure = '', etatSante = '', typeCouverture = '') {
    document.getElementById('insuranceType').value = type;
    document.getElementById('insuranceId').value = id;
    const detailsDiv = document.getElementById('insuranceDetails');
    detailsDiv.innerHTML = ''; 

    console.log("Type:", type);
    console.log("ageConducteur:", ageConducteur);
    console.log("etatSante:", etatSante);

    if (type === 'Automobile') {
        detailsDiv.innerHTML = `
            <label for="ageConducteur">Age du conducteur:</label>
            <input type="number" id="ageConducteur" name="ageConducteur" class="border rounded px-3 py-2" value="${ageConducteur}"><br><br>
            
            <label for="typeVehicule">Type de véhicule:</label>
            <input type="text" id="typeVehicule" name="typeVehicule" class="border rounded px-3 py-2" value="${typeVehicule}"><br><br>

            <label for="utilisationVehicule">Utilisation du véhicule:</label>
            <input type="text" id="utilisationVehicule" name="utilisationVehicule" class="border rounded px-3 py-2" value="${utilisationVehicule}"><br><br>

            <label for="historiqueConduite">Historique de conduite:</label>
            <input type="text" id="historiqueConduite" name="historiqueConduite" class="border rounded px-3 py-2" value="${historiqueConduite}"><br><br>
        `;
    } else if (type === 'Habitation') {
        detailsDiv.innerHTML = `
            <label for="valeurBien">Valeur du bien:</label>
            <input type="number" id="valeurBien" name="valeurBien" class="border rounded px-3 py-2" value="${valeurBien}"><br><br>

            <label for="typeLogement">Type de logement:</label>
            <input type="text" id="typeLogement" name="typeLogement" class="border rounded px-3 py-2" value="${typeLogement}"><br><br>

            <label for="localisation">Localisation:</label>
            <input type="text" id="localisation" name="localisation" class="border rounded px-3 py-2" value="${localisation}"><br><br>

            <label for="systemeSecurite">Système de sécurité:</label>
            <input type="checkbox" id="systemeSecurite" name="systemeSecurite" ${systemeSecurite ? 'checked' : ''}><br><br>
        `;
    } else if (type === 'Sante') {
        detailsDiv.innerHTML = `
            <label for="ageAssure">Age:</label>
            <input type="number" id="ageAssure" name="ageAssure" class="border rounded px-3 py-2" value="${ageAssure}"><br><br>

            <label for="etatSante">Etat de santé:</label>
            <input type="text" id="etatSante" name="etatSante" class="border rounded px-3 py-2" value="${etatSante}"><br><br>

            <label for="typeCouverture">Type de couverture:</label>
            <input type="text" id="typeCouverture" name="typeCouverture" class="border rounded px-3 py-2" value="${typeCouverture}"><br><br>
        `;
    }

    document.getElementById('insuranceModal').classList.remove('hidden'); 
}

function closeModal() {
    document.getElementById('insuranceModal').classList.add('hidden');
}
</script>

</body>
</div>
</html>