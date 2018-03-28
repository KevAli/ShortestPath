package test.shortpath.graph;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import shortpath.graph.*;
import shortpath.graph.strategy.BFSStrategy;
import shortpath.graph.strategy.DFSStrategy;
import shortpath.tools.Tools;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * BuildGraph Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>03/28/2018</pre>
 */
public class BuildGraphTest {

    @Before
    public void before() throws Exception {
        System.out.println("Before Test");
    }

    @After
    public void after() throws Exception {
        System.out.println("After Test");
    }

    /**
     * Method: buildGraph()
     */
    @Test
    public void testBuildGraph() throws Exception {
        List<Wendy> wendyList = Tools.getWendyList(BuildGraph.WendyPath);
        List<BurgerKing> burgerKingList = Tools.getBurgerKingList(BuildGraph.BurgerKingPath);
        List<Mcdonald> mcdonaldList = Tools.getMcdonaldList(BuildGraph.McdonaldPath);
        List<Vertex> vertexList = Tools.getVertexList(BuildGraph.cityPath);
        List<Meal> mealList = Tools.getMealList(BuildGraph.MealPath);
        Map<String, Set<String>> edgeMap = Tools.getEdge(BuildGraph.edgePath, vertexList);

        Graph graph = new Graph(vertexList, edgeMap);
        graph.setStrategy(new DFSStrategy());
        List<List<String>> DFSresult = graph.runStrategy();
        System.out.println("----------------------------------------DFSSearchEnd");
        graph.setStrategy(new BFSStrategy());
        List<List<String>> BFSresult = graph.runStrategy();
        System.out.println("----------------------------------------BFSSearchEnd");

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
        graph.setEdgeListWeight();
        Dijstra dijstra = new Dijstra(vertexList);
        dijstra.runDijstra();
        StringBuffer text = new StringBuffer();
        text.append("\nAll the BFS path from BOSTON:\n");
        for (List<String> stringList : BFSresult) {
            text.append("\t");
            text.append(stringList);
            text.append("\n");
        }
        text.append("\nAll the DFS path from BOSTON:\n");
        for (List<String> stringList : DFSresult) {
            text.append("\t");
            text.append(stringList);
            text.append("\n");
        }
        Tools.textWrite(BuildGraph.a2_JUnitPath, text.toString());
    }


} 
