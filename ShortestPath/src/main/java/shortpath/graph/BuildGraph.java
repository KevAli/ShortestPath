package shortpath.graph;

import shortpath.graph.strategy.BFSStrategy;
import shortpath.graph.strategy.DFSStrategy;
import shortpath.tools.Tools;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuildGraph {
    private static final String edgePath = "E:\\shortPath\\2XB3_A3_DataSets\\connectedCities.txt";
    private static final String cityPath = "E:\\shortPath\\2XB3_A3_DataSets\\USCities.csv";
    private static final String BurgerKingPath = "E:\\shortPath\\2XB3_A3_DataSets\\burgerking.csv";
    private static final String McdonaldPath = "E:\\shortPath\\2XB3_A3_DataSets\\mcdonalds.csv";
    private static final String WendyPath = "E:\\shortPath\\2XB3_A3_DataSets\\wendys.csv";
    private static final String MealPath = "E:\\shortPath\\2XB3_A3_DataSets\\menu.csv";

    public static void buildGraph() throws InterruptedException {
        List<Wendy> wendyList = Tools.getWendyList(WendyPath);
        System.out.println("----------------------------------------WendyReadEnd");
        List<BurgerKing> burgerKingList = Tools.getBurgerKingList(BurgerKingPath);
        System.out.println("----------------------------------------BurgerKingReadEnd");
        List<Mcdonald> mcdonaldList = Tools.getMcdonaldList(McdonaldPath);
        System.out.println("----------------------------------------McdonaldReadEnd");
        List<Vertex> vertexList = Tools.getVertexList(cityPath);
        System.out.println("----------------------------------------VertexReadEnd");
        List<Meal> mealList = Tools.getMealList(MealPath);
        System.out.println("----------------------------------------MealReadEnd");
        Map<String, Set<String>> edgeMap = Tools.getEdge(edgePath, vertexList);
        System.out.println("----------------------------------------edgeMapReadEnd");


        Graph graph = new Graph(vertexList, edgeMap);
        graph.setStrategy(new DFSStrategy());
        graph.runStrategy();
        System.out.println("----------------------------------------DFSSearchEnd");
        graph.setStrategy(new BFSStrategy());
        graph.runStrategy();

        for (Wendy wendy : wendyList) {
            for (Vertex vertex : vertexList) {
                vertex.addWendy(wendy);
            }
        }
        for (Mcdonald mcdonald : mcdonaldList) {
            for (Vertex vertex : vertexList) {
                vertex.addMcdonald(mcdonald);
            }
        }
        for (BurgerKing burgerKing : burgerKingList) {
            for (Vertex vertex : vertexList) {
                vertex.addBurgerKing(burgerKing);
            }
        }
        for (Vertex vertex : vertexList) {
            System.out.println(vertex.getBurgerKingList().size());
        }
        System.out.println("----------------------------------------BurgerKingListSize");
        for (Vertex vertex : vertexList) {
            System.out.println(vertex.getMcdonaldList().size());
        }
        System.out.println("----------------------------------------McdonaldListSize");
        for (Vertex vertex : vertexList) {
            System.out.println(vertex.getWendyList().size());
        }
        System.out.println("----------------------------------------WendyListSize");

        graph.setEdgeListWeight();
        System.out.println("----------------------------------------setEdgeListWeightEnd");
//        for (Edge edge : graph.getEdgeList()) {
//            System.out.println(edge);
//        }

        Dijstra dijstra = new Dijstra(vertexList);
        dijstra.runDijstra();
        dijstra.getSortestPath();



    }
}
