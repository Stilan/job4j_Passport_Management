create table passport (
    id serial primary key,
    name TEXT,
    lastname TEXT,
    series int,
    number int,
    expiration_date date,
    UNIQUE (series, number)
);

select * from passport;
drop table passport;
