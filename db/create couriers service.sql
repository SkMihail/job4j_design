create table couriers(
	id serial primary key,
	surname varchar(255),
	phone varchar(20)
);

create table client_list(
	id serial primary key,
	surname varchar(255),
	phone varchar(20)
);

create table order_list(
	id serial primary key,
	destination varchar(255),
	recepient_id int references client_list(id),
	sender_id int references client_list(id),
	status varchar(255)
);

create table currient_orders(
	id serial primary key,
	executer_courerier_id int references couriers(id) unique,
	currient_order_id int references order_list(id) unique
);

create table regions(
	id serial primary key,
	area_name varchar(255)
);

create table couriers_regions(
	couriers_id int references couriers(id),
	regions_id int references regions(id)
);

