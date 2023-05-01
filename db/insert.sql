insert into role (role_name)
values ('admin'), ('engener'), ('manager');

insert into rules (rule)
values ('удаление'),
       ('добавление'),
       ('изменение'),
	   ('чтение');

insert into users (surname, role_id)
values ('Иванов', 1), ('Петров', 2), ('Сидоров', 3);

insert into roles_rules (role_id, rules_id) VALUES
    (1, 1), (1, 2), (1, 3), (1, 4),
    (2, 2), (2, 3), (2, 4),
	(3, 4);

insert into category (cat_name)
values ('авария'), ('новая'), ('плановый ремонт');

insert into state (state_name)
values ('открыта'), ('выполняется'), ('закрыта');

insert into item (item_title, user_id, category_id, state_id)
values ('Заявка на проведение планового ремонта', 2, 3, 1),
       ('Заявка на устранение аварии', 3, 1, 3),
       ('Новая заявка на подключение нового пользователя', 1, 2, 2);

insert into attachs (item_id, attachs_path)
values (1, '/путь/к/приложенному/файлу1'),
       (2, '/путь/к/приложенному/файлу2'),
       (3, '/путь/к/приложенному/файлу3');

insert into comments (item_id, comments_text)
values (1, 'Ремонт нужно выполнить до конца месяца'),
       (1, 'низкий уровень сигнала КТВ'),
       (2, 'не работает свитч по адресу ...'),
	   (2, 'инженер поднял свитч, был выключен автомат');