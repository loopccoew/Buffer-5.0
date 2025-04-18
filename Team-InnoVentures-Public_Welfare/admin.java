package buffer;
import java.util.*;

public class admin {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of nodes in the graph: ");
        int nodes = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int edges = scanner.nextInt();

        clearConsole();

        Graph graph = new Graph(nodes);

        System.out.println("Enter the edges (source destination weight):");
        for (int i = 0; i < edges; i++) {
            int src = scanner.nextInt();
            int dest = scanner.nextInt();
            int weight = scanner.nextInt();
            graph.addEdge(src, dest, weight);
        }

        graph.printAdjacencyMatrix();

        scanner.close();
    }

    public static void clearConsole() {
        try {
            // Clear the console by printing ANSI escape code
            System.out.print("\033[H\033[2J");
            System.out.flush();
        } catch (final Exception e) {
            // Handle any exceptions
            System.out.println("Failed to clear console: " + e.getMessage());
        }
    }
}
