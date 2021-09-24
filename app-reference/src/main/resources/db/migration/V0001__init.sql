create table if not exists translate
(
    id         bigserial primary key,
    created_at timestamp     not null default now(),
    updated_at timestamp,
    locale     varchar(255)  not null,
    key        varchar(255)  not null,
    value      varchar(2048) not null,
    unique (locale, key)
);

create table if not exists movement_reason
(
    id         bigserial primary key,
    created_at timestamp    not null default now(),
    updated_at timestamp,
    intro      varchar(255) not null
);

create table if not exists movement_point
(
    id         bigserial primary key,
    created_at timestamp    not null default now(),
    updated_at timestamp,
    intro      varchar(255) not null
);

create table if not exists movement_scenario
(
    id         bigserial primary key,
    created_at timestamp not null default now(),
    updated_at timestamp,
    reason_id  bigint    not null,
    point_id   bigint    not null
);

create table if not exists movement_route
(
    id              bigserial primary key,
    created_at      timestamp not null default now(),
    updated_at      timestamp,
    source_point_id bigint    not null,
    reason_id       bigint    not null,
    target_point_id bigint    not null,
    unique (source_point_id, reason_id)
);

insert into translate(locale, key, value)
values ('en', 'MovementPointModel.1', 'MovementPointModel.1'),
       ('en', 'MovementReasonModel.1', 'MovementReasonModel.1')
;

insert into movement_reason(created_at, intro)
values (now(), 'ощущая урчание в животе, вы отправляетесь в путь.');

insert into movement_point(created_at, intro)
values (now(), 'придя вы видите у костра обглоданные кости');

insert into movement_scenario(reason_id, point_id)
values (1, 1);

insert into movement_route(id, source_point_id, reason_id, target_point_id)
values (1, 1, 1, 1),
       (2, 1, 2, 2);
