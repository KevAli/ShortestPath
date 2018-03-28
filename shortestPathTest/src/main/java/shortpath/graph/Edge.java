package shortpath.graph;

import lombok.Data;

@Data
public class Edge {
    Vertex cityFrom;
    Vertex cityTo;
    double weight;

    public Edge(Vertex cityFrom, Vertex cityTo) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.weight = distanceOfCities();
    }

    public Edge() {
    }

    private double distanceOfCities() {
        return (cityFrom.getLatitude() - cityTo.getLatitude()) * (cityFrom.getLatitude() - cityTo.getLatitude()) +
                (cityFrom.getLongitude() - cityTo.getLongitude()) * (cityFrom.getLongitude() - cityTo.getLongitude());
    }
}
