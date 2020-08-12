package dev.shermende.game.service.feign.local;

import dev.shermende.game.service.feign.TranslateService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(contextId = "translate", value = "app-reference", url = "http://127.0.0.1:8100/translates", fallbackFactory = TranslateService.TranslateServiceFallback.class)
public interface TranslateLocalService extends TranslateService {

}