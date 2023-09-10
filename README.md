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
All Apis except Auth Required user to logged in.

### Auth

| Method | Url                         | Decription                           | Sample Valid Request Body | 
| ------ |-----------------------------|--------------------------------------|---------------------------|
| POST   | /api/v1/auth/register       | For new user to Register             | [JSON](#register)         |
| POST   | /api/v1/auth/login          | For user to Login                    | [JSON](#login)            |
| POST   | /api/v1/auth/current-user   | To get current loggedin user details | [JSON](#login)            |

### Posts

| Method | Url                                               | Description                                           | Sample Valid Request Body |
| ------ |---------------------------------------------------|-------------------------------------------------------| ------------------------- |
| GET    | /api/v1/posts                                     | Get all posts                                         | |
| GET    | /api/v1/posts/{postId}                            | Get post by id                                        | |
| GET    | /api/v1/user/{userId}/posts                       | Get all posts by particular user                      | |
| GET    | /api/v1/posts/search/{searchkeyword}              | Get post which contains searchKeyword                 | |
| GET    | /api/v1/category/{categoryId}/posts               | Get post by category                                  | |
| POST   | /api/v1/user/{userId}/category/{categoryId}/posts | Create new post (If userId belongs to logged in user) | [JSON](#postcreate) |
| PUT    | /api/v1/posts/{postId}                            | Update post (If post belongs to logged in user)       | [JSON](#postupdate) |
| DELETE | /api/v1/posts/{postId}                            | Delete post (If post belongs to logged in user)       | |

### Comments

| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/posts/{postId}/comments | Get all comments which belongs to post with id = postId | |
| GET    | /api/posts/{postId}/comments/{id} | Get comment by id if it belongs to post with id = postId | |
| POST   | /api/posts/{postId}/comments | Create new comment for post with id = postId (By logged in user) | [JSON](#commentcreate) |
| PUT    | /api/posts/{postId}/comments/{id} | Update comment by id if it belongs to post with id = postId (If comment belongs to logged in user or logged in user is admin) | [JSON](#commentupdate) |
| DELETE | /api/posts/{postId}/comments/{id} | Delete comment by id if it belongs to post with id = postId (If comment belongs to logged in user or logged in user is admin) | |

### Users

| Method | Url                    | Description                                   | Sample Valid Request Body |
| ------ |------------------------|-----------------------------------------------| ------------------------- |
| GET    | /api/v1/users          | Get all users (Only for admins)               | |
| GET    | /api/v1/users/{userId} | Get user by userId (Only for admins)          | |
| POST   | /api/v1/users          | Add user (Only for admins)                    | [JSON](#usercreate) |
| PUT    | /api/v1/users/{userId} | Update user (Only user can update themselves) | [JSON](#userupdate) |
| DELETE | /api/v1/users/{userId} | Delete user (Only for admins)                 | |

### Category

| Method | Url                             | Description                                          | Sample Valid Request Body |
| ------ |---------------------------------|------------------------------------------------------|---------------------------|
| GET    | /api/v1/categories              | Get all categories (Only for admins)                 |                           |
| GET    | /api/v1/categories/{categoryId} | Get category details by categoryId (Only for admins) |                           |
| POST   | /api/v1/categories              | Add category (Only for admins)                       | [JSON](#categorycreate)   |
| PUT    | /api/v1/categories/{categoryId} | Update category (Only for admins)                    | [JSON](#categoryupdate)   |
| DELETE | /api/v1/categories/{categoryId} | Delete category (Only for admins)                    |                           |





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

##### <a id="postcreate">Create post -> /api/v1/user/{userId}/category/{categoryId}/posts</a>
```json
{
    "title": "Healthy Lifestyle Tips",
    "content": "Tips for a healthier life."
    ##Image is also required as part of request
  
}
```

##### <a id="postupdate">Create post -> /api/v1/posts/{postId} </a>
```json
{
    "title": "Digital Art Showcase",
    "content": "A collection of digital art pieces."
    ##Image is also required as part of request
  
}
```

##### <a id="usercreate">Create User -> /api/v1/users</a>
```json
{
    "username": "john",
    "name": "John Doe",
    "email": "john@example.com",
    "password": "john",
    "about": "I am a new user"
}
```

##### <a id="userupdate">Update User -> /api/v1/users/{userId}</a>
```json
{
    "name": "John Cena",
    "username": "johncena",
    "email": "john@example.com",
    "password": "john",
    "about": "I am John Cena"
}
```

##### <a id="categorycreate">Update Category -> /api/v1/categories</a>
```json
{
    "categoryName": "Fitness",
    "categoryDescription": "Fit and Healthy Life"
}
```

##### <a id="categoryupdate">Update Category -> /api/v1/categories/{categoryId}</a>
```json
{
    "categoryName": "Fitness",
    "categoryDescription": "Post about Fit and Healthy Life"
}
```