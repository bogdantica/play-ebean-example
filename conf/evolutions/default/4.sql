
# --- !Ups


alter table anunt add constraint fk_anunt_user_1 foreign key (user_id) references user(id) on delete restrict on update restrict;
  create index ix_anunt_user_1 on anunt(user_id);

ALTER TABLE anunt ADD CONSTRAINT fk_anunt_categorie_1 FOREIGN KEY (categorie_id)
  REFERENCES categorie(id) ON DELETE RESTRICT ON UPDATE RESTRICT ;

CREATE INDEX ix_anunt_categorie_1 on anunt(categorie_id);


alter table anunt ADD CONSTRAINT fk_anunt_judet_1 FOREIGN KEY (judet_id)
  REFERENCES judet(id) on DELETE restrict on update RESTRICT;



alter table comentariu add constraint fk_comentariu_user_1
  foreign key (user_id) REFERENCES user(id) on DELETE RESTRICT on UPDATE  RESTRICT ;
create index ix_comentariu_user_1 on comentariu(user_id);

alter table comentariu add constraint fk_comentariu_anunt_1
  FOREIGN key (anunt_id) REFERENCES anunt(id) on DELETE RESTRICT on UPDATE RESTRICT ;



create sequence anunt_seq start with 1000;
create sequence comentariu_seq start with 1000;
create sequence judet_seq start with 1;
create sequence user_seq start with 1000;
CREATE SEQUENCE categorie_seq START WITH 1000;



