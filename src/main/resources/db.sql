create table public.bmi
(
    id      serial
        constraint bmi_pk
            primary key,
    weight  integer          not null,
    height  integer,
    bmi     double precision not null,
    name    text             not null,
    verdict text             not null,
    created timestamp        not null
);