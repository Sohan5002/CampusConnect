# CampusConnect Frontend

A clean, simple frontend for the CampusConnect Student Management System.

## Features

- **JWT Authentication**: Secure login with token-based authentication
- **Role-Based UI**: Different interfaces for Admin, Faculty, and Student roles
- **Student Management**: Full CRUD operations for student records
- **Real-time Data**: All data fetched from backend REST APIs
- **Dashboard**: Statistics and grade distribution
- **Responsive Design**: Works on desktop and mobile devices

## Technology Stack

- **HTML5**: Semantic markup
- **CSS3**: Modern styling with CSS variables
- **Vanilla JavaScript**: No frameworks, pure JavaScript
- **Fetch API**: For backend communication

## File Structure

```
frontend/
├── index.html          # Login page
├── dashboard.html      # Dashboard with statistics
├── students.html       # Student list with pagination
├── student-form.html   # Add/Edit student form
├── css/
│   └── style.css       # All styles
└── js/
    ├── config.js       # API configuration and auth utilities
    ├── auth.js         # Authentication functions
    ├── login.js        # Login page logic
    ├── dashboard.js    # Dashboard statistics
    ├── students.js     # Student list management
    └── student-form.js # Student form handling
```

## Setup

1. Ensure the Spring Boot backend is running on `http://localhost:8080`

2. Open `index.html` in a web browser or use a local server:

```bash
# Using Python
cd frontend
python -m http.server 8000

# Using Node.js (http-server)
npx http-server frontend -p 8000

# Using PHP
php -S localhost:8000 -t frontend
```

3. Navigate to `http://localhost:8000` in your browser

## Default Credentials

- **Admin**: 
  - Username: `admin`
  - Password: `admin123`

- **Faculty**: 
  - Username: `faculty`
  - Password: `faculty123`

## Usage

### Login
1. Enter username and password
2. Click "Login"
3. JWT token is stored in localStorage
4. Redirected to dashboard

### Dashboard
- View total students count
- See average marks
- View grade distribution
- Navigate to student list

### Student List
- View all students in a table
- Search by name or roll number
- Filter by department
- Pagination support
- Edit/Delete buttons (based on role)

### Add/Edit Student
- Fill in student information
- Marks automatically calculate grade (via backend trigger)
- Form validation
- Success/error messages

## Role Permissions

### Admin
- Full access to all features
- Can create, read, update, and delete students
- Can view all statistics

### Faculty
- Can create, read, and update students
- Can update marks (grade auto-calculated)
- Cannot delete students
- Can view all statistics

### Student
- Read-only access
- Can view student list
- Cannot modify any data

## API Integration

All API calls are made to:
- Base URL: `http://localhost:8080/api`
- Authentication: JWT token in `Authorization: Bearer {token}` header
- CORS: Enabled on backend for all origins

## Browser Compatibility

- Chrome/Edge (latest)
- Firefox (latest)
- Safari (latest)
- Mobile browsers

## Notes

- JWT tokens are stored in localStorage
- Tokens expire after 24 hours (backend configuration)
- All data is fetched from backend - no mock data
- Error handling for network failures
- Automatic redirect to login on 401 errors

