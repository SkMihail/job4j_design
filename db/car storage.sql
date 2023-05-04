create table car_bodies(
	id serial primary key,
	name varchar(255)
);

create table car_engines(
	id serial primary key,
	name varchar(255)
);

create table car_transmissions(
	id serial primary key,
	name varchar(255)
);

create table car(
	id serial primary key,
	name varchar(255),
	body_id int references car_bodies(id),
	engine_id int references car_engines(id),
	transmission_id int references car_transmissions(id)
);

insert into car_bodies(name) values
	('Автобус'),
	('Пикап'),
	('Хэтчбек'),
	('Седан');
	
insert into car_engines(name) values
	('Дизельный'),
	('Электродвигатель'),
	('На газе'),
	('Бензиновый'),
	('Стирлинга');
	
insert into car_transmissions(name)	values
	('МКПП'),
	('АКПП'),
	('Вариатор'),
	('Роботизированая КП');
	
insert into car(name, body_id, engine_id, transmission_id) values
	('ISUZU BUS', 1, 1, 1),
	('Toyota Hi-Lux', 2, 2, 1),
	('Honda Accord', 4, 4, 2),
	('Nissan Leaf', 4, 2, 3),
	('Nissan Datsun', 2, 2, 2),
	('Kia Sorento', 2, null, 1),
	('Isuzu Bighorn', 2, 1, null);
-- 1. Вывести список всех машин и все привязанные к ним детали.
-- Нужно учесть, что каких-то деталей машина может и не содержать. 
-- В таком случае значение может быть null при выводе
create view car_park 
as select c.name as "car_name", b.name as "body_name", e.name as "engine_name", t.name as "transmission_name"
		from car as c
		left join car_bodies b on c.body_id = b.id
		left join car_engines e on c.engine_id = e.id
		left join car_transmissions t on c.transmission_id = t.id;
		
select * from car_park;

--2. Вывести кузова, которые не используются НИ в одной машине. 
select b.name from car_bodies b left join car c
on c.body_id = b.id where c.body_id isnull;
--3. Вывести двигатели, которые не используются НИ в одной машине.
select e.name from car_engines e left join car c
on c.engine_id = e.id where c.id isnull;
--4. Вывести коробки передач, которые не используются НИ в одной машине
select t.name from car_transmissions t left join car c
on c.transmission_id = t.id where c.id isnull;
