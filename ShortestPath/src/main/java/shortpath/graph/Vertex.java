package shortpath.graph;

import lombok.Getter;
import lombok.Setter;
import shortpath.tools.Tools;

import java.util.*;

@Getter
@Setter
public class Vertex {
    String name = "";
    Set<Vertex> adjacentVertexSet = new HashSet<Vertex>();
    Map<Vertex,Double> adjcentWeightMap = new HashMap<Vertex, Double>();
    List<Mcdonald> mcdonaldList = new ArrayList<Mcdonald>();
    List<Wendy> wendyList = new ArrayList<Wendy>();
    List<BurgerKing> burgerKingList = new ArrayList<BurgerKing>();
    double latitude = 0;
    double longitude = 0;
    boolean searched = false;

    public Vertex(String name, double latitude, double longitude) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void addMcdonald(Mcdonald mcdonald) {
        if (Tools.isInTheCity(Tools.getDistanceOfRestaurantToCity(this, mcdonald))) {
            mcdonaldList.add(mcdonald);
        }
    }

    public void addWendy(Wendy wendy) {
        if (Tools.isInTheCity(Tools.getDistanceOfRestaurantToCity(this, wendy))) {
            wendyList.add(wendy);
        }
    }

    public void addBurgerKing(BurgerKing burgerKing) {
        if (Tools.isInTheCity(Tools.getDistanceOfRestaurantToCity(this, burgerKing))) {
            burgerKingList.add(burgerKing);
        }
    }
}
