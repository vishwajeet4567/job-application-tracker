# job-application-tracker
# Job Application Tracker - Complete Setup Guide

## Prerequisites

Before starting, ensure you have these installed on your system:

1. **Java JDK 17 or higher**
   - Download from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://adoptium.net/)
   - Verify installation: `java -version`

2. **Maven 3.6+**
   - Download from [Apache Maven](https://maven.apache.org/download.cgi)
   - Verify installation: `mvn -version`

3. **MySQL 8.0+**
   - Download from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
   - Verify installation: `mysql --version`

4. **Postman** (for API testing)
   - Download from [Postman](https://www.postman.com/downloads/)

## Project Structure

Create this folder structure in your project directory:

```
job-application-tracker/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── jobtracker/
│   │   │           ├── JobTrackerApplication.java
│   │   │           ├── controller/
│   │   │           │   └── JobApplicationController.java
│   │   │           ├── model/
│   │   │           │   └── JobApplication.java
│   │   │           ├── repository/
│   │   │           │   └── JobApplicationRepository.java
│   │   │           └── service/
│   │   │               └── JobApplicationService.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/
└── pom.xml
```

## Step-by-Step Setup Instructions

### Step 1: Setup MySQL Database

Open MySQL Command Line or MySQL Workbench and execute:

```sql
-- Create database
CREATE DATABASE job_tracker_db;

-- Use the database
USE job_tracker_db;

-- Verify database is created
SHOW DATABASES;
```

### Step 2: Create Project Directory

```bash
# Create project directory
mkdir job-application-tracker
cd job-application-tracker

# Create folder structure
mkdir -p src/main/java/com/jobtracker/{controller,model,repository,service}
mkdir -p src/main/resources
mkdir -p src/test/java
```

### Step 3: Add Project Files

Copy each file from the artifacts into the corresponding directory:

1. **pom.xml** → Root directory
2. **JobTrackerApplication.java** → src/main/java/com/jobtracker/
3. **JobApplication.java** → src/main/java/com/jobtracker/model/
4. **JobApplicationRepository.java** → src/main/java/com/jobtracker/repository/
5. **JobApplicationService.java** → src/main/java/com/jobtracker/service/
6. **JobApplicationController.java** → src/main/java/com/jobtracker/controller/
7. **application.properties** → src/main/resources/

### Step 4: Configure Database Credentials

Edit `src/main/resources/application.properties` and update your MySQL credentials:

```properties
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
```

### Step 5: Build the Project

Open terminal in project root directory and run:

```bash
# Clean and build the project
mvn clean install

# This will download all dependencies and compile the project
```

### Step 6: Run the Application

```bash
# Run the application
mvn spring-boot:run
```

You should see output like:
```
Started JobTrackerApplication in X.XXX seconds
```

The application will run on **http://localhost:8080**

## Testing the API with Postman

### 1. Create a Job Application (POST)

- **URL:** `http://localhost:8080/api/applications`
- **Method:** POST
- **Headers:** `Content-Type: application/json`
- **Body (raw JSON):**

```json
{
  "company": "Google",
  "position": "Software Engineer",
  "status": "Applied",
  "appliedDate": "2025-01-15",
  "location": "Mountain View, CA",
  "notes": "Referred by John Doe"
}
```

### 2. Get All Applications (GET)

- **URL:** `http://localhost:8080/api/applications`
- **Method:** GET

### 3. Get Application by ID (GET)

- **URL:** `http://localhost:8080/api/applications/1`
- **Method:** GET

### 4. Update Application (PUT)

- **URL:** `http://localhost:8080/api/applications/1`
- **Method:** PUT
- **Headers:** `Content-Type: application/json`
- **Body:**

```json
{
  "company": "Google",
  "position": "Software Engineer",
  "status": "Interview Scheduled",
  "appliedDate": "2025-01-15",
  "location": "Mountain View, CA",
  "notes": "Interview on Jan 20th"
}
```

### 5. Delete Application (DELETE)

- **URL:** `http://localhost:8080/api/applications/1`
- **Method:** DELETE

### 6. Get Applications by Status (GET)

- **URL:** `http://localhost:8080/api/applications/status/Applied`
- **Method:** GET

### 7. Get Applications by Company (GET)

- **URL:** `http://localhost:8080/api/applications/company/Google`
- **Method:** GET

## Common Status Values

Use these consistent status values:
- Applied
- Under Review
- Interview Scheduled
- Interview Completed
- Offer Received
- Rejected
- Withdrawn

## Troubleshooting

### Issue: Port 8080 already in use
**Solution:** Change port in `application.properties`:
```properties
server.port=8081
```

### Issue: Cannot connect to MySQL
**Solution:** 
1. Verify MySQL is running: `sudo systemctl status mysql` (Linux) or check Services (Windows)
2. Check credentials in `application.properties`
3. Ensure database exists: `SHOW DATABASES;`

### Issue: Build failures
**Solution:**
1. Check Java version: `java -version` (must be 17+)
2. Check Maven version: `mvn -version`
3. Clean Maven cache: `mvn clean`

### Issue: Table not created
**Solution:** Check `application.properties` has:
```properties
spring.jpa.hibernate.ddl-auto=update
```

## Verify Database Tables

After running the application, check if table was created:

```sql
USE job_tracker_db;
SHOW TABLES;
DESCRIBE job_applications;
```

## Next Steps

1. Add input validation using `@Valid` and Bean Validation
2. Implement exception handling with `@ControllerAdvice`
3. Add pagination for large datasets
4. Create a frontend (React, Angular, or Vue)
5. Add security with Spring Security
6. Implement search functionality
7. Add email notifications

## Sample cURL Commands (Alternative to Postman)

```bash
# Create application
curl -X POST http://localhost:8080/api/applications \
  -H "Content-Type: application/json" \
  -d '{"company":"Amazon","position":"Backend Developer","status":"Applied","appliedDate":"2025-01-15"}'

# Get all applications
curl http://localhost:8080/api/applications

# Get by ID
curl http://localhost:8080/api/applications/1

# Update application
curl -X PUT http://localhost:8080/api/applications/1 \
  -H "Content-Type: application/json" \
  -d '{"company":"Amazon","position":"Backend Developer","status":"Interview Scheduled","appliedDate":"2025-01-15"}'

# Delete application
curl -X DELETE http://localhost:8080/api/applications/1
```

## Project Features Implemented

✅ RESTful API design  
✅ CRUD operations (Create, Read, Update, Delete)  
✅ Spring Boot framework  
✅ MySQL relational database  
✅ JPA/Hibernate ORM  
✅ Repository pattern  
✅ Service layer architecture  
✅ REST Controller with proper HTTP methods  
✅ Custom query methods (findByStatus, findByCompany)

Your Job Application Tracker is now ready to use!
