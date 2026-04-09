create table Product
(
    id        bigint auto_increment
        primary key,
    name      varchar(75)   not null,
    price     decimal(5, 2) not null,
    category  varchar(100)  not null,
    available bool          not null
);

