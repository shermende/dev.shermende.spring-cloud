package dev.shermende.game.service.feign.local;

import dev.shermende.game.service.feign.AuthorizationService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(value = "env-authorization", url = "http://127.0.0.1:8082", fallbackFactory = AuthorizationService.AuthorizationServiceFallback.class)
public interface AuthorizationLocalService extends AuthorizationService {

}