create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table buyers(
    id serial primary key,
    name varchar(255)
);

create table devices_buyers(
    id serial primary key,
    device_id int references devices(id),
    buyers_id int references buyers(id)
);

insert into devices(name, price) values 
('smartphone', 10000),
('tv', 45000),
('mixer', 3000),
('headphone', 1000);

insert into buyers(name) values 
('Ivan'),
('Olga'),
('Mikhail'),
('Daria');

insert into devices_buyers(device_id, buyers_id) values
(1, 1), (4, 1),
(2, 3),
(3, 2), (2, 2),
(1, 4), (2, 4), (3, 4), (4, 4);

select avg(price) from devices;

select buyers.name, avg(devices.price) as avg_price
from buyers
join devices_buyers on devices_buyers.buyers_id = buyers.id
join devices on devices_buyers.device_id = devices.id
group by buyers.name;

select buyers.name, avg(devices.price) as avg_price
from buyers
join devices_buyers on devices_buyers.buyers_id = buyers.id
join devices on devices_buyers.device_id = devices.id
group by buyers.name
having avg(devices.price) > 6000;