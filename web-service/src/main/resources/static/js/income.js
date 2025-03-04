document.addEventListener('DOMContentLoaded', function() {

    function getCookieValue(cookieName) {
        const matches = document.cookie.match(new RegExp('(?:^|; )' + cookieName + '=([^;]*)'));
        return matches ? decodeURIComponent(matches[1]) : null;
    }

    const userName = getCookieValue('USER_NAME');
    if (!userName) {
        console.warn('USER_NAME cookie not found');
        return;
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
