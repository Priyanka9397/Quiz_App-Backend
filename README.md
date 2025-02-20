# Quiz App

This is a backend application for a website used to conduct quizzes in batches and schedules. The application is built using Spring Boot and MongoDB.

## Project Structure

- `src/main/java/com/example/quizapp/`
  - `config/`: Contains configuration files for the application.
  - `controller/`: Contains the controllers for handling API requests.
  - `model/`: Contains the models representing the data structures.
  - `repository/`: Contains the repository interfaces for interacting with MongoDB.
  - `service/`: Contains the services for business logic.

## Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/githubnext/workspace-blank.git
   cd workspace-blank
   ```

2. Open the project in your favorite IDE (e.g., IntelliJ, Eclipse).

3. Configure MongoDB connection properties in `src/main/resources/application.properties`:
   ```properties
   spring.data.mongodb.uri=mongodb://localhost:27017/quizapp
   ```

4. Run the Spring Boot application:
   ```bash
   ./mvnw spring-boot:run
   ```

## Usage Instructions

### STUDENT Role

- **Login/Register**: Students can register and log in to the application.
- **View Quizzes**: Students can view current, upcoming, and completed quizzes.

### ADMIN Role

- **User Management**: Admins can add, edit, and disable users.
- **Quiz Management**: Admins can create, modify, and delete quizzes.
- **Batch Management**: Admins can create batches based on the users.
- **Task Management**: Admins can assign quizzes to batches with a start time and end time as a task.
