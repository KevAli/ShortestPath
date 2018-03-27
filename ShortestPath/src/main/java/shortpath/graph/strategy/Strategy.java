package shortpath.graph.strategy;

import shortpath.graph.Vertex;

import java.util.List;

public abstract class Strategy {
    public abstract List<List<String>> Strategy(List<Vertex> vertexList);
}
