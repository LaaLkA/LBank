document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registerForm');
    const errorDiv = document.getElementById('registerError');

    if (!form) return;

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(form);
        const userName = formData.get('userName');
        const password = formData.get('password');

        // Отправляем запрос на "/register"
        fetch('/register', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ userName, password })
        })
            .then(async response => {
                if (!response.ok) {
                    const text = await response.text();
                    throw new Error(text);
                }
                return response.text();
            })
            .then(data => {
                console.log('Registration success:', data);
                // При успехе можно, например, отправить на /login
                window.location.href = '/login';
            })
            .catch(err => {
                console.error('Registration error:', err);
                errorDiv.textContent = 'Ошибка: ' + err.message;
            });
    });
});
