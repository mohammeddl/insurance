<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Insert Insurance Details</title>
    <link href="https://cdn.jsdelivr.net/npm/tailwindcss@2.2.19/dist/tailwind.min.css" rel="stylesheet">
</head>
<body>

<header class="relative bg-blue-400">
    <div class="flex items-center justify-between h-24 px-8 mx-auto max-w-7xl">

        <a href="/insurance/home" class="relative z-10 flex items-center w-auto  text-2xl font-extrabold leading-none text-white select-none">
            Insurance.
        </a>

        <nav class="items-center justify-center hidden space-x-8 text-gray-200 md:flex">
            <a href="/insurance" x-data="{ hover: false }" @mouseenter="hover = true" @mouseleave="hover = false" class="relative inline-block text-base font-bold text-gray-700 uppercase transition duration-150 ease hover:text-gray-900">
                <span class="block">Insurance</span>
                <span class="absolute bottom-0 left-0 inline-block w-full h-1 -mb-1 overflow-hidden">
                    <span x-show="hover" class="absolute inset-0 inline-block w-full h-1 h-full transform border-t-2 border-blue-600" x-transition:enter="transition ease-out duration-300" x-transition:enter-start="-translate-x-full" x-transition:enter-end="translate-x-0" x-transition:leave="transition ease-out duration-300" x-transition:leave-start="translate-x-0" x-transition:leave-end="translate-x-full"></span>
                </span>
            </a>
            <a href="/contrat/all" x-data="{ hover: false }" @mouseenter="hover = true" @mouseleave="hover = false" class="relative inline-block text-base font-bold text-white uppercase transition duration-150 ease hover:text-gray-900">
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
            
            <a href="/logout" class="inline-flex items-center justify-center px-4 py-2 text-base font-medium leading-6 text-white whitespace-no-wrap bg-blue-600 border border-blue-700 rounded-md shadow-sm hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-blue-500">
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
    <h2 class="text-3xl font-bold text-center mb-6">Choose Your Insurance Type</h2>
    <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
        
        <!-- Automobile Card -->
        <div class="bg-white shadow-md rounded-lg p-6 text-center cursor-pointer" onclick="openModal('Automobile')">
            <img src="path/to/car-insurance-icon.png" alt="Automobile" class="w-24 mx-auto mb-4">
            <h3 class="text-xl font-semibold">Automobile Insurance</h3>
            <p class="text-gray-600">Secure your vehicle with our comprehensive insurance options.</p>
            <button class="mt-4 bg-blue-500 text-white py-2 px-4 rounded">Read More</button>
        </div>
        
        <!-- Habitation Card -->
        <div class="bg-white shadow-md rounded-lg p-6 text-center cursor-pointer" onclick="openModal('Habitation')">
            <img src="path/to/home-insurance-icon.png" alt="Habitation" class="w-24 mx-auto mb-4">
            <h3 class="text-xl font-semibold">Home Insurance</h3>
            <p class="text-gray-600">Protect your home with our flexible insurance plans.</p>
            <button class="mt-4 bg-blue-500 text-white py-2 px-4 rounded">Read More</button>
        </div>
        
        <!-- Sante Card -->
        <div class="bg-white shadow-md rounded-lg p-6 text-center cursor-pointer" onclick="openModal('Sante')">
            <img src="path/to/health-insurance-icon.png" alt="Sante" class="w-24 mx-auto mb-4">
            <h3 class="text-xl font-semibold">Health Insurance</h3>
            <p class="text-gray-600">Comprehensive coverage for all your health needs.</p>
            <button class="mt-4 bg-blue-500 text-white py-2 px-4 rounded">Read More</button>
        </div>
    </div>
</div>

<!-- Modal -->
<div id="insuranceModal" class="fixed inset-0 hidden bg-gray-500 bg-opacity-75 flex justify-center items-center">
    <div class="bg-white rounded-lg p-8 max-w-lg w-full">
        <h3 class="text-2xl font-bold mb-4">Insurance Details</h3>
        
        <form action="/insurance/contrat/create" method="POST" id="insuranceForm">
            <input type="hidden" id="insuranceType" name="insuranceType">
            <div id="insuranceDetails"></div>
            <button type="submit" class="mt-4 bg-green-500 text-white py-2 px-6 rounded">Submit</button>
        </form>

        <button onclick="closeModal()" class="mt-4 bg-red-500 text-white py-2 px-4 rounded">Close</button>
    </div>
</div>

<script>
    function openModal(type) {
        document.getElementById('insuranceType').value = type;
        var detailsDiv = document.getElementById('insuranceDetails');
        detailsDiv.innerHTML = '';
        
        if (type === 'Automobile') {
            detailsDiv.innerHTML = `
                <label for="ageConducteur">Driver's age:</label>
                <input type="number" id="ageConducteur" name="ageConducteur" required class="border rounded px-3 py-2"><br><br>
                
                <label for="typeVehicule">Vehicle type:</label>
                <input type="text" id="typeVehicule" name="typeVehicule" required class="border rounded px-3 py-2"><br><br>

                <label for="utilisationVehicule">Use of vehicle:</label>
                <input type="text" id="utilisationVehicule" name="utilisationVehicule" required class="border rounded px-3 py-2"><br><br>

                <label for="historiqueConduite">Historique de conduite:</label>
                <input type="text" id="historiqueConduite" name="historiqueConduite" required class="border rounded px-3 py-2"><br><br>
            `;
        } else if (type === 'Habitation') {
            detailsDiv.innerHTML = `
                <label for="valeurBien">Value of the property:</label>
                <input type="number" id="valeurBien" name="valeurBien" required class="border rounded px-3 py-2"><br><br>

                <label for="typeLogement">Type of accommodation:</label>
                <input type="text" id="typeLogement" name="typeLogement" required class="border rounded px-3 py-2"><br><br>

                <label for="localisation">Location:</label>
                <input type="text" id="localisation" name="localisation" required class="border rounded px-3 py-2"><br><br>

                <label for="systemeSecurite">Security System:</label>
                <input type="checkbox" id="systemeSecurite" name="systemeSecurite"><br><br>
            `;
        } else if (type === 'Sante') {
            detailsDiv.innerHTML = `
                <label for="ageAssure">Age:</label>
                <input type="number" id="ageAssure" name="ageAssure" required class="border rounded px-3 py-2"><br><br>

                <label for="etatSante">Health status:</label>
                <input type="text" id="etatSante" name="etatSante" required class="border rounded px-3 py-2"><br><br>

                <label for="typeCouverture">Type of cover:</label>
                <input type="text" id="typeCouverture" name="typeCouverture" required class="border rounded px-3 py-2"><br><br>
            `;
        }

        document.getElementById('insuranceModal').classList.remove('hidden');
    }

    function closeModal() {
        document.getElementById('insuranceModal').classList.add('hidden');
    }
</script>

</body>
</html>
