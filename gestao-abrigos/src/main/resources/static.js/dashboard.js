
document.addEventListener('DOMContentLoaded', function() {
    // Inicializa o mapa (exemplo com Leaflet)
    if (document.getElementById('map')) {
        initMap();
    }


    setInterval(updateDashboardData, 30000);
});

function initMap() {
    const map = L.map('map').setView([-23.5505, -46.6333], 12);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '© OpenStreetMap contributors'
    }).addTo(map);


    document.querySelectorAll('.abrigo-marker').forEach(abrigo => {
        const lat = abrigo.dataset.lat;
        const lng = abrigo.dataset.lng;
        const nome = abrigo.dataset.nome;

        L.marker([lat, lng])
            .addTo(map)
            .bindPopup(`<b>${nome}</b>`);
    });
}

async function updateDashboardData() {
    try {
        const response = await fetch('/api/dashboard/atualizacoes');
        const data = await response.json();


        document.getElementById('total-abrigos').textContent = data.totalAbrigos;
        document.getElementById('recursos-criticos').textContent = data.recursosCriticos;

    } catch (error) {
        console.error('Erro ao atualizar dashboard:', error);
    }
}