window.onload = function () {
    loadCompetitions();
    loadTeams();
    loadStages();
    loadGroups();
    loadStadiums();
};

function populateSelect(selectId, items, labelField = "name") {
    const select = document.getElementById(selectId);
    select.innerHTML = '<option value="">Select...</option>';

    items.forEach(item => {
        const option = document.createElement('option');
        option.value = item.id;
        option.textContent = item[labelField];
        select.appendChild(option);
    });
}

function loadCompetitions() {
    fetch('/api/competitions')
        .then(res => res.json())
        .then(data => populateSelect('competitionId', data));
}

function loadTeams() {
    fetch('/api/teams')
        .then(res => res.json())
        .then(data => {
            populateSelect('homeTeamId', data);
            populateSelect('awayTeamId', data);
        });
}

function loadStages() {
    fetch('/api/stages')
        .then(res => res.json())
        .then(data => populateSelect('stageId', data));
}

function loadGroups() {
    fetch('/api/groups')
        .then(res => res.json())
        .then(data => populateSelect('groupId', data));
}

function loadStadiums() {
    fetch('/api/stadiums')
        .then(res => res.json())
        .then(data => populateSelect('stadiumId', data));
}

function addEvent() {
    const event = {
        season: Number(document.getElementById('season').value),
        date: document.getElementById('date').value,
        timeUtc: document.getElementById('time').value,
        message: document.getElementById('message').value,

        competitionId: Number(document.getElementById('competitionId').value),
        stageId: Number(document.getElementById('stageId').value),
        groupId: Number(document.getElementById('groupId').value) || null,
        stadiumId: Number(document.getElementById('stadiumId').value) || null,

        homeTeamId: Number(document.getElementById('homeTeamId').value) || null,
        awayTeamId: Number(document.getElementById('awayTeamId').value)
    };

    fetch('/api/events', {
        method: 'POST',
        headers: {'Content-Type': 'application/json'},
        body: JSON.stringify(event)
    })
        .then(res => {
            if (!res.ok) throw new Error("Failed to create event");
            alert("Event created!");
            window.location.href = "events.html";
        })
        .catch(err => console.error(err));
}