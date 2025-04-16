import java.sql.*;
import java.util.*;
import java.net.URL;
import java.time.LocalDate;

public class elephantSQL_console {
    public static void main(String[] args) throws Exception {

        String url = "jdbc:postgresql://isabelle.db.elephantsql.com/yczrhgub";
        String username = "yczrhgub";
        String password = "xspSCM8b4pLLGkYTW088oIgK4fbyzOtC";
        Connection con = DriverManager.getConnection(url, username, password);

        Map<Integer, List<String>> detailsMap = new HashMap<>();
        // Key=pid
        TreeMap<String, List<String>> citiesMap = new TreeMap<>();
        // Key=seq+&&&+pid+@@@+cityName
        Map<Integer, List<String>> itiMap = new HashMap<>();
        // Key=(id*100)+day
        Map<String, List<String>> packageTypesMap = new HashMap<>();
        // Key=pid+packageType
        Map<String, List<String>> amenitiesMap = new HashMap<>();
        // Key=pid+packageType+amenity
        Map<Integer, List<URL>> imagesMap = new HashMap<>();
        // Key=pid

        Statement statement = con.createStatement();

        String sql2 = "SELECT * FROM public.\"Itineraries\"";
        ResultSet rs2 = statement.executeQuery(sql2);

        while (rs2.next()) {
            int id = rs2.getInt("Package_pid");
            String day = rs2.getString("Day");
            String description = rs2.getString("Description");

            List<String> itinerariesList = new ArrayList<>();
            itinerariesList.add(day);
            itinerariesList.add(description);

            // Creating key
            int key_iti = (id * 100) + (Integer.parseInt(day.substring(4)));

            // Store details
            itiMap.put(key_iti, itinerariesList);
        }
        Map<Integer, List<String>> itinerariesMap = sortbykey(itiMap);

        String sql3 = "SELECT * FROM public.\"Package_Type\"";
        ResultSet rs3 = statement.executeQuery(sql3);

        while (rs3.next()) {
            int id = rs3.getInt("Package_pid");
            String packageType = rs3.getString("Package_Type");
            int cost = rs3.getInt("Cost");
            String descrip = rs3.getString("Description");

            List<String> packageTypeList = new ArrayList<>();
            packageTypeList.add(packageType);
            packageTypeList.add(String.valueOf(cost));
            packageTypeList.add(descrip);

            // Creating key
            String key_pt = String.valueOf(id).concat(packageType);

            // Store details
            packageTypesMap.put(key_pt, packageTypeList);
        }

        String sql = "SELECT * FROM public.\"Packages\"";
        ResultSet rs = statement.executeQuery(sql);

        while (rs.next()) {
            int id = rs.getInt("Package_pid");
            String name = rs.getString("Package_name");
            String des = rs.getString("Description");
            int dur = rs.getInt("Duration");
            int stars = rs.getInt("Stars");
            String vf = rs.getString("valid_from");
            String vt = rs.getString("valid_to");
            String cancellation = rs.getString("Cancellation_policy");
            String inc = rs.getString("Inclusions");
            String exc = rs.getString("Exclusions");

            // Create ArrayList
            List<String> packageDetails = new ArrayList<>();

            packageDetails.add(name);
            packageDetails.add(des);
            packageDetails.add(String.valueOf(dur));
            packageDetails.add(String.valueOf(stars));
            packageDetails.add(vf);
            packageDetails.add(vt);
            packageDetails.add(cancellation);
            packageDetails.add(inc);
            packageDetails.add(exc);
            packageDetails.add(String.valueOf(getMinCost(String.valueOf(id), packageTypesMap)));

            // Store details
            detailsMap.put(id, packageDetails);
        }

        String sql4 = "SELECT * FROM public.\"Amenities\"";
        ResultSet rs4 = statement.executeQuery(sql4);

        while (rs4.next()) {
            int id = rs4.getInt("Package_pid");
            String packageType = rs4.getString("Package_Type");
            String a = rs4.getString("Amenities");

            List<String> amenitiesList = new ArrayList<>();
            amenitiesList.add(a);

            // Creating key
            String c = String.valueOf(id).concat(packageType);
            String key_a = c.concat(a);

            // Store values
            amenitiesMap.put(key_a, amenitiesList);
        }

        String sql5 = "SELECT * FROM public.\"Images\"";
        ResultSet rs5 = statement.executeQuery(sql5);

        while (rs5.next()) {
            int id = rs5.getInt("Package_pid");
            String image = rs5.getString("Image");

            // Convert string URL to URL object
            URL imageUrl = new URL(image);

            List<URL> imagesURL = new ArrayList<>();
            imagesURL.add(imageUrl);

            // Store details
            imagesMap.put(id, imagesURL);
        }

        Scanner sc = new Scanner(System.in);

        int choice;
        do {
            System.out.println("Choose an option:");
            System.out.println("1. View all packages");
            System.out.println("2. View packages by city");
            System.out.println("3. Exit");
            choice = sc.nextInt();
            Map<Integer, List<String>> SortedDetailsMap = new HashMap<>();
            int sortOption = 1;
            int sortOrder = 1;

            switch (choice) {
                case 1:
                    SortedDetailsMap = sortPackagesByCost(detailsMap, sortOrder);
                    String sql1 = "SELECT * FROM public.\"Cities\"";
                    ResultSet rs1 = statement.executeQuery(sql1);

                    while (rs1.next()) {
                        int id = rs1.getInt("Package_pid");
                        String cities = rs1.getString("City");
                        int no_of_days = rs1.getInt("No_Of_Days");

                        List<String> citiesList = new ArrayList<>();
                        citiesList.add(cities);
                        citiesList.add(String.valueOf(no_of_days));

                        // Get the sequence from the sorted details map based on the current package ID
                        List<String> packageDetails = SortedDetailsMap.get(id);
                        String sequence = packageDetails != null ? packageDetails.get(packageDetails.size() - 1) : "";

                        // Creating key
                        String key_city = sequence + "&&&" + id + "@@@" + cities;

                        // Store details
                        citiesMap.put(key_city, citiesList);
                    }

                    viewAllPackages(detailsMap, citiesMap, itinerariesMap, imagesMap, packageTypesMap, amenitiesMap);
                    break;

                case 2:
                    do{
                        System.out.println("Sort packages by-\n1-Cost\n2-Star rating\n3-Duration");
                        sortOption = sc.nextInt();
                        sc.nextLine();
                        if (sortOption >= 1 && sortOption <= 3) {

                        } else {
                            System.out.println("Invalid option chosen");
                        }
                    } while (!(sortOption >= 1 && sortOption <= 3));

                    do {
                        System.out.println("Sort in:\n1-Ascending\n2-Descending");
                        sortOrder = sc.nextInt();
                        sc.nextLine();
                        if (sortOrder == 1 || sortOrder == 2) {

                        } else {
                            System.out.println("Invalid option chosen");
                        }
                    }while (!(sortOrder>=1 && sortOrder<=2));

                    do{
                        switch (sortOption) {
                            case 1:
                                SortedDetailsMap = sortPackagesByCost(detailsMap, sortOrder);
                                break;
                            case 2:
                                SortedDetailsMap = sortPackagesByStarRatings(detailsMap, sortOrder);
                                break;
                            case 3:
                                SortedDetailsMap = sortPackagesByDuration(detailsMap, sortOrder);
                                break;
                            default:
                                System.out.println("Invalid option chosen!!!");
                                System.out.println("Sort packages by-\n1-Cost\n2-Star rating\n3-Duration");
                                sortOption = sc.nextInt();
                                sc.nextLine();
                        }
                    } while (!(sortOption >= 1 && sortOption <= 3));


                    String sql1_1 = "SELECT * FROM public.\"Cities\"";
                    ResultSet rs1_1 = statement.executeQuery(sql1_1);

                    while (rs1_1.next()) {
                        int id = rs1_1.getInt("Package_pid");
                        String cities = rs1_1.getString("City");
                        int no_of_days = rs1_1.getInt("No_Of_Days");

                        List<String> citiesList = new ArrayList<>();
                        citiesList.add(cities);
                        citiesList.add(String.valueOf(no_of_days));

                        // Get the sequence from the sorted details map based on the current package ID
                        List<String> packageDetails = SortedDetailsMap.get(id);
                        String sequence = packageDetails != null ? packageDetails.get(packageDetails.size() - 1) : "";

                        // Creating key
                        String key_city = sequence + "&&&" + id + "@@@" + cities;

                        // Store details
                        citiesMap.put(key_city, citiesList);
                    }
                    viewPackagesByCity(detailsMap, citiesMap, itinerariesMap, imagesMap, packageTypesMap, amenitiesMap);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }

        } while (choice != 3);

        sc.close();
        con.close();
    }

