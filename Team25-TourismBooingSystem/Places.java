package tourismbooking;

import java.util.ArrayList;

public class Places {
    // Inner class representing a place
    public static class Place { 
        String destination; // Attribute for destination
        String[] attractions; // Fixed-size array for attractions
        int index = 0;

        // Constructor
        public Place(String destination) { 
            this.destination = destination; // Initialize the destination
            this.attractions = new String[3]; // Initialize the attractions array
        }

        // Method to add an attraction
        public void addAttraction(String attraction) {
            if (index < 3) { // Check if there's room to add
                attractions[index] = attraction; 
                index++; // Move to the next position
            } else {
                System.out.println("Cannot add more than three attractions.");
            }
        }

        // Method to get all attractions
        public String[] getAttractions() {
            return attractions; 
        }

        // Method to get the destination
        public String getDestination() {
            return destination;
        }
    }

    // List to store hill stations (places)
    private static ArrayList<Place> hillStations = new ArrayList<>();

    // Static method to add places
    public static void addHillStations() {
        // Create five hill stations
        Place hillStation1 = new Place("Manali");
        hillStation1.addAttraction("Solang Valley");
        hillStation1.addAttraction("Rohtang Pass");
        hillStation1.addAttraction("Hadimba Temple");

        Place hillStation2 = new Place("Shimla");
        hillStation2.addAttraction("The Ridge");
        hillStation2.addAttraction("Mall Road");
        hillStation2.addAttraction("Jakhoo Temple");

        Place hillStation3 = new Place("Ooty");
        hillStation3.addAttraction("Botanical Gardens");
        hillStation3.addAttraction("Ooty Lake");
        hillStation3.addAttraction("Doddabetta Peak");

        Place hillStation4 = new Place("Darjeeling");
        hillStation4.addAttraction("Tiger Hill");
        hillStation4.addAttraction("Batasia Loop");
        hillStation4.addAttraction("Padmaja Naidu Himalayan Zoological Park");

        Place hillStation5 = new Place("Nainital");
        hillStation5.addAttraction("Naini Lake");
        hillStation5.addAttraction("Naina Peak");
        hillStation5.addAttraction("Tiffin Top");

        // Add the hill stations to the list
        hillStations.add(hillStation1);
        hillStations.add(hillStation2);
        hillStations.add(hillStation3);
        hillStations.add(hillStation4);
        hillStations.add(hillStation5);
    }

    // Method to get all hill stations
    public static ArrayList<Place> getHillStations() {
        return hillStations;
    }
}
