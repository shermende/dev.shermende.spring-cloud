package dev.shermende.reference.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.shermende.lib.secure.model.UserPrincipal;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Data
@Builder
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Relation(value = "item", collectionRelation = "data")
public class UserModel extends RepresentationModel<UserModel> implements UserPrincipal {

    private Long id;

    private String email;

    private char[] token;

    @Override
    public Long id() {
        return getId();
    }

    @Override
    public String login() {
        return getEmail();
    }

    @Override
    public char[] token() {
        return getToken();
    }

    @Override
    public UserPrincipal attachToken(char[] token) {
        return setToken(token);
    }

}
