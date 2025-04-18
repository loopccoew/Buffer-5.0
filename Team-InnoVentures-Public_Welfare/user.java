package buffer;
import java.util.*;

public class user {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes in the graph: ");
        int nodes = scanner.nextInt();

        Graph graph = new Graph(nodes);

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        System.out.println("Enter the edges (source destination weight):");
        for (int i = 0; i < edges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }

        System.out.print("Enter the source node: ");
        int src = scanner.nextInt();

        System.out.print("Enter the destination node: ");
        int dest = scanner.nextInt();

        List<Integer> shortestPath = graph.shortestPath(src, dest);
        System.out.println("Shortest Path from " + src + " to " + dest + ": " + shortestPath);

        int distance = graph.calculateDistance(shortestPath);
        System.out.println("Distance: " + distance + " units");

        List<Integer> chargingPoints = graph.getChargingPoints(shortestPath);
        System.out.println("Charging Points available in the path: " + chargingPoints);

        String recommendedVehicle = graph.recommendElectricVehicle(distance);
        System.out.println("Recommended Electric Vehicle: " + recommendedVehicle);

        double carbonEmission = graph.calculateCarbonEmission(distance);
        System.out.println("Carbon Emission: " + carbonEmission + " kg");

        scanner.close();       
    }
}

