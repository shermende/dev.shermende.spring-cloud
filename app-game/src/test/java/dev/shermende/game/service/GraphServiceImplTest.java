package dev.shermende.game.service;

import dev.shermende.game.service.graph.impl.GraphServiceImpl;
import lombok.val;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GraphServiceImplTest {

    private static final int TRUST_THRESHOLD = 10000;
    private final EasyRandom easyRandom = new EasyRandom();

    @InjectMocks
    private GraphServiceImpl graphService;

    @Test
    void generateGraphEachPeakToEachPeakTest() {
        val edgeChange = 100;
        val peaksCount = Math.abs(easyRandom.nextInt(100));
        val routes = graphService.generateGraph(peaksCount, edgeChange);
        Assertions.assertEquals(peaksCount * peaksCount - peaksCount, routes.size());
        for (int i = 0; i < peaksCount; i++) {
            Assertions.assertNotEquals(routes.get(i).getSource(), routes.get(i).getTarget());
        }
    }

    @Test
    void generateGraphAtLeastOneRouteTest() {
        val edgeChange = 0;
        val peaksCount = Math.abs(easyRandom.nextInt(TRUST_THRESHOLD));
        val routes = graphService.generateGraph(peaksCount, edgeChange);
        for (int i = 0; i < peaksCount; i++) {
            Assertions.assertEquals(i, routes.get(i).getSource());
            Assertions.assertNotEquals(routes.get(i).getSource(), routes.get(i).getTarget());
        }
    }

}
