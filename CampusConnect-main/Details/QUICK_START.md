# CampusConnect - Quick Start Guide

## Prerequisites
- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Web browser (Chrome, Firefox, Safari, or Edge)

## Backend Setup

1. **Database Setup**
   ```sql
   CREATE DATABASE campusconnect CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
   Run the SQL script: `src/main/resources/schema.sql`

2. **Configure Database**
   Edit `src/main/resources/application.properties`:
   ```properties
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```

3. **Build and Run Backend**
   ```bash
   cd Details
   mvn clean install
   mvn spring-boot:run
   ```
   Backend runs on: `http://localhost:8080`

## Frontend Setup

1. **Start a Local Server**
   
   **Option 1: Python**
   ```bash
   cd frontend
   python -m http.server 8000
   ```

   **Option 2: Node.js (http-server)**
   ```bash
   npm install -g http-server
   cd frontend
   http-server -p 8000
   ```

   **Option 3: PHP**
   ```bash
   cd frontend
   php -S localhost:8000
   ```

2. **Open in Browser**
   Navigate to: `http://localhost:8000`

## Default Login Credentials

- **Admin**: `admin` / `admin123`
- **Faculty**: `faculty` / `faculty123`

## Testing the Application

1. **Login**
   - Open `http://localhost:8000`
   - Login with admin credentials
   - You'll be redirected to the dashboard

2. **View Students**
   - Click "View All Students"
   - See the student list with pagination

3. **Add Student**
   - Click "Add New Student"
   - Fill in the form
   - Marks will automatically calculate grade (via database trigger)

4. **Edit Student**
   - Click "Edit" on any student
   - Update information
   - Grade recalculates automatically when marks change

5. **Delete Student** (Admin only)
   - Click "Delete" on any student
   - Confirm deletion

## API Endpoints

All endpoints require JWT authentication (except `/api/auth/signin`):

- `POST /api/auth/signin` - Login
- `GET /api/students` - List all students (paginated)
- `GET /api/students/{id}` - Get student by ID
- `POST /api/students` - Create student
- `PUT /api/students/{id}` - Update student
- `DELETE /api/students/{id}` - Delete student
- `GET /api/students/search?rollNo=X&name=Y` - Search students

## Troubleshooting

### Backend won't start
- Check MySQL is running
- Verify database credentials in `application.properties`
- Ensure port 8080 is not in use

### Frontend can't connect to backend
- Verify backend is running on `http://localhost:8080`
- Check browser console for CORS errors
- Ensure API base URL in `frontend/js/config.js` is correct

### Login fails
- Check backend logs for errors
- Verify default users exist in database (run `data.sql`)
- Check JWT token is being stored in localStorage

### Grade not calculating
- Verify database triggers are created (run `schema.sql`)
- Check marks field is being set
- Verify trigger exists: `SHOW TRIGGERS;` in MySQL

## Project Structure

```
CampusConnect-main/
└── Details/
    ├── src/main/java/          # Backend Java code
    ├── src/main/resources/     # Configuration files
    ├── frontend/                # Frontend HTML/CSS/JS
    │   ├── index.html
    │   ├── dashboard.html
    │   ├── students.html
    │   ├── student-form.html
    │   ├── css/
    │   └── js/
    └── pom.xml
```

## Next Steps

- Add more students via the UI
- Test different user roles (Admin, Faculty, Student)
- Explore the dashboard statistics
- Test search and filtering features

