-- Test data initialization for H2 database

-- Insert predefined roles
INSERT INTO roles (name, description, created_at) VALUES ('ADMIN', 'Administrator role', NOW());
INSERT INTO roles (name, description, created_at) VALUES ('TREASURY_MANAGER', 'Treasury manager role', NOW());
INSERT INTO roles (name, description, created_at) VALUES ('FINANCE_ANALYST', 'Finance analyst role', NOW());
INSERT INTO roles (name, description, created_at) VALUES ('CORPORATE_USER', 'Corporate user role', NOW());
