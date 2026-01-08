-- CampusConnect Database Schema
-- Create database
CREATE DATABASE IF NOT EXISTS campusconnect CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE campusconnect;

-- Drop tables if they exist (in reverse order of dependencies)
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS student_data;

-- Create roles table
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(20) NOT NULL UNIQUE,
    INDEX idx_role_name (name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    enabled BOOLEAN NOT NULL DEFAULT TRUE,
    INDEX idx_username (username),
    INDEX idx_email (email)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create user_roles junction table
CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    INDEX idx_user_id (user_id),
    INDEX idx_role_id (role_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Create student_data table
CREATE TABLE student_data (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    roll_no INT NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    department VARCHAR(100) NOT NULL,
    marks DOUBLE,
    grade VARCHAR(2),
    date_of_birth DATE,
    gender VARCHAR(10),
    contact_number VARCHAR(15) UNIQUE,
    home_address VARCHAR(255),
    enrollment_date DATE,
    program VARCHAR(100),
    father_name VARCHAR(100),
    mother_name VARCHAR(100),
    INDEX idx_roll_no (roll_no),
    INDEX idx_email (email),
    INDEX idx_department (department)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Insert default roles
INSERT INTO roles (name) VALUES ('ADMIN'), ('FACULTY'), ('STUDENT');

-- Trigger to automatically calculate grade based on marks
DELIMITER $$

CREATE TRIGGER calculate_grade_before_insert
BEFORE INSERT ON student_data
FOR EACH ROW
BEGIN
    IF NEW.marks IS NOT NULL THEN
        IF NEW.marks >= 90 THEN
            SET NEW.grade = 'A+';
        ELSEIF NEW.marks >= 80 THEN
            SET NEW.grade = 'A';
        ELSEIF NEW.marks >= 70 THEN
            SET NEW.grade = 'B+';
        ELSEIF NEW.marks >= 60 THEN
            SET NEW.grade = 'B';
        ELSEIF NEW.marks >= 50 THEN
            SET NEW.grade = 'C+';
        ELSEIF NEW.marks >= 40 THEN
            SET NEW.grade = 'C';
        ELSE
            SET NEW.grade = 'F';
        END IF;
    END IF;
END$$

CREATE TRIGGER calculate_grade_before_update
BEFORE UPDATE ON student_data
FOR EACH ROW
BEGIN
    IF NEW.marks IS NOT NULL AND (OLD.marks IS NULL OR NEW.marks != OLD.marks) THEN
        IF NEW.marks >= 90 THEN
            SET NEW.grade = 'A+';
        ELSEIF NEW.marks >= 80 THEN
            SET NEW.grade = 'A';
        ELSEIF NEW.marks >= 70 THEN
            SET NEW.grade = 'B+';
        ELSEIF NEW.marks >= 60 THEN
            SET NEW.grade = 'B';
        ELSEIF NEW.marks >= 50 THEN
            SET NEW.grade = 'C+';
        ELSEIF NEW.marks >= 40 THEN
            SET NEW.grade = 'C';
        ELSE
            SET NEW.grade = 'F';
        END IF;
    END IF;
END$$

DELIMITER ;

