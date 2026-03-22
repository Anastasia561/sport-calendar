let currentPage = 0;
let totalPages = 1;
const pageSize = 3;

function loadEvents() {
    fetch(`/api/events?page=${currentPage}&size=${pageSize}`)
        .then(res => res.json())
        .then(data => {
            renderEvents(data.data.content);
            updatePagination(data.data);
        })
        .catch(err => console.error(err));
}

function renderEvents(events) {
    const container = document.getElementById('events');
    container.innerHTML = '';

    events.forEach(event => {
        const homeTeam = event.homeTeam?.name ?? 'TBD';

        const div = document.createElement('div');
        div.className = 'event';

        div.innerHTML = `
            <strong>${homeTeam} vs ${event.awayTeam.name}</strong><br>
            Sport: ${event.sport}<br>
            Status: ${event.status}<br>
            Date: ${event.dateVenue}<br>
            Time: ${event.timeVenueUTC}<br>
            Score: ${event.homeGoals} - ${event.awayGoals}
        `;

        container.appendChild(div);
    });
}

function updatePagination(data) {
    totalPages = data.totalPages;

    document.getElementById('pageInfo').innerText =
        `Page ${currentPage + 1} of ${totalPages}`;
}

function nextPage() {
    if (currentPage + 1 < totalPages) {
        currentPage++;
        loadEvents();
    }
}

function prevPage() {
    if (currentPage > 0) {
        currentPage--;
        loadEvents();
    }
}

loadEvents();