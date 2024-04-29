import heapq

class WasteBin:
    def __init__(self, id, fill_level=0, waste_type='general'):
        self.id = id
        self.fill_level = fill_level
        self.waste_type = waste_type

class Graph:
    def __init__(self):
        self.vertices = {}

    def add_vertex(self, bin):
        self.vertices[bin.id] = bin
        bin.adjacent = {}

    def add_edge(self, from_bin, to_bin, distance):
        self.vertices[from_bin.id].adjacent[to_bin.id] = distance
        self.vertices[to_bin.id].adjacent[from_bin.id] = distance

    def dijkstra(self, start_bin_id):
        distances = {bin_id: float('inf') for bin_id in self.vertices}
        distances[start_bin_id] = 0
        pq = [(0, start_bin_id)]
        while pq:
            current_dist, current_bin_id = heapq.heappop(pq)
            if current_dist > distances[current_bin_id]:
                continue
            for neighbor_id, distance in self.vertices[current_bin_id].adjacent.items():
                new_distance = current_dist + distance
                if new_distance < distances[neighbor_id]:
                    distances[neighbor_id] = new_distance
                    heapq.heappush(pq, (new_distance, neighbor_id))
        return distances

class TrieNode:
    def __init__(self):
        self.children = {}
        self.is_end_of_word = False

class Trie:
    def __init__(self):
        self.root = TrieNode()

    def insert(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                node.children[char] = TrieNode()
            node = node.children[char]
        node.is_end_of_word = True

    def search(self, word):
        node = self.root
        for char in word:
            if char not in node.children:
                return False
            node = node.children[char]
        return node is not None and node.is_end_of_word

class PriorityQueue:
    def __init__(self):
        self.heap = []
        self.count = 0

    def push(self, item, priority):
        heapq.heappush(self.heap, (priority, self.count, item))
        self.count += 1

    def pop(self):
        return heapq.heappop(self.heap)[-1]

    def __len__(self):
        return len(self.heap)

class WasteManagementSystem:
    def __init__(self):
        self.graph = Graph()
        self.bins = {}
        self.fill_levels = {}
        self.trie = Trie()

    def add_bin(self, bin):
        self.graph.add_vertex(bin)
        self.bins[bin.id] = bin
        self.fill_levels[bin.id] = bin.fill_level
        # Insert waste type into trie
        self.trie.insert(bin.waste_type)

    def add_connection(self, from_bin, to_bin, distance):
        self.graph.add_edge(from_bin, to_bin, distance)

    def update_fill_level(self, bin_id, fill_level):
        self.fill_levels[bin_id] = fill_level

    def predict_next_bin(self, required_waste_type=None):
        if not self.fill_levels:
            return None
        
        # Filter bins by required waste type
        filtered_bins = {bin_id: fill_level for bin_id, fill_level in self.fill_levels.items() if required_waste_type is None or self.bins[bin_id].waste_type == required_waste_type}

        if not filtered_bins:
            return None
        
        # Create priority queue based on fill level
        pq = PriorityQueue()
        for bin_id, fill_level in filtered_bins.items():
            pq.push(bin_id, fill_level)
        
        # Predict bin with lowest fill level
        return pq.pop()

def main():
    waste_system = WasteManagementSystem()

    print("=== Waste Management System ===")
    print("Please enter information about waste bins and connections.")

    # Taking inputs for waste bin locations
    while True:
        bin_id = input("Enter bin ID (or 'done' to finish): ")
        if bin_id.lower() == 'done':
            break
        fill_level = int(input("Enter the fill level (in percentage) for bin {}: ".format(bin_id)))
        waste_type = input("Enter the type of waste for bin {} (e.g., organic, recyclable, non-recyclable): ".format(bin_id))
        waste_system.add_bin(WasteBin(bin_id, fill_level, waste_type))

    # Taking inputs for connections between waste bins
    while True:
        from_bin_id = input("Enter the ID of the source bin for connection (or 'done' to finish): ")
        if from_bin_id.lower() == 'done':
            break
        if from_bin_id not in waste_system.bins:
            print("Error: The entered source bin ID does not exist. Please enter a valid bin ID.")
            continue
        to_bin_id = input("Enter the ID of the destination bin for connection: ")
        if to_bin_id not in waste_system.bins:
            print("Error: The entered destination bin ID does not exist. Please enter a valid bin ID.")
            continue
        distance = int(input("Enter the distance (in meters) between bins {} and {}: ".format(from_bin_id, to_bin_id)))
        waste_system.add_connection(waste_system.bins[from_bin_id], waste_system.bins[to_bin_id], distance)

    # Updating the fill levels based on user inputs for each bin
    print("Please update the fill levels for each bin:")
    for bin_id in waste_system.bins:
        fill_level = int(input("Enter the new fill level (in percentage) for bin {}: ".format(bin_id)))
        waste_system.update_fill_level(bin_id, fill_level)

    # Predicting the next bin based on fill levels
    print("Predicting the next bin for collection...")
    next_bin = waste_system.predict_next_bin()
    if next_bin:
        print("The predicted next bin for collection is bin", next_bin)
        print("This bin is selected based on its lower fill level compared to other bins, prioritizing bins that need immediate attention.")
        print("Please ensure timely collection to optimize waste management efficiency.")
        
        # Continue with other operations
        while True:
            print("\n=== Additional Functionalities ===")
            print("1. View all bins and their details")
            print("2. Find the shortest path between two bins")
            print("3. Search for bins based on waste type")
            print("4. Exit")

            choice = input("Enter your choice: ")

            if choice == '1':
                print("\n=== All Bins and Their Details ===")
                for bin_id, bin in waste_system.bins.items():
                    print("Bin ID:", bin_id)
                    print("Fill Level:", bin.fill_level, "%")
                    print("Waste Type:", bin.waste_type)
                    print()
            elif choice == '2':
                from_bin_id = input("Enter the ID of the source bin: ")
                to_bin_id = input("Enter the ID of the destination bin: ")
                if from_bin_id in waste_system.bins and to_bin_id in waste_system.bins:
                    distances = waste_system.graph.dijkstra(from_bin_id)
                    shortest_distance = distances[to_bin_id]
                    print("Shortest distance between bins {} and {}: {} meters".format(from_bin_id, to_bin_id, shortest_distance))
                else:
                    print("Invalid bin IDs. Please enter valid bin IDs.")
            elif choice == '3':
                search_type = input("Enter the waste type to search for: ")
                found_bins = [bin_id for bin_id, bin in waste_system.bins.items() if bin.waste_type == search_type]
                if found_bins:
                    print("Bins with waste type '{}':".format(search_type))
                    for bin_id in found_bins:
                        print("Bin ID:", bin_id)
                else:
                    print("No bins found with waste type '{}'.".format(search_type))
            elif choice == '4':
                print("Exiting program.")
                break
            else:
                print("Invalid choice. Please enter a number between 1 and 4.")
    else:
        print("No bins available for prediction.")

if __name__ == "__main__":
    main()
