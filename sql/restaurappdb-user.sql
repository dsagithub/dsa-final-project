drop user 'restapp'@'localhost';
create user 'restapp'@'localhost' identified by 'restapp';
grant all privileges on restaurappdb.* to 'restapp'@'localhost';
flush privileges;