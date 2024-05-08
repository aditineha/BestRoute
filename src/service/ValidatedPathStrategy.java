package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.DeliveryPath;
import model.Location;
import util.HaversineDistance;

public class ValidatedPathStrategy implements PathStrategy {

  private static final double AVERAGE_SPEED = 20; // km/hr

  @Override
  public DeliveryPath findValidatedOptimalPath(Location start, List<Location> locations,
      Map<Location, Double> preparationTimes) {
     // Get all valid paths based on the constraint that restaurants must be visited before consumers
     List<List<Location>> validPaths = generateValidPaths(locations, preparationTimes);

     double minTime = Double.MAX_VALUE;
     List<Location> bestPath = null;

     for (List<Location> path : validPaths) {
         double totalDistance = calculateTotalDistance(start, path);
         double totalTime = totalDistance / AVERAGE_SPEED;

         for (Location loc : path) {
             if (preparationTimes.containsKey(loc)) {
                 totalTime += preparationTimes.get(loc); // Add preparation time
             }
         }

         if (totalTime < minTime) {
             minTime = totalTime;
             bestPath = path;
         }
     }

     return new DeliveryPath(bestPath, minTime);
  }

  private double calculateTotalDistance(Location start, List<Location> path) {
    double totalDistance = HaversineDistance.calculateDistance(start, path.get(0));

    for (int i = 0; i < path.size() - 1; i++) {
        totalDistance += HaversineDistance.calculateDistance(path.get(i), path.get(i + 1));
    }

    return totalDistance;
  }

  private List<List<Location>> generateValidPaths(List<Location> locations, Map<Location, Double> preparationTimes) {
    List<Location> restaurants = new ArrayList<>(preparationTimes.keySet());
    List<Location> consumers = new ArrayList<>(locations);
    consumers.removeAll(restaurants); // Separate consumers from restaurants

    List<List<Location>> validPaths = new ArrayList<>();

    // Generate all valid combinations where restaurants are visited before consumers
    for (List<Location> restaurantOrder : generatePermutations(restaurants)) {
        for (List<Location> consumerOrder : generatePermutations(consumers)) {
            List<Location> combinedOrder = new ArrayList<>(restaurantOrder);
            combinedOrder.addAll(consumerOrder);
            validPaths.add(combinedOrder);
        }
    }

    return validPaths;
  }

  private List<List<Location>> generatePermutations(List<Location> locations) {
    if (locations.size() == 0) {
        return List.of(List.of());
    }

    List<List<Location>> permutations = new ArrayList<>();
    for (int i = 0; i < locations.size(); i++) {
        Location current = locations.get(i);
        List<Location> remaining = new ArrayList<>(locations);
        remaining.remove(i);

        List<List<Location>> subPermutations = generatePermutations(remaining);
        for (List<Location> subPermutation : subPermutations) {
            List<Location> newPermutation = new ArrayList<>();
            newPermutation.add(current);
            newPermutation.addAll(subPermutation);

            permutations.add(newPermutation);
        }
    }

    return permutations;
}
      
  
  
}
