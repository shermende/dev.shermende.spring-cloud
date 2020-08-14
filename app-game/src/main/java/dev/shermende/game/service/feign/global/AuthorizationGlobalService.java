package dev.shermende.game.service.feign.global;

import dev.shermende.game.service.feign.AuthorizationService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(value = "env-authorization", fallbackFactory = AuthorizationService.AuthorizationServiceFallback.class)
public interface AuthorizationGlobalService extends AuthorizationService {

}
