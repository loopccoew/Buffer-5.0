Task Manager GUI
The Task Manager GUI is a Java application designed to help users manage their tasks effectively. It provides a graphical user interface (GUI) for adding, viewing, and deleting tasks, along with displaying task details such as title, description, and deadline.

Features
Add Task: Users can add new tasks by providing a title, description, and deadline in the specified format (dd/MM/yyyy HH:mm).
View Tasks: Users can view all added tasks sorted by their deadlines.The task which is to be done first is displayed first based on the deadline. Tasks are displayed in a text area within the GUI.
Delete Task: Users can select and delete tasks from the list. Tasks are deleted based on their titles.


Tools andTechniques used
ArrayList: Used to store the tasks added by the user. The ArrayList allows for dynamic resizing and efficient storage and retrieval of task objects.
Date: Utilized to represent task deadlines. Dates are parsed and formatted using the SimpleDateFormat class, ensuring consistency and compatibility with user input and display.
JTextArea: Used to display tasks within the GUI. The JTextArea component provides a scrollable text area for presenting task details to the user.
JScrollPane: Incorporated to enable scrolling functionality within the JTextArea when the number of tasks exceeds the visible area, ensuring a seamless user experience.
Custom Comparator: Implemented a custom comparator (TaskComparator) to sort tasks based on their deadlines. This comparator is used in conjunction with Collections.sort() to maintain tasks in ascending order of deadlines.


Requirements
Java Development Kit (JDK) 
Java Swing library for GUI components
Video Link
https://drive.google.com/file/d/19CG9PDrTrxDTZPwUtqw-EiJAGBzpJ2y3/view?usp=sharing






15 Day Report 1:
https://docs.google.com/document/d/12FsSRyhOI-NnVypT2zaPgVI-l46HSBTjMBG_fGUeczg/edit?usp=sharing

15 Day Report 2:
https://docs.google.com/document/d/16XrLwLSu-Wkn_sPkGkDTiV9RffEM6ZMwkeWE_xL0a1Q/edit?usp=sharing
