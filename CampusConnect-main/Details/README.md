# CampusConnect - Student Management System

A comprehensive Spring Boot backend application for educational institutions to manage student data, roles, scores, and automated grading.

## Features

- **Student Management**: Full CRUD operations for student records
- **Role-Based Access Control**: Admin, Faculty, and Student roles with JWT authentication
- **Automated Grading**: Database triggers automatically calculate grades based on marks
- **RESTful APIs**: Well-designed REST endpoints with proper HTTP methods and status codes
- **Pagination & Sorting**: Efficient data retrieval with pagination support
- **Input Validation**: Comprehensive validation using Jakarta Bean Validation
- **Global Exception Handling**: Centralized error handling with proper error responses
- **Database Optimization**: Indexed tables and optimized queries for performance

## Tech Stack

- **Java**: 17
- **Spring Boot**: 3.4.1
- **Spring Data JPA**: For database operations
- **Spring Security**: For authentication and authorization
- **JWT**: JSON Web Tokens for stateless authentication
- **MySQL**: Database with triggers for grade automation
- **Maven**: Build and dependency management
- **Lombok**: For reducing boilerplate code
- **ModelMapper**: For DTO to Entity mapping

## Prerequisites

- Java 17 or higher
- Maven 3.6+
- MySQL 8.0+
- IDE (IntelliJ IDEA, Eclipse, or VS Code)

## Database Setup

1. Create MySQL database:
```sql
CREATE DATABASE campusconnect CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

2. Run the schema script located at `src/main/resources/schema.sql` to create tables, indexes, and triggers.

3. The application will automatically create/update tables on startup (configured in `application.properties`).

## Configuration

1. Update `src/main/resources/application.properties`:
   - Set your MySQL username: `spring.datasource.username=your_username`
   - Set your MySQL password: `spring.datasource.password=your_password`
   - Or use environment variables: `DB_USERNAME` and `DB_PASSWORD`

2. JWT Secret (optional):
   - Default secret is provided, but for production, set `JWT_SECRET` environment variable

## Running the Application

1. Clone the repository:
```bash
cd CampusConnect-main/Details
```

2. Build the project:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn spring-boot:run
```

Or run the main class: `com.example.demo.StudentDetailsApplication`

The application will start on `http://localhost:8080`

## API Endpoints

### Authentication

- **POST** `/api/auth/signup` - Register a new user
- **POST** `/api/auth/signin` - Login and get JWT token

### Student Management (Requires Authentication)

- **POST** `/api/students` - Create a new student (Admin, Faculty)
- **GET** `/api/students` - Get all students with pagination (Admin, Faculty)
- **GET** `/api/students/{id}` - Get student by ID (Admin, Faculty, Student)
- **GET** `/api/students/roll/{rollNo}` - Get student by roll number (Admin, Faculty, Student)
- **PUT** `/api/students/{id}` - Update student (Admin, Faculty)
- **DELETE** `/api/students/{id}` - Delete student (Admin only)
- **GET** `/api/students/name/{name}` - Search students by name (Admin, Faculty)
- **GET** `/api/students/department/{department}` - Get students by department with pagination (Admin, Faculty)
- **GET** `/api/students/search?rollNo={rollNo}&name={name}` - Search students (Admin, Faculty)

## Default Users

After running the application with `data.sql`, default users are created:

- **Admin**: 
  - Username: `admin`
  - Password: `admin123`
  
- **Faculty**: 
  - Username: `faculty`
  - Password: `faculty123`

## Grade Calculation

Grades are automatically calculated by database triggers based on marks:

- **A+**: 90-100
- **A**: 80-89
- **B+**: 70-79
- **B**: 60-69
- **C+**: 50-59
- **C**: 40-49
- **F**: Below 40

## Testing with Postman

1. **Register/Login**:
   - POST to `/api/auth/signin` with username and password
   - Copy the JWT token from response

2. **Access Protected Endpoints**:
   - Add header: `Authorization: Bearer {your_jwt_token}`
   - Use appropriate HTTP methods (GET, POST, PUT, DELETE)

3. **Example Request**:
```
POST http://localhost:8080/api/students
Headers:
  Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
  Content-Type: application/json

Body:
{
  "rollNo": 101,
  "name": "John Doe",
  "email": "john.doe@example.com",
  "department": "Computer Science",
  "marks": 85.5
}
```

## Project Structure

```
src/main/java/com/example/demo/
├── Controller/          # REST Controllers
├── Service/            # Business Logic Layer
├── Repository/         # Data Access Layer
├── DTO/                # Data Transfer Objects
├── model/              # Entity Classes
├── security/           # Security Configuration
├── config/             # Configuration Classes
└── exception/          # Exception Handlers
```

## Security

- JWT-based stateless authentication
- Role-based authorization (ADMIN, FACULTY, STUDENT)
- Password encryption using BCrypt
- CORS enabled for cross-origin requests

## Database Schema

- **users**: User accounts for authentication
- **roles**: Available roles (ADMIN, FACULTY, STUDENT)
- **user_roles**: Many-to-many relationship between users and roles
- **student_data**: Student information with indexes on roll_no, email, and department

## Contributing

1. Follow Java coding conventions
2. Use meaningful variable and method names
3. Add proper comments for complex logic
4. Ensure all tests pass before submitting

## License

This project is for educational purposes.

## Support

For issues or questions, please create an issue in the repository.

