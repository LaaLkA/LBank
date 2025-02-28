document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('loginForm');
    const errorDiv = document.getElementById('loginError');

    if (!form) return;

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // останавливаем стандартную отправку формы

        const formData = new FormData(form);
        const userName = formData.get('userName');
        const password = formData.get('password');

        // Отправляем запрос на "/login",
        // где ваш AuthApiController ожидает @RequestBody AuthRequest
        fetch('/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userName: userName, password: password })
        })
            .then(async response => {
                if (!response.ok) {
                    const text = await response.text();
                    throw new Error(text);
                }
                // Если OK, получаем JSON или строку
                return response.text();
            })
            .then(data => {
                // data, например, {"status":"ok"}
                console.log('Login successful:', data);
                // Перенаправляем на /home
                window.location.href = '/home';
            })
            .catch(err => {
                console.error('Login error:', err);
                // Здесь можно распарсить, если вернулся JSON вида {"error":"..."}
                // Но сейчас у нас это просто текст, выведем целиком
                errorDiv.textContent = 'Ошибка: ' + err.message;
            });
    });
});
