package model;

import java.util.List;

public class DeliveryPath {
  private final List<Location> path;
  private final double totalTime;

  public DeliveryPath(List<Location> path, double totalTime) {
      this.path = List.copyOf(path);
      this.totalTime = totalTime;
  }

  public List<Location> getPath() {
      return path;
  }

  public double getTotalTime() {
      return totalTime;
  }

  @Override
  public String toString() {
      return "Path: " + path + ", Total Time: " + totalTime + " hrs";
  }
}
