package dev.shermende.authorization.resource;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class UserResource {

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}
