create table Order_Items
(
    id         bigint auto_increment
        primary key,
    order_id   bigint        not null,
    product_id bigint        not null,
    quantity   int           not null,
    price      decimal(5, 2) not null,
    subtotal   decimal(8, 2) not null,
    constraint Order_Items_orders_id_fk
        foreign key (order_id) references orders (id),
    constraint Order_Items_product_id_fk
        foreign key (product_id) references product (id)
);

