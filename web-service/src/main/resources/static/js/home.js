document.addEventListener('DOMContentLoaded', function() {
    const balanceValueElem = document.getElementById('balanceValue');
    const refreshBalanceBtn = document.getElementById('refreshBalanceBtn');

    // Функция для отправки запроса в BalanceApiController и обновления баланса
    function updateBalance() {
        // Допустим, у вас userName жестко задан "asd"
        // В реальном приложении можно динамически получать имя,
        // например, через Thymeleaf переменные или поля ввода
        const userName = "asd";

        fetch('/api/balance/get', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userName: userName })
        })
            .then(response => {
                // Проверяем, не вернулся ли код ошибки
                if (!response.ok) {
                    return response.text().then(text => { throw new Error(text); });
                }
                // Если всё OK, сервер вернёт Double. При парсе из JSON получится число
                return response.json();
            })
            .then(data => {
                // data — это баланс, число
                balanceValueElem.textContent = data;
            })
            .catch(error => {
                console.error('Ошибка при запросе баланса:', error);
                // При желании можно вывести сообщение пользователю
                balanceValueElem.textContent = "Ошибка";
            });
    }

    // Навешиваем обработчик на кнопку
    refreshBalanceBtn.addEventListener('click', updateBalance);

    // Можно сразу запросить баланс при загрузке страницы
    updateBalance();
});
