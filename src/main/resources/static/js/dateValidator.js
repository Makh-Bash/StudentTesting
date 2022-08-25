"use strict";

const submitButton = document.getElementById('submitButton');

submitButton.addEventListener('click', function (event) {

    const dateInput = document.getElementById('date');

    if (!dateInput.value) {
        let errorText = 'Введите дату';
        document.getElementById('dateText').innerText = errorText;
        event.preventDefault();
    }
    else if (new Date(dateInput.value).getFullYear() < 1900) {
        let errorText = 'Введите корректную дату рождения';
        document.getElementById('dateText').innerText = errorText;
        event.preventDefault();
    }

})