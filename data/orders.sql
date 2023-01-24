create table orders (
    transact_id SERIAL primary key,
    customer_id INT not null,
    order_date TIMESTAMP,
    CONSTRAINT fk_customer_order
    FOREIGN KEY(customer_id)
    REFERENCES customers(id)
);


create table order_lines (
    transact_id int not null,
    product_id  varchar(100) not null,
    quantity    int not null,
    price_at_ordertime numeric,
    primary key (transact_id, product_id),
    CONSTRAINT fk_order_lines
    FOREIGN KEY(transact_id)
    REFERENCES orders(transact_id)
);