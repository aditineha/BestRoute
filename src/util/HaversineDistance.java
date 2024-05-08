package util;

import model.Location;
import static java.lang.Math.*;


public class HaversineDistance {
  private static final double EARTH_RADIUS = 6371; // in km

  public static double calculateDistance(Location loc1, Location loc2) {
      double lat1 = toRadians(loc1.getLatitude());
      double lon1 = toRadians(loc1.getLongitude());
      double lat2 = toRadians(loc2.getLatitude());
      double lon2 = toRadians(loc2.getLongitude());

      double latDiff = lat2 - lat1;
      double lonDiff = lon2 - lon1;

      double a = sin(latDiff / 2) * sin(latDiff / 2) +
              cos(lat1) * cos(lat2) * sin(lonDiff / 2) * sin(lonDiff / 2);
      double c = 2 * atan2(sqrt(a), sqrt(1 - a));

      return EARTH_RADIUS * c;
  }

  
}
