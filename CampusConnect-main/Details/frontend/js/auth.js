// Authentication utilities

// Make authenticated API request
async function apiRequest(url, options = {}) {
    const token = getToken();
    
    const defaultHeaders = {
        'Content-Type': 'application/json'
    };

    if (token) {
        defaultHeaders['Authorization'] = `Bearer ${token}`;
    }

    const config = {
        ...options,
        headers: {
            ...defaultHeaders,
            ...options.headers
        }
    };

    try {
        const response = await fetch(`${API_CONFIG.BASE_URL}${url}`, config);
        
        if (response.status === 401) {
            // Unauthorized - redirect to login
            clearAuthData();
            window.location.href = 'index.html';
            throw new Error('Unauthorized');
        }

        const data = await response.json();
        
        if (!response.ok) {
            throw new Error(data.message || `HTTP error! status: ${response.status}`);
        }

        return data;
    } catch (error) {
        console.error('API request failed:', error);
        throw error;
    }
}

// Redirect to login if not authenticated
function requireAuth() {
    if (!isAuthenticated()) {
        window.location.href = 'index.html';
        return false;
    }
    return true;
}

// Display user info in navbar
function displayUserInfo() {
    const userInfo = getUserInfo();
    if (userInfo) {
        const userInfoElement = document.getElementById('userInfo');
        if (userInfoElement) {
            userInfoElement.textContent = `Logged in as: ${userInfo.username} (${userInfo.roles.join(', ')})`;
        }
    }
}

// Setup logout button
function setupLogout() {
    const logoutBtn = document.getElementById('logoutBtn');
    if (logoutBtn) {
        logoutBtn.addEventListener('click', () => {
            clearAuthData();
            window.location.href = 'index.html';
        });
    }
}

// Initialize auth on page load
document.addEventListener('DOMContentLoaded', () => {
    displayUserInfo();
    setupLogout();
});

