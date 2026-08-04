# CashSphere Documentation Index

Complete guide to CashSphere documentation and resources.

## Quick Navigation

### 🚀 Getting Started
- **[QUICK_START.md](QUICK_START.md)** - 5-minute setup guide (Docker or Local)
- **[README.md](README.md)** - Project overview and features

### 📚 For Developers
- **[DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md)** - Step-by-step guide for adding features
- **[ARCHITECTURE.md](ARCHITECTURE.md)** - Deep dive into system architecture

### 🏗️ Infrastructure
- **[docker-compose.yml](docker-compose.yml)** - Docker Compose configuration
- **[Dockerfile](Dockerfile)** - Multi-stage Docker build
- **[.github/workflows/ci.yml](.github/workflows/ci.yml)** - GitHub Actions CI/CD

### 📋 Project Info
- **[COMPLETION_REPORT.md](COMPLETION_REPORT.md)** - Phase 1 completion details
- **[MAINTENANCE.md](MAINTENANCE.md)** - Ongoing maintenance guide

---

## Documentation by Role

### 👨‍💼 Project Manager / Tech Lead
Start here:
1. [README.md](README.md) - Project overview
2. [COMPLETION_REPORT.md](COMPLETION_REPORT.md) - What's been built
3. [ARCHITECTURE.md](ARCHITECTURE.md) - System design

### 👨‍💻 Backend Developer
Start here:
1. [QUICK_START.md](QUICK_START.md) - Get backend running
2. [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md) - Backend module creation
3. [ARCHITECTURE.md](ARCHITECTURE.md) - System patterns

Key files to review:
- `src/main/java/com/casksphere/module/auth/` - Authentication module example
- `pom.xml` - Maven dependencies
- `src/main/resources/application.yml` - Configuration

### 👩‍💻 Frontend Developer
Start here:
1. [QUICK_START.md](QUICK_START.md) - Get frontend running
2. [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md) - React page creation
3. [ARCHITECTURE.md](ARCHITECTURE.md) - Frontend architecture

Key files to review:
- `frontend/src/pages/` - Page components
- `frontend/src/api/` - API integration
- `frontend/src/store/` - State management

### 🔧 DevOps / Infrastructure
Start here:
1. [docker-compose.yml](docker-compose.yml) - Container setup
2. [.github/workflows/ci.yml](.github/workflows/ci.yml) - CI/CD pipeline
3. [MAINTENANCE.md](MAINTENANCE.md) - Operations guide

### 🛡️ Security / QA
Start here:
1. [ARCHITECTURE.md](ARCHITECTURE.md) - Security implementation
2. [COMPLETION_REPORT.md](COMPLETION_REPORT.md) - Security features
3. [MAINTENANCE.md](MAINTENANCE.md) - Security maintenance

---

## Documentation by Topic

