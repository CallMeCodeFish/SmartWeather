create table if not exists user
(
    id int auto_increment,
    name varchar(50) null,
    account_type int null,
    account_id varchar(50) null,
    avatar_url varchar(100) null,
    token char(36) null,
    created_at bigint null,
    updated_at bigint null,
    constraint user_pk
        primary key (id)
);

create table if not exists recommendation
(
    id int auto_increment,
    type varchar(50) not null,
    text varchar(256) not null,
    weather_code int not null,
    weather_main varchar(8) not null,
    constraint recommendation_pk
        primary key (id)
);