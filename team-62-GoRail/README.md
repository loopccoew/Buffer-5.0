Demo video link: https://drive.google.com/file/d/1kRI1BcsElpZ6ytrA8IPmTALXEYZHF6mZ/view?usp=sharing

Report 1 : https://docs.google.com/document/d/1Hr-PcZaEJkBstPJIHcLmxxmPvjghgDzS/edit?usp=sharing&ouid=109584937389394148034&rtpof=true&sd=true

report 2: https://docs.google.com/document/d/1AZOBMFDG9K43l1i3b5HaHywjmVF3y5Vs/edit?usp=sharing&ouid=109584937389394148034&rtpof=true&sd=true

Project Title: GO-RAIL - Railway Reservation Platform "From the moment you step on board, we're here to elevate your train journey to new heights of happiness and satisfaction. Let's make every mile better together."

Problem Statement: The traditional railway ticket booking process is often cumbersome, leading to inefficiencies and inconvenience for passengers. There is a need for a modern reservation platform that offers seamless booking experiences, real-time information updates, and improved resource utilization to enhance public welfare.

GO-RAIL is a railway reservation platform designed with the mission of providing efficient, convenient, and accessible travel solutions to all passengers. Whether it's a daily commute or a long-distance journey, GO-RAIL aims to make every train trip better, safer, and happier for everyone.

GO-RAIL is a Java-based railway reservation platform designed to provide convenient and accessible train ticket booking services . The platform aims to streamline the process of reserving train tickets for passengers while ensuring fair access to transportation facilities for all.

Features:

Admin Module:

Admin Login: Administrators can securely log in to the system using their credentials.

Admin Create Account: New administrators can create accounts to access system functionalities.

Admin Menu: Once logged in, administrators can perform various tasks such as adding trains, viewing all trains, updating seat availability, and managing feedback and complaints.

User Module:

User Login: Users can log in to the system using their credentials.

User Create Account: New users can create accounts to book tickets and provide feedback.

Search Train: Users can search for trains based on source and destination stations.

Book Tickets: Users can book tickets for available trains, select berth types, order food, and proceed to payment.

Show My Bookings: Users can view their booked tickets.

Train Kitchen: Users can view the menu of available food items in the train kitchen.

My Journey: Users can view their current journey details, including the remaining distance to the destination.

Customer Helpline and Feedback: Users can access customer helpline services, submit complaints, and provide feedback

Data Structures Used:

HashMap: Usage: Used extensively throughout the project for efficient storage and retrieval of key-value pairs.

Where: Storing user information and authentication credentials. Maintaining train details such as train number, name, source, destination, and seat availability. Recording distances between stations for train routes. Storing feedback and complaints provided by users.

Why: HashMap provides constant-time performance for basic operations such as get and put, making it suitable for fast data access and manipulation.

ArrayList: Usage: Used to store dynamic lists of elements.

Where: Maintaining lists of trains and bookings.

Managing food menu items and selected food items during ticket booking. Why: ArrayList offers dynamic resizing and efficient random access, making it suitable for scenarios where the size of the data collection may vary.

Set (HashSet): Usage: Utilized to store unique elements with no duplicates.

Where: Keeping track of visited stations during Dijkstra's algorithm implementation. Why: HashSet ensures that each station is visited only once during pathfinding algorithms, preventing redundant computations and optimizing performance. PriorityQueue:

Usage: Used to implement a priority queue data structure.

Where: Ordering food menu items based on their ratings in the train kitchen feature.

Why: PriorityQueue maintains elements in a sorted order based on their natural ordering or custom comparator, making it suitable for scenarios where elements need to be processed in a specific order of priority.

Map.Entry: Usage: Represents a key-value pair in a map.

Where: Used in conjunction with HashMap to iterate over key-value pairs during various operations such as Dijkstra's algorithm and train kitchen menu sorting.

Why: Map.Entry allows convenient access to both keys and values stored in a map, facilitating efficient iteration and manipulation of map entries.

Why Data Structures:

Efficiency: Chosen data structures are optimized for efficient storage, retrieval, and manipulation of data, ensuring optimal performance of the railway reservation platform.

Functionality: Each data structure is selected based on its suitability for specific functionalities and requirements within the project, enabling smooth execution of operations such as user authentication, ticket booking, pathfinding, and menu sorting.

Flexibility: The dynamic nature of ArrayList and HashSet allows for easy addition and removal of elements, accommodating changes in data size and content over time.

Maintainability: By leveraging appropriate data structures, the project maintains clean and organized code, enhancing readability and ease of maintenance for future updates and modifications.
