insert into fauna (name, avg_age, discovery_date) values 
   ('Остромордый карась fish', 4380, '1930-01-01'),
   ('Желтохохлый спинорог fish', 1825, '1965-01-01'),
   ('Краснохвостый цапля', 3650, '1895-01-01'),
   ('Восточноамурская черепаха', 14600, '1884-01-01'),
   ('Гимнокалицея желтая', 14600, '1970-01-01'),
   ('Водомерка веслоносная', 1095, null);
   
select * from fauna;
select * from fauna where name like '%fish';
select * from fauna where avg_age > 10000 and avg_age < 21000;
update fauna set discovery_date = null where name = 'Водомерка веслоносная';
delete from fauna where name = 'Водомерка веслоносная';
insert into fauna (name, avg_age, discovery_date) values 
   ('Водомерка веслоносная', 1095, null);
select * from fauna where discovery_date isnull;
select * from fauna where discovery_date < '1950-01-01';



   