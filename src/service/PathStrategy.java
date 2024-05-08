package service;

import java.util.List;
import java.util.Map;

import model.DeliveryPath;
import model.Location;

public interface PathStrategy {

    DeliveryPath findValidatedOptimalPath(Location start, List<Location> locations, Map<Location, Double> preparationTimes);


}
