INSERT INTO users (user_id, email, username, password)
VALUES (1, 'admin@mail.ru', 'admin', '$2a$12$04A5Sm9Dzw2.s9qD2S1JTeX3jyytft5N7ep7OrZcp18qj2S2/jqb6'),
       (2, 'user@mail.ru', 'user', '$2a$12$DXIrCvZIuwGE7TKw.GQ8aOjYNM0gSSMI8yfkzvtNw6EZyyPKOk/L.');

INSERT INTO roles (user_id, role)
VALUES (1, 'ADMIN'),
       (2, 'USER'),
       (1, 'USER');

INSERT INTO restaurants (restaurant_id, title)
VALUES (1, 'One restaurant'),
       (2, 'Two restaurant'),
       (3, 'Three restaurant'),
       (4, 'Four restaurant'),
       (5, 'Five restaurant');

INSERT INTO dishes (dish_id, restaurant_id, title, price, date)
VALUES (1, 1, 'Baked potato', 1, '10-April-2021'),
       (2, 1, 'Burger', 2, '10-April-2021'),
       (3, 1, 'Casserole', 3, '10-April-2021'),
       (4, 1, 'Chicken salad', 4, '11-April-2021'),
       (5, 1, 'Crumble', 5, '11-April-2021'),
       (6, 1, 'Curry', 6, '11-April-2021'),
       (7, 2, 'Onion Rings', 1, '10-April-2021'),
       (8, 2, 'Mozzarella Sticks', 2, '10-April-2021'),
       (9, 2, 'Reuben', 3, '10-April-2021'),
       (10, 2, 'Shrimp', 4, '11-April-2021'),
       (11, 2, 'Calamari', 5, '11-April-2021'),
       (12, 2, 'Arancini', 6, '11-April-2021'),
       (13, 3, 'Greek Gyro', 1, now()),
       (14, 3, 'Buffalo Chicken', 2, now()),
       (15, 3, 'Roast Beef', 3, now()),
       (16, 4, 'Ham Slider', 3, now()),
       (17, 4, 'Pizza Slider', 2, now()),
       (18, 4, 'Potato Cakes', 1, now());

INSERT INTO votes (vote_id, user_id, restaurant_id, date)
VALUES (1, 1, 1, '11-April-2021'),
       (2, 2, 2, '11-April-2021'),
       (3, 1, 3, now()),
       (4, 2, 4, now());