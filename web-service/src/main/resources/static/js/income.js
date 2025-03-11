document.addEventListener('DOMContentLoaded', function() {
    const balanceValueElem = document.getElementById('balanceValue');
    const userNameElem = document.getElementById('userName');

    function getCookieValue(cookieName) {
        const matches = document.cookie.match(new RegExp('(?:^|; )' + cookieName + '=([^;]*)'));
        return matches ? decodeURIComponent(matches[1]) : null;
    }

    const userName = getCookieValue('USER_NAME');

    if (userName && userNameElem) {
        userNameElem.textContent = userName;
    }

    function loadExpenses() {
        fetch(`/api/income/list?receiver=${encodeURIComponent(userName)}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error('Ошибка загрузки списка трат');
                }
                return response.json();
            })
            .then(data => {
                console.log('Expenses loaded:', data);
                renderTable(data);
            })
            .catch(error => {
                console.error('Ошибка при запросе списка трат:', error);
            });
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
                    return response.text().then(text => { throw new Error(text ); });
                }
                return response.json();
            })
            .then(data => {
                balanceValueElem.textContent = data;
            })
            .catch(error => {
                console.error('Ошибка при запросе баланса:', error);
                balanceValueElem.textContent = "Ошибка";
            });
    }

    updateBalance();

    function renderTable(expenses) {
        const tableBody = document.querySelector('#incomeTable tbody');
        tableBody.innerHTML = '';

        expenses.forEach(exp => {
            const row = document.createElement('tr');
            row.innerHTML = `
                <td>${exp.sender}</td>
                <td>${exp.receiver}</td>
                <td>${exp.amount}</td>
            `;
            tableBody.appendChild(row);
        });
    }

    loadExpenses();
});
