package dev.shermende.reference.db.repository.movement;

import dev.shermende.lib.support.dal.db.repository.QueryDslRepository;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.entity.movement.QMovementScenario;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "data")
public interface MovementScenarioRepository extends QueryDslRepository<MovementScenario, Long, QMovementScenario> {

    @Override
    default void customize(@NotNull QuerydslBindings bindings, @NotNull QMovementScenario root) {

    }

}