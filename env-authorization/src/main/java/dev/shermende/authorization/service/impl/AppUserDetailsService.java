package dev.shermende.authorization.service.impl;

import dev.shermende.authorization.db.entity.User;
import dev.shermende.authorization.security.ExtendedUser;
import dev.shermende.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String login) {
        return getUser(userService.findOneByEmail(login).orElseThrow(() -> new UsernameNotFoundException(login)));
    }

    private UserDetails getUser(User user) {
        return new ExtendedUser(user.getId(), user.getEmail(),
            user.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

}
