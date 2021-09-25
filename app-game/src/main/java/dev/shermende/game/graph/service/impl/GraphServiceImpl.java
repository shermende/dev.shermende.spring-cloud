package dev.shermende.game.graph.service.impl;

import dev.shermende.game.graph.resource.GraphEdge;
import dev.shermende.game.graph.service.GraphService;
import dev.shermende.game.utils.GraphUtils;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class GraphServiceImpl implements GraphService {

    @Override
    public List<GraphEdge> generateGraph(
            int peaksCount,
            int edgeChange
    ) {
        if (peaksCount < 0) throw new IllegalArgumentException("'peaksCount' cannot be less than zero");
        if (edgeChange < 0) throw new IllegalArgumentException("'edgeChange' cannot be less than zero");
        val list = new ArrayList<GraphEdge>();
        for (int i = 0; i < peaksCount; i++) {
            boolean atLeastOneEdge = false;
            for (int j = 0; j < peaksCount; j++) {
                if (i == j) continue;
                if (!GraphUtils.isMatch(edgeChange)) continue;
                list.add(GraphEdge.builder().source(i).target(j).build());
                atLeastOneEdge = true;
            }
            if (atLeastOneEdge) continue;
            list.add(GraphEdge.builder().source(i).target(GraphUtils.nextPeakPosition(i, peaksCount)).build());
        }
        return list;
    }

}
