create table if not exists point_description
(
    id          bigserial primary key,
    created_at  timestamp not null default now(),
    updated_at  timestamp,
    game_id     bigint    not null,
    point_id    bigint    not null,
    intro       varchar(4096),
    description text
);

create table if not exists reason_description
(
    id          bigserial primary key,
    created_at  timestamp not null default now(),
    updated_at  timestamp,
    game_id     bigint    not null,
    reason_id    bigint    not null,
    intro       varchar(4096),
    description text
);
