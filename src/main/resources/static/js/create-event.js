window.onload = function () {
    loadCompetitions();
    loadTeams();
    loadStages();
    loadGroups();
    loadStadiums();
};

function clearErrors() {
    document.querySelectorAll('.error').forEach(e => e.textContent = '');
    document.querySelectorAll('input, select').forEach(e => e.classList.remove('input-error'));

    const globalError = document.getElementById('globalError');
    globalError.textContent = '';
    globalError.style.display = 'none';
}

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
        .then(data => populateSelect('competitionId', data.data));
}

function loadTeams() {
    fetch('/api/teams')
        .then(res => res.json())
        .then(data => {
            populateSelect('homeTeamId', data.data);
            populateSelect('awayTeamId', data.data);
        });
}

function loadStages() {
    fetch('/api/stages')
        .then(res => res.json())
        .then(data => populateSelect('stageId', data.data));
}

function loadGroups() {
    fetch('/api/groups')
        .then(res => res.json())
        .then(data => populateSelect('groupId', data.data));
}

function loadStadiums() {
    fetch('/api/stadiums')
        .then(res => res.json())
        .then(data => populateSelect('stadiumId', data.data));
}

function handleValidationErrors(errorData) {
    const validationErrors = errorData?.error?.validationErrors;

    if (validationErrors && validationErrors.length > 0) {
        validationErrors.forEach(err => {
            const field = err.field;
            const message = err.message;

            const errorElement = document.getElementById(field + "Error");
            const inputElement = document.getElementById(field);

            if (errorElement) errorElement.textContent = message;
            if (inputElement) inputElement.classList.add("input-error");
        });
    }

    const globalError = document.getElementById('globalError');

    if (errorData?.error?.message) {
        globalError.textContent = errorData.error.message;
        globalError.style.display = 'block';
    }
}

function addEvent() {
    clearErrors();

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
        .then(async res => {
            if (!res.ok) {
                const errorData = await res.json();
                handleValidationErrors(errorData);
                throw new Error("Validation failed");
            }
            return res.json();
        })
        .then(() => {
            alert("Event created!");
            window.location.href = "events.html";
        })
        .catch(err => console.error(err));
}