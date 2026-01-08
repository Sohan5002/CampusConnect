// API Configuration
const API_CONFIG = {
    BASE_URL: 'http://localhost:8080/api',
    AUTH_ENDPOINT: '/auth',
    STUDENTS_ENDPOINT: '/students'
};

// Get JWT token from localStorage
function getToken() {
    return localStorage.getItem('jwt_token');
}

// Get user info from localStorage
function getUserInfo() {
    const userInfo = localStorage.getItem('user_info');
    return userInfo ? JSON.parse(userInfo) : null;
}

// Save JWT token and user info
function saveAuthData(token, userInfo) {
    localStorage.setItem('jwt_token', token);
    localStorage.setItem('user_info', JSON.stringify(userInfo));
}

// Clear auth data
function clearAuthData() {
    localStorage.removeItem('jwt_token');
    localStorage.removeItem('user_info');
}

// Check if user is authenticated
function isAuthenticated() {
    return getToken() !== null;
}

// Get user roles
function getUserRoles() {
    const userInfo = getUserInfo();
    return userInfo ? userInfo.roles : [];
}

// Check if user has role
function hasRole(role) {
    const roles = getUserRoles();
    return roles.includes(role.toUpperCase());
}

// Check if user is admin
function isAdmin() {
    return hasRole('ADMIN');
}

// Check if user is faculty
function isFaculty() {
    return hasRole('FACULTY');
}

// Check if user is student
function isStudent() {
    return hasRole('STUDENT');
}

// Check if user can create/update students
function canModifyStudents() {
    return isAdmin() || isFaculty();
}

// Check if user can delete students
function canDeleteStudents() {
    return isAdmin();
}

