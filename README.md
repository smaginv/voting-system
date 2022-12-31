Spring Boot, Spring Data, Spring Security, PostgreSQL, Ehcache, MapStruct, OpenAPI
===============================

## REST API Voting System

## Application launch:

**1. Clone the repository**

```bash
git clone https://github.com/smaginv/voting-system.git
```

**2. Create PostgreSQL database**
```bash
create database voting
```

**3. Change PostgreSQL username and password in application.yml**


**4. Run the app using maven**

```bash
mvn spring-boot:run
```

**The app will start running at:**  
http://localhost:8080/api

**Swagger/OpenAPI Specification:**  
http://localhost:8080/api/swagger-ui/index.html


---


Task:


Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

* 2 types of users: admin and regular users
* Admin can input a restaurant, and it's lunch menu of the day (2-5 items usually, just a dish name and price)
* Menu changes each day (admins do the updates)
* Users can vote on which restaurant they want to have lunch at
* Only one vote counted per user
* If user votes again the same day:
    - If it is before 11:00 we assume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

---

**REST API**

## Administrator

Administrator controller:

| Method | URL                       | Description          |
|--------|---------------------------|----------------------|
| GET    | /api/admin/users/{id}     | Get user by id       |
| GET    | /api/admin/users/username | Get user by username |
| GET    | /api/admin/users/email    | Get user by email    |
| GET    | /api/admin/users          | Get all users        |
| PATCH  | /api/admin/users/{id}     | Set role to user     |
| POST   | /api/admin/users          | Create user          |
| DELETE | /api/admin/users/{id}     | Delete user by id    |

GET (get user by id) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/admin/users/1' --user admin:admin`

GET (get user by username) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/admin/users/username?username=user' --user admin:admin`

GET (get user by email) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/admin/users/email?email=user@mail.ru' --user admin:admin`

GET (get all users) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/admin/users' --user admin:admin`

PATCH (set role) Curl:  
`curl -X 'PATCH' 'http://localhost:8080/api/admin/users/2?role=ADMIN' --user admin:admin`

POST (create user) Curl:  
`curl -X 'POST' 'http://localhost:8080/api/admin/users' -H 'Content-Type: application/json' -d '{"username":"new user", "email":"new@mail.ru", "password":"newpass"}' --user admin:admin`

DELETE (delete user) Curl:  
`curl -X 'DELETE' 'http://localhost:8080/api/admin/users/2' --user admin:admin`

#

Dish Controller:

| Method | URL                                         | Description                         |
|--------|---------------------------------------------|-------------------------------------|
| GET    | /api/restaurants/{restaurantId}/dishes/{id} | Get dish by id and restaurant id    |
| PATCH  | /api/restaurants/{restaurantId}/dishes/{id} | Update dish for restaurant          |
| POST   | /api/restaurants/{restaurantId}/dishes      | Create dish for restaurant          |
| DELETE | /api/restaurants/{restaurantId}/dishes/{id} | Delete dish by id and restaurant id |

GET (get dish) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/restaurants/1/dishes/1' --user admin:admin`

PATCH (update dish) Curl:  
`curl -X 'PATCH' 'http://localhost:8080/api/restaurants/1/dishes/1' -H 'Content-Type: application/json' -d '{"title": "Updated dish", "price": "55", "date": "28.12.2022"}' --user admin:admin`

POST (create dish) Curl:  
`curl -X 'POST' 'http://localhost:8080/api/restaurants/1/dishes' -H 'Content-Type: application/json' -d '{"title":"New dish", "price":"10", "date":"20.12.2022"}' --user admin:admin`

DELETE (delete dish) Curl:  
`curl -X 'DELETE' 'http://localhost:8080/api/restaurants/1/dishes/5' --user admin:admin`

#

Profile controller:

| Method | URL          | Description         |
|--------|--------------|---------------------|
| GET    | /api/profile | Get your details    |
| PATCH  | /api/profile | Update your details |
| DELETE | /api/profile | Delete your account |

GET (get your details) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/profile' --user admin:admin`

PATCH (update your details) Curl:  
`curl -X 'PATCH' 'http://localhost:8080/api/profile' -H 'Content-Type: application/json' -d '{"username": "updated user", "email": "admin@mail.ru", "password": "updated"}' --user admin:admin`

DELETE (delete your account) Curl:  
`curl -X 'DELETE' 'http://localhost:8080/api/profile' --user admin:admin`

#

Restaurant Controller:

