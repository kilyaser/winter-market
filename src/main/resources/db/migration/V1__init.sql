create table products (id bigserial primary key, title varchar(255), price int);
insert into products (title, price) values ('Art-1', 80), ('Art-2', 25), ('Art-3', 300);

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