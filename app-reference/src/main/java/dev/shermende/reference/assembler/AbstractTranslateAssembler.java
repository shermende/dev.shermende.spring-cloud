package dev.shermende.reference.assembler;

import dev.shermende.reference.db.entity.Translate;
import dev.shermende.reference.db.repository.TranslateRepository;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

public abstract class AbstractTranslateAssembler<E, M extends RepresentationModel<?>> extends RepresentationModelAssemblerSupport<E, M> {
    private static final Translate TRANSLATE = new Translate();

    private final TranslateRepository translateRepository;

    public AbstractTranslateAssembler(
        TranslateRepository translateRepository,
        Class<?> controllerClazz,
        Class<M> modelClazz
    ) {
        super(controllerClazz, modelClazz);
        this.translateRepository = translateRepository;
    }

    protected String getTranslate(@NotNull Class<?> clazz, @NotNull Long id) {
//        return translateRepository.findFirstByLocaleAndKey(getLocale(), getKey(clazz, id)).orElse(TRANSLATE).getValue();
        return null;
    }

    protected String getLocale() {
        return LocaleContextHolder.getLocale().getLanguage();
    }

    protected String getKey(@NotNull Class<?> clazz, @NotNull Long id) {
        return String.format("%s.%d", clazz.getSimpleName(), id);
    }

}
