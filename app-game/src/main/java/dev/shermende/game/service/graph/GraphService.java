package dev.shermende.game.service.graph;

import lombok.Builder;
import lombok.Data;

import java.util.List;

public interface GraphService {

    List<GraphEdge> generateGraph(
            int peaksCount,
            int edgeChange
    );

    @Data
    @Builder
    class GraphEdge {
        int source;
        int target;
    }
}
