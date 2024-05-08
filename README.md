# Delivery Path Optimization

This project calculates the optimal delivery path for a delivery executive given a set of input locations. The program determines the best order of visits to minimize the total delivery time, considering preparation times at restaurants and travel distances between locations.

## Project Overview
The project takes input from a text file, which includes:
- The start location where the delivery executive begins.
- A list of restaurants with their preparation times.
- A list of consumers to whom deliveries are to be made.

Using a validated strategy, the program generates valid paths and calculates the optimal path based on travel distances and preparation times. The final output is the optimal path and the corresponding total time.

## Prerequisites
- Java Development Kit (JDK) 8 or later.
- A text editor or Integrated Development Environment (IDE) for Java development.

## Project Structure
- `Main.java`: The main entry point for the program. Reads the input from the specified text file and calculates the optimal delivery path.
- `FileProcessor.java`: Contains methods to read and parse the input file to extract the necessary locations and preparation times.
- `Location.java`: Represents a geographical location with name, latitude, and longitude.
- `DeliveryPath.java`: Represents the optimal path, including the total time required for delivery.
- `Haversine.java`: Contains a utility function to calculate distances between geographical locations.
- `ValidatedPathStrategy.java`: Implements the strategy for generating valid paths and finding the optimal one.
- `OrderProcessor.java`: Orchestrates the pathfinding process using a strategy pattern.
- `DeliveryScheduler.java`: Manages the scheduling and finds the optimal delivery path.

## Usage
1. Prepare an input file with the necessary data. The input file should contain:
    - A start location with the following format:
      ```
      Start: LocationName,Latitude,Longitude
      ```
    - Restaurant details with preparation times:
      ```
      Restaurant: RestaurantName,Latitude,Longitude,PreparationTimeInHours
      ```
    - Consumer details:
      ```
      Consumer: ConsumerName,Latitude,Longitude
      ```

2. Ensure the project is properly compiled.

3. Run the `Main.java` file, specifying the path to your input file:
   ```bash
   java Main
