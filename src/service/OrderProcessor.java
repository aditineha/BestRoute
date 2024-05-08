package service;

import java.util.List;
import java.util.Map;

import model.DeliveryPath;
import model.Location;

public class OrderProcessor {

  private final PathStrategy pathStrategy;

  public OrderProcessor(PathStrategy pathStrategy) {
    this.pathStrategy = pathStrategy;
  }

  public DeliveryPath calculateOptimalPath(Location start, List<Location> locations, Map<Location, Double> preparationTimes) {
    return pathStrategy.findValidatedOptimalPath(start, locations, preparationTimes);
}

}
  

