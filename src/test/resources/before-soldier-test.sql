delete from soldiers;
delete from users;

insert into users (id, username, password) values (1, 'player', 'password');
insert into soldiers (id, name, user_id) values (1, 'soldier', 1),(3, 'toBeDeleted', 1);