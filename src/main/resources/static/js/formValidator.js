"use strict";

const submitButton = document.getElementById('submitButton');

submitButton.addEventListener("click", function (event) {

    let form = document.getElementById('main');
    for (let i = 0; i < form.elements.length; i++) {
        let input = form.elements[i];
        if (input.getAttribute('type') === 'text' ||
            input.tagName.toLowerCase() === 'textarea') {
            if (input.value !== "" &&
                input.value.replace(/\s/g, "") === "") {

                let errorText = 'Поля не могут быть пустыми или состоять из пробелов';
                document.getElementById('validateText').innerText = errorText;
                event.preventDefault();
                break;
            }
        }
    }
});

