package shortpath.graph;

import lombok.Data;

@Data
public class Meal {
    private String restaurant;
    private String name;
    private double price;
    private String comment;

    public Meal(String restaurant, String name, double price, String comment) {
        this.restaurant = restaurant;
        this.name = name;
        this.price = price;
        this.comment = comment;
    }
}
