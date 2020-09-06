insert into OAUTH_CLIENT_DETAILS (client_id, client_secret, scope, authorized_grant_types)
values ('client', -- client-id
        '$2a$10$PqaJjMII/AXm7jCTU4Srh.fq4G0YET8HvfsFVxgAtxQgWzek1Nma.', -- client-secret = secret
        'oauth2:any', -- scopes
        'password,refresh_token' -- grant types
       );