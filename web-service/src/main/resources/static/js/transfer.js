document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById("transferForm");
    const resultBlock = document.getElementById("resultBlock");
    const balanceValueElem = document.getElementById('balanceValue');
    const userNameElem = document.getElementById('userName');
    const balanceCountElem = document.querySelector('.balance-count');

    const successModal = document.getElementById('successModal');
    const modalMessage = document.getElementById('modalMessage');
    const goHomeBtn = document.getElementById('goHomeBtn');
    const newTransferBtn = document.getElementById('newTransferBtn');

    function getCookieValue(cookieName) {
        const match = document.cookie.match(new RegExp('(^|;\\s*)' + cookieName + '=([^;]*)'));
        return match ? decodeURIComponent(match[2]) : null;
    }

    const userName = getCookieValue('USER_NAME');

    if (userName && userNameElem) {
        userNameElem.textContent = userName;
    }

    function updateBalance() {

        fetch('/api/balance/get', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text);
                    });
                }
                return response.json();
            })
            .then(data => {
                const formattedBalance = Number(data).toLocaleString('ru-RU', {
                    minimumFractionDigits: 2,
                    maximumFractionDigits: 2
                });
                balanceValueElem.textContent = formattedBalance;
                autoFitFont(balanceCountElem);
            })
            .catch(error => {
                console.error('Ошибка при запросе баланса:', error);
                balanceValueElem.textContent = "Ошибка";
            });
    }

    updateBalance();

    function autoFitFont(el) {
        const containerWidth = el.clientWidth;
        let currentFontSize = parseFloat(window.getComputedStyle(el).fontSize);
        while (el.scrollWidth > containerWidth && currentFontSize > 8) {
            currentFontSize -= 1;
            el.style.fontSize = currentFontSize + 'px';
        }
    }

    form.addEventListener("submit", function (event) {

        event.preventDefault();

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
            body: JSON.stringify(body),
            credentials: "include"
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