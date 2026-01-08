// Dashboard functionality

let currentPage = 0;
const pageSize = 100; // Get all students for stats

document.addEventListener('DOMContentLoaded', async () => {
    if (!requireAuth()) return;

    // Display role badge
    const roleBadge = document.getElementById('roleBadge');
    const userInfo = getUserInfo();
    if (roleBadge && userInfo) {
        roleBadge.textContent = userInfo.roles.join(', ');
    }

    // Show/hide buttons based on role
    const addStudentBtn = document.getElementById('addStudentBtn');
    if (addStudentBtn) {
        addStudentBtn.style.display = canModifyStudents() ? 'inline-block' : 'none';
        addStudentBtn.addEventListener('click', () => {
            window.location.href = 'student-form.html';
        });
    }

    const viewStudentsBtn = document.getElementById('viewStudentsBtn');
    if (viewStudentsBtn) {
        viewStudentsBtn.addEventListener('click', () => {
            window.location.href = 'students.html';
        });
    }

    // Load dashboard stats
    await loadDashboardStats();
});

async function loadDashboardStats() {
    try {
        // Get all students for statistics
        const response = await apiRequest(
            `${API_CONFIG.STUDENTS_ENDPOINT}?page=0&size=${pageSize}&sortBy=id&sortDir=ASC`
        );

        const students = response.data || [];
        const totalStudents = response.totalItems || students.length;

        // Update total students
        const totalStudentsEl = document.getElementById('totalStudents');
        if (totalStudentsEl) {
            totalStudentsEl.textContent = totalStudents;
        }

        // Calculate average marks
        const studentsWithMarks = students.filter(s => s.marks !== null && s.marks !== undefined);
        const averageMarks = studentsWithMarks.length > 0
            ? (studentsWithMarks.reduce((sum, s) => sum + s.marks, 0) / studentsWithMarks.length).toFixed(2)
            : 'N/A';

        const averageMarksEl = document.getElementById('averageMarks');
        if (averageMarksEl) {
            averageMarksEl.textContent = averageMarks;
        }

        // Calculate grade distribution
        const gradeDistribution = calculateGradeDistribution(students);
        displayGradeDistribution(gradeDistribution);

    } catch (error) {
        console.error('Failed to load dashboard stats:', error);
        showMessage('Failed to load dashboard statistics', 'error');
    }
}

function calculateGradeDistribution(students) {
    const distribution = {
        'A+': 0,
        'A': 0,
        'B+': 0,
        'B': 0,
        'C+': 0,
        'C': 0,
        'F': 0
    };

    students.forEach(student => {
        if (student.grade) {
            distribution[student.grade] = (distribution[student.grade] || 0) + 1;
        }
    });

    return distribution;
}

function displayGradeDistribution(distribution) {
    const container = document.getElementById('gradeDistribution');
    if (!container) return;

    container.innerHTML = '';

    Object.entries(distribution).forEach(([grade, count]) => {
        const gradeItem = document.createElement('div');
        gradeItem.className = 'grade-item';
        gradeItem.innerHTML = `<span><strong>${grade}</strong></span><span>${count} students</span>`;
        container.appendChild(gradeItem);
    });
}

function showMessage(message, type = 'success') {
    const messageEl = document.getElementById('message');
    if (messageEl) {
        messageEl.textContent = message;
        messageEl.className = `message ${type}`;
        setTimeout(() => {
            messageEl.className = 'message';
        }, 5000);
    }
}

