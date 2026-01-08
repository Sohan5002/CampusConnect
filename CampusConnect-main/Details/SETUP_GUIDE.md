# CampusConnect Setup Guide

## Quick Start

### 1. Database Setup

Execute the SQL script to create the database schema:

```bash
mysql -u root -p < src/main/resources/schema.sql
```

Or manually run the SQL commands in `src/main/resources/schema.sql` using MySQL Workbench or command line.

### 2. Configuration

Edit `src/main/resources/application.properties`:

```properties
# Update these values
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
```

Or set environment variables:
```bash
export DB_USERNAME=your_username
export DB_PASSWORD=your_password
```

### 3. Build and Run

```bash
# Build the project
mvn clean install

# Run the application
mvn spring-boot:run
```

### 4. Test Authentication

**Login as Admin:**
```bash
curl -X POST http://localhost:8080/api/auth/signin \
  -H "Content-Type: application/json" \
  -d '{"username":"admin","password":"admin123"}'
```

Copy the `token` from the response.

### 5. Test Student API

**Create a Student:**
```bash
curl -X POST http://localhost:8080/api/students \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -d '{
    "rollNo": 101,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "department": "Computer Science",
    "marks": 85.5
  }'
```

**Get All Students:**
```bash
curl -X GET "http://localhost:8080/api/students?page=0&size=10" \
  -H "Authorization: Bearer YOUR_JWT_TOKEN"
```

## Postman Collection

Import these endpoints into Postman:

1. **Authentication**
   - POST `/api/auth/signin` - Login
   - POST `/api/auth/signup` - Register

2. **Student Management**
   - GET `/api/students` - List all (with pagination)
   - GET `/api/students/{id}` - Get by ID
   - GET `/api/students/roll/{rollNo}` - Get by roll number
   - POST `/api/students` - Create new
   - PUT `/api/students/{id}` - Update
   - DELETE `/api/students/{id}` - Delete
   - GET `/api/students/name/{name}` - Search by name
   - GET `/api/students/department/{department}` - Filter by department
   - GET `/api/students/search?rollNo=X&name=Y` - Advanced search

## Troubleshooting

### Database Connection Issues
- Ensure MySQL is running
- Verify username/password in `application.properties`
- Check database name is `campusconnect`

### JWT Token Issues
- Token expires after 24 hours (86400000 ms)
- Ensure `Authorization: Bearer {token}` header is included
- Token is obtained from `/api/auth/signin` endpoint

### Grade Not Calculating
- Ensure database triggers are created (run `schema.sql`)
- Check that `marks` field is being set
- Verify trigger exists: `SHOW TRIGGERS;` in MySQL

### Compilation Errors
- Ensure Java 17+ is installed: `java -version`
- Clean and rebuild: `mvn clean install`
- Check IDE is using correct JDK version

## Default Credentials

- **Admin**: admin / admin123
- **Faculty**: faculty / faculty123

**Note**: Change these passwords in production!

## API Response Format

### Success Response
```json
{
  "message": "Student created successfully",
  "data": {
    "id": 1,
    "rollNo": 101,
    "name": "John Doe",
    "email": "john.doe@example.com",
    "department": "Computer Science",
    "marks": 85.5,
    "grade": "A"
  }
}
```

### Error Response
```json
{
  "timestamp": "2024-01-01T12:00:00",
  "status": 400,
  "error": "Bad Request",
  "message": "Student with roll number 101 already exists"
}
```

## Grade Scale

- A+: 90-100
- A: 80-89
- B+: 70-79
- B: 60-69
- C+: 50-59
- C: 40-49
- F: Below 40

Grades are automatically calculated by database triggers when marks are inserted or updated.

