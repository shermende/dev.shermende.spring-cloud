package dev.shermende.game.feign;

import dev.shermende.lib.model.authorization.UserModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "authorization")
public interface AuthorizationService {

    @PostMapping("/introspect")
    UserModel introspect(@RequestParam String token);

}
