drop table if exists t_menu CASCADE;
drop table if exists t_order CASCADE;
drop table if exists t_order_coffee CASCADE;

create table t_coffee (
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    name varchar(255),
    price bigint,
    primary key (id)
);

create table t_order (
    id bigint auto_increment,
    create_time timestamp,
    update_time timestamp,
    customer varchar(255),
    state integer not null,
    primary key (id)
);

create table t_order_coffee (
    coffee_order_id bigint not null,
    items_id bigint not null
);

insert into t_coffee(name, price, create_time, update_time) values ('espresso', 2000, now(), now());
insert into t_coffee(name, price, create_time, update_time) values ('latte', 2500, now(), now());
insert into t_coffee(name, price, create_time, update_time) values ('capuccino', 2500, now(), now());
insert into t_coffee(name, price, create_time, update_time) values ('mocha', 3000, now(), now());
insert into t_coffee(name, price, create_time, update_time) values ('macchiato', 3000, now(), now());
