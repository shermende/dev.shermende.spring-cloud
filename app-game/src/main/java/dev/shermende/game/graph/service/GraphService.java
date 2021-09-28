package dev.shermende.game.graph.service;

import dev.shermende.game.graph.resource.GraphEdge;

import java.util.List;
import java.util.Set;

public interface GraphService {

//    List<GraphEdge> generateGraph(
//            int peaksCount,
//            int edgeChange
//    );

    <T> Set<GraphEdge<T>> generateGraph(
            List<T> vertices
    );

}
