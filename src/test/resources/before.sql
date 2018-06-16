delete from soldiers;
delete from users;
#insert into users (id, username, password) values (1, 'user', 'password');

insert into users (id, username, password) values (2, 'player', 'password');
insert into soldiers (id, name, user_id) values (1, 'soldier', 2);