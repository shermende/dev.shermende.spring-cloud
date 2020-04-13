package dev.shermende.authorization.service;

import dev.shermende.authorization.db.entity.User;
import dev.shermende.authorization.resource.UserResource;
import dev.shermende.lib.db.service.CrudService;

import java.util.Optional;

public interface UserService extends CrudService<User, Long> {

    User registration(UserResource resource);

    Optional<User> findOneByEmail(String email);

}
