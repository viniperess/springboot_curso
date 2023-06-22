# springboot_curso

![image](https://github.com/viniperess/springboot_curso/assets/107048343/5db2728d-ebdb-4bbf-be0a-22e4ecc60f13)

# Java Spring Boot Free Course Management System
This is a Java Spring Boot project for a free course management system. The system provides basic CRUD operations along with search functionality by name. The main entities in the system are courses, classes, students, and enrollments.

## Features
Create, read, update, and delete operations for courses, classes, students, and enrollments.
Search functionality to find courses, classes, students, and enrollments by name.
Easy management of course information, class schedules, student details, and enrollments.
Efficient retrieval and manipulation of data using Spring Boot's powerful features.

## Technologies Used
The project utilizes the following technologies:

Java: The programming language used for the project.
Spring Boot: The framework used for building the application.
Spring Data JPA: The persistence framework for data access.
Hibernate: The ORM (Object-Relational Mapping) tool for mapping Java objects to database tables.
MariaDB: The relational database management system for data storage.
Gradle: The build automation tool for managing dependencies and building the project.

### Getting Started
To get started with the project, follow these steps:

Clone the repository:

bash
Copy code
git clone [https://github.com/viniperess/springboot_curso.git]
Navigate to the project directory:

bash
Copy code
cd your-repository
Configure the database connection in the application.properties file:

properties
Copy code
spring.datasource.url=jdbc:mariadb://localhost:3306/livre
spring.datasource.username=your-root
spring.datasource.password=your-

### Usage
Once the application is up and running, you can perform the following actions:

Create a new course: Provide the course details such as name, description, duration, price, and prerequisites.
Create a new class: Specify the course, class schedule, date, time, vacancies, and turn.
Add a new student: Enter the student's information such as name, ID card (RG), tax ID number (CPF), and address.
Enroll a student: Select a course and class to enroll the student.
You can also perform read, update, and delete operations on courses, classes, students, and enrollments. Additionally, use the search functionality to find entities by name.

### Contributing
Contributions to the project are welcome! If you find any issues or want to enhance the system, feel free to open a pull request. Please ensure that you follow the existing code style and provide detailed information about the changes you have made.

### License
This project is licensed under the MIT License.

### Contact
If you have any questions or suggestions regarding the project, feel free to contact the project maintainer at <a href="https://www.linkedin.com/in/vin%C3%ADciusgperes">Linkedin</a>.

