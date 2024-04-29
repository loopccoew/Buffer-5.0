#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct mynode {
    char name[20];
    char emailId[50];
    int phone;
    struct mynode* link;
} Node;

Node* start = NULL;

//global variables
int k, amount,travel_cost;
char type[60], place[30], date[20];

//function declaration
void heading();
void details();
void display();
void Beaches();
void Mountains();
void Desert();
void City();
void Countryside();
void Pilgrimage();
void compare();
void add_node(char name[20], char email[50], int phone);
void printTravelOptions(char *place);
void printRoutesToCity(char *place);
int getCityIndex(char *place);
int calculateTotalCost(int* priceArray);

int main() {
    int choice;

    heading();

//displaying menu
    

    printf("\n\n\t\t\t\t\t\t                  MENU");
    


    printf("\n\t\t\t\t1. Beaches\n");
    printf("\t\t\t\t2. Mountains\n");
    printf("\t\t\t\t3. Desert\n");
    printf("\t\t\t\t4. City\n");
    printf("\t\t\t\t5. Countryside\n");
    printf("\t\t\t\t6. Pilgrimage\n");
    printf("\t\t\t\tEnter your preferred choice from above mentioned categories(1-6): ");
    scanf("%d", &choice);

    switch (choice) {
        case 1:
            Beaches();
            break;
        case 2:
            Mountains();
            break;
        case 3:
            Desert();
            break;
        case 4:
            City();
            break;
        case 5:
            Countryside();
            break;
        case 6:
            Pilgrimage();
            break;
        default:
            printf("Enter Right Choice...");
            break;
    }

    compare();
 
    details();
    printRoutesToCity(place);
   printTravelOptions(place);
    display();

    return 0;
}

void Beaches() {
    heading();
    strcpy(type, "Beaches");
    printf("\n\t\t\t\t1. Goa    5 Days 4 Nights \n");
    printf("\t\t\t\t2. Pondicherry  4 Days 3 Nights \n");
    printf("\t\t\t\t3. Lakshadweep 7 Days 6 Nights \n");
}

void Mountains() {
    heading();
    strcpy(type, "Mountains");
    printf("\n\t\t\t\t1. Shimla 6 Days 7 Nights \n");
    printf("\t\t\t\t2. Manali 5 Days 4 Nights \n");
    printf("\t\t\t\t3. Kashmir 7 Days 16 Nights \n");
}

void Desert() {
    heading();
    strcpy(type, "Desert");
    printf("\n\t\t\t\t1. Jaisalmer 5 Days 4 Nights \n");
    printf("\t\t\t\t2. Ladakh 5 Days 4 Nights \n");
}

void City() {
    heading();
    strcpy(type, "City");
    printf("\n\t\t\t\t1. Mumbai 3 Days 2 Nights \n");
    printf("\t\t\t\t2. Udaipur 5 Days 4 Nights \n");
    printf("\t\t\t\t3. Delhi 3 Days 2 Nights \n");
}

void Countryside() {
    heading();
    strcpy(type, "Countryside");
    printf("\n\t\t\t\t1. Assam 6 Days 7 Nights \n");
    printf("\t\t\t\t2. Kerala 5 Days 4 Nights \n");
    printf("\t\t\t\t3. Ooty 4 Days 3 Nights\n");
}

void Pilgrimage() {
    heading();
    strcpy(type, "Pilgrimage");
    printf("\n\t\t\t\t1. Kedarnath 5 Days 4 Nights \n");
    printf("\t\t\t\t2. Mathura 4 Days 3 Nights \n");
    printf("\t\t\t\t3. Somnath 4 Days 3 Nights \n");
    printf("\t\t\t\t4. Ayodhya 4 Days 3 Nights \n");
}



int getCityIndex(char *place) {
    char *placesnames[18] = {"Goa", "Pondicherry", "Lakshadweep",  "Shimla", "Manali", "Kashmir", "Jaisalmer", "Ladakh", "Mumbai","Delhi", "Udaipur", "Assam", "Kerala", "Ooty", "Kedarnath", "Mathura", "Somnath", "Ayodhya"};

    for (int i = 0; i < 18; i++) {
        if (strcmp(place, placesnames[i]) == 0) {
            return i; 
        }
    }
    return -1; 
}
//caculate total price per person
int calculateTotalCost(int* priceArray) {
    return priceArray[0] + priceArray[1] + priceArray[2] + priceArray[3];
}


