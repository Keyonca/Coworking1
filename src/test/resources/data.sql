INSERT INTO coworking.user (id, surname, first_name, last_name, email, password, phone_number, photo, form_of_work) VALUES (1, 'Петров', 'Петр', 'Петрович', 'petrov.p@gmail.ru', '123123123', '+79508888888', null, 'Работа в офисе');
INSERT INTO coworking.user (id, surname, first_name, last_name, email, password, phone_number, photo, form_of_work) VALUES (2, 'Иванов', 'Иван', 'Иванович', 'ivanov.v@gmail.ru', 'Qwerty123', '+79507914514', null, 'Работа в офисе');


insert into coworking.user_type(id, name) VALUES(1, 'Пользователь');
insert into coworking.user_type(id, name) VALUES(2, 'HR-менеджер');

insert into coworking.job_title(id, name) VALUES(1, 'Тимлид');
insert into coworking.job_title(id, name) VALUES(2, 'Backend-разработчик');
insert into coworking.job_title(id, name) VALUES(3, 'Frontend-разработчик');
insert into coworking.job_title(id, name) VALUES(4, 'Дизайнер');
insert into coworking.job_title(id, name) VALUES(5, 'Тестировщик');
insert into coworking.job_title(id, name) VALUES(6, 'Аналитик');

insert into coworking.workplace(id, name, is_anyone_sitting_here) VALUES(1, 'Рабочее место 1', false);
insert into coworking.workplace(id, name, is_anyone_sitting_here) VALUES(2, 'Рабочее место 2', false);
insert into coworking.workplace(id, name, is_anyone_sitting_here) VALUES(3, 'Рабочее место 1', true);

insert into coworking.booking_history(id, start_date_and_time, end_date_and_time) VALUES(1, '30-04-2024 14:00', '30-04-2024 16:00');
insert into coworking.booking_history(id, start_date_and_time, end_date_and_time) VALUES(2, '30-04-2024 16:00', '30-04-2024 17:00');
insert into coworking.booking_history(id, start_date_and_time, end_date_and_time) VALUES(3, '01-05-2024 10:00', '01-05-2024 13:00');


