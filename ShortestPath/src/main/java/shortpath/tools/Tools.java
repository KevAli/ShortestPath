package shortpath.tools;

import com.csvreader.CsvReader;
import shortpath.graph.*;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Tools {
    /**
     * from filePath to a Buffer Reader
     *
     * @param filePath
     * @return reader
     */
    public static BufferedReader getReader(String filePath) {
        File textFile = new File(filePath);
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(textFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return reader;
    }

    /**
     * read Edge Message from filePath
     *
     * @param filePath
     * @return
     */
    public static Map<String, Set<String>> getEdge(String filePath, List<Vertex> vertexList) {
        BufferedReader reader = getReader(filePath);
        Map<String, Set<String>> edgeMap = new HashMap<String, Set<String>>();
        for (Vertex vertex : vertexList) {
//            System.out.println(vertex.getName());
            edgeMap.put(vertex.getName(), new HashSet<String>());
        }
        String tempString = "";
        try {
            while ((tempString = reader.readLine()) != null) {
                String[] edge = tempString.split(",");
                String from = edge[0].toUpperCase().trim();
                String to = edge[1].toUpperCase().trim();
//                System.out.println(from + "------" + to);
                //directional edge
                edgeMap.get(from).add(to);
                edgeMap.get(to).add(from);
//                System.out.println(edgeMap.get(from));
//                System.out.println(edgeMap.get(to));
//                System.out.println("---------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(edgeMap.size());
//        for (String s:edgeMap.keySet()){
//            System.out.println(s+"-------"+edgeMap.get(s));
//        }
        return edgeMap;
    }

    /**
     * read Mcdonal Message from filePath
     *
     * @param filePath
     * @return
     */
    public static List<Mcdonald> getMcdonaldList(String filePath) {
        List<Mcdonald> mcdonaldList = new ArrayList<Mcdonald>();
        String[] tempString = null;
        try {
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            while (csvReader.readRecord()) {
                tempString = csvReader.getValues();
                Mcdonald mcdonald = new Mcdonald(tempString[2].toUpperCase().trim(), Double.parseDouble(tempString[1]),
                        Double.parseDouble(tempString[0]), tempString[3].toUpperCase().trim());
                mcdonaldList.add(mcdonald);
//                System.out.println(mcdonald);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mcdonaldList;

    }

    /**
     * read BurgerKing Message from filePath
     *
     * @param filePath
     * @return
     */
    public static List<BurgerKing> getBurgerKingList(String filePath) {
        List<BurgerKing> burgerKingList = new ArrayList<BurgerKing>();
        String[] tempString = null;
        try {
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            while (csvReader.readRecord()) {
                tempString = csvReader.getValues();
                BurgerKing burgerKing = new BurgerKing(tempString[2].toUpperCase().trim(), Double.parseDouble(tempString[1]),
                        Double.parseDouble(tempString[0]), tempString[3].toUpperCase().trim());
                burgerKingList.add(burgerKing);
//                System.out.println(burgerKing);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return burgerKingList;
    }

    /**
     * read Wendy Message from filePath
     *
     * @param filePath
     * @return
     */
    public static List<Wendy> getWendyList(String filePath) {
        List<Wendy> wendyList = new ArrayList<Wendy>();
        String[] tempString = null;
        try {
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            while (csvReader.readRecord()) {
                tempString = csvReader.getValues();
                Wendy wendy = new Wendy(tempString[2].toUpperCase().trim(), Double.parseDouble(tempString[1]),
                        Double.parseDouble(tempString[0]), tempString[3].toUpperCase().trim());
                wendyList.add(wendy);
//                System.out.println(wendy);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wendyList;
    }

    /**
     * read Vertex Message from filePath
     *
     * @param filePath
     * @return
     */
    public static List<Vertex> getVertexList(String filePath) {
        List<Vertex> vertexList = new ArrayList<Vertex>();
        String[] tempString = null;
        try {
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            csvReader.readHeaders();
            while (csvReader.readRecord()) {
                tempString = csvReader.getValues();
                Vertex vertex = new Vertex(tempString[3].toUpperCase().trim(),
                        Double.parseDouble(tempString[4]), Double.parseDouble(tempString[5]));
                vertexList.add(vertex);
//                System.out.println(vertex);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vertexList;
    }

    /**
     * read Meal Message from filePath
     *
     * @param filePath
     * @return
     */
    public static List<Meal> getMealList(String filePath) {
        List<Meal> mealList = new ArrayList<Meal>();
        String[] tempString = null;
        try {
            CsvReader csvReader = new CsvReader(filePath, ',', Charset.forName("UTF-8"));
            csvReader.readHeaders();
//            System.out.println(csvReader.getHeaders()[0]);
            while (csvReader.readRecord()) {
                tempString = csvReader.getValues();
                Meal meal = new Meal(tempString[0], tempString[1],
                        Double.parseDouble(tempString[2].replace("$", "").toUpperCase().trim()), tempString[3]);
                mealList.add(meal);
//                System.out.println(meal);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mealList;
    }

    /**
     * get the distance[Latitude,Longitude] from Rentaurant ot city
     *
     * @param vertex
     * @param restaurant
     * @return
     */
    public static double[] getDistanceOfRestaurantToCity(Vertex vertex, Restaurant restaurant) {
        double[] distance = new double[2];
        distance[0] = Math.abs(vertex.getLatitude() - restaurant.getLatitude());
        distance[1] = Math.abs(vertex.getLongitude() - restaurant.getLongitude());
        return distance;
    }

    /**
     * if the restaurant in the city
     * @param distance
     * @return
     */
    public static boolean isInTheCity(double[] distance) {
        if (distance[0] <= 0.5 && distance[1] <= 0.5) {
            return true;
        } else {
            return false;
        }
    }

}