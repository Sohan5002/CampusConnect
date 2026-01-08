// Student form functionality

document.addEventListener('DOMContentLoaded', async () => {
    if (!requireAuth()) return;

    // Check if user can modify students
    if (!canModifyStudents()) {
        showMessage('You do not have permission to add or edit students', 'error');
        setTimeout(() => {
            window.location.href = 'students.html';
        }, 2000);
        return;
    }

    const form = document.getElementById('studentForm');
    const formTitle = document.getElementById('formTitle');
    const studentId = document.getElementById('studentId');

    // Get student ID from URL
    const urlParams = new URLSearchParams(window.location.search);
    const id = urlParams.get('id');

    if (id) {
        // Edit mode
        formTitle.textContent = 'Edit Student';
        await loadStudent(id);
    } else {
        // Add mode
        formTitle.textContent = 'Add Student';
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        await saveStudent();
    });
});

async function loadStudent(id) {
    try {
        const student = await apiRequest(`${API_CONFIG.STUDENTS_ENDPOINT}/${id}`);
        
        // Populate form
        document.getElementById('studentId').value = student.id;
        document.getElementById('rollNo').value = student.rollNo || '';
        document.getElementById('name').value = student.name || '';
        document.getElementById('email').value = student.email || '';
        document.getElementById('department').value = student.department || '';
        document.getElementById('marks').value = student.marks || '';
        // Grade is read-only, displayed for reference
        if (student.grade) {
            const marksGroup = document.getElementById('marks').parentElement;
            let gradeDisplay = marksGroup.querySelector('.grade-display');
            if (!gradeDisplay) {
                gradeDisplay = document.createElement('div');
                gradeDisplay.className = 'grade-display';
                gradeDisplay.style.cssText = 'margin-top: 0.5rem; color: var(--primary-color); font-weight: 500;';
                marksGroup.appendChild(gradeDisplay);
            }
            gradeDisplay.textContent = `Current Grade: ${student.grade}`;
        }
        document.getElementById('gender').value = student.gender || '';
        document.getElementById('contactNumber').value = student.contactNumber || '';
        document.getElementById('homeAddress').value = student.homeAddress || '';
        document.getElementById('program').value = student.program || '';
        document.getElementById('fatherName').value = student.fatherName || '';
        document.getElementById('motherName').value = student.motherName || '';

        // Format dates
        if (student.dateOfBirth) {
            document.getElementById('dateOfBirth').value = student.dateOfBirth.split('T')[0];
        }
        if (student.enrollmentDate) {
            document.getElementById('enrollmentDate').value = student.enrollmentDate.split('T')[0];
        }

    } catch (error) {
        console.error('Failed to load student:', error);
        showMessage('Failed to load student: ' + error.message, 'error');
        setTimeout(() => {
            window.location.href = 'students.html';
        }, 2000);
    }
}

async function saveStudent() {
    const errorMessage = document.getElementById('errorMessage');
    errorMessage.textContent = '';
    errorMessage.classList.remove('show');

    const studentId = document.getElementById('studentId').value;
    const formData = {
        rollNo: parseInt(document.getElementById('rollNo').value),
        name: document.getElementById('name').value,
        email: document.getElementById('email').value,
        department: document.getElementById('department').value,
        marks: document.getElementById('marks').value ? parseFloat(document.getElementById('marks').value) : null,
        gender: document.getElementById('gender').value || null,
        contactNumber: document.getElementById('contactNumber').value || null,
        homeAddress: document.getElementById('homeAddress').value || null,
        program: document.getElementById('program').value || null,
        fatherName: document.getElementById('fatherName').value || null,
        motherName: document.getElementById('motherName').value || null,
        dateOfBirth: document.getElementById('dateOfBirth').value || null,
        enrollmentDate: document.getElementById('enrollmentDate').value || null
    };

    // Remove null/empty values
    Object.keys(formData).forEach(key => {
        if (formData[key] === null || formData[key] === '') {
            delete formData[key];
        }
    });

    try {
        let response;
        if (studentId) {
            // Update existing student
            response = await apiRequest(`${API_CONFIG.STUDENTS_ENDPOINT}/${studentId}`, {
                method: 'PUT',
                body: JSON.stringify(formData)
            });
        } else {
            // Create new student
            response = await apiRequest(API_CONFIG.STUDENTS_ENDPOINT, {
                method: 'POST',
                body: JSON.stringify(formData)
            });
        }

        showMessage(response.message || (studentId ? 'Student updated successfully' : 'Student created successfully'), 'success');
        
        setTimeout(() => {
            window.location.href = 'students.html';
        }, 1500);

    } catch (error) {
        console.error('Save failed:', error);
        const errorMsg = error.message || 'Failed to save student';
        errorMessage.textContent = errorMsg;
        errorMessage.classList.add('show');
    }
}

function showMessage(message, type = 'success') {
    // Create message element if it doesn't exist
    let messageEl = document.getElementById('formMessage');
    if (!messageEl) {
        messageEl = document.createElement('div');
        messageEl.id = 'formMessage';
        messageEl.className = 'message';
        const form = document.getElementById('studentForm');
        form.insertBefore(messageEl, form.firstChild);
    }

    messageEl.textContent = message;
    messageEl.className = `message ${type}`;
    setTimeout(() => {
        messageEl.className = 'message';
    }, 5000);
}

