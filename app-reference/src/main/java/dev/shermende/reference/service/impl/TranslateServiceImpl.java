package dev.shermende.reference.service.impl;

import dev.shermende.lib.support.dal.service.AbstractCrudService;
import dev.shermende.reference.db.entity.QTranslate;
import dev.shermende.reference.db.entity.Translate;
import dev.shermende.reference.db.repository.TranslateRepository;
import dev.shermende.reference.service.TranslateService;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
        return findOne(QTranslate.translate.locale.eq(locale).and(QTranslate.translate.key.eq(key)));
    }

}
