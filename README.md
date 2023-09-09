# Blogify-REST-APIs
REST APIs for blogging application made using Spring Boot 3

## General info

The application provides REST API that may be used to manage posts and comments as well as go through the registration and logging user process using a JWT token with a pagination feature included. API returns information about the number of pages and posts whilst notifying what page the user is on, including the last page. Feature for blog users comments has been added as well.

## Technologies
* Java 18
* Spring Boot 6
* Spring Security 6
* Hibernate
* Maven
* Tomcat
* MySQL
* Lombok
* DevTools
* JWT token
* ModelMapper
* Swagger

## Explore Rest APIs

The app defines following CRUD APIs.

### Auth

| Method | Url                | Decription               | Sample Valid Request Body | 
| ------ |--------------------|--------------------------|---------------------------|
| POST   | /api/auth/register | For new user to Register | [JSON](#register)         |
| POST   | /api/auth/login    | For user to Login        | [JSON](#login)            |



Test them using postman or any other rest client.
## Sample Valid JSON Request Bodys

##### <a id="register">Sign Up -> /api/auth/signup</a>
```json
{
    "name": "John Doe",
    "username": "userjohn",
    "password": "password",
    "email": "john.doe@example.com",
    "about": "I am Developer"
}
```

##### <a id="login">Log In -> /api/auth/signin</a>
```json
{
    "username": "userjohn",
    "password": "password"
}
```