document.addEventListener('DOMContentLoaded', function() {
    function getCookieValue(cookieName) {
        const matches = document.cookie.match(new RegExp('(?:^|; )' + cookieName + '=([^;]*)'));
        return matches ? decodeURIComponent(matches[1]) : null;
    }

    const userName = getCookieValue('USER_NAME');
    if (!userName) {
        console.warn('USER_NAME cookie not found');
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
