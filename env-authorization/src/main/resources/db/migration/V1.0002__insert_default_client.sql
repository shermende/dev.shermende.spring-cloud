insert into OAUTH_CLIENT_DETAILS (client_id, client_secret, scope, authorized_grant_types)
values ('default', -- client-id
 '$2a$08$FZxvL2azm1BJuBKo8qkBYuow1JbRd1/Zb2CRnHZ1wbt4b7Kq0qxiC', -- client-secret = default
 'oauth2:default', -- scopes
 'password,refresh_token' -- grant types
 );