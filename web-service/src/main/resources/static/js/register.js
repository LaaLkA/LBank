document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('registerForm');
    const errorDiv = document.getElementById('registerError');

    if (!form) return;

    form.addEventListener('submit', function (event) {
        event.preventDefault();

        const formData = new FormData(form);
        const userName = formData.get('userName');
        const password = formData.get('password');
        const rePassword = formData.get('re-password');

        if (password !== rePassword) {
            errorDiv.textContent = 'Пароли не совпадают';
            return;
        }

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
                window.location.href = '/login';
            })
            .catch(err => {
                console.error('Registration error:', err);
                errorDiv.textContent = 'Ошибка: ' + err.message;
            });
    });
});