| Method | URL                                | Description                 |
|--------|------------------------------------|-----------------------------|
| GET    | /api/restaurants/{id}              | Get restaurant by id        |
| GET    | /api/restaurants/{id}/menu/today   | Get today's restaurant menu |
| GET    | /api/restaurants/{id}/menu/on-date | Get restaurant menu on date |
| GET    | /api/restaurants                   | Get all restaurants         |
| PATCH  | /api/restaurants/{id}              | Update restaurant           |
| POST   | /api/restaurants                   | Create restaurant           |
| DELETE | /api/restaurants/{id}              | Delete restaurant           |

GET (get restaurant) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/restaurants/3' --user admin:admin`

GET (get today's restaurant menu) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/restaurants/4/menu/today' --user admin:admin`

GET (get restaurant menu on date) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/restaurants/1/menu/on-date?date=2021-04-10' --user admin:admin`

GET (get all restaurants) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/restaurants' --user admin:admin`

PATCH (update restaurant) Curl:  
`curl -X 'PATCH' 'http://localhost:8080/api/restaurants/4' -H 'Content-Type: application/json' -d '{"title":"Updated restaurant"}' --user admin:admin`

DELETE (delete restaurant) Curl:  
`curl -X 'DELETE' 'http://localhost:8080/api/restaurants/4' --user admin:admin`

POST (create restaurant) Curl:  
`curl -X 'POST' 'http://localhost:8080/api/restaurants' -H 'Content-Type: application/json' -d '{"title": "new restaurant"}' --user admin:admin`

#

Vote Controller:

| Method | URL                | Description           |
|--------|--------------------|-----------------------|
| GET    | /api/votes/today   | Get all today votes   |
| GET    | /api/votes/on-date | Get all votes on date |

GET (get all today votes) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/votes/today' --user admin:admin`

GET (get all votes on date) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/votes/on-date?date=2021-04-11' --user admin:admin`


---

## User

Profile controller:

| Method | URL          | Description         |
|--------|--------------|---------------------|
| GET    | /api/profile | Get your details    |
| PATCH  | /api/profile | Update your details |
| DELETE | /api/profile | Delete your account |

GET (get your details) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/profile' --user user:user`

PATCH (update your details) Curl:  
`curl -X 'PATCH' 'http://localhost:8080/api/profile' -H 'Content-Type: application/json' -d '{"username": "Updated user", "email": "user@mail.ru", "password": "user"}' --user user:user`

DELETE (delete your account) Curl:  
`curl -X 'DELETE' 'http://localhost:8080/api/profile' --user user:user`

#

Restaurant Controller:

| Method | URL                                | Description                 |
|--------|------------------------------------|-----------------------------|
| GET    | /api/restaurants/{id}/menu/today   | Get today's restaurant menu |
| GET    | /api/restaurants/{id}/menu/on-date | Get restaurant menu on date |

GET (get today's restaurant menu) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/restaurants/4/menu/today' --user user:user`

GET (get restaurant menu on date) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/restaurants/1/menu/on-date?date=2021-04-10' --user user:user`

#

Vote controller:

| Method | URL                    | Description                     |
|--------|------------------------|---------------------------------|
| GET    | /api/user/vote/today   | Get today's user vote           |
| GET    | /api/user/vote/on-date | Get the user's vote on the date |
| GET    | /api/user/votes        | Get the user's voting history   |
| PATCH  | /api/user/vote         | Re-vote, only before 11:00      |
| POST   | /api/user/vote         | To vote, only before 11:00      |
| DELETE | /api/user/vote         | Delete today's user vote        |

GET (get today's user vote) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/user/vote/today' --user user:user`

GET (get the user's vote on the date) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/user/vote/on-date?date=2021-04-11' --user user:user`

GET (get the user's voting history) Curl:  
`curl -X 'GET' 'http://localhost:8080/api/user/votes' --user user:user`

PATCH (re-vote, only before 11:00) Curl:  
`curl -X 'PATCH' 'http://localhost:8080/api/user/vote?restaurantId=1' --user user:user`

POST (to vote, only before 11:00) Curl:  
`curl -X 'POST' 'http://localhost:8080/api/user/vote?restaurantId=2' --user user:user`

DELETE (delete today's user vote) Curl:  
`curl -X 'DELETE' 'http://localhost:8080/api/user/vote' --user user:user`


---

## Anonymous

Profile controller:

| Method | URL                   | Description   |
|--------|-----------------------|---------------|
| POST   | /api/profile/register | Register user |

POST (register user) Curl:  
`curl -X 'POST' 'http://localhost:8080/api/profile/register' -H 'Content-Type: application/json' -d '{"username":"new user", "email":"new@mail.ru", "password":"newuser"}'`