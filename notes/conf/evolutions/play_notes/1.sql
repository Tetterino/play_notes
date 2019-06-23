# --- !Ups
create table categories(
    id integer not NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    name varchar(20) not NULL,
    created_at datetime not NULL,
    updated_at datetime not NULL
);

create table notes(
    id integer not NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    title varchar(20) not NULL,
    description text(500) not NULL,
    category_id integer,
    created_at datetime not NULL,
    updated_at datetime not NULL,

    foreign key (category_id)
       references categories(id)
       on delete set null
);
