package shortpath.graph;

import lombok.Data;

@Data
public class BurgerKing extends Restaurant {
    String name;
    double latitude;
    double longitude;
    String address;

    public BurgerKing(String name, double latitude, double longitude, String address) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}
