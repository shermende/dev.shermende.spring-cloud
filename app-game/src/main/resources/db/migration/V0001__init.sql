create table if not exists game
(
    id          bigserial primary key,
    created_at  timestamp not null default now(),
    updated_at  timestamp,
    user_id     bigint    not null,
    scenario_id bigint    not null,
    source_id   bigint    not null,
    target_id   bigint    not null
);

create table if not exists graph_map
(
    id         bigserial primary key,
    created_at timestamp not null default now(),
    updated_at timestamp,
    user_id    bigint    not null,
    game_id    bigint    not null,
    source_id  bigint    not null,
    target_id  bigint    not null
);
