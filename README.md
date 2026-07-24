# CrudDemo — CRUD Operations using Spring Data JPA

Answer to: **Implement CRUD Operations using Spring Data JPA [CO4, K3]**

This project maps directly onto the 6 steps in the assignment brief.

## 1. New Maven project
Standard Maven layout, `groupId=com.example`, `artifactId=CrudDemo`.

## 2. Dependencies in `pom.xml`
- `spring-boot-starter-web` — REST controllers
- `spring-boot-starter-data-jpa` — Spring Data JPA / Hibernate
- `spring-boot-starter-validation` — `@Valid` request validation
- `h2` — in-memory database (runtime scope)
- `lombok` — reduces boilerplate (getters/setters)

## 3. Entity class with JPA annotations
`entity/Employee.java` uses `@Entity`, `@Table`, `@Id`, `@GeneratedValue`, `@Column`, plus bean-validation annotations (`@NotBlank`, `@Email`, `@Positive`).

## 4. Repository interface
`repository/EmployeeRepository.java` extends `JpaRepository<Employee, Long>`, which already provides `save`, `findAll`, `findById`, `delete`, etc. A derived query method `findByEmail` is included to show Spring Data JPA's query-generation-from-method-name feature.

## 5. Service and controller classes
- `service/EmployeeService.java` — business logic layer, one method per CRUD operation (`create`, `findAll`, `findById`, `update`, `delete`).
- `controller/EmployeeController.java` — `@RestController` exposing REST endpoints, delegating to the service, and wrapping responses in `ResponseEntity` to control HTTP status codes explicitly.
- `exception/ResourceNotFoundException.java` + `exception/GlobalExceptionHandler.java` — centralised error handling via `@RestControllerAdvice`.

## 6. Testing with Postman / HTTP status codes

Base URL: `http://localhost:8080/api/employees`

| Operation | Method | URL | Success Status | Error Status |
|---|---|---|---|---|
| Create | POST | `/api/employees` | `201 Created` | `400 Bad Request` (validation) |
| Read all | GET | `/api/employees` | `200 OK` | — |
| Read one | GET | `/api/employees/{id}` | `200 OK` | `404 Not Found` |
| Update | PUT | `/api/employees/{id}` | `200 OK` | `404 Not Found`, `400 Bad Request` |
| Delete | DELETE | `/api/employees/{id}` | `204 No Content` | `404 Not Found` |

### Sample requests

**Create** — `POST /api/employees`
```json
{
  "name": "Riya Sharma",
  "department": "Engineering",
  "email": "riya.sharma@example.com",
  "salary": 55000
}
```

**Update** — `PUT /api/employees/1`
```json
{
  "name": "Riya Sharma",
  "department": "Product",
  "email": "riya.sharma@example.com",
  "salary": 62000
}
```

**Delete** — `DELETE /api/employees/1` → `204 No Content`

**Not found example** — `GET /api/employees/99` → `404 Not Found`
```json
{
  "timestamp": "2026-07-23T10:15:30",
  "status": 404,
  "error": "Not Found",
  "message": "Employee not found with id: 99"
}
```

## Running the project

```bash
mvn clean install
mvn spring-boot:run
```

App runs at `http://localhost:8080`. H2 console at `http://localhost:8080/h2-console`
(JDBC URL: `jdbc:h2:mem:cruddb`, username `sa`, blank password).
