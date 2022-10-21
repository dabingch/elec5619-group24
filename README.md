# ELEC5619 Group24

## Used libraries

### Backend

* springboot 2.7.3
* spring-boot-starter-data-jpa 2.7.3
* spring-boot-starter-web 2.7.3
* spring-boot-starter-data-mail 2.7.3
* spring-boot-starter-test 2.7.3
* JUnit 4.13.2
* mysql-connector-java 8.0.3
* lombok 1.8.24
* log4j-core 2.17.1

### Frontend

* @element-plus/icons-vue 2.0.10
* axios 1.1.2
* core-js 3.8.3
* element-plus 2.2.17
* element-ui 2.15.10
* vue 3.2.13
* vue-router 4.1.5

## Working functionalities

* Sign in
* Sign up with email verification
* Check and update personal profile
* Check all available courses
* Search courses by the course name
* Check all comments for course review
* Check all comments of a course 
* Check all posts for finding teammates
* Check all posts of a course
* Make a course review for a course
* Create a new course if the user can not find a course
* Create a new post to find teammates
* Edit and update a post
* Send a request email to a post owner
* Admin functionalities such as enable/disable course, student and posts

## Environment Variables
Edit `application.yml` file to add relevant variables. E.g.

```
url: <Datasource URL>
username: <Datebase username>
password: <Database password>
```

## Database

Our database is hosted on cloud, and you can access it though credentials below:

```
url: jdbc:mysql://<IP_ADDRESS>/HDhelper
username: root
password: <PASSWORD>
```

> You can simply append the credentials in `application.yml` to allow the server to connect to it, or you can use your own URL either to local/remote cloud database server.

## Email SMTP Server

We used `maildev`'s SMTP server to connect to a SMTP server to send out our activation emails. Therefore, for this service to be available. You are **requested to install and connect to [maildev](https://github.com/maildev/maildev)** before running the server.

![image](https://user-images.githubusercontent.com/61271027/193802860-d32378da-ac0e-4864-ad7b-7bfe84f2837c.png)

## Install

```shell
cd frontend

npm install
```

## Run

### Backend

```shell
cd backend

mvn clean

mvn install

mvn spring-boot:run
```

### Frontend

```shell
cd frontend

npm run serve
```
