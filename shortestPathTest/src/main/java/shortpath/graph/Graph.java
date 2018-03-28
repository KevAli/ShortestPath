package shortpath.graph;

import lombok.Data;
import shortpath.graph.strategy.Strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Data
public class Graph {
    List<Vertex> vertexList;
    Map<String, Set<String>> edgeMap;    //Directional edge
    Strategy strategy;
    List<List<String>> routeList = new ArrayList<List<String>>();
    List<Edge> edgeList = new ArrayList<Edge>();

    public void setEdgeListWeight() {
        for (Vertex vertex : vertexList) {
            for (Vertex v : vertex.getAdjacentVertexSet()) {
                Edge edge = new Edge(vertex, v);
                edgeList.add(edge);
                vertex.getAdjcentWeightMap().put(v, edge.getWeight());
            }
        }
    }

    public List<List<String>> runStrategy() {
        return this.strategy.Strategy(vertexList);
    }

    public Graph(List<Vertex> vertexList, Map<String, Set<String>> edgeMap) {
        this.vertexList = vertexList;
        this.edgeMap = edgeMap;
        this.setEdge();
    }

    private void setEdge() {
        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < vertexList.size(); j++) {
                Vertex vertex1 = vertexList.get(i);
                Vertex vertex2 = vertexList.get(j);
                if (edgeMap.get(vertex1.getName()).contains(vertex2.getName())) {
                    vertex1.getAdjacentVertexSet().add(vertex2);
                }
            }
        }
    }
}
