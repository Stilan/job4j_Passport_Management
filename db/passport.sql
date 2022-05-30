create table passport (
    id serial primary key not null,
    name varchar(2000),
    lastname varchar(2000),
    series int,
    created date
);

select * from passport;

drop table passport;
