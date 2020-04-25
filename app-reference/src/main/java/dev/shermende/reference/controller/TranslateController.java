package dev.shermende.reference.controller;

import dev.shermende.lib.model.reference.TranslateModel;
import dev.shermende.reference.assembler.TranslateModelAssembler;
import dev.shermende.reference.service.TranslateService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/translates")
public class TranslateController {

    private final TranslateService service;
    private final TranslateModelAssembler assembler;

    @GetMapping("/custom/findOneByLocaleAndKey")
    public TranslateModel findOneBySourcePointIdAndReasonId(
        @PageableDefault Pageable pageable,
        @RequestParam("locale") String locale,
        @RequestParam("key") String key
    ) {
        return assembler.toModel(service.findOneByLocaleAndKey(locale, key).orElseThrow(EntityNotFoundException::new));
    }

}
