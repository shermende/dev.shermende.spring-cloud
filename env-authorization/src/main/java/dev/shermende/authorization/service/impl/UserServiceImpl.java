package dev.shermende.authorization.service.impl;

import dev.shermende.authorization.db.entity.QUser;
import dev.shermende.authorization.db.entity.User;
import dev.shermende.authorization.db.repository.UserRepository;
import dev.shermende.authorization.resource.UserResource;
import dev.shermende.authorization.service.UserService;
import dev.shermende.lib.jpa.service.AbstractCrudService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl extends AbstractCrudService<User, Long, QUser> implements UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
        UserRepository repository,
        PasswordEncoder passwordEncoder
    ) {
        super(repository);
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User registration(UserResource resource) {
        return save(User.builder().email(resource.getEmail()).password(passwordEncoder.encode(resource.getPassword())).build());
    }

    @Override
    public Optional<User> findOneByEmail(String email) {
        return repository.findOne(QUser.user.email.equalsIgnoreCase(email));
    }

}
