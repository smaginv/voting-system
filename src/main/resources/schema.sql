DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;

DROP SEQUENCE IF EXISTS user_seq;
DROP SEQUENCE IF EXISTS restaurant_seq;
DROP SEQUENCE IF EXISTS dish_seq;
DROP SEQUENCE IF EXISTS vote_seq;

CREATE TABLE users
(
    user_id  BIGINT PRIMARY KEY NOT NULL,
    email    VARCHAR UNIQUE     NOT NULL,
    username VARCHAR UNIQUE     NOT NULL,
    password VARCHAR            NOT NULL
);
CREATE SEQUENCE user_seq START 20 INCREMENT 10;
ALTER SEQUENCE user_seq OWNED BY users.user_id;
ALTER TABLE users
    ALTER COLUMN user_id SET DEFAULT nextval('user_seq');

CREATE TABLE roles
(
    user_id BIGINT  NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    role    VARCHAR NOT NULL
);
ALTER TABLE roles
    ADD CONSTRAINT user_role_unique UNIQUE (user_id, role);

CREATE TABLE restaurants
(
    restaurant_id BIGINT PRIMARY KEY NOT NULL,
    title         VARCHAR UNIQUE     NOT NULL
);
CREATE SEQUENCE restaurant_seq START 20 INCREMENT 10;
ALTER SEQUENCE restaurant_seq OWNED BY restaurants.restaurant_id;
ALTER TABLE restaurants
    ALTER COLUMN restaurant_id SET DEFAULT nextval('restaurant_seq');

CREATE TABLE dishes
(
    dish_id       BIGINT PRIMARY KEY NOT NULL,
    restaurant_id BIGINT             NOT NULL REFERENCES restaurants (restaurant_id) ON DELETE CASCADE,
    title         VARCHAR            NOT NULL,
    price         INT                NOT NULL,
    date          DATE               NOT NULL
);
CREATE SEQUENCE dish_seq START 20 INCREMENT 10;
ALTER SEQUENCE dish_seq OWNED BY dishes.dish_id;
ALTER TABLE dishes
    ALTER COLUMN dish_id SET DEFAULT nextval('dish_seq');
ALTER TABLE dishes
    ADD CONSTRAINT restaurant_dish_date_unique UNIQUE (restaurant_id, title, date);

CREATE TABLE votes
(
    vote_id       BIGINT PRIMARY KEY NOT NULL,
    user_id       BIGINT             NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    restaurant_id BIGINT             NOT NULL REFERENCES restaurants (restaurant_id) ON DELETE CASCADE,
    date          DATE               NOT NULL
);
CREATE SEQUENCE vote_seq START 20 INCREMENT 10;
ALTER SEQUENCE vote_seq OWNED BY votes.vote_id;
ALTER TABLE votes
    ALTER COLUMN vote_id SET DEFAULT nextval('vote_seq');
ALTER TABLE votes
    ADD CONSTRAINT user_date_unique UNIQUE (user_id, date)