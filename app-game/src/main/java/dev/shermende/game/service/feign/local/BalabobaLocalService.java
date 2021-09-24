package dev.shermende.game.service.feign.local;

import dev.shermende.game.service.feign.BalabobaService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Profile;

@Profile({"local"})
@FeignClient(value = "BalabobaService", url = "https://zeapi.yandex.net", fallbackFactory = BalabobaService.BalabobaServiceFallback.class)
public interface BalabobaLocalService extends BalabobaService {

}
