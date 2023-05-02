create table meteo_parametr(
    id serial primary key,
    parametr varchar(255)
);

create table people(
    id serial primary key,
    meteo_ependency int references meteo_parametr(id)
);

alter table people add column name varchar(255);

insert into meteo_parametr(parametr) values
('temperature'),
('atmospheric pressure'),
('magnetic storm');

select * from meteo_parametr;

insert into people (name, meteo_ependency) values
('Jhon', 1),
('Silvia', 1),
('Radmon', 3),
('Gorgia', 2);

select * from people;

select * from people join meteo_parametr on people.meteo_ependency=meteo_parametr.id;

select mp.parametr, p.name from meteo_parametr as mp join people as p on mp.id = p.meteo_ependency;

select p.name as "Имя" , mp.parametr "параметр" from meteo_parametr as mp join people as p on mp.id = p.meteo_ependency;

