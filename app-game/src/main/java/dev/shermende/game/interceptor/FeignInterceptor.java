package dev.shermende.game.interceptor;

import dev.shermende.lib.model.authorization.UserModel;
import dev.shermende.support.spring.component.Interceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class FeignInterceptor implements Interceptor {

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void intercept(Object o) {
        Optional<UserModel> userModel = (Optional<UserModel>) o;
        log.info("{}", userModel);
    }

}