### Authentication & Security
- [QUICK_START.md](QUICK_START.md#test-credentials) - Default test credentials
- [ARCHITECTURE.md](ARCHITECTURE.md#security-architecture) - JWT flow
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#add-authorization-check) - Adding authorization
- [README.md](README.md#security-features) - Security features list

### Backend Development
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#backend-adding-a-new-module) - Create new module
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#step-2-create-repository) - Repository pattern
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#testing-guidelines) - Writing tests
- [ARCHITECTURE.md](ARCHITECTURE.md#design-patterns) - Design patterns

### Frontend Development
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#frontend-adding-a-new-page) - Create new page
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#step-1-create-api-client) - API integration
- [ARCHITECTURE.md](ARCHITECTURE.md#frontend-architecture) - Frontend patterns

### Deployment & Infrastructure
- [QUICK_START.md](QUICK_START.md#option-1-docker-compose-fastest) - Docker Compose
- [README.md](README.md#docker) - Docker guide
- [MAINTENANCE.md](MAINTENANCE.md#upgrading-casksphere) - Upgrades
- [.github/workflows/ci.yml](.github/workflows/ci.yml) - CI/CD pipeline

### Database
- [ARCHITECTURE.md](ARCHITECTURE.md#database-design) - Schema design
- [MAINTENANCE.md](MAINTENANCE.md#database-optimization) - Optimization
- [MAINTENANCE.md](MAINTENANCE.md#backup--recovery) - Backups

### Testing
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#testing-guidelines) - Test patterns
- [COMPLETION_REPORT.md](COMPLETION_REPORT.md#testing-summary) - Test cases
- [QUICK_START.md](QUICK_START.md#useful-commands) - Running tests

### Troubleshooting
- [QUICK_START.md](QUICK_START.md#troubleshooting) - Common issues
- [MAINTENANCE.md](MAINTENANCE.md#troubleshooting-guide) - Troubleshooting
- [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#common-issues--solutions) - Developer issues

---

## File Structure

### Root Files
```
├── README.md              ← Start here
├── QUICK_START.md         ← 5-minute setup
├── ARCHITECTURE.md        ← System design
├── DEVELOPER_GUIDE.md     ← Feature development
├── COMPLETION_REPORT.md   ← Project status
├── MAINTENANCE.md         ← Operations guide
├── INDEX.md              ← This file
├── LICENSE               ← MIT License
├── .env.example          ← Environment template
├── .gitignore            ← Git ignore rules
```

### Code Directories
```
├── src/                   ← Backend code
│   ├── main/java/        ← Application source
│   └── test/java/        ← Test code
├── frontend/             ← React frontend
│   └── src/
└── .github/workflows/    ← CI/CD pipelines
```

### Configuration
```
├── pom.xml               ← Maven configuration
├── docker-compose.yml    ← Docker orchestration
├── Dockerfile            ← Container build
└── frontend/
    ├── vite.config.js
    ├── tailwind.config.js
    └── package.json
```

---

## Key Features by Phase

### ✅ Phase 1: Authentication & Core (COMPLETE)
- User registration and login
- JWT authentication
- Role-based access control
- User profile management
- Token refresh workflow
- Global exception handling
- API documentation

### 📅 Phase 2: Company Management (Planned)
- CRUD operations for companies
- Company search and filtering
- Company dashboard

### 📅 Phase 3-11: Additional Modules (Planned)
- Corporate Accounts
- Treasury Dashboard
- Payment Management
- Approval Workflows
- Liquidity Management
- Financial Reports
- Analytics
- Notifications
- Audit Logging

---

## Technology Stack Reference

### Backend
- **Java 21** - Programming language
- **Spring Boot 3.3.3** - Framework
- **Spring Security 6** - Authentication
- **Spring Data JPA** - Database access
- **Hibernate** - ORM
- **MySQL 8.3** - Database
- **JUnit 5** - Testing
- **Mockito** - Mocking

### Frontend
- **React 18.3** - UI library
- **Vite 5.4** - Build tool
- **Tailwind CSS 3.4** - Styling
- **React Router 6** - Navigation
- **Axios 1.7** - HTTP client
- **Zustand 4.5** - State management

### Infrastructure
- **Docker** - Containerization
- **Docker Compose** - Orchestration
- **GitHub Actions** - CI/CD
- **Maven 3.9.8** - Build tool
- **npm** - Package manager

---

## Common Development Workflows

### Setting up for the first time
1. Read [README.md](README.md)
2. Follow [QUICK_START.md](QUICK_START.md)
3. Review [ARCHITECTURE.md](ARCHITECTURE.md)

### Adding a new backend feature
1. Read [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#backend-adding-a-new-module)
2. Follow the step-by-step module creation
3. Write tests using the patterns shown
4. Follow code quality standards

### Adding a new frontend page
1. Read [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md#frontend-adding-a-new-page)
2. Create API client first
3. Build page component
4. Add route to App.jsx
5. Test and style

### Deploying to production
1. Review [MAINTENANCE.md](MAINTENANCE.md#upgrading-casksphere)
2. Tag release with version
3. Use Docker for deployment
4. Verify with smoke tests

### Troubleshooting issues
1. Check [QUICK_START.md](QUICK_START.md#troubleshooting)
2. Review [MAINTENANCE.md](MAINTENANCE.md#troubleshooting-guide)
3. Check logs with Docker
4. Search for similar issues

---

## API Documentation

### Live Documentation
- **Swagger UI**: http://localhost:8080/api/swagger-ui.html (when running)
- **OpenAPI JSON**: http://localhost:8080/api/v3/api-docs

### Documentation Files
- [README.md](README.md#api-endpoints) - Endpoint list
- [ARCHITECTURE.md](ARCHITECTURE.md#data-flow) - Data flow diagrams

---

## Important Links

### External Resources
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [Tailwind CSS](https://tailwindcss.com)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [Docker Documentation](https://docs.docker.com/)

### Internal Navigation
- Backend code: `src/main/java/com/casksphere/`
- Frontend code: `frontend/src/`
- Tests: `src/test/java/com/casksphere/`
- Database schema: `src/main/resources/db/migration/`

---

## Version & Release Info

**Current Version**: 1.0.0  
**Release Date**: August 4, 2026  
**Phase**: 1 - Authentication & Core Infrastructure  
**Status**: Production Ready

### Release History
- **v1.0.0** - Initial release with authentication and core infrastructure

---

## Getting Help

### Documentation
1. Check the relevant documentation file above
2. Search for your topic in the documentation index
3. Review code examples in DEVELOPER_GUIDE.md

### Common Questions
- **How do I run the project?** → See [QUICK_START.md](QUICK_START.md)
- **How do I add a new feature?** → See [DEVELOPER_GUIDE.md](DEVELOPER_GUIDE.md)
- **What's the architecture?** → See [ARCHITECTURE.md](ARCHITECTURE.md)
- **How do I deploy?** → See [MAINTENANCE.md](MAINTENANCE.md)

### Support
- GitHub Issues: Create an issue for bugs or features
- Documentation: File an issue to improve docs
- Security: Report security issues to security@casksphere.com

---

## Document Map

```
Documentation Structure:
├── README.md           (Project Overview)
├── QUICK_START.md      (Setup Guide)
├── ARCHITECTURE.md     (System Design)
├── DEVELOPER_GUIDE.md  (Development Reference)
├── COMPLETION_REPORT.md (Project Status)
├── MAINTENANCE.md      (Operations Guide)
└── INDEX.md            (This File)

Code Structure:
├── Backend
│   ├── Controllers (API Endpoints)
│   ├── Services (Business Logic)
│   ├── Repositories (Data Access)
│   ├── Entities (JPA Models)
│   └── DTOs (API Contracts)
├── Frontend
│   ├── Pages (Full Pages)
│   ├── Components (Reusable Components)
│   ├── API (HTTP Client)
│   └── Store (State Management)
└── Infrastructure
    ├── Docker
    ├── CI/CD
    └── Configuration
```

---

**Last Updated**: August 4, 2026  
**For Questions**: See relevant documentation or create an issue on GitHub
