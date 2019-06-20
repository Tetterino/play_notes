# --- !Ups
create table categories(
    id integer not NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    name varchar(20) not NULL,
    created_at datetime not NULL,
    updated_at datetime not NULL
);

# --- !Downs
drop table categories;
