package dev.shermende.game.interceptor;

import dev.shermende.support.spring.component.Interceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class FeignInterceptor implements Interceptor {

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void intercept(Object o) {
        log.info("{}", o);
    }

}
