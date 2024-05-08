package service;

import java.util.List;
import java.util.Map;

import model.DeliveryPath;
import model.Location;

public class DeliveryScheduler {

    private final OrderProcessor orderProcessor;

    public DeliveryScheduler(OrderProcessor orderProcessor) {
        this.orderProcessor = orderProcessor;
    }

    public DeliveryPath scheduleOptimalDelivery(Location start, List<Location> locations, Map<Location, Double> preparationTimes) {
      return orderProcessor.calculateOptimalPath(start, locations, preparationTimes);
  }
}
  

