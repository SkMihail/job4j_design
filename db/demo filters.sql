create table type(
	id serial primary key,
	name varchar(255));


create table product(
	id serial primary key,
	name varchar(255),
	type_id int references type(id),
	expired_date date,
	price float);
	
insert into type (name) values 
    ('Фрукты'),
    ('Овощи'),
    ('Мясо'),
    ('Рыба'),
	('Сыр');
	
insert into product (name, type_id, expired_date, price) values 
    ('Яблоки', 1, '2023-05-31', 160.00),
    ('Апельсины', 1, '2023-05-31', 220.00),
    ('Бананы', 1, '2023-05-31', 260.00),
    ('Картофель', 2, '2023-05-31', 60.00),
    ('Морковь', 2, '2023-05-31', 80.00),
    ('Свинина', 3, '2023-05-31', 450.00),
    ('Говядина', 3, '2023-05-31', 550.00),
    ('Семга', 4, '2023-05-31', 1200.00),
    ('Тунец', 4, '2023-05-31', 1500.00),
	('Российский', 5, '2023-06-30', 400.00),
    ('Чеддер', 5, '2023-07-15', 700.00),
    ('Гауда', 5, '2023-07-30', 800.00),
    ('Брынза', 5, '2023-07-20', 550.00);
	
insert into product (name, type_id, expired_date, price) values 
    ('Яблоки', 1, '2023-04-28', 50.00),
    ('Апельсины', 1, '2023-04-28', 50.00),
    ('Бананы', 1, '2023-04-28', 50.00);
	
insert into type (name) values 
    ('Молоко');
	
insert into product (name, type_id, expired_date, price) values 
    ('Чугуевское', 6, '2023-05-26', 120.00),
    ('Фермерское подворье', 6, '2022-12-28', 101.00);
	
insert into type (name) values 
    ('Мороженое');
	
insert into product (name, type_id, expired_date, price) values 
    ('Сливочное мороженое', 7, '2023-09-26', 80.00),
    ('мороженое шоколадное', 7, '2022-12-28', 80.00);	

--1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name, p.expired_date, p.price, type.name
from product as p
join type on p.type_id = type.id where type.name like 'Сыр';

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select * from product where name like '%мороженое%';

--3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select p.name, p.expired_date, p.price, t.name
from product as p
join type t on p.type_id = t.id where p.expired_date < current_date;

--4. Написать запрос, который выводит самый дорогой продукт.
--   Запрос должен быть универсальный и находить все продукты с максимальной ценой.
select * from product order by price desc limit 1;

--5. Написать запрос, который выводит для каждого типа количество продуктов
--   к нему принадлежащих. В виде имя_типа, количество
select t.name as группа_товара, count(*) as товаров_в_группе
FROM product p
JOIN type t ON p.type_id = t.id
GROUP BY t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name, p.expired_date, p.price, t.name
from product as p
join type t on p.type_id = t.id where t.name in ('Сыр', 'Молоко');

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 3 штук.
--Под количеством подразумевается количество продуктов определенного типа.
--Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла",
--которые ему принадлежат, то количество продуктов типа "СЫР" будет 2. 
select t.name as группа_товара, count(*) as товаров_в_группе
FROM product p
JOIN type t ON p.type_id = t.id
GROUP BY t.name having count(*) < 3;

--8. Вывести все продукты и их тип.
select p.name, t.name
from product as p
join type t on p.type_id = t.id;
	
