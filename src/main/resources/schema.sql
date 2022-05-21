create schema scores;

create table scores.user
(
    id   bigserial
        constraint user_pk
            primary key,
    name varchar
);

create unique index user_id_uindex
    on scores.user (id);
