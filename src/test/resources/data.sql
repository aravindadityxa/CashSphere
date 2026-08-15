-- Test data initialization for H2 database

-- Insert predefined roles
INSERT INTO roles (name, description) VALUES ('ADMIN', 'Administrator role');
INSERT INTO roles (name, description) VALUES ('TREASURY_MANAGER', 'Treasury manager role');
INSERT INTO roles (name, description) VALUES ('FINANCE_ANALYST', 'Finance analyst role');
INSERT INTO roles (name, description) VALUES ('CORPORATE_USER', 'Corporate user role');
