document.addEventListener('DOMContentLoaded', function() {

    // Допустим, имя пользователя хранится в cookie USER_NAME (не HttpOnly),
    // иначе можете получать userName откуда-то ещё, например, передавать через Thymeleaf.
    function getCookieValue(cookieName) {
        const matches = document.cookie.match(new RegExp('(?:^|; )' + cookieName + '=([^;]*)'));
        return matches ? decodeURIComponent(matches[1]) : null;
    }

    const userName = getCookieValue('USER_NAME');
    if (!userName) {
        console.warn('USER_NAME cookie not found');
        return; // или показать сообщение пользователю
    }

    // Функция для запроса списка трат
    function loadExpenses() {
        // Делаем запрос на наш web-service endpoint: /api/expense/list?sender=...
        fetch(`/api/expense/list?sender=${encodeURIComponent(userName)}`)
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

    // Функция для отображения данных в таблице
    function renderTable(expenses) {
        const tableBody = document.querySelector('#expenseTable tbody');
        tableBody.innerHTML = ''; // очищаем

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

    // При загрузке страницы сразу подгружаем траты
    loadExpenses();
});
