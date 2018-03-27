package shortpath.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Dijstra {
    private List<Vertex> vertexList;
    private int[][] pathMatrix;
    private double[][] weightMatrix;
    private double[] D;
    private boolean[][] P;
    private boolean[] f;

    public void runDijstra() {
        for (int v = 0; v < vertexList.size(); v++) {
            f[v] = false;
            D[v] = weightMatrix[0][v];
            for (int w = 0; w < vertexList.size(); w++) {
                P[v][w] = false;
            }
            if (D[v] < 88888888) {
                P[v][0] = true;
                P[v][v] = true;
            }
        }
        D[0] = 0;
        f[0] = true;
        for (int v = 1; v < vertexList.size(); v++) {
            double min = 88888888;
            for (int w = 0; w < vertexList.size(); w++) {
                if (!f[w]) {
                    if (D[w] < min) {
                        v = w;
                        min = D[w];
                    }
                }
            }
            f[v] = true;
            for (int w = 0; w < vertexList.size(); w++) {
                if (!f[w] && (min + weightMatrix[v][w] < D[w])) {
                    D[w] = min + weightMatrix[v][w];
                    pathMatrix[0][w] = v;
                    P[w] = P[v];
                }
            }
        }
//        for (int i = 0; i < vertexList.size(); i++) {
//            System.out.println(D[i]);
//        }
    }

    public List<String> getSortestPath() {
        List<String> stringList = new ArrayList<String>();
        int numEnd = 8888888;
        for (Vertex vertex : vertexList) {
            if (vertex.getName().equals("MINNEAPOLIS")) {
                numEnd = vertexList.indexOf(vertex);
                System.out.println(numEnd);
                break;
            }
        }
        while (numEnd != 0) {
            stringList.add(vertexList.get(numEnd).getName());
            numEnd = pathMatrix[0][numEnd];
        }
        stringList.add(vertexList.get(0).getName());
        Collections.reverse(stringList);
        System.out.println(stringList);
        return stringList;
    }

    public Dijstra(List<Vertex> vertexList) {
        /**
         *  Make BOSTON as v0
         */
        for (Vertex v : vertexList) {
            if (v.getName().equals("BOSTON")) {
                vertexList.remove(v);
                vertexList.add(0, v);
                System.out.println(vertexList.get(0).getName());
                break;
            }
        }
        this.vertexList = vertexList;
        this.weightMatrix = new double[vertexList.size()][vertexList.size()];
        this.pathMatrix = new int[vertexList.size()][vertexList.size()];
        this.P = new boolean[vertexList.size()][vertexList.size()];
        this.f = new boolean[vertexList.size()];
        this.D = new double[vertexList.size()];
        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < vertexList.size(); j++) {
                weightMatrix[i][j] = 88888888;
                pathMatrix[i][j] = j;
            }
        }
        for (int i = 0; i < vertexList.size(); i++) {
            for (int j = 0; j < vertexList.size(); j++) {

                if (vertexList.get(i).getAdjcentWeightMap().get(vertexList.get(j)) != null) {
                    weightMatrix[i][j] = vertexList.get(i).getAdjcentWeightMap().get(vertexList.get(j));
                }
                pathMatrix[i][j] = i;
            }
        }
//        for (int i = 0; i < vertexList.size(); i++) {
//            for (int j = 0; j < vertexList.size(); j++) {
//                System.out.print(weightMatrix[i][j] + "\t");
//            }
//            System.out.print("\n");
//        }
    }
}