    public static void viewAllPackages(Map<Integer, List<String>> detailsMap,
                                       Map<String, List<String>> citiesMap,
                                       Map<Integer, List<String>> itinerariesMap,
                                       Map<Integer, List<URL>> imagesMap,
                                       Map<String, List<String>> packageTypesMap,
                                       Map<String, List<String>> amenitiesMap) {
        Scanner scanner = new Scanner(System.in);
        // Iterate through all packages and display details
        for (Map.Entry<Integer, List<String>> entry : detailsMap.entrySet()) {
            int packageId = entry.getKey();
            List<String> details = entry.getValue();

            System.out.println("Package ID: " + packageId);
            System.out.println("Package Name: " + details.get(0));
            System.out.println("Description: " + details.get(1));
            System.out.println("Duration: " + details.get(2) + " days");
            System.out.println("Stars: " + details.get(3));
            System.out.println("Valid From: " + details.get(4));
            System.out.println("Valid To: " + details.get(5));
            System.out.println();
        }
        System.out.println("Enter the package ID you want to view details for:");
        int selectedPackageId = scanner.nextInt();
        viewAllInfo(selectedPackageId, detailsMap, citiesMap, itinerariesMap, imagesMap, packageTypesMap, amenitiesMap);
    }

    public static void viewPackagesByCity(Map<Integer, List<String>> detailsMap,
                                          Map<String, List<String>> citiesMap,
                                          Map<Integer, List<String>> itinerariesMap,
                                          Map<Integer, List<URL>> imagesMap,
                                          Map<String, List<String>> packageTypesMap,
                                          Map<String, List<String>> amenitiesMap) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter city name:");
        String city = scanner.nextLine();

