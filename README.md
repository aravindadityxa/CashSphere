# CashSphere - Enterprise Treasury & Cash Management Platform

**CashSphere** is a production-quality enterprise Treasury & Cash Management platform built with modern enterprise technology. Designed for corporate finance and treasury teams, it enables comprehensive management of corporate accounts, cash positions, liquidity, payment workflows, approvals, and treasury analytics.

## Features

### Phase 1: Authentication & Core Infrastructure ✅

#### Authentication Module
- **User Registration** - Create new accounts with email verification
- **Login/Logout** - Secure JWT-based authentication
- **JWT Authentication** - Access and refresh token flow
- **Role-Based Access Control (RBAC)** - Fine-grained authorization
  - **ADMIN** - Full system access
  - **TREASURY_MANAGER** - Operational treasury management
  - **FINANCE_ANALYST** - Reporting and analytics access
  - **CORPORATE_USER** - Standard user access
- **User Profile Management** - View and manage user information
- **Session Management** - Token refresh and revocation

#### Core Infrastructure
- Global exception handling with validation error mapping
- OpenAPI/Swagger documentation
- Health check endpoint
- Spring Security 6 configuration with JWT
- MySQL database with proper schema and relationships

### Phase 2-11: Coming Next
- Company Management
- Corporate Accounts Management
- Treasury Dashboard
- Payment Management
- Approval Workflows
- Liquidity Management
- Financial Reporting
- Analytics
- Notifications
- Audit Logging

## Technology Stack

### Backend
- **Java 21** - Latest LTS version
- **Spring Boot 3.3.3** - Modern Spring stack
- **Spring Security 6** - Authentication and authorization
- **Spring Data JPA** - Data access layer
- **Hibernate** - ORM framework
- **Maven 3.9.8** - Build and dependency management
- **JWT (JJWT 0.12.3)** - Token-based authentication
- **MapStruct** - DTO mapping
- **Lombok** - Code generation
- **JUnit 5 & Mockito** - Testing

### Frontend
- **React 18.3** - UI library
- **Vite 5.4** - Build tool
- **Tailwind CSS 3.4** - Utility-first styling
- **React Router 6** - Client-side routing
- **Axios 1.7** - HTTP client
- **React Hook Form 7.5** - Form handling
- **Recharts 2.12** - Charts and visualizations
- **Lucide React 0.4** - Icons
- **Zustand 4.5** - State management

### Database
- **MySQL 8.3** - Relational database
- **Proper indexing and constraints** - Performance optimization

### Infrastructure
- **Docker** - Containerization
- **Docker Compose** - Multi-container orchestration
- **GitHub Actions** - CI/CD pipeline (coming)

## Project Structure

```
CashSphere/
├── src/
│   ├── main/
│   │   ├── java/com/casksphere/
│   │   │   ├── CashSphereApplication.java
│   │   │   ├── controller/              # REST controllers
│   │   │   ├── security/                # JWT and security
│   │   │   ├── config/                  # Spring configuration
│   │   │   ├── exception/               # Global exception handling
│   │   │   └── module/                  # Business modules
│   │   │       └── auth/                # Authentication module
│   │   │           ├── controller/
│   │   │           ├── service/
│   │   │           ├── repository/
│   │   │           ├── entity/
│   │   │           ├── dto/
│   │   │           └── mapper/
│   │   └── resources/
│   │       ├── application.yml          # Application config
│   │       └── db/migration/            # SQL schemas
│   └── test/
├── frontend/                           # React frontend
│   ├── src/
│   │   ├── pages/                      # Page components
│   │   ├── components/                 # Reusable components
│   │   ├── layouts/                    # Layout components
│   │   ├── api/                        # API client
│   │   ├── store/                      # Zustand state management
│   │   └── App.jsx
│   ├── vite.config.js
│   ├── tailwind.config.js
│   └── package.json
├── docker-compose.yml
├── Dockerfile
└── pom.xml
```

## Installation & Setup

### Prerequisites
- Java 21+ JDK
- Node.js 20+
- MySQL 8.3+ (or use Docker)
- Maven 3.9.8+
- Docker & Docker Compose (optional)

### Option 1: Using Docker Compose (Recommended)

```bash
# Clone the repository
git clone https://github.com/yourusername/casksphere.git
cd casksphere

# Start all services
docker-compose up --build

# Access the application
# Frontend: http://localhost:5173
# Backend API: http://localhost:8080
# API Docs: http://localhost:8080/api/swagger-ui.html
```

### Option 2: Local Development

#### Backend Setup

