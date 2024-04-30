The project aims to develop a social networking platform specifically designed for students of a specific college.
The problem statement entails creating a backend system for a college level application to support the functionalities of a student social networking platform. This includes managing student details,  calculating scores based on shared interests and facilitating communication through messaging.


The primary data structure utilized is the  HashMap in Java. It offers efficient storage and retrieval of key-value pairs, which suits the requirements of storing student details and messages effectively. Other data structures used include list and map.
In the provided Java code, HashMaps are nested within each other to store complex data structures representing student details, including their likings, scores, and messages.

Let's break down how HashMaps are nested and utilized:

1.Outer HashMap (studentDetails):
  This HashMap stores student details, where each student's roll number serves as the key.
  The value associated with each roll number is another HashMap containing various attributes such as name, year, branch, likings, scores, messages, and password.
  
2.Nested HashMaps:
  Within the studentDetails HashMap, there are nested HashMaps for likings and messages.
  
3.The likings HashMap stores each student's likings, where the liking (e.g., "music", "sports") serves as the key, and a HashMap containing the liking's description 
  serves as the value.
  
4.The messages HashMap stores messages sent from one student to another, where the receiver's roll number serves as the key, and a List of messages serves as the value.

Below link redirects to a folder that contains the two progress reports and implementation video of the project:-
https://drive.google.com/drive/folders/1ruDPEOO5X-1dmwoTjk1X4XAgubLW-Hkt?usp=sharing
