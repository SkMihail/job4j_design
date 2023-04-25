create table sales(
id serial primary key,
customer text,
phone bigint,
date_of_order date,
value bigint,
status boolean);

insert into sales (name, phone, date_of_order, value, status) values ('Ольга', '79025456615', '08.08.2023', '5', 'true');
insert into sales (name, phone, date_of_order, value, status) values ('Иван', '7908651642', '10.08.2023', '1', 'false');
delete from sales;
alter table sales alter column phone type varchar;
insert into sales (name, phone, date_of_order, value, status) values ('Иван', '7908651642', '10.08.2023', '1', 'false');
select * from sales;

