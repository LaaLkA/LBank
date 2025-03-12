document.addEventListener('DOMContentLoaded', function() {
    const balanceValueElem = document.getElementById('balanceValue');
    const userNameElem = document.getElementById('userName');
    const logoutBtn = document.getElementById('logoutBtn');
    const balanceCountElem = document.querySelector('.balance-count');

    function getCookieValue(cookieName){
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
                    return response.text().then(text => { throw new Error(text ); });
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

    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            document.cookie = 'JWT_TOKEN=; Max-Age=0; path=/';
            document.cookie = 'USER_NAME=; Max-Age=0; path=/';
            window.location.href = '/login';
        });
    }
});
