insert into users values('xurtasun', MD5('urtasun'), 'Xavier Urtasun Lopez', 'xavi.urta@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('xurtasun', 'registered');

insert into users values('admin', MD5('admin'), 'Administrator', 'admin@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('admin', 'administrador');

insert into users values('javi', MD5('javi'), 'Javier Cancer', 'javi_cancer@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('javi', 'registered');

insert into users values('manu', MD5('manu'), 'Manuel Leiva', 'manuel_leiva@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('manu', 'registered');

insert into users values('juan', MD5('juan'), 'Juan Lopez', 'juan_lopez@hotmail.com','Girona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('juan', 'registered');

insert into users values('jose', MD5('jose'), 'Jose Breton', 'breton_campeon@yahoo.com','Tarragona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('jose', 'registered');

insert into users values('uri', MD5('uri'), 'Oriol Consuegra', 'o_consu@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('uri', 'registered');

insert into users values('david', MD5('david'), 'David Hernandez', 'david.h@gmail.com','Barcelona',0,'my_photo.jpg');
insert into user_roles (username, rolename) values ('david', 'registered');

insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('SantBoapanzinha','Ronda de Sant Ramon, 33','936667343','Boi@hotmail.com','10:00-23:00','Americana','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Al Natural','Arquitecte Rovira, 3, 43001 Tarragona, España','977 21 64 54','alnatural@hotmail.com','9:00-23:00','Otros','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Asador de Montgat','Avinguda Jordana','934691870','asador.montgat@hotmail.com','12:00-23:00','Española','javi','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El Somni','Placa la Doma, 1, 17251 Calonge','972652732','elsomni.stantoni@hotmail.com','12:00-23:00','Catalana','javi','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Tagliatella','Calle Temple, 9, Badalona, Barcelona','933899526','','12:00-23:00','Otros','uri','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Yoshinoya-8','Rambla del Poblenou, 153','933 300 303','yoshinoya-8@hotmail.com','12:30-16:30h y de 20:30-24h','Japonesa','juan','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Xalet Suis','Calle Rovira Roure, 9, 25006 Lleida, España','933899526','xaletsuis@hotmail.com','12:30-15:30h y de 20:30-23:30h','Catalana','juan','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Gran Muralla','Francesc Eiximenis nº 15-17, 17005 Girona, España','972218713','xaletsuis@hotmail.com','12:30-15:30h y de 20:30-23:30h','Catalana','xurtasun','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Bar 1900','Passeig Estacio 49, Baixos, 25600 Balaguer, España','933559826','','8:30-23:30','Americana','jose','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Maitanqui','Carrer Miquel Rosset numero 6, 17488 Cadaqués, España','933899526','','mar - dom 18:00 - 3:00','Tailandesa','javi','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Tian An-Men','Marques de Montoliu, 2, 43002 Tarragona, España','977245404','','dom - sáb 12:00 - 16:30 y de 20:00 - 0:00','China','manu','Tarragona');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (1,'Muy buen servicio','7','Es un restaurante muy agradable, la comida muy buena','Febrero','xurtasun','0','0');  

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (1,'El servicio no esta mal','5','La comida esta bien, pero la ubicacion es inaccesible','Abril','javi','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (3,'Excelente restaurante','10','El restaurante esta genial','Junio','manu','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (5,'Poca variedad','4','La carta es muy limitada en cuanto a contenido','Diciembre','juan','0','0');  
  
  







