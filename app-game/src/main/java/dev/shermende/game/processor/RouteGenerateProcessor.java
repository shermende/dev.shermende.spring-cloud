package dev.shermende.game.processor;

import dev.shermende.game.processor.steps.RouteGenerateProcessorMoveStep;
import dev.shermende.game.processor.steps.RouteGenerateProcessorQuestStep;
import dev.shermende.support.spring.processor.AbstractStepProcessor;
import dev.shermende.support.spring.processor.Step;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Component
public class RouteGenerateProcessor extends AbstractStepProcessor<RouteGenerateProcessorCtx, RouteGenerateProcessorCtx> {

    protected RouteGenerateProcessor(BeanFactory factory) {
        super(factory);
    }

    @Override
    protected Collection<Class<? extends Step<RouteGenerateProcessorCtx, RouteGenerateProcessorCtx>>> steps() {
        return Arrays.asList(
            RouteGenerateProcessorMoveStep.class,
            RouteGenerateProcessorQuestStep.class
        );
    }

}