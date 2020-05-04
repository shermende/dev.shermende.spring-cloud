package dev.shermende.reference.assembler;

import dev.shermende.reference.controller.TranslateController;
import dev.shermende.reference.db.entity.Translate;
import dev.shermende.reference.model.TranslateModel;
import org.jetbrains.annotations.NotNull;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class TranslateModelAssembler extends RepresentationModelAssemblerSupport<Translate, TranslateModel> {

    public TranslateModelAssembler() {
        super(TranslateController.class, TranslateModel.class);
    }

    @NotNull
    @Override
    public TranslateModel toModel(@NotNull Translate entity) {
        return TranslateModel.builder()
            .id(entity.getId())
            .locale(entity.getLocale())
            .key(entity.getKey())
            .value(entity.getValue())
            .build();
    }

}
