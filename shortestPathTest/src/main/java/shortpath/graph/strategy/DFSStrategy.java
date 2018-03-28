package shortpath.graph.strategy;

import shortpath.graph.Vertex;

import java.util.ArrayList;
import java.util.List;

public class DFSStrategy extends Strategy {
    private Boolean[] visited;
    private List<String> stringList = new ArrayList<String>();
    private List<List<String>> routeList = new ArrayList<List<String>>();

    @Override
    public List<List<String>> Strategy(List<Vertex> vertexList) {
        visited = new Boolean[vertexList.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        Vertex beginVertex = vertexList.get(0);
        for (Vertex vertex : vertexList) {
            if (vertex.getName() == "BOSTON") {
                beginVertex = vertex;
            }
        }
        //search from BOSTON
        visited[vertexList.indexOf(beginVertex)] = true;
        DFS(vertexList, beginVertex);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                stringList = new ArrayList<String>();
                DFS(vertexList, vertexList.get(i));
            }
        }
        return routeList;
    }

    private void DFS(List<Vertex> vertexList, Vertex vertex) {
        visited[vertexList.indexOf(vertex)] = true;
        List<Vertex> adjacetVList = new ArrayList<Vertex>(vertex.getAdjacentVertexSet());
        /**
         * visited function
         */
        {
            boolean addPath = true;
            for (Vertex v : adjacetVList) {
                if (!visited[vertexList.indexOf(v)]) {
                    addPath = false;
                    break;
                }
            }
            if (addPath) {
                List<String> newList = new ArrayList<String>(stringList);
                stringList.add(vertex.getName());
                routeList.add(stringList);
                stringList = newList;
            }
        }
        for (Vertex v : adjacetVList) {
            if (!visited[vertexList.indexOf(v)]) {
                stringList.add(vertex.getName());
                DFS(vertexList, v);
            }
        }
    }
}
