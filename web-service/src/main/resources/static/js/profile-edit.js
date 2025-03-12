document.addEventListener('DOMContentLoaded', () => {
    const profileUserNameElem = document.getElementById('profileUserName');
    const emailInput = document.getElementById('emailInput');
    const firstNameInput = document.getElementById('firstNameInput');
    const lastNameInput = document.getElementById('lastNameInput');
    const phoneInput = document.getElementById('phoneInput');
    const errorBlock = document.getElementById('errorBlock');
    const profileEditForm = document.getElementById('profileEditForm');

    function getCookieValue(name) {
        const match = document.cookie.match(new RegExp('(^|;\\s*)' + name + '=([^;]*)'));
        return match ? decodeURIComponent(match[2]) : null;
    }

    const userName = getCookieValue('USER_NAME');

    function loadProfile() {
        if (!userName) {
            errorBlock.textContent = 'Ошибка: userName не найден в cookies';
            return;
        }

        fetch(`/api/profile/${encodeURIComponent(userName)}`, {
            method: 'GET',
            credentials: 'include'
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Ошибка загрузки профиля');
                }
                return response.json();
            })
            .then(profileData => {
                profileUserNameElem.textContent = profileData.userName || '';
                emailInput.value = profileData.email || '';
                firstNameInput.value = profileData.firstName || '';
                lastNameInput.value = profileData.lastName || '';
                phoneInput.value = profileData.phone || '';
            })
            .catch(err => {
                errorBlock.textContent = 'Ошибка при запросе профиля: ' + err.message;
            });
    }

    loadProfile();

    profileEditForm.addEventListener('submit', (e) => {
        e.preventDefault();
        errorBlock.textContent = '';
        const emailVal = emailInput.value.trim();
        const firstNameVal = firstNameInput.value.trim();
        const lastNameVal = lastNameInput.value.trim();
        const phoneVal = phoneInput.value.trim();

        const body = {
            email: emailVal,
            firstName: firstNameVal,
            lastName: lastNameVal,
            phone: phoneVal
        };

        fetch(`/api/profile/${encodeURIComponent(userName)}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            credentials: 'include',
            body: JSON.stringify(body)
        })
            .then(response => {
                if (!response.ok) {
                    return response.text().then(text => {
                        throw new Error(text || 'Ошибка при обновлении профиля');
                    });
                }
                return response.json();
            })
            .then(data => {
                alert('Профиль успешно обновлён!');
            })
            .catch(err => {
                errorBlock.textContent = 'Ошибка: ' + err.message;
            });
    });
});
