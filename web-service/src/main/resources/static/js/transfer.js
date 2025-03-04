document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("transferForm");
    const resultBlock = document.getElementById("resultBlock");

    form.addEventListener("submit", function (event) {
        event.preventDefault(); // отменяем стандартное поведение формы

        const formData = new FormData(form);
        const receiver = formData.get("receiver");
        const amount = formData.get("amount");

        const body = {
            receiver: receiver,
            amount: amount
        }

        fetch("/api/transfer", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(body)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
                return response.text();
            })
            .then(data => {
                resultBlock.innerHTML = `<p style="color: green;">Перевод выполнен: ${data}</p>`;
            })
            .catch(error => {
                resultBlock.innerHTML = `<p style="color: red;">Ошибка: ${error.message}</p>`;
            });
    });
});