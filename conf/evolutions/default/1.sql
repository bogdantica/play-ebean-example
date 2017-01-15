

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

create table categorie(
  id    bigint not null,
  nume  VARCHAR(255),

  CONSTRAINT pk_categorie PRIMARY KEY (id)
);

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

create table judet (
  id  BIGINT not null,
  code  VARCHAR(255),
  judet VARCHAR(255),
  CONSTRAINT pk_judet PRIMARY KEY (id)

);

create sequence anunt_seq start with 1000;
create sequence comentariu_seq start with 1000;
create sequence judet_seq start with 1000;


alter table anunt add constraint fk_anunt_user_1 foreign key (user_id) references user(id) on delete restrict on update restrict;
create index ix_anunt_user_1 on anunt(user_id);

alter table anunt ADD CONSTRAINT fk_anunt_categorie_1 FOREIGN KEY (categorie_id)
  REFERENCES categorie(id) on DELETE restrict on update RESTRICT;

alter table anunt ADD CONSTRAINT fk_anunt_judet_1 FOREIGN KEY (judet_id)
  REFERENCES judet(id) on DELETE restrict on update RESTRICT;

CREATE index ix_anunt_categorie_1 on anunt(categorie_id);


alter table comentariu add constraint fk_comentariu_user_1
  foreign key (user_id) REFERENCES user(id) on DELETE RESTRICT on UPDATE  RESTRICT ;
create index ix_comentariu_user_1 on comentariu(user_id);

alter table comentariu add constraint fk_comentariu_anunt_1
  FOREIGN key (anunt_id) REFERENCES anunt(id) on DELETE RESTRICT on UPDATE RESTRICT ;


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




