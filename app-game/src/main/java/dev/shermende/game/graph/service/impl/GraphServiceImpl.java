package dev.shermende.game.graph.service.impl;

import dev.shermende.game.graph.resource.GraphEdge;
import dev.shermende.game.graph.service.GraphService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Slf4j
@Service
public class GraphServiceImpl implements GraphService {

    private final Random random = new Random();

//    @Override
//    public List<GraphEdge> generateGraph(
//            int peaksCount,
//            int edgeChange
//    ) {
//        if (peaksCount < 0) throw new IllegalArgumentException("'peaksCount' cannot be less than zero");
//        if (edgeChange < 0) throw new IllegalArgumentException("'edgeChange' cannot be less than zero");
//        val list = new ArrayList<GraphEdge>();
//        for (int i = 0; i < peaksCount; i++) {
//            boolean atLeastOneEdge = false;
//            for (int j = 0; j < peaksCount; j++) {
//                if (i == j) continue;
//                if (!GraphUtils.isMatch(edgeChange)) continue;
//                list.add(GraphEdge.builder().source(i).target(j).build());
//                atLeastOneEdge = true;
//            }
//            if (atLeastOneEdge) continue;
//            list.add(GraphEdge.builder().source(i).target(GraphUtils.nextPeakPosition(list, i, peaksCount)).build());
//        }
//        return list;
//    }

    @Override
    public <T> Set<GraphEdge<T>> generateGraph(
            List<T> vertices
    ) {
        Collections.shuffle(vertices);
        val list = new ArrayList<GraphEdge<T>>();

        for (int i = 0; i < vertices.size() / 2; i++)
            vertices.add(vertices.get(random.nextInt(vertices.size())));

        for (int i = 1; i < vertices.size(); i++)
            list.add(GraphEdge.<T>builder().source(vertices.get(i - 1)).target(vertices.get(i)).build());

        Collections.shuffle(list);
        return new HashSet<>(list);
    }

}
