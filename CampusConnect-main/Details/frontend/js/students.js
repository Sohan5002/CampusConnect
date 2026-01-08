// Students list page functionality

let currentPage = 0;
let currentPageSize = 10;
let currentSortBy = 'id';
let currentSortDir = 'ASC';
let totalPages = 1;
let departments = [];

document.addEventListener('DOMContentLoaded', async () => {
    if (!requireAuth()) return;

    // Show/hide add button based on role
    const addStudentBtn = document.getElementById('addStudentBtn');
    if (addStudentBtn) {
        addStudentBtn.style.display = canModifyStudents() ? 'inline-block' : 'none';
        addStudentBtn.addEventListener('click', () => {
            window.location.href = 'student-form.html';
        });
    }

    // Setup search
    const searchBtn = document.getElementById('searchBtn');
    const searchInput = document.getElementById('searchInput');
    
    if (searchBtn) {
        searchBtn.addEventListener('click', handleSearch);
    }
    
    if (searchInput) {
        searchInput.addEventListener('keypress', (e) => {
            if (e.key === 'Enter') {
                handleSearch();
            }
        });
    }

    // Load departments for filter
    await loadDepartments();
    
    // Load students
    await loadStudents();
});

async function loadDepartments() {
    try {
        const response = await apiRequest(
            `${API_CONFIG.STUDENTS_ENDPOINT}?page=0&size=1000`
        );
        
        const students = response.data || [];
        const uniqueDepartments = [...new Set(students.map(s => s.department).filter(Boolean))];
        departments = uniqueDepartments.sort();

        const departmentFilter = document.getElementById('departmentFilter');
        if (departmentFilter) {
            uniqueDepartments.forEach(dept => {
                const option = document.createElement('option');
                option.value = dept;
                option.textContent = dept;
                departmentFilter.appendChild(option);
            });
        }
    } catch (error) {
        console.error('Failed to load departments:', error);
    }
}

async function loadStudents(page = 0, size = currentPageSize, sortBy = currentSortBy, sortDir = currentSortDir) {
    try {
        showLoading();

        const departmentFilter = document.getElementById('departmentFilter')?.value;
        let response;

        if (departmentFilter) {
            response = await apiRequest(
                `${API_CONFIG.STUDENTS_ENDPOINT}/department/${encodeURIComponent(departmentFilter)}?page=${page}&size=${size}`
            );
        } else {
            response = await apiRequest(
                `${API_CONFIG.STUDENTS_ENDPOINT}?page=${page}&size=${size}&sortBy=${sortBy}&sortDir=${sortDir}`
            );
        }

        const students = response.data || [];
        totalPages = response.totalPages || 1;
        currentPage = response.currentPage || 0;

        displayStudents(students);
        updatePagination();

    } catch (error) {
        console.error('Failed to load students:', error);
        showMessage('Failed to load students: ' + error.message, 'error');
        showError();
    }
}

function displayStudents(students) {
    const tbody = document.getElementById('studentsTableBody');
    if (!tbody) return;

    if (students.length === 0) {
        tbody.innerHTML = '<tr><td colspan="7" class="loading">No students found</td></tr>';
        return;
    }

    tbody.innerHTML = students.map(student => `
        <tr>
            <td>${student.rollNo || '-'}</td>
            <td>${student.name || '-'}</td>
            <td>${student.email || '-'}</td>
            <td>${student.department || '-'}</td>
            <td>${student.marks !== null && student.marks !== undefined ? student.marks : '-'}</td>
            <td><strong>${student.grade || '-'}</strong></td>
            <td>
                ${canModifyStudents() ? `
                    <button class="btn btn-primary" onclick="editStudent(${student.id})" style="padding: 0.5rem 1rem; font-size: 0.875rem;">Edit</button>
                ` : ''}
                ${canDeleteStudents() ? `
                    <button class="btn btn-danger" onclick="deleteStudent(${student.id})" style="padding: 0.5rem 1rem; font-size: 0.875rem;">Delete</button>
                ` : ''}
            </td>
        </tr>
    `).join('');
}

function showLoading() {
    const tbody = document.getElementById('studentsTableBody');
    if (tbody) {
        tbody.innerHTML = '<tr><td colspan="7" class="loading">Loading students...</td></tr>';
    }
}

function showError() {
    const tbody = document.getElementById('studentsTableBody');
    if (tbody) {
        tbody.innerHTML = '<tr><td colspan="7" class="loading">Error loading students</td></tr>';
    }
}

function updatePagination() {
    const prevBtn = document.getElementById('prevPageBtn');
    const nextBtn = document.getElementById('nextPageBtn');
    const pageInfo = document.getElementById('pageInfo');

    if (prevBtn) {
        prevBtn.disabled = currentPage === 0;
        prevBtn.onclick = () => {
            if (currentPage > 0) {
                loadStudents(currentPage - 1);
            }
        };
    }

    if (nextBtn) {
        nextBtn.disabled = currentPage >= totalPages - 1;
        nextBtn.onclick = () => {
            if (currentPage < totalPages - 1) {
                loadStudents(currentPage + 1);
            }
        };
    }

    if (pageInfo) {
        pageInfo.textContent = `Page ${currentPage + 1} of ${totalPages}`;
    }
}

async function handleSearch() {
    const searchInput = document.getElementById('searchInput');
    const searchTerm = searchInput?.value.trim();

    if (!searchTerm) {
        await loadStudents();
        return;
    }

    try {
        showLoading();

        // Try to parse as roll number
        const rollNo = parseInt(searchTerm);
        let response;

        if (!isNaN(rollNo)) {
            // Search by roll number and name
            response = await apiRequest(
                `${API_CONFIG.STUDENTS_ENDPOINT}/search?rollNo=${rollNo}&name=${encodeURIComponent(searchTerm)}`
            );
        } else {
            // Search by name only
            response = await apiRequest(
                `${API_CONFIG.STUDENTS_ENDPOINT}/name/${encodeURIComponent(searchTerm)}`
            );
        }

        const students = Array.isArray(response) ? response : (response.data || []);
        displayStudents(students);
        
        // Update pagination for search results
        totalPages = 1;
        currentPage = 0;
        updatePagination();

    } catch (error) {
        console.error('Search failed:', error);
        showMessage('Search failed: ' + error.message, 'error');
        showError();
    }
}

async function editStudent(id) {
    window.location.href = `student-form.html?id=${id}`;
}

async function deleteStudent(id) {
    if (!confirm('Are you sure you want to delete this student?')) {
        return;
    }

    try {
        await apiRequest(`${API_CONFIG.STUDENTS_ENDPOINT}/${id}`, {
            method: 'DELETE'
        });

        showMessage('Student deleted successfully', 'success');
        await loadStudents(currentPage);

    } catch (error) {
        console.error('Delete failed:', error);
        showMessage('Failed to delete student: ' + error.message, 'error');
    }
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

// Make functions available globally
window.editStudent = editStudent;
window.deleteStudent = deleteStudent;

