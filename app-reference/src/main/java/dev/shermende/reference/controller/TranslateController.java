package dev.shermende.reference.controller;

import dev.shermende.reference.assembler.TranslateModelAssembler;
import dev.shermende.reference.model.TranslateModel;
import dev.shermende.reference.service.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/translates")
public class TranslateController {

    private final TranslateService service;
    private final TranslateModelAssembler assembler;

    @GetMapping("/findOneByKey")
    public TranslateModel findOneByKey(
        @RequestParam("key") String key
    ) {
        return assembler.toModel(service.findOneByLocaleAndKey(LocaleContextHolder.getLocale().getLanguage(), key)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)));
    }

}
