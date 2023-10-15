-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, active, password)
values(1, 'admin@gmail.com', 'Admin', 'Admin', null, null, null, null, true, 'adminPwd123@');

-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, active, password)
values(2, 'test123@test.com', 'John', 'Doe', 'New York', 'Wall Street1', '1234567890', '1234567890', true, 'user1Pwd123@');

-- password: admin
insert into users(id, email, first_name, last_name, city, address, phone_number, post_index, active, password)
values(3, 'ivan123@test.com', 'Ivan', 'Ivanov', 'Moscow', 'Tverskaya street 1', '1234567890', '1234567890', true, 'user2Pwd123@']);

insert into user_role (user_id, roles) values (1, 'ADMIN');
insert into user_role (user_id, roles) values (2, 'USER');
insert into user_role (user_id, roles) values (3, 'USER');
