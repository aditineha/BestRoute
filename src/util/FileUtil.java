package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.DeliveryInput;
import model.DeliveryPath;
import model.Location;

public class FileUtil {
  public static DeliveryInput readInputFromFile(String filePath) throws IOException {
      List<String> lines = Files.readAllLines(Paths.get(filePath)); // Read all lines from the file
      Location start = null;
      Map<Location, Double> restaurants = new LinkedHashMap<>(); // Maintain insertion order
      List<Location> consumers = new ArrayList<>();

      for (String line : lines) {
          line = line.trim();
          if (line.startsWith("Start:")) {
              line = line.substring(6);
              // Extracting name, latitude, and longitude for the start location
              String[] parts = line.split(",");
              start = parseLocation(parts[0].trim(), parts[1].trim(), parts[2].trim()); // Requires 3 inputs
          } else if (line.startsWith("Restaurant:")) {
              line = line.substring(11);
              String[] parts = line.split(",");
              Location restaurant = parseLocation(parts[0].trim(), parts[1].trim(), parts[2].trim());
              double prepTime = Double.parseDouble(parts[3].trim()); // Preparation time
              restaurants.put(restaurant, prepTime);
          } else if (line.startsWith("Consumer:")) {
              line = line.substring(9);
              String[] parts = line.split(",");
              Location consumer = parseLocation(parts[0].trim(), parts[1].trim(), parts[2].trim());
              consumers.add(consumer);
          }
      }

      if (start == null || restaurants.isEmpty() || consumers.isEmpty()) {
          throw new IllegalArgumentException("Missing required data in the input file.");
      }

      return new DeliveryInput(start, restaurants, consumers);
  }

    private static Location parseLocation(String name, String latStr, String lonStr) {
        double latitude = Double.parseDouble(latStr);
        double longitude = Double.parseDouble(lonStr);

        return new Location(name, latitude, longitude);
    }
  
}
