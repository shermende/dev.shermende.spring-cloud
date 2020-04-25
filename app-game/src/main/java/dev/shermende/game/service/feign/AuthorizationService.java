package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.lib.model.authorization.UserModel;
import dev.shermende.support.spring.component.annotation.InterceptResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "env-authorization")
public interface AuthorizationService {

    @PostMapping("/introspect")
    @InterceptResult(FeignInterceptor.class)
    UserModel introspect();

}
