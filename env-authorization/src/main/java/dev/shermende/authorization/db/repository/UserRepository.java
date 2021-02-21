package dev.shermende.authorization.db.repository;

import dev.shermende.authorization.db.entity.QUser;
import dev.shermende.authorization.db.entity.User;
import dev.shermende.lib.dal.db.repository.QueryDslRepository;

public interface UserRepository extends QueryDslRepository<User, Long, QUser> {

}