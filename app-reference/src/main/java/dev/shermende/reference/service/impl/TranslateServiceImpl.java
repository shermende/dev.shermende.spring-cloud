package dev.shermende.reference.service.impl;

import dev.shermende.lib.dal.service.AbstractCrudService;
import dev.shermende.reference.db.entity.QTranslate;
import dev.shermende.reference.db.entity.Translate;
import dev.shermende.reference.db.repository.TranslateRepository;
import dev.shermende.reference.service.TranslateService;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class TranslateServiceImpl extends AbstractCrudService<Translate, Long, QTranslate>
    implements TranslateService {

    public TranslateServiceImpl(
        TranslateRepository repository
    ) {
        super(repository);
    }

    @Override
    public Optional<Translate> findOneByLocaleAndKey(
        @NotNull String locale,
        @NotNull String key
    ) {
        final Optional<Translate> translate = findOne(QTranslate.translate.locale.eq(locale).and(QTranslate.translate.key.eq(key)));
        log.debug("[Translate] [found] [{}]", translate);
        return translate;
    }

}
