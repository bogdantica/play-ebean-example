

# --- First database schema

# --- !Ups

create table anunt(
  id      bigint not null ,
  titlu     varchar(255),
  categorie_id bigint not null,
  pret      double,
  localitate varchar(255),
  judet_id varchar(255),
  telefon varchar(255),
  afisari bigint,
  user_id bigint ,
  poza varchar(255),
  poza2 varchar(255),
  detalii varchar(255),
  dataora TIMESTAMP,

  constraint pk_anunt primary key (id)

);

-- create table categorie(
--   id    bigint ,
--   nume  VARCHAR(255),
--
--   CONSTRAINT pk_categorie PRIMARY KEY (id)
-- );

create table categorie(
  id    BIGINT NOT NULL ,
  nume  VARCHAR(255),

  CONSTRAINT pk_categorie  PRIMARY KEY(id)
);

insert into categorie (id, nume) values ( 1000, 'Auto');
insert into categorie (id, nume) values ( 1001, 'Moto');
insert into categorie (id, nume) values (  1002,'Electrocasnice');
insert into categorie (id, nume) values (  1003,'IT');
insert into categorie (id, nume) values (  1004,'Hobby');

create table comentariu (
  id  bigint not null,
  user_id bigint not null,
  anunt_id bigint not null,
  dataora timestamp,
  comentariu  varchar(255),

  constraint pk_comentariu primary key(id)
);

create table user(
  id  bigint not null,
  nume  varchar(255),
  prenume varchar(255),
  telefon varchar(255),
  parola  varchar(255),
  email   varchar(255),
  rol varchar(255),

  constraint pk_user primary key(id)
);

insert into user(id,nume,prenume,email,parola) values (1000,'Tica', 'Bogdan','admin@admin.com','admin123');
insert into user(id,nume,prenume,email,parola) values (1010,'Tica', 'Agnus','utilizator@email.com','admin123');

insert into anunt (id,titlu,categorie_id,pret,detalii,poza,judet_id,afisari,user_id) values ( 1000,'Skoda Octavia 2',1000,34200,'Functioneaza impecabil','skoda.jpg',2,0,1010);
insert into anunt (id,titlu,categorie_id,pret,detalii,poza,judet_id,afisari,user_id) values ( 1002,'Alfa Romeo',1000,2405,'Mici probleme','alfa.jpg',5,0,1010);
insert into anunt (id,titlu,categorie_id,pret,detalii,poza,judet_id,afisari,user_id) values ( 1003,'Mercedes',1000,8405,'Perfecta stare','mercedes.jpg',6,0,1010);

insert into anunt (id,titlu,categorie_id,pret,detalii,poza,judet_id,afisari,user_id) values ( 1004,'Ducati 2',1001,34200,'Functioneaza impecabil','ducati.jpg',8,0,1000);
insert into anunt (id,titlu,categorie_id,pret,detalii,poza,judet_id,afisari,user_id) values ( 1005,'Suzuki',1001,2405,'Mici probleme','suzuki.jpg',9,0,1000);
insert into anunt (id,titlu,categorie_id,pret,detalii,poza,judet_id,afisari,user_id) values ( 1006,'BMW',1001,8405,'Perfecta stare','bmw.jpg',10,0,1000);


create table judet (
  id  BIGINT not null,
  code  VARCHAR(255),
  judet VARCHAR(255),
  CONSTRAINT pk_judet PRIMARY KEY (id)

);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists anunt;

drop table if exists comentariu;

drop table if EXISTS categorie;

drop table if exists user;

drop table if exists judet;



SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists anunt_seq;

drop sequence if exists comentariu_seq;

drop sequence if exists user_seq;

drop sequence if exists judet_seq;

drop sequence if exists categorie_seq;





