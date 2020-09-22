package dev.shermende.authorization.security;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

@Value
@ToString(of = {"id"})
@EqualsAndHashCode(of = {"id"}, callSuper = false)
public class ExtendedUser extends User {

    Long id;

    public ExtendedUser(
        Long id,
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities
    ) {
        super(username, password, authorities);
        this.id = id;
    }

}
