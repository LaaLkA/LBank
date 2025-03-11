document.addEventListener('DOMContentLoaded', function() {
    const balanceValueElem = document.getElementById('balanceValue');
    const userNameElem = document.getElementById('userName');

    function getCookieValue(cookieName) {
        const matches = document.cookie.match(new RegExp('(?:^|; )' + cookieName + '=([^;]*)'));
        return matches ? decodeURIComponent(matches[1]) : null;
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
            console.log('Profile data:', profileData);
            document.getElementById('profileUserName').textContent = profileData.userName || '';
            document.getElementById('profileFirstName').textContent = profileData.firstName || '';
            document.getElementById('profileLastName').textContent = profileData.lastName || '';
            document.getElementById('profilePhone').textContent = profileData.phone || '';
        })
        .catch(error => {
            console.error('Ошибка при запросе профиля:', error);
        });
});
