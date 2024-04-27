package buffer;
import java.util.*;

class Graph {
    private int V;
    private LinkedList<Edge>[] adj;

    Graph(int v) {
        V = v;
        adj = new LinkedList[V];
        for (int i = 0; i < V; ++i)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int u, int v, int w) {
        adj[u].add(new Edge(v, w));
        adj[v].add(new Edge(u, w)); // Assuming bidirectional edges
    }

    List<Integer> shortestPath(int src, int dest) {
        PriorityQueue<Node> pq = new PriorityQueue<>(V, Comparator.comparingInt(a -> a.dist));
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] parent = new int[V];
        Arrays.fill(parent, -1);
        pq.add(new Node(src, 0));
        dist[src] = 0;

        while (!pq.isEmpty()) {
            int u = pq.poll().id;
            for (Edge e : adj[u]) {
                int v = e.to;
                int weight = e.weight;
                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.add(new Node(v, dist[v]));
                    parent[v] = u;
                }
            }
        }

        List<Integer> path = new ArrayList<>();
        for (int i = dest; i != -1; i = parent[i])
            path.add(i);
        Collections.reverse(path);
        return path;
    }

    int calculateDistance(List<Integer> path) {
        int distance = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            int u = path.get(i);
            int v = path.get(i + 1);
            for (Edge e : adj[u]) {
                if (e.to == v) {
                    distance += e.weight;
                    break;
                }
            }
        }
        return distance;
    }

    List<Integer> getChargingPoints(List<Integer> path) {
        List<Integer> chargingPoints = new ArrayList<>();
        for (Integer node : path) {
            if (node == 1 || node == 3) { // Assuming EV charging stations are located at nodes 1 and 3
                chargingPoints.add(node);
            }
        }
        return chargingPoints;
    }

    double calculateCarbonEmission(int distance) {
        // Assuming a carbon emission rate of 0.2 kg/km for electric vehicles
        return distance * 0.2;
    }

    String recommendElectricVehicle(int distance) {
        if (distance <= 10) {
            return "Electric Bike";
        } else if (distance <= 20) {
            return "Electric Car";
        } else {
            return "Electric Bus";
        }
    }

    void printAdjacencyMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                boolean edgeExists = false;
                for (Edge e : adj[i]) {
                    if (e.to == j) {
                        edgeExists = true;
                        break;
                    }
                }
                System.out.print(edgeExists ? "1 " : "0 ");
            }
            System.out.println();
        }
    }
}

class Edge {
    int to;
    int weight;

    Edge(int t, int w) {
        to = t;
        weight = w;
    }
}

class Node {
    int id;
    int dist;

    Node(int i, int d) {
        id = i;
        dist = d;
    }
}
