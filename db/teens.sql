create table teens(
    id serial primary key,
    name varchar(255),
	gender varchar(255)
);

insert into teens(name, gender) values
('teen1', 'F'),
('teen2', 'F'),
('teen3', 'M'),
('teen4', 'F'),
('teen5', 'M'),
('teen6', 'M');

--5. Создать таблицу teens с атрибутами name, gender и заполнить ее. 
--   Используя cross join составить все возможные разнополые пары
select t1.name, t2.name from teens t1 cross join teens t2 
where t1.id < t2.id and t1.gender <> t2.gender;