```bash
# Navigate to project root
cd casksphere

# Create MySQL database
mysql -u root -p
> CREATE DATABASE casksphere_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
> CREATE USER 'casksphere_user'@'localhost' IDENTIFIED BY 'casksphere_user_password';
> GRANT ALL PRIVILEGES ON casksphere_db.* TO 'casksphere_user'@'localhost';

# Build backend
mvn clean install

# Run backend
mvn spring-boot:run
```

#### Frontend Setup

```bash
# Navigate to frontend directory
cd frontend

# Install dependencies
npm install

# Start development server
npm run dev

# Access at http://localhost:5173
```

## API Endpoints

### Authentication
- `POST /api/v1/auth/register` - Register new user
- `POST /api/v1/auth/login` - Login with credentials
- `POST /api/v1/auth/refresh-token` - Refresh access token
- `POST /api/v1/auth/logout` - Logout user
- `GET /api/v1/auth/profile` - Get current user profile

### Health
- `GET /api/health` - Health check endpoint

### API Documentation
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api/v3/api-docs

## Authentication Flow

1. **Registration**: User creates account → JWT tokens generated → Stored in localStorage
2. **Login**: User provides credentials → Authentication validated → JWT tokens issued
3. **API Access**: Bearer token included in Authorization header
4. **Token Refresh**: Expired token → Refresh token validates → New access token issued
5. **Logout**: Refresh tokens revoked → Session cleared

## Database Schema

### Core Tables
- `users` - User accounts
- `roles` - User roles
- `user_roles` - Role assignments
- `refresh_tokens` - Token management

### Indexes
- Username, email, and role lookups optimized
- Refresh token queries optimized for expiry

## Security Features

- **JWT Authentication** - Stateless token-based auth
- **Refresh Tokens** - Extended session management
- **BCrypt Password Hashing** - Secure password storage
- **Role-Based Access Control** - Fine-grained permissions
- **Spring Security 6** - Modern security configuration
- **CSRF Protection** - Cross-site request forgery prevention
- **Secure Headers** - HTTP security headers
- **Input Validation** - Bean Validation framework
- **Exception Handling** - Secure error responses

## Development Guidelines

### Code Quality
- Clean, self-documenting code
- SOLID principles
- Constructor injection for dependencies
- DTOs for API contracts
- MapStruct for entity-to-DTO mapping
- No TODOs or placeholders

### Testing
- JUnit 5 with Mockito
- Unit tests for services
- Integration tests for controllers
- Test coverage for critical paths

### API Design
- RESTful conventions
- Proper HTTP status codes
- Consistent response formats
- Pagination support
- Sorting and filtering
- Search capabilities

## Running Tests

### Backend Tests
```bash
mvn test
```

### Frontend Tests
```bash
cd frontend
npm test
```

## Build & Deployment

### Build Docker Image
```bash
docker build -t casksphere:1.0.0 .
```

### Run Container
```bash
docker run -p 8080:8080 casksphere:1.0.0
```

### Using Docker Compose
```bash
docker-compose up -d
docker-compose logs -f
docker-compose down
```

## Future Enhancements

### Phase 2: Company Management
- CRUD operations for companies
- Company search and filtering
- Company dashboard

### Phase 3: Corporate Accounts
- Account creation and management
- Balance inquiries
- Account history
- Account freezing

### Phase 4: Treasury Dashboard
- Cash position visualization
- Liquidity metrics
- KPI dashboard
- Cash flow charts

### Phase 5: Payment Management
- Payment beneficiary management
- Payment creation and scheduling
- Payment approval workflows
- Payment history

### Phase 6: Approval Workflows
- Maker-Checker pattern
- Approval queue management
- Approval history
- Audit trail

### Phase 7: Liquidity Management
- Cash pool management
- Daily liquidity forecasts
- Liquidity alerts

### Phase 8: Financial Reporting
- Cash flow reports
- Liquidity reports
- Payment reports
- PDF/Excel export

### Phase 9: Analytics
- Monthly analytics
- Yearly analytics
- Payment trends
- Cash trends

### Phase 10: Notifications
- Payment alerts
- Liquidity alerts
- Large transaction alerts

### Phase 11: Audit Logging
- Login audit logs
- Payment audit logs
- Company audit logs
- Approval audit logs

## Contributing

This is a reference implementation for enterprise treasury management. Contributions follow enterprise coding standards.

## License

Proprietary - This project is created as a reference architecture for enterprise applications.

## Support

For issues, feature requests, or questions, please refer to the project documentation or create an issue in the repository.

---

**CashSphere** - Building Enterprise Treasury Excellence
