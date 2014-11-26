drop database if exists restaurappdb;
create database restaurappdb;

use restaurappdb;

create table users (
	username				varchar(20) not null primary key,
	userpass				char(32) not null,
	nombre					varchar(70) not null,
	email					varchar(255) not null,
	provincia				varchar(70) not null,
	contador_O_U			int not null,
	foto					varchar(255) not null	
);

create table user_roles (
	username				varchar(20) not null,
	rolename 				varchar(20) not null,
	foreign key(username)	references users(username) on delete cascade,
	primary key (username, rolename)
);

create table restaurantes (
	idrestaurante 			int not null auto_increment primary key,
	nombre 					varchar(20) not null,
	direccion				varchar(100) not null,
	telefono				varchar(500),
	email					varchar (200),
	horario					varchar (70),
	categoria				varchar (100) not null,
	creador					varchar (20) not null,
	provincia				varchar (70) not null,
	foreign key(creador)	references users(username)
	
);


create table opiniones (
	idopinion				int not null auto_increment primary key,
	idrestaurante			int not null,
	titulo					varchar(70) not null,
	puntuacion				int not null,
	texto					varchar(250) not null,
	mes_estancia			varchar(100) not null,	
	username				varchar(20) not null,
	cont_utilidad			int not null,
	cont_noutilidad			int not null,
	creation_timestamp		datetime not null default current_timestamp,
	foreign key(username)	references users(username) on delete cascade

);


create table fotos (
	idfoto					int not null auto_increment primary key,
	idrestaurante			int not null,
	ubi_foto				varchar(70) not null,
	texto					varchar(150) not null,
	username				varchar(20),
	foreign key(username)	references users(username)

);
