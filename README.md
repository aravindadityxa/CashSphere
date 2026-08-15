# CashSphere

Enterprise Treasury & Cash Management Platform for corporate finance and treasury teams.

CashSphere is a secure web application designed to help organizations manage corporate cash positions, liquidity, accounts, payment operations, approval workflows, and treasury analytics from a centralized platform.

## Overview

CashSphere provides a unified platform for corporate treasury operations, enabling finance teams to monitor liquidity, manage corporate accounts, control payment workflows, and analyze cash movements.

The platform follows a layered enterprise architecture with a Spring Boot backend, React frontend, and MySQL database.

## Key Features

### Authentication & Security
- JWT-based authentication
- Refresh token support
- BCrypt password encryption
- Role-based access control
- Secure REST APIs
- Input validation
- Global exception handling

### Corporate Management
- Company management
- Corporate account management
- Account balance monitoring
- Account history
- Account status management

### Treasury Management
- Current cash position
- Available liquidity
- Working capital monitoring
- Cash flow analysis
- Liquidity forecasting
- Treasury KPIs

### Payment Management
- Payment creation
- Payment scheduling
- Payment status tracking
- Beneficiary management
- Payment approval and rejection
- Payment history

### Approval Workflow
- Maker-checker workflow
- Approval queue
- Rejection workflow
- Approval history
- Role-based authorization

### Analytics & Reporting
- Cash position analytics
- Liquidity analytics
- Payment trends
- Monthly analytics
- Yearly analytics
- Treasury reports
- Financial reports

### Audit & Notifications
- User activity tracking
- Login history
- Payment audit trail
- Approval history
- Payment notifications
- Liquidity alerts
- Large transaction alerts

## Architecture

CashSphere follows an enterprise layered architecture:

```text
┌──────────────────────────────────────────────┐
│                 React Frontend               │
│       Vite · Tailwind · React Router         │
└──────────────────────┬───────────────────────┘
                       │ REST API
                       ▼
┌──────────────────────────────────────────────┐
│              Spring Boot Backend             │
├──────────────────────────────────────────────┤
│ Controllers                                   │
│      ↓                                        │
│ Services                                      │
│      ↓                                        │
│ Repositories                                  │
│      ↓                                        │
│ JPA / Hibernate                               │
└──────────────────────┬───────────────────────┘
                       │
                       ▼
┌──────────────────────────────────────────────┐
│                    MySQL                     │
│              cashsphere database             │
└──────────────────────────────────────────────┘
