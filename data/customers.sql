create table customers (
    id SERIAL primary key,
    first_name varchar(100) not null,
    last_name varchar(100) not null,
    postal_address text,
    email_address varchar(100),
    phone_number varchar(30)
);