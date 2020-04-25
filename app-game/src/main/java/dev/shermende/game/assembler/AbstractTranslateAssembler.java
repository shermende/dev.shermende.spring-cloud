package dev.shermende.game.assembler;

import dev.shermende.game.service.feign.TranslateService;
import dev.shermende.lib.model.reference.TranslateModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public abstract class AbstractTranslateAssembler<E, M extends RepresentationModel<?>> extends RepresentationModelAssemblerSupport<E, M> {
    private static final TranslateModel TRANSLATE_MODEL = new TranslateModel();

    private final TranslateService translateService;

    public AbstractTranslateAssembler(
        TranslateService translateService,
        Class<?> controllerClazz,
        Class<M> modelClazz
    ) {
        super(controllerClazz, modelClazz);
        this.translateService = translateService;
    }

    protected String getTranslate(@NotNull Class<?> clazz, @NotNull Long id) {
        return translateService.findByLocaleAndKey(getLocale(), getKey(clazz, id)).orElse(TRANSLATE_MODEL).getValue();
    }

    protected String getLocale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    protected String getKey(@NotNull Class<?> clazz, @NotNull Long id) {
        return String.format("%s.%d", clazz.getSimpleName(), id);
    }

}
