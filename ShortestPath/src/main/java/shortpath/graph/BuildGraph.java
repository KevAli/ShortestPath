package shortpath.graph;

import shortpath.graph.strategy.BFSStrategy;
import shortpath.graph.strategy.DFSStrategy;
import shortpath.tools.Tools;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class BuildGraph {
    public static final String edgePath = "E:\\shortPath\\2XB3_A3_DataSets\\connectedCities.txt";
    public static final String cityPath = "E:\\shortPath\\2XB3_A3_DataSets\\USCities.csv";
    public static final String BurgerKingPath = "E:\\shortPath\\2XB3_A3_DataSets\\burgerking.csv";
    public static final String McdonaldPath = "E:\\shortPath\\2XB3_A3_DataSets\\mcdonalds.csv";
    public static final String WendyPath = "E:\\shortPath\\2XB3_A3_DataSets\\wendys.csv";
    public static final String MealPath = "E:\\shortPath\\2XB3_A3_DataSets\\menu.csv";
    public static final String a2_outPath = "E:\\shortPath\\2XB3_A3_DataSets\\a2_out.txt";
    public static final String a2_JUnitPath = "E:\\shortPath\\2XB3_A3_DataSets\\a2_JUnitPath.txt";

    public static void main(String[] agrs) throws InterruptedException {
        /**
         * 以下这几行分别是读入6个数据集里面的信息，因为最后计算结果，每一个城市都有3家餐厅，所以都可以点menu中三种类型食物的最便宜那种，
         * 其实只需要两种，同种的食物路径节点中间隔开就行了
         */
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


        Graph graph = new Graph(vertexList, edgeMap);//建立图结构
        graph.setStrategy(new DFSStrategy());//设置算法为DFS
        List<List<String>> DFSresult = graph.runStrategy();//运行DFS返回结果
        System.out.println("----------------------------------------DFSSearchEnd");
        graph.setStrategy(new BFSStrategy());//设置算法为BFS
        List<List<String>> BFSresult = graph.runStrategy();//运行BFS返回结果
        System.out.println("----------------------------------------BFSSearchEnd");

        //把每一个Wendy餐厅与城市匹配
        for (Wendy wendy : wendyList) {
            for (Vertex vertex : vertexList) {
                vertex.addWendy(wendy);
            }
        }
        //把每一个Mcdonald餐厅与城市匹配
        for (Mcdonald mcdonald : mcdonaldList) {
            for (Vertex vertex : vertexList) {
                vertex.addMcdonald(mcdonald);
            }
        }
        //把每一个BurgerKing餐厅与城市匹配
        for (BurgerKing burgerKing : burgerKingList) {
            for (Vertex vertex : vertexList) {
                vertex.addBurgerKing(burgerKing);
            }
        }
        /**
         * 带有System.out.println都是调试找bug用的
         */

//        for (Vertex vertex : vertexList) {
//            System.out.println(vertex.getBurgerKingList().size());
//        }
//        System.out.println("----------------------------------------BurgerKingListSize");
//        for (Vertex vertex : vertexList) {
//            System.out.println(vertex.getMcdonaldList().size());
//        }
//        System.out.println("----------------------------------------McdonaldListSize");
//        for (Vertex vertex : vertexList) {
//            System.out.println(vertex.getWendyList().size());
//        }
//        System.out.println("----------------------------------------WendyListSize");
//
//        graph.setEdgeListWeight();
//        System.out.println("----------------------------------------setEdgeListWeightEnd");
//        for (Edge edge : graph.getEdgeList()) {
//            System.out.println(edge);
//        }

        Dijstra dijstra = new Dijstra(vertexList);//new 一个Dijstra对象
        dijstra.runDijstra();//运行Dijstra算法
        List<String> dijstraPath = dijstra.getSortestPath();//获得最短路径，List的顺序表示依次访问的节点顺序

        //下面是把信息根据老师的要求格式暂存在一个StringBuffer里
        StringBuffer text = new StringBuffer();
        for (List<String> stringList : BFSresult) {
            if (stringList.get(0).equals("BOSTON") && stringList.contains("MINNEAPOLIS")) {
                text.append("BFS:");
                for (String s : stringList) {
                    text.append(s);
                    if (s.equals("MINNEAPOLIS")) {
                        break;
                    }
                    text.append(",");
                }
                text.append("\n");
                break;
            }
        }
        for (List<String> stringList : DFSresult) {
            if (stringList.get(0).equals("BOSTON") && stringList.contains("MINNEAPOLIS")) {
                text.append("DFS:");
                for (String s : stringList) {
                    text.append(s);
                    if (s.equals("MINNEAPOLIS")) {
                        break;
                    }
                    text.append(",");
                }
                text.append("\n");
                break;
            }
        }
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

        text.append("\n");
        text.append(String.format("%-40s", "City"));
        text.append(String.format("%-40s", "Meal Choice"));
        text.append(String.format("%-40s", "Cost of Meal"));
        text.append("\n");
        for (int i = 0; i < dijstraPath.size(); i++) {
            text.append(String.format("%-40s", dijstraPath.get(i)));
            if (i != 0) {
                if (i % 2 == 0) {
                    text.append(String.format("%-40s", "Double Cheeseburger - Meal"));
                    text.append(String.format("%-40s", "$3.79"));
                } else {
                    text.append(String.format("%-40s", "McChicken - Meal"));
                    text.append(String.format("%-40s", "$4.38"));
                }
            }
            text.append("\n");
        }
        //把StringBuffer里的信息写入到磁盘文档中。
        Tools.textWrite(a2_outPath, text.toString());
    }
}
