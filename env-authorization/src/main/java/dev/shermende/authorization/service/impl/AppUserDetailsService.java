package dev.shermende.authorization.service.impl;

import dev.shermende.authorization.db.entity.User;
import dev.shermende.authorization.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return getUser(userService.findOneByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email)));
    }

    private UserDetails getUser(User user) {
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail()).password(user.getPassword()).authorities(AuthorityUtils.NO_AUTHORITIES).build();
    }

}
