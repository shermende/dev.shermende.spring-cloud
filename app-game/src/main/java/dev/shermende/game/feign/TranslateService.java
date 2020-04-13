package dev.shermende.game.feign;

import dev.shermende.lib.model.reference.TranslateModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "reference", url = "http://localhost:8080")
public interface TranslateService {

    @GetMapping("/translates")
    PagedModel<TranslateModel> translate(@RequestHeader(HttpHeaders.AUTHORIZATION) String token);

}
