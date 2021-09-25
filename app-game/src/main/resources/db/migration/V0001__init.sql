create table if not exists game
(
    id              bigserial primary key,
    created_at      timestamp not null default now(),
    updated_at      timestamp,
    user_id         bigint    not null,
    scenario_id     bigint    not null,
    source_point_id bigint,
    reason_id       bigint    not null,
    target_point_id bigint    not null
);

create table if not exists game_route
(
    id         bigserial primary key,
    created_at timestamp not null default now(),
    updated_at timestamp,
    user_id    bigint    not null,
    game_id    bigint    not null,
    source_point_id  bigint    not null,
    reason_id  bigint    not null,
    target_point_id  bigint    not null
);
