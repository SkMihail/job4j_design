create table departments(
    id serial primary key,
    name varchar(255)
);

create table employees(
    id serial primary key,
    name varchar(255),
    department_id int references departments(id)
);

insert into departments(name) values ('Отдел 1');
insert into departments(name) values ('Отдел 2');
insert into departments(name) values ('Отдел 3');

insert into employees(name, department_id) values ('Сотрудник 1', 1);
insert into employees(name, department_id) values ('Сотрудник 2', 2);
insert into employees(name, department_id) values ('Сотрудник 3', 3);
insert into employees(name, department_id) values ('Сотрудник 4', null);
insert into employees(name, department_id) values ('Сотрудник 5', null);
insert into employees(name, department_id) values ('Сотрудник 6', 1);

--2. Выполнить запросы с left, right, full, cross соединениями
select d.name as "Отдел", e.name as "Сотрудник" from departments as d
left join employees e on d.id = e.department_id;

select d.name as "Отдел", e.name as "Сотрудник" from departments as d
right join employees e on d.id = e.department_id;

select d.name as "Отдел", e.name as "Сотрудник" from departments as d
cross join employees e;

--3. Используя left join найти департаменты, у которых нет работников
insert into departments(name) values ('Отдел 4');

select d.name as "Отдел", e.name as "Сотрудник" from departments as d
left join employees e on d.id = e.department_id where e.name isnull;

--4. Используя left и right join написать запросы, которые давали бы
--   одинаковый результат (порядок вывода колонок в эти запросах также
--   должен быть идентичный). 
select d.name as "Отдел", e.name as "Сотрудник" from departments as d
left join employees e on d.id = e.department_id;

select d.name as "Отдел", e.name as "Сотрудник" from employees e
right join departments as d on d.id = e.department_id;
