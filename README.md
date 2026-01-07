# LabSync - Medical Lab Management System

## Tech Stack
- Java 17
- Spring Boot 3.x
- MySQL 8.x
- Maven

## Setup Instructions
1. Clone repository
```
   git clone https://github.com/mmahesh7/labsync-api.git
```

2. Create MySQL database
```sql
   CREATE DATABASE labsync;
```

3. Update `application.properties` with your DB credentials

4. Run application
```
   mvn spring-boot:run
```

5. API runs on `http://localhost:8080`

## API Endpoints

### User Management
- POST /api/users - Create user
- GET /api/users - Get all users
- GET /api/users/{id} - Get user by ID
- PUT /api/users/{id} - Update user
- DELETE /api/users/{id} - Delete user

## Testing
Use Postman collection: [link to collection]

## Phase Completion
- [x] Phase 1: User Management
- [ ] Phase 2: Authentication & Security
