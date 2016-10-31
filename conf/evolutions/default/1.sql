# --- First database schema

# --- !Ups

create table company (
  id                        bigint not null,
  name                      varchar(255),
  constraint pk_company primary key (id))
;

create table computer (
  id                        bigint not null,
  name                      varchar(255),
  introduced                timestamp,
  discontinued              timestamp,
  company_id                bigint,
  constraint pk_computer primary key (id))
;

create sequence company_seq start with 1000;

create sequence computer_seq start with 1000;

alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
create index ix_computer_company_1 on computer (company_id);

create table species (
    id                      bigint not null,
    local_name              varchar(255),
    scientific_name         varchar(255),
    documented              timestamp,
    can_fly                 boolean,
    is_extinct              boolean,
    genus_id                bigint,
    constraint pk_species primary key (id)
);

create table genus (
    id                      bigint not null,
    scientific_name         varchar(255),
    constraint pk_genus primary key (id)
);

create sequence species_seq start with 1000;

create sequence genus_seq start with 1000;

alter table species add constraint fk_species_genus_1 foreign key (genus_id) references genus (id) on delete restrict on update restrict;
create index ix_species_genus_1 on species (genus_id);


# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists company;

drop table if exists computer;

drop table if exists species;

drop table if exists genus;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists company_seq;

drop sequence if exists computer_seq;

drop sequence if exists species_seq;

drop sequence if exists genus_seq;