//to compare two cities based on expenditure
void compare() {
    char destination1[20], destination2[20];
    int ch, place1Index, place2Index;

    printf("\nEnter the name of two destinations from above for comparison of price for different categories:\n");
    printf("destination 1: ");
    scanf("%s", destination1);
    printf("detination 2: ");
    scanf("%s", destination2);

    printf("\nComparison between %s and %s:\n", destination1, destination2);
    char *citynames[18] = {"Goa", "Pondicherry", "Lakshadweep", "Shimla", "Manali", "Kashmir", "Jaisalmer", "Ladakh", "Mumbai","Delhi", "Udaipur", "Assam", "Kerala", "Ooty", "Kedarnath", "Mathura", "Somnath", "Ayodhya"};
    //char *columns[5] = {"Accommodation", "Food", "Activities", "Travel", "Total Cost per Person "};
    int prices[18][4] = {
        {500, 300, 800, 2000},
        {400, 250, 600, 1500},
        {600, 400, 1000, 2500},
        {500, 300, 700, 2000},
        {700, 500, 1000, 1500},
        {800, 600, 1200, 2000},
        {1000, 800, 1500, 3000},
        {900, 600, 1200, 2500},
        {1200, 900, 1800, 000},
        {400, 250, 500, 1000},
        {800, 600, 1200, 2000},
        {600, 400, 800, 1500},
        {700, 500, 1000, 1500},
        {500, 300, 700, 1000},
        {800, 600, 1200, 2000},
        {400, 250, 600, 1000},
        {700, 500, 1000, 1500},
        {500, 300, 700, 1000}};
    place1Index = getCityIndex(destination1);
    place2Index = getCityIndex(destination2);

//displaying comparison table

   printf("Following cost are for per person :\n\n");
   printf("+----------------------+-----------------+-----------------+-----------------+-----------------+------------------|\n");
   printf(" %-20s | %-15s | %-15s | %-15s | %-15s | %-23s \n", "Destination Name", "Accommodation", "Food", "Activities", "Travel", "Total Cost ");
   printf("+----------------------+-----------------+-----------------+-----------------+-----------------+-------------------|\n");
   printf(" %-20s | %-15d | %-15d | %-15d | %-15d | %-23d \n", citynames[place1Index], prices[place1Index][0], prices[place1Index][1], prices[place1Index][2], prices[place1Index][3],     calculateTotalCost(prices[place1Index]));
   printf(" %-20s | %-15d | %-15d | %-15d | %-15d | %-23d \n", citynames[place2Index], prices[place2Index][0], prices[place2Index][1], prices[place2Index][2], prices[place2Index][3], calculateTotalCost(prices[place2Index]));
   printf("+----------------------+-----------------+-----------------+-----------------+-----------------+-------------------|\n");


//taking choice from user
    printf("\n\n1.%s\n 2.%s\n", destination1, destination2);

    printf("Enter your preferred choice of destination from above(1/2):");
    scanf("%d", &ch);
    switch (ch) {
        case 1:
            printf("You selected destination 1: %s\n", destination1);
            amount=calculateTotalCost(prices[place1Index]);
            strcpy(place,destination1);
            break;
        case 2:
            printf("You selected destination 2: %s\n", destination2);
            strcpy(place,destination2);
           amount=calculateTotalCost(prices[place2Index]);
            break;
        default:
            printf("Invalid choice\n");
            break;
    }
   
}


//different routes to travel 
double travelData[18][3][2] = {
    // Bus, Train, Flight
    {{3, 1000}, {6, 600}, {3.5, 3500}},      // Goa
    {{4, 1200}, {8, 800}, {4, 4000}},        // Pondicherry
    {{5, 1400}, {10, 1600}, {4.5, 4500}},    // Lakshadweep
    {{7, 1800}, {12, 2000}, {5, 5000}},      // Shimla
    {{8, 2400}, {14, 3000}, {6, 6000}},      // Manali
    {{9, 3000}, {16, 3600}, {7, 7000}},      // Kashmir
    {{9, 3600}, {16, 4000}, {7, 7000}},      // Jaisalmer
    {{10, 4000}, {18, 5000}, {8, 8000}},     // Ladakh
    {{4, 800}, {8, 1200}, {3.5, 3500}},      // Mumbai
    {{6, 1200}, {10, 1600}, {4, 4000}},      // Udaipur
    {{6, 5000}, {10, 1600}, {4, 4000}},      // Assam
    {{7, 1400}, {12, 1800}, {4.5, 4500}},    // Kerala
    {{5, 1000}, {9, 1400}, {4.25, 4250}},    // Ooty
    {{8, 4000}, {14, 2000}, {6, 5000}},      // Kedarnath
    {{5, 4000}, {9, 1400}, {4.25, 6000}},    // Mathura
    {{7, 1400}, {12, 1800}, {4.5, 4500}},    // Somnath
    {{7, 4500}, {12, 1800}, {4.5, 8000}}     // Ayodhya
};
void printRoutesToCity(char *place) {
    int cityIndex = getCityIndex(place);
   printf("\n**Following prices are per person costs**");
    printf("\n**Routes from Pune to %s:**\n", place);
printf("+----------------------+--------------+--------------+\n");
printf("| %-20s | %-12s | %-12s |\n", "Mode", "Price (INR) ", "Time (hours)");
printf("+----------------------+--------------+--------------+\n");
printf("| %-20s | %-12.2f | %-12.2f |\n", "Bus/Taxi", travelData[cityIndex][0][1], travelData[cityIndex][0][0]);
printf("| %-20s | %-12.2f | %-12.2f |\n", "Train", travelData[cityIndex][1][1], travelData[cityIndex][1][0]);
printf("| %-20s | %-12.2f | %-12.2f |\n", "Flight", travelData[cityIndex][2][1], travelData[cityIndex][2][0]);
printf("+----------------------+--------------+--------------+\n");}


