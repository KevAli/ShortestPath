package shortpath.graph;

import lombok.Data;

@Data
public abstract class Restaurant {
    String name;
    double latitude;
    double longitude;
    String address;
}
