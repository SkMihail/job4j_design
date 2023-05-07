create table products (
    id serial primary key,
    name varchar(50),
    producer varchar(50),
    count integer default 0,
    price integer
);

insert into products (name, producer, count, price) VALUES ('product_1', 'producer_1', 3, 50);

select * from products;
delete from products;
--1)Триггер должен срабатывать после вставки данных, для любого товара
--и просто насчитывать налог на товар (нужно прибавить налог к цене товара).
--Действовать он должен не на каждый ряд, а на запрос (statement уровень)
create or replace function tax()
    returns trigger as
$$
    BEGIN
        update products
        set price = price + price * 0.2
        where id in (select id from inserted);
        return new;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger tax_trigger
    after insert on products
    referencing new table as inserted
    for each statement
    execute procedure tax();

--2)Триггер должен срабатывать до вставки данных и насчитывать налог на товар
--(нужно прибавить налог к цене товара). Здесь используем row уровень.
create or replace function before_func()
    returns trigger as
$$
    BEGIN
        new.price = new.price + new.price * 0.2;
        return NEW;
    END;
$$
LANGUAGE 'plpgsql';	

create trigger before_trigger
    before insert
    on products
    for each row
    execute procedure before_func();

--3)Нужно написать триггер на row уровне, который сразу после
--вставки продукта в таблицу products, будет заносить имя, 
--цену и текущую дату в таблицу history_of_price. 

create table history_of_price (
    id serial primary key,
    name varchar(50),
    price integer,
    date timestamp
);

create or replace function insert_history_of_price()
  returns trigger as
$$
begin
  insert into history_of_price (name, price, date)
  values (new.name, new.price, current_timestamp);
  return null;
end;
$$
language plpgsql;

create trigger after_insert_product
after insert
on products
for each row
execute procedure insert_history_of_price();

select * from history_of_price;
drop trigger before_trigger on products;