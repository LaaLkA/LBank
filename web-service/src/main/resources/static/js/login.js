document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('loginForm');
    const errorDiv = document.getElementById('loginError');

    if (!form) return;

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(form);
        const userName = formData.get('userName');
        const password = formData.get('password');

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
                return response.text();
            })
            .then(data => {
                console.log('Login successful:', data);
                window.location.href = '/home';
            })
            .catch(err => {
                console.error('Login error:', err);

                errorDiv.textContent = 'Ошибка: ' + err.message;
            });
    });
});
