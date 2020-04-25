package dev.shermende.reference.service;

import dev.shermende.lib.db.service.CrudService;
import dev.shermende.reference.db.entity.Translate;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public interface TranslateService extends CrudService<Translate, Long> {

    Optional<Translate> findOneByLocaleAndKey(@NotNull String locale, @NotNull String key);

}
