package shortpath.graph;

import lombok.Data;

@Data
public class Wendy extends Restaurant {
    String name;
    double latitude;
    double longitude;
    String address;

    public Wendy(String name, double latitude, double longitude, String address) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.address = address;
    }
}
