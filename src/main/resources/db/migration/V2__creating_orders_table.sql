create table Orders
(
    id             bigint auto_increment
        primary key,
    date_time      datetime      not null,
    total_amount   decimal(8, 2) not null,
    payment_method varchar(50)   not null,
    status         varchar(50)   not null,
    cashier_name   varchar(100)  not null
);

