package model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DeliveryInput {
    private final Location start;
    private final Map<Location, Double> restaurants;
    private final List<Location> consumers;

    public DeliveryInput(Location start, Map<Location, Double> restaurants, List<Location> consumers) {
        this.start = start;
        this.restaurants = new LinkedHashMap<>(restaurants); // Maintain insertion order
        this.consumers = new ArrayList<>(consumers);
    }

    public Location getStart() {
        return start;
    }

    public Map<Location, Double> getRestaurants() {
        return restaurants;
    }

    public List<Location> getConsumers() {
        return consumers;
    }
}
