package dev.shermende.reference.assembler;

import dev.shermende.reference.db.entity.Translate;
import dev.shermende.reference.service.TranslateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public abstract class AbstractTranslateAssembler<E, M extends RepresentationModel<?>> extends RepresentationModelAssemblerSupport<E, M> {

    private final TranslateService translateService;

    public AbstractTranslateAssembler(
        TranslateService translateService,
        Class<?> controllerClazz,
        Class<M> modelClazz
    ) {
        super(controllerClazz, modelClazz);
        this.translateService = translateService;
    }

    private String getLocale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    protected String getTitle(@NotNull Class<?> clazz, @NotNull Long id) {
        final String key = getTitleKey(clazz, id);
        return translateService.findOneByLocaleAndKey(getLocale(), key)
            .orElse(Translate.builder().value(key).build()).getValue();
    }

    protected String getDescription(@NotNull Class<?> clazz, @NotNull Long id) {
        final String key = getDescriptionKey(clazz, id);
        return translateService.findOneByLocaleAndKey(getLocale(), key)
            .orElse(Translate.builder().value(key).build()).getValue();
    }

    private String getTitleKey(@NotNull Class<?> clazz, @NotNull Long id) {
        return String.format("%s.title.%d", clazz.getSimpleName(), id);
    }

    private String getDescriptionKey(@NotNull Class<?> clazz, @NotNull Long id) {
        return String.format("%s.description.%d", clazz.getSimpleName(), id);
    }

}