        for (Map.Entry<String, List<String>> cityEntry : citiesMap.entrySet()) {
            List<String> cities = cityEntry.getValue();
            if (cities.contains(city)) {
                String[] parts = cityEntry.getKey().split("&&&"); // Split the key string using "&&&" delimiter
                String packageIdStr = parts[1].split("@@@")[0]; // Extract the package ID part before "@@@" delimiter
                int packageIdFromCity = Integer.parseInt(packageIdStr);

                for (Map.Entry<Integer, List<String>> entry : detailsMap.entrySet()) {
                    int packageId = entry.getKey();
                    List<String> details = entry.getValue();

                    if (packageIdFromCity == packageId) {
                        System.out.println("Package ID: " + packageId);
                        System.out.println("Package Name: " + details.get(0));
                        System.out.println("Description: " + details.get(1));
                        System.out.println("Duration: " + details.get(2) + " days");
                        System.out.println("Stars: " + details.get(3));
                        System.out.println("Valid From: " + details.get(4));
                        System.out.println("Valid To: " + details.get(5));
                        System.out.println();
                    }
                }
            }
        }
        System.out.println("Enter the package ID you want to view details for:");
        int selectedPackageId = scanner.nextInt();
        viewAllInfo(selectedPackageId, detailsMap, citiesMap, itinerariesMap, imagesMap, packageTypesMap, amenitiesMap);
    }

    public static void viewAllInfo(int selectedPackageId,
                                   Map<Integer, List<String>> detailsMap,
                                   Map<String, List<String>> citiesMap,
                                   Map<Integer, List<String>> itinerariesMap,
                                   Map<Integer, List<URL>> imagesMap,
                                   Map<String, List<String>> packageTypesMap,
                                   Map<String, List<String>> amenitiesMap) {
        Scanner sc = new Scanner(System.in);
        // Display details of the selected package
        if (detailsMap.containsKey(selectedPackageId)) {
            List<String> selectedPackageDetails = detailsMap.get(selectedPackageId);

            // Display package details
            System.out.println("Selected Package Details:");
            System.out.println("Package ID: " + selectedPackageId);
            System.out.println("Package Name: " + selectedPackageDetails.get(0));
            System.out.println("Description: " + selectedPackageDetails.get(1));
            System.out.println("Duration: " + selectedPackageDetails.get(2) + " days");
            System.out.println("Stars: " + selectedPackageDetails.get(3));
            System.out.println("Valid From: " + selectedPackageDetails.get(4));
            System.out.println("Valid To: " + selectedPackageDetails.get(5));
            System.out.println();
            System.out.println();

            // Display cities for the selected package
            System.out.println("Cities:");
            for (Map.Entry<String, List<String>> cityEntry : citiesMap.entrySet()) {
                String[] parts = cityEntry.getKey().split("&&&"); // Split the key string using "&&&" delimiter
                String packageIdStr = parts[1].split("@@@")[0]; // Extract the package ID part before "@@@" delimiter
                int packageIdFromCity = Integer.parseInt(packageIdStr);

                if ((packageIdFromCity) == (selectedPackageId)) {
                    System.out.println("City: " + cityEntry.getValue().get(0));
                    System.out.println("NoOfDays: " + cityEntry.getValue().get(1));
                }
            }
            System.out.println();
            System.out.println();

            // Display itineraries for the selected package
            System.out.println("Itineraries:");
            for (Map.Entry<Integer, List<String>> itiEntry : itinerariesMap.entrySet()) {
                if ((itiEntry.getKey()) / 100 == selectedPackageId) {
                    System.out.println("Day: " + itiEntry.getValue().get(0));
                    System.out.println("Description: " + itiEntry.getValue().get(1));
                }
            }
            System.out.println();
            System.out.println();

            // Display images for the selected package
            System.out.println("Images:");
            List<URL> images = imagesMap.get(selectedPackageId);
            if (images != null) {
                for (URL imageUrl : images) {
                    System.out.println(imageUrl);
                }
            }
            System.out.println();
            System.out.println();

            // Display package types and amenities for the selected package
            List<String> packageTypeKeys = new ArrayList<>();
            for (Map.Entry<String, List<String>> packageTypeEntry : packageTypesMap.entrySet()) {
                if (packageTypeEntry.getKey().startsWith(Integer.toString(selectedPackageId))) {
                    List<String> packageType = packageTypeEntry.getValue();
                    System.out.println();
                    System.out.println("Package Type: " + packageType.get(0));
                    System.out.println("Cost: " + packageType.get(1));
                    System.out.println("Description: " + packageType.get(2));
                    System.out.println("Amenities:");

                    // Display amenities for this package type
                    for (Map.Entry<String, List<String>> amenityEntry : amenitiesMap.entrySet()) {
                        if (amenityEntry.getKey().contains(Integer.toString(selectedPackageId)) && amenityEntry.getKey().contains(packageType.get(0))) {
                            List<String> amenityDetails = amenityEntry.getValue();
                            System.out.println("- " + amenityDetails.get(0));
                        }
                    }
                }
            }

            System.out.println();
            System.out.println("Do you want to book this package?(Y/N)");
            String c = sc.nextLine();
            if (c.equalsIgnoreCase("Y")) {
                // Prompt the user to select a package type
                System.out.println("Package Types:");
                System.out.println();

                for (Map.Entry<String, List<String>> packageTypeEntry : packageTypesMap.entrySet()) {
                    if (packageTypeEntry.getKey().startsWith(Integer.toString(selectedPackageId))) {
                        List<String> packageType = packageTypeEntry.getValue();
                        System.out.println("Package Type: " + packageType.get(0));
                        System.out.println("Cost: " + packageType.get(1));
                    }
                }

                System.out.println();
                System.out.println("Select package type");
                // Get user input for the package type
                String selectedPackageType = sc.nextLine();

                // Check if the selected package type is valid
                if (packageTypesMap.containsKey((String.valueOf(selectedPackageId)).concat(selectedPackageType))) {
                    // Valid package type selected, proceed with booking

                    System.out.println("Enter name of lead passenger");
                    String name= sc.nextLine();

                    System.out.println("Enter no of people:");
                    int ppl = sc.nextInt();
                    sc.nextLine();

                    boolean isPhoneNumberValid, isEmailValid;

                    do {
                        // Prompt user to enter phone number
                        System.out.print("Enter lead passenger phone number: ");
                        String phoneNumber = sc.nextLine();

                        // Validate phone number
                        isPhoneNumberValid = isValidPhoneNumber(phoneNumber);
                        if (isPhoneNumberValid) {
                            System.out.println("Phone number is valid.");
                        } else {
                            System.out.println("Invalid phone number.");
                        }
                    } while (!isPhoneNumberValid);

                    do {
                        // Prompt user to enter email ID
                        System.out.print("Enter lead passenger email ID: ");
                        String email = sc.nextLine();

                        // Validate email ID
                        isEmailValid = isValidEmail(email);
                        if (isEmailValid) {
                            System.out.println("Email ID is valid.");
                        } else {
                            System.out.println("Invalid email ID.");
                        }
                    } while (!isEmailValid);

                    double cost = Double.parseDouble(packageTypesMap.get((String.valueOf(selectedPackageId)).concat(selectedPackageType)).get(1));
                    double totCost = (ppl * cost) + (0.12 * ppl * cost);
                    System.out.println();
                    System.out.println("CONGRATULATIONS!!! YOUR PACKAGE IS SUCCESSFULLY BOOKED");
                    System.out.println();
                    int currentYear = LocalDate.now().getYear();

                    // Generate a random four-digit number
                    int randomFourDigitNumber = (int) (Math.random() * 10000);

                    // Format the random number as a string with leading zeros if necessary
                    String formattedRandomNumber = String.format("%04d", randomFourDigitNumber);

                    // Construct the booking reference number
                    String bookingReferenceNumber = "BKR-" + currentYear + "-" + formattedRandomNumber;

                    // Print the booking reference number
                    System.out.println("Your Booking Reference Number is: " + bookingReferenceNumber);
                    System.out.println();
                    System.out.println("*****Booking Details*****");
                    System.out.println("Package ID: " + selectedPackageId);
                    System.out.println("Package Name: " + selectedPackageDetails.get(0));
                    System.out.println("Description: " + selectedPackageDetails.get(1));
                    System.out.println("Duration: " + selectedPackageDetails.get(2) + " days");
                    System.out.println("Star Rating: " + selectedPackageDetails.get(3));
                    System.out.println("Cancellation Policy: " + selectedPackageDetails.get(6));
                    System.out.println("Package Type: " + selectedPackageType);
                    System.out.println("Number of People: " + ppl);
                    System.out.println("Base Cost per Person: Rs " + cost);
                    System.out.println("Tax (12%): Rs " + (0.12 * ppl * cost));
                    System.out.println("-------------------------------");
                    System.out.println("Final Total (Including Tax): Rs " + totCost);
                    System.out.println();
                } else {
                    System.out.println("Invalid package type!");
                    System.out.println();
                }
            }
        } else {
            System.out.println("Invalid package ID!");
            System.out.println();
        }
    }

    public static Map<Integer, List<String>> sortbykey(Map<Integer, List<String>> map) {
        // TreeMap sorts the keys automatically
        TreeMap<Integer, List<String>> sortedMap = new TreeMap<>(map);
        return sortedMap;
    }

    public static void bubbleSortPackageTypesByCost(Map<String, List<String>> packageTypesMap) {
        List<Map.Entry<String, List<String>>> list = new LinkedList<>(packageTypesMap.entrySet());
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                int cost1 = Integer.parseInt(list.get(j).getValue().get(1));
                int cost2 = Integer.parseInt(list.get(j + 1).getValue().get(1));
                if (cost1 > cost2) {
                    // Swap elements
                    Collections.swap(list, j, j + 1);
                }
            }
        }
        // Convert sorted list back to map
        packageTypesMap.clear();
        for (Map.Entry<String, List<String>> entry : list) {
            packageTypesMap.put(entry.getKey(), entry.getValue());
        }
    }


    public static Map<Integer, List<String>> sortPackagesByDuration(Map<Integer, List<String>> detailsMap, int a) {
        // Convert Map to List of entries
        List<Map.Entry<Integer, List<String>>> list = new LinkedList<>(detailsMap.entrySet());

        // Sort the list based on duration
        list.sort((o1, o2) -> {
            if (a == 1) {
                return o1.getValue().get(2).compareTo(o2.getValue().get(2));
            } else {
                return o2.getValue().get(2).compareTo(o1.getValue().get(2));
            }
        });

        // Convert sorted list back to Map
        Map<Integer, List<String>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<String>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static Map<Integer, List<String>> sortPackagesByStarRatings(Map<Integer, List<String>> detailsMap, int a) {
        // Convert Map to List of entries
        List<Map.Entry<Integer, List<String>>> list = new LinkedList<>(detailsMap.entrySet());

        // Sort the list based on star ratings
        list.sort((o1, o2) -> {
            if (a == 1) {
                return o1.getValue().get(3).compareTo(o2.getValue().get(3));
            } else {
                return o2.getValue().get(3).compareTo(o1.getValue().get(3));
            }
        });

        // Convert sorted list back to Map
        Map<Integer, List<String>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<String>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static Map<Integer, List<String>> sortPackagesByCost(Map<Integer, List<String>> detailsMap, int a) {
        // Convert Map to List of entries
        List<Map.Entry<Integer, List<String>>> list = new LinkedList<>(detailsMap.entrySet());

        // Sort the list based on cost
        list.sort((o1, o2) -> {
            if (a == 1) {
                return o1.getValue().get(9).compareTo(o2.getValue().get(9));
            } else {
                return o2.getValue().get(9).compareTo(o1.getValue().get(9));
            }
        });

        // Convert sorted list back to Map
        Map<Integer, List<String>> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Integer, List<String>> entry : list) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public static int getMinCost(String packageId, Map<String, List<String>> packageTypesMap) {
        int minCost = Integer.MAX_VALUE;
        for (Map.Entry<String, List<String>> entry : packageTypesMap.entrySet()) {
            if (entry.getKey().startsWith(packageId)) {
                int cost = Integer.parseInt(entry.getValue().get(1));
                if (cost < minCost) {
                    minCost = cost;
                }
            }
        }
        return minCost;
    }

    // Method to validate phone number
    private static boolean isValidPhoneNumber(String phoneNumber) {
        // Check if phone number contains only digits and has 10 characters
        return phoneNumber.matches("[0-9]+") && phoneNumber.length() == 10;
    }

    // Method to validate email ID
    private static boolean isValidEmail(String email) {
        // Check if email contains '@' symbol
        return email.contains("@");
    }
}
