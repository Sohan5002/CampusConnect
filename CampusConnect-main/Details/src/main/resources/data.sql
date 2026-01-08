-- Initial Data for CampusConnect
-- This file is executed automatically by Spring Boot if spring.jpa.defer-datasource-initialization=true

-- Insert default roles (if not exists)
INSERT IGNORE INTO roles (name) VALUES ('ADMIN'), ('FACULTY'), ('STUDENT');

-- Insert default admin user (password: admin123)
-- Password is bcrypt encoded for "admin123"
INSERT IGNORE INTO users (username, email, password, enabled) 
VALUES ('admin', 'admin@campusconnect.edu', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iwy7pK8O', TRUE);

-- Assign ADMIN role to admin user
INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'admin' AND r.name = 'ADMIN';

-- Insert sample faculty user (password: faculty123)
INSERT IGNORE INTO users (username, email, password, enabled) 
VALUES ('faculty', 'faculty@campusconnect.edu', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iwy7pK8O', TRUE);

-- Assign FACULTY role to faculty user
INSERT IGNORE INTO user_roles (user_id, role_id)
SELECT u.id, r.id FROM users u, roles r WHERE u.username = 'faculty' AND r.name = 'FACULTY';

