insert into users values('xurtasun', MD5('urtasun'), 'Xavier Urtasun Lopez', 'xavi.urta@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('xurtasun', 'registered');

insert into users values('admin', MD5('admin'), 'Administrator', 'admin@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('admin', 'administrador');

insert into users values('javi', MD5('javi'), 'Javier Cancer', 'javi_cancer@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('javi', 'registered');

insert into users values('manu', MD5('manu'), 'Manuel Leiva', 'manuel_leiva@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('manu', 'registered');

insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('SantBo','Ronda Sant Ramon n33','936667343','Boi@hotmail.com','10:00-23:00','Catalana','Manu','Barcelona');
select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('VilaRes','Calle Vilarrovi n33','936647343','Vila@hotmail.com','9:00-23:00','China','Manu','Tarragona');