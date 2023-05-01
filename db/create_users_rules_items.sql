create table role(
	id serial primary key,
	role_name varchar(255)
);

create table rules(
	id serial primary key,
	permissions varchar(255),
	restriction varchar(255)
);

create table users(
	id serial primary key,
	surname varchar(255),
	role_id int references role(id)
);

create table roles_rules(
    id serial primary key,
    rules_id int references rules(id),
    role_id int references role(id)
);
	
create table category(
    id serial primary key,
    cat_name varchar(255)
);

create table state(
    id serial primary key,
    state_name varchar(255)
);

create table item(
    id serial primary key,
    item_title varchar(255),
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);
   
create table attachs(
	id serial primary key,
	item_id int references item(id),
	attachs_path varchar(255)
);

create table comments(
	id serial primary key,
	item_id int references item(id),
	comments_text text
);
