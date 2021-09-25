package dev.shermende.game.graph.service;

import dev.shermende.game.graph.resource.GraphEdge;

import java.util.List;

public interface GraphService {

    List<GraphEdge> generateGraph(
            int peaksCount,
            int edgeChange
    );

}
