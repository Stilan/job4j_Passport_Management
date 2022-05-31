create table passport (
    id serial primary key,
    name TEXT,
    lastname TEXT,
    series int,
    number int,
    created date,
    UNIQUE (series, number)
);