//getting preferred choice of route from user
 
char getUserTravelChoice() {
    char choice;
    printf("\nEnter 'B' for Bus/Taxi, 'T' for Train, or 'F' for Flight: ");
    scanf(" %c", &choice);
    return choice;
}

void printTravelOptions(char *place) {
    int cityIndex = getCityIndex(place);
    printf("_____________________________________________");

    printf("\nTravel Options from Pune to %s:\n", place);
    char travelChoice = getUserTravelChoice();
    printRoutesToCity(place);
    switch (travelChoice) {
        case 'B':
            printf("\nYou selected Car for traveling. Approximate price: %.2f INR.\n", travelData[cityIndex][0][1]);
           travel_cost=travelData[cityIndex][0][1];
                      break;
        case 'T':
            printf("\nYou selected Train for traveling. Approximate price: %.2f INR.\n", travelData[cityIndex][1][1]);
            travel_cost=travelData[cityIndex][1][1];

            break;
        case 'F':
            printf("\nYou selected Flight for traveling. Approximate price: %.2f INR.\n", travelData[cityIndex][2][1]);
           travel_cost=travelData[cityIndex][2][1];
                        break;
        default:
            printf("\nInvalid choice.\n");
            break;
    }
}

//taking input of passenger details from user
void details() {
    int i, a;
    char name[20], email[50];

    heading();
    

    printf("\n\t\t\t\tEnter Number Of Passengers: ");
    scanf("%d", &k);
    printf("\t\t\t\tEnter Date (DD/MM/YY)\n\t\t\t\t(enter dates after May 2024): ");
    fflush(stdin);
    fgets(date,sizeof(date), stdin);
    for (i = 1; i <= k; i++) {
        printf("\n\t\t\t\tEnter Passenger %d Details:-\n", i);
        printf("\t\t\t\tEnter Name: ");
        fflush(stdin);
        fgets(name,sizeof(name),stdin);
        printf("\t\t\t\tEnter Email: ");
        fflush(stdin);
        fgets(email,sizeof(email),stdin);
        printf("\t\t\t\tEnter Phone: ");
        scanf("%d", &a);
        add_node(name, email, a);
    }
}


//displaying travel information
void display() {
    Node* ptr;
    int j;
    printf("\t\t\t\t_____________________________________________\n");

    printf("\n\t\t\t\t\t\t Your Tour :-\n");
    printf("\n\t\t\t\tTotal Amount(Tour cost+ travel): Rs.%d/-\n", (amount+(travel_cost*2))*k);
    printf("\t\t\t\tDate : %s\n", date);
    printf("\t\t\t\tType : %s\n", type);
    printf("\t\t\t\tPlace : %s\n", place);
    printf("\t\t\t\tNumber of Passengers : %d\n", k);
    printf("\t\t\t\t_____________________________________________\n");

    printf("\n\t\t\t\t\t\tPassenger Details:-\n");

    ptr = start;
    for (j = 1; j <= k; j++) {
        printf("\n\t\t\t\tPassenger %d\n", j);
        printf("\t\t\t\tName: %s\n", ptr->name);
        printf("\t\t\t\tEmail: %s\n", ptr->emailId);
        printf("\t\t\t\tPhone: %d\n", ptr->phone);
        ptr = ptr->link;
    }

    printf("\t\t\t\t_____________________________________________\n");
    printf("\t\t\t\tWe will contact you further and send brouchure.");
    printf("\n\n\t\t\t\tHave a Nice Tour :) !!!\n");
}


//Adds a new node to the linked list to store passenger details
void add_node(char name[20], char email[50], int phone) {
    Node* temp;
    temp = (Node*)malloc(sizeof(Node));

    if (temp == NULL) {
        printf("\nMemory Cannot be allocated.\n");
        exit(0);
    }

    strcpy(temp->name, name);
    strcpy(temp->emailId, email);
    temp->phone = phone;
    temp->link = NULL;

    if (start == NULL)
        start = temp;
    else {
        Node* p;
        p = start;

        while (p->link != NULL)
            p = p->link;

        p->link = temp;
    }
}

//display heading
void heading() {
    system("cls");
    printf("\n\n\n\t\t\t\t\t _______________________________________________________\n");
    printf("\t\t\t\t\t|                                                       |\n");
    printf("\t\t\t\t\t|                      TouristaTrips                    |\n");
    printf("\t\t\t\t\t|_______________________________________________________|\n");
}
