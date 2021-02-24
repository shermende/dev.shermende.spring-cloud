create table if not exists translate
(
    id         bigint primary key auto_increment,
    created_at timestamp     not null default now(),
    updated_at timestamp,
    locale     varchar(255)  not null,
    key        varchar(255)  not null,
    value      varchar(2048) not null,
    unique (locale, key)
);

create table if not exists movement_reason
(
    id         bigint primary key auto_increment,
    created_at timestamp not null default now(),
    updated_at timestamp
);

create table if not exists movement_point
(
    id         bigint primary key auto_increment,
    created_at timestamp not null default now(),
    updated_at timestamp
);

create table if not exists quest
(
    id         bigint primary key auto_increment,
    created_at timestamp not null default now(),
    updated_at timestamp
);

create table if not exists movement_scenario
(
    id         bigint primary key auto_increment,
    created_at timestamp not null default now(),
    updated_at timestamp,
    reason_id  bigint    not null,
    point_id   bigint    not null
);

create table if not exists m2m_point_quest
(
    point_id bigint not null,
    quest_id bigint not null
);

insert into translate(locale, key, value)
values ('en', 'MovementPointModel.1', 'MovementPointModel.1'),
       ('en', 'MovementReasonModel.1', 'MovementReasonModel.1')
;

insert into movement_reason(id, created_at)
values (1, now()); -- move
insert into movement_reason(id, created_at)
values (2, now()); -- accept quest
insert into movement_reason(id, created_at)
values (3, now()); -- complete quest

insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());
insert into movement_point(created_at)
values (now());

insert into quest(created_at)
values (now());

insert into m2m_point_quest(point_id, quest_id)
values (1, 1);

insert into movement_scenario(reason_id, point_id)
values (1, 1);