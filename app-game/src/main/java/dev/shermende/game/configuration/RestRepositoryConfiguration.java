package dev.shermende.game.configuration;

import dev.shermende.game.db.entity.Game;
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
                .exposeIdsFor(Game.class)
        );
    }

}
