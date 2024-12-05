-- Create the database
CREATE DATABASE prodcat_db;

-- Use the database
USE prodcat_db;

-- Table `categories`
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(100) NOT NULL,
                            description TEXT
);

-- Table `products`
CREATE TABLE products (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          designation VARCHAR(100) NOT NULL,
                          price DOUBLE NOT NULL,
                          quantity INT NOT NULL,
                          category_id INT NOT NULL,
                          CONSTRAINT fk_category FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);

-- Enum for roles
CREATE TYPE role_enum AS ENUM ('ADMIN', 'USER');

-- Table `users`
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       login VARCHAR(50) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       active BOOLEAN NOT NULL DEFAULT TRUE,
                       role role_enum NOT NULL
);

-- Some constraints to ensure consistency
ALTER TABLE users
    ADD CONSTRAINT chk_role CHECK (role IN ('ADMIN', 'USER'));

-- Create indexes to optimize searches
CREATE INDEX idx_category_name ON categories (name);
CREATE INDEX idx_product_designation ON products (designation);
