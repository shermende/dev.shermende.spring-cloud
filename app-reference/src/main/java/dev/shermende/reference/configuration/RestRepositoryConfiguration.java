package dev.shermende.reference.configuration;

import dev.shermende.reference.db.entity.movement.MovementPoint;
import dev.shermende.reference.db.entity.movement.MovementReason;
import dev.shermende.reference.db.entity.movement.MovementScenario;
import dev.shermende.reference.db.entity.movement.Quest;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.ProfileResourceProcessor;
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration;

@Configuration
public class RestRepositoryConfiguration extends RepositoryRestMvcConfiguration {

    public RestRepositoryConfiguration(
        ApplicationContext context,
        ObjectFactory<ConversionService> conversionService
    ) {
        super(context, conversionService);
    }

    @Override
    public ProfileResourceProcessor profileResourceProcessor(RepositoryRestConfiguration config) {
        return super.profileResourceProcessor(
            config
                .exposeIdsFor(Quest.class)
                .exposeIdsFor(MovementPoint.class)
                .exposeIdsFor(MovementReason.class)
                .exposeIdsFor(MovementScenario.class)
        );
    }

}
