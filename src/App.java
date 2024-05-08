import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.DeliveryInput;
import model.DeliveryPath;
import model.Location;
import service.DeliveryScheduler;
import service.OrderProcessor;
import service.PathStrategy;
import service.ValidatedPathStrategy;
import util.FileUtil;

public class App {
    public static void main(String[] args) throws Exception {
        String inputFilePath = "/home/xb/Documents/input.txt"; // Path to the input file

        try {
            DeliveryInput input = FileUtil.readInputFromFile(inputFilePath);

            Location start = input.getStart();
            Map<Location, Double> restaurants = input.getRestaurants();
            List<Location> consumers = input.getConsumers();

            List<Location> allLocations = new ArrayList<>(restaurants.keySet());
            allLocations.addAll(consumers);

            PathStrategy validatedStrategy = new ValidatedPathStrategy();
            OrderProcessor orderProcessor = new OrderProcessor(validatedStrategy);
            DeliveryScheduler scheduler = new DeliveryScheduler(orderProcessor);

            DeliveryPath optimalPath = scheduler.scheduleOptimalDelivery(start, allLocations, restaurants);

            if (optimalPath != null) {
                System.out.println("Optimal Path: " + optimalPath);
            } else {
                System.out.println("No valid path found.");
            }

        } catch (IOException e) {
            System.err.println("Error reading input file: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

}




