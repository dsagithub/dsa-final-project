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

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Al Natural','Arquitecte Rovira, 3','977 21 64 54','alnatural@hotmail.com','9:00-23:00','Otros','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Asador de Montgat','Avinguda Jordana','934691870','asador.montgat@hotmail.com','12:00-23:00','Española','javi','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El Somni','Placa la Doma, 1','972652732','elsomni.stantoni@hotmail.com','12:00-23:00','Catalana','javi','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Tagliatella','Calle Temple, 9','933899526','','12:00-23:00','Otros','uri','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Yoshinoya-8','Rambla del Poblenou, 153','933 300 303','yoshinoya-8@hotmail.com','12:30-16:30h y de 20:30-24h','Japonesa','juan','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Xalet Suis','Calle Rovira Roure, 9','933899526','xaletsuis@hotmail.com','12:30-15:30h y de 20:30-23:30h','Catalana','juan','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Gran Muralla','Francesc Eiximenis nº 15-17','972218713','xaletsuis@hotmail.com','12:30-15:30h y de 20:30-23:30h','Catalana','xurtasun','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Bar 1900','Passeig Estacio 49','933559826','','8:30-23:30','Americana','jose','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Maitanqui','Carrer Miquel Rosset numero 6','933899526','','mar - dom 18:00 - 3:00','Tailandesa','javi','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Tian An-Men','Marques de Montoliu, 2','977245404','','dom - sáb 12:00 - 16:30 y de 20:00 - 0:00','China','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('La Cuina de la Loli','Carrer de Olot,26','936 01 04 66','','9:00 - 17:00','Catalana','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El vi de deu','carrer major, 31','931 73 03 09','','dom - sáb 12:00 - 16:30 y de 20:00 - 0:00','Catalana','javi','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurant Montiel','C/ Flassaders, 19,','932 68 37 29','','dom - sáb 12:00 - 16:30 y de 20:00 - 0:00','Catalana','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurante Lasarte','C/ Mallorca, 259','93 445 32 42','','12:00 - 16:30 y de 20:00 - 0:00','Española','javi','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Bambarol','Carrer Santalo, 21','932507074','bambarol@hotmail.com','19:30 - 23:30','Española','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Arcano','Carrer dels Mercaders, 10','932 95 64 67','','20:00 - 0:00','Española','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Son Hao','Carrer de Muntaner, 66','934 53 83 03','','12:00 - 16:30 y de 20:00 - 23:00','China','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Chen Ji','Ali Bei, 65','932 476 831','','8:00 - 0:00','China','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurante Chino Hoy','Travesera de les Corts, 281','934300079','','12:00 - 0:00','China','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Petit Bangkok','c. Vallirana, 29','616185196','','13:00 - 15:30','Tailandesa','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('KIN THAI','Garcilaso, 4','977245404','','12:00 - 16:30 y de 20:00 - 0:00','Tailandesa','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Thailandes Thai Restaurant','Avd. Diagonal, 379','935663116','thairest@hotmail.com','12:00 - 16:30 y de 20:00 - 0:00','Tailandesa','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('klein84','C/ Sant Esteve, 84','937 61 31 86','','20:00 - 0:00','Americana','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Taco Love','Calle Venecuela, 3','937545704','','14:00 - 0:00','Americana','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Koy Shunka','Carrer de Copons','93 412 79 39','','12:00 - 16:30 y de 20:00 - 0:00','Japonesa','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Koku Kitchen','C/ Carabassa, 19','933 156 411','','12:00 - 16:30 y de 20:00 - 0:00','Japonesa','manu','Barcelona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El Rincón del Bierzo','Calle Sant Pere , 16','977 69 18 82','','12:00 - 16:30 y de 20:00 - 0:00','Española','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('La Tortillita','Carrer Escaladei, 2','977 38 45 64','','12:00 - 16:30 y de 20:00 - 0:00','Española','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Portal 22','Plaza Portal Nou, 22','977 62 21 99','','12:00 - 16:30 y de 20:00 - 0:00','Española','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Brots Restaurant','C/ Nou, 45','977 82 73 28','','12:00 - 16:30 y de 20:00 - 0:00','Catalana','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Les Barques Can Joan','Plaza San Pere ,6','977 73 58 85','','12:00 - 16:30 y de 20:00 - 0:00','Catalana','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Cal Ganxo','Carrer de la Font F, 9','977 34 44 58','','12:00 - 16:30 y de 20:00 - 0:00','Catalana','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Casa Shanghai','Avenida de Juan XXIII, 1','977363906','','12:00 - 16:30 y de 20:00 - 0:00','China','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Mei Mei','Barcelona, 40','977385434','','12:00 - 16:30 y de 20:00 - 0:00','China','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Chino Fulin','Barcelona, 29','977 12 46 61','','12:00 - 16:30 y de 20:00 - 0:00','China','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Little Bangkok','Vallroquetes ,5','616 862 713','','12:00 - 16:30 y de 20:00 - 0:00','Tailandesa','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Thai Salou Restaurant','Carrer de Barbastre ,13','977 35 04 06','','12:00 - 16:30 y de 20:00 - 0:00','Tailandesa','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Nou Mon Lin','C/. Via Augusta, 2','977 225631','','12:00 - 16:30 y de 20:00 - 0:00','Tailandesa','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Harleys & Blues','Avenida Tarragona, 15','977 16 15 63','','12:00 - 16:30 y de 20:00 - 0:00','Americana','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Hollywood Beach Calafell','Avinguda Sant Joan de Deu, 38','977 69 96 29','','12:00 - 16:30 y de 20:00 - 0:00','Americana','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Western Bar Cheers Cambrils','Roger de Lluria, 20','680 30 22 43','','12:00 - 16:30 y de 20:00 - 0:00','Americana','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Ajumma Sushi Bar','Carrer Galio ,12','977 756260','','12:00 - 16:30 y de 20:00 - 0:00','Japonesa','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Sushi Kai Salou','C/ Valencia, 4','977 35 42 98','','12:00 - 16:30 y de 20:00 - 0:00','Japonesa','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Sushi Ya','Vidal i Barraquer, 61','977750432','','12:00 - 16:30 y de 20:00 - 0:00','Japonesa','manu','Tarragona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Er Occitan',' Calle Major, 66','973 64 73 66','occitanl@hotmail.com','8:00-22:00','Tailandesa','manu','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El fogony','Avda. Generalitat, 45','973621225','fogony@gmail.com','12:00-23:00','Catalana','javi','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurant Mare de la Font','Avinguda Blondel, 29','973621223','Mare@hotmail.com','11:00-24:00','Americana','javi','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurant PKtus',' Carrer de la Creu 16','973 64 73 55','pk@hotmail.com','8:00-22:00','Tailandesa','manu','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurant Fermin','Ctra. de Bassella, 26','973221225','fermin@gmail.com','12:00-23:00','Catalana','juan','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurant d4','Doctor fleming 49','972321223','d@hotmail.com','11:00-24:00','Americana','juan','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Xalet Suis','Calle Rovira Roure, 9','923 45 73 66','suis@hotmail.com','9:00-23:00','Tailandesa','jose','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Restaurant txakoli','Calle des Comets, 4','973621335','tx@gmail.com','13:00-23:00','Catalana','uri','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El Forn Restaurante','Canal, 2','973321443','fr@hotmail.com','9:00-22:00','Americana','javi','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Cal Xirricló','C/ Doctor Fleming, 53','923 45 73 55','cal@hotmail.com','12:00-23:00','China','xurtasun','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Forn Rosa Serra','Ctra. De Ponts, 47','973622235','rosa@gmail.com','13:00-24:00','China','xurtasun','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El Forn Restaurante','Canal, 2','973321443','fr@hotmail.com','9:00-22:00','China','manu','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Pinotage Restaurante and Cafe','Avenguda Carretera 7','923 45 73 22','caand@hotmail.com','14:00-23:00','Española','javi','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Era Coquela','Av. Garona, 29','953633235','era@gmail.com','13:00-24:00','Española','javi','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Nabiu','Nabiu','973321773','nab@hotmail.com','14:00-22:00','Española','manu','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Arbeletxe','Sant Ermengol, 22','993 45 73 33','arbe@hotmail.com','8:00-23:00','Japonesa','uri','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('estel de la Merce','Cardenal Cisneros, 30','953239935','estel@gmail.com','12:00-22:00','Japonesa','david','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('La Tarteria','Calle Deth Fort 8','923221773','tarteria@hotmail.com','10:00-22:00','Japonesa','manu','Lleida');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El Celler de Can Roca','Carrer de Can Sunyer, 48','972 222 157','elceller@hotmail.com','8:00-23:00','Catalana','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('La Lonja','Carrer Sant Tomás, 11','972 36 74 99','lonja@hotmail.com','12:00-23:00','Española','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('El Barroco','Carrer Pla den Retalla 2','972258632','elbarroco@hotmail.com','8:00-23:00','Española','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Bar Informal','Calle Moxo Numero, 6','606 35 53 93','informal_bar@hotmail.com','19:00-23:00','Española','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Pagoda','Enric Granados, 8','972 341 955','pagoda@hotmail.com','8:00-23:00','China','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Dong Xing','Cami Carlins, 10','993 49 26 33','yichin@hotmail.com','8:00-23:00','China','javi','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('BAHIA DE ORO','Paseo Maritimo Gran Reserva, 1','972451951','bhoro@hotmail.com','8:00-23:00','China','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Maitanqui','Carrer Miquel Rosset, 6','972 25 85 98','maitanqui@hotmail.com','8:00-23:00','Tailandesa','manu','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Siam Shiki','Av Rei Joan Carles I, 4','972 452 951','siam@hotmail.com','8:00-23:00','Tailandesa','manu','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Pattaya','Avenue Joan Carlos, 17','972 453 078','','10:00-23:00','Tailandesa','manu','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('La Parrilla','C/ Oriente, 12','619 80 32 59','','8:00-23:00','Americana','manu','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('piccadily bistro','Paseo de Agustín Font','993 45 73 33','','8:00-23:00','Americana','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Texas Ranch','Passage Sabanell, 2','972 338 034','texasranch@hotmail.com','8:00-23:00','Americana','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('haiku','Pianc, 3','625 02 10 33','haiku@hotmail.com','9:00-23:00','Japonesa','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Tokio Teppanyaki','C/ Ferran Puig, 26','645 45 73 33','teppan@hotmail.com','18:00-23:00','Japonesa','uri','Girona');

select sleep(1);insert into restaurantes ( nombre, direccion, telefono, email, horario, categoria, creador, provincia) values ('Sushi la riba','Placa de la Independencia, 12','872 081 490','','8:00-23:00','Japonesa','uri','Girona');


insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (1,'Muy buen servicio','7','Es un restaurante muy agradable, la comida muy buena','Febrero','xurtasun','0','0');  

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (1,'El servicio no esta mal','5','La comida esta bien, pero la ubicacion es inaccesible','Abril','javi','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (3,'Excelente restaurante','10','El restaurante esta genial','Junio','manu','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (5,'Poca variedad','4','La carta es muy limitada en cuanto a contenido','Diciembre','juan','0','0'); 

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (13,'Gran servicio','10','El restaurante esta genial, me ha gustado mucho','Junio','javi','0','0'); 

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (22,'Excelente restaurante','10','El restaurante esta genial, gran servicio','Agosto','javi','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (32,'No esta mal','6','El restaurante esta bien pero ya esta','Noviembre','javi','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (32,'Excelente restaurante','10','El restaurante esta genial, gran servicio','Agosto','manu','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (7,'No me ha gustado nada','3','La comida esta mala y sabe a tierra','Agosto','manu','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (25,'No esta mal','7','De primero ensalada, de segundo carne puuum','Enero','manu','0','0');

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (18,'La vieja','5','Muy recomendable por la vieja','Agosto','manu','0','0'); 

insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (18,'Buen serivicio','5','Comida normalita, servicio muy bueno','Julio','david','0','0'); 
  
insert into opiniones ( idrest, titulo, puntuacion, texto, mes_estancia, username, cont_utilidad, cont_noutilidad) values (15,'Me ha gustado','7','Comida muy buena, servicio un poco lento','Febrero','javi','0','0');     
  
  
    
  







