package dev.shermende.game.service.feign.global;

import dev.shermende.game.service.feign.TranslateService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"!local"})
@FeignClient(contextId = "translate", name = "app-reference", path = "/translates", fallbackFactory = TranslateService.TranslateServiceFallback.class)
public interface TranslateGlobalService extends TranslateService {

}