package dev.shermende.game.graph.resource;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GraphEdge {
    private int source;
    private int target;
}
