create table categories (
    id              bigserial primary key,
    title           varchar(255) unique,
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default  current_timestamp
);

insert into categories (title) values ('Books'), ('Prints'), ('Original Art');

create table products
(   id bigserial    primary key,
    title           varchar(255),
    category_id     bigint references categories (id),
    price           numeric(8, 2),
    height          int,
    weight          int,
    description     varchar(255),
    image           varchar(100),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default  current_timestamp
);

insert into products (title, price, category_id, height, weight, description, image) values
('Bear', 800.50, 2, 50, 30, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/bear.jpg'),
('Cat hairstyle', 25, 2, 20, 20, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/cat_hairstyle.jpg'),
('Magic', 700.70, 2, 25, 25, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/magic.jpg'),
('Morning coffee', 700, 2, 25, 25, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/morningcoffee.jpg'),
('Peace', 500.00, 2, 30, 30, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/peace.jpg'),
('Submarine', 1000.00, 2, 30, 50, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/submarine.jpg'),
('Zhulka', 2000.00, 1, 30, 50, 'HA book that describes the adventures of a kind and small dog Zhulka', 'images/carts/zhulka.jpg'),
('Dinner with friends', 400.00, 2, 40, 50, 'High-quality giclee print with nonfading ink on premium textured paper.', 'images/carts/dinner.jpg');

create table users
(
    id          bigserial primary key,
    username    varchar(36) not null,
    password    varchar(80) not null
);

create table roles
(
    id          bigserial primary key,
    name        varchar(50) not null

);

create table user_roles
(
    user_id     bigint not null references users (id),
    role_id     bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('Bob', '$2y$10$8tK2L7s92T3wBEEVtkb3..560RXHsP9tPoO6iI6FNFOZ0BYlVeVY.'),
       ('John', '$2y$10$8tK2L7s92T3wBEEVtkb3..560RXHsP9tPoO6iI6FNFOZ0BYlVeVY.');

insert into user_roles (user_id, role_id)
values (1, 1),
       (2, 2);

create table orders
(
    id              bigserial primary key,
    user_id         bigint not null references users (id),
    total_price     numeric(8, 2) not null,
    address         varchar(255),
    phone           varchar(255),
    created_at      timestamp default current_timestamp,
    updated_at      timestamp default current_timestamp
);

create table  order_items
(
    id                  bigserial primary key,
    product_id          bigint not null references products (id),
    order_id            bigint not null references orders (id),
    quantity            int not null,
    price_per_product   numeric(8, 2) not null,
    price               numeric(8, 2) not null,
    created_at          timestamp default current_timestamp,
    updated_at          timestamp default current_timestamp
);