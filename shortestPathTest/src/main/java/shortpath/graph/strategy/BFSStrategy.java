package shortpath.graph.strategy;

import shortpath.graph.Vertex;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BFSStrategy extends Strategy {
    private Boolean[] visited;
    private Queue<Vertex> vertexQueue = new ConcurrentLinkedQueue<Vertex>();
    private List<List<String>> routeList = new ArrayList<List<String>>();

    @Override
    public List<List<String>> Strategy(List<Vertex> vertexList) {
        visited = new Boolean[vertexList.size()];
        for (int i = 0; i < visited.length; i++) {
            visited[i] = false;
        }
        Vertex beginVertex = vertexList.get(0);
        for (Vertex vertex : vertexList) {
            if (vertex.getName().equals("BOSTON")) {
                beginVertex = vertex;
            }
        }
        //search from BOSTON
        vertexList.remove(beginVertex);
        vertexList.add(0, beginVertex);
        for (int i = 0; i < vertexList.size(); i++) {
            if (!visited[i]) {
                visited[i] = true;
                Vertex vertex = vertexList.get(i);
                /**
                 * visit V
                 */
                {
                    List<String> newList = new ArrayList<String>();
                    newList.add(vertex.getName());
                    routeList.add(newList);
                }
                vertexQueue.add(vertex);
                while (!vertexQueue.isEmpty()) {
                    Vertex v = vertexQueue.remove();

                    List<Vertex> vAdjacentList = new ArrayList<Vertex>(v.getAdjacentVertexSet());
                    for (int j = 0; j < vAdjacentList.size(); j++) {
                        Vertex u = vAdjacentList.get(j);
                        if (!visited[vertexList.indexOf(u)]) {
                            visited[vertexList.indexOf(u)] = true;
                            /**
                             * visit V
                             */
                            {
                                for (int k = 0; k < routeList.size(); k++) {
                                    List<String> sList = routeList.get(k);

                                    if (sList.get(sList.size() - 1) == v.getName()) {
                                        if (j < sList.size() - 1) {
                                            List<String> newList = new ArrayList<String>(sList);
                                            newList.add(u.getName());
                                            routeList.add(newList);
                                        } else {
                                            sList.add(u.getName());
                                        }
                                    }
                                }
                            }
                            vertexQueue.add(u);
                        }
                    }
                }
            }
        }
        return routeList;
    }
}
