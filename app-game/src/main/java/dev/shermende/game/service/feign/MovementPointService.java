package dev.shermende.game.service.feign;

import dev.shermende.game.interceptor.FeignInterceptor;
import dev.shermende.game.model.MovementPointModel;
import dev.shermende.support.spring.aop.intercept.annotation.InterceptResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

public interface MovementPointService {

    @GetMapping
    @InterceptResult(FeignInterceptor.class)
    PagedModel<MovementPointModel> findAll();

    @Slf4j
    @Component
    class MovementPointServiceFallback implements FallbackFactory<MovementPointService> {
        @Override
        public MovementPointService create(Throwable throwable) {
            log.error(throwable.getMessage());
            return PagedModel::empty;
        }
    }
}
