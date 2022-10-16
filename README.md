# elec5619-group24

## Used libraries

### Backend

* springboot 2.7.3
* spring-boot-starter-data-jpa 2.7.3
* spring-boot-starter-web 2.7.3
* spring-boot-starter-data-mail 2.7.3
* spring-boot-starter-test 2.7.3
* JUnit 4.13.2
* mysql-connector-java 2.7.3
* lombok 1.8.24
* log4j-core 2.17.1

### Frontend

* @element-plus/icons-vue ^2.0.10
* axios 1.1.2
* core-js 3.8.3
* element-plus 2.2.17
* element-ui 2.15.10
* vue 3.2.13
* vue-router 4.1.5

## Working functionalities

* Sign in
* Sign up with email verification
* Check and Update personal profile
* Check all available courses
* Search courses by the course name
* Check all comments of courses
* Check all comments of a course 
* Check all posts of courses
* Check all posts of a course
* Comment and rate a course
* Create a new course if the user can not find a course
* Create a post to find teammate
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
url: jdbc:mysql://35.189.27.112/HDhelper
username: root
password: elec5619group24
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

After adding the environment variables in `application.yml` and enable `maildev` server, you can simply run the app though runing the `HdhelperApplication.class`

### Frontend

```shell
cd frontend

npm run serve
```
