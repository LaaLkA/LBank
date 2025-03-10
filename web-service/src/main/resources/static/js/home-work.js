document.addEventListener("DOMContentLoaded", function () {
    const contentBlock = document.getElementById("content");
    const balanceValueElem = document.getElementById('balanceValue');
    const userNameElem = document.getElementById('userName');
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
                balanceValueElem.textContent = data;
            })
            .catch(error => {
                console.error('Ошибка при запросе баланса:', error);
                balanceValueElem.textContent = "Ошибка";
            });
    }

    updateBalance();

    document.querySelectorAll(".menu a").forEach(link => {
        link.addEventListener("click", function (event) {
            event.preventDefault();
            const page = this.getAttribute("data-page"); // Получаем имя страницы

            if (!page) return;

            // Загружаем HTML-контент с сервера
            fetch(`/${page}.html`)
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`Ошибка загрузки ${page}.html`);
                    }
                    return response.text();
                })
                .then(html => {
                    contentBlock.innerHTML = html; // Вставляем полученный HTML
                    executePageScripts(page); // Загружаем скрипты страницы
                })
                .catch(error => {
                    console.error("Ошибка загрузки страницы:", error);
                    contentBlock.innerHTML = "<p style='color:red;'>Ошибка загрузки контента</p>";
                });
        });
    });

    function executePageScripts(page) {
        let script = document.createElement("script");
        script.src = `/js/${page}.js`;
        script.type = "text/javascript";
        script.onload = () => console.log(`${page}.js загружен`);
        document.body.appendChild(script);
    }
});
