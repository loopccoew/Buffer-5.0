# Team Wellness Wizards
# Mental Health Assessment Tool

## Overview
This project is a simple mental health assessment tool designed to provide users with guidance on potential mental health conditions based on their responses to a series of questions. The tool prompts users with questions related to their mental well-being and uses their responses to categorize their potential conditions. It provides suggestions and resources based on the identified condition.

## Features
- Clinic Finder:
  - Users input their location of residence.
  - The program calculates the nearest mental health clinic based on the provided location.
  - Information about the nearest clinic, including name, distance, contact details, and website, is displayed to the user.

- Mental Health Assessment:
  - Users undergo a series of questions related to their mental health symptoms and experiences.
  - Each question presents multiple options for the user to choose from.
  - Based on the user's responses, the program evaluates potential mental health conditions.
  - The assessment provides feedback on potential mental health conditions identified from the user's responses.

## Implementation
- The program is implemented in Java.
- Array of Objects is used to store the user information.Each element of the array represents a single user, and the object's fields store specific details about that user.
- A series of interconnected nodes represent the questions and options for the mental health assessment.Each node contains a question and a list of options that lead to the next node based on the user's selection.This is implemented with Graph using linkedlist.
- The clinic finder functionality calculates distances between locations and clinics to determine the nearest one using Graph implemented by arrays.
- The mental health assessment functionality employs a decision tree approach to evaluate user responses.

## Team Members
- Shweta Bagade
- Pradnya Bapat
- Aarya Patil

## Links:
- video : https://drive.google.com/file/d/1S4ZZ4EY1yuJFEfi8tbj5gMSLDeDpoCAf/view?usp=sharing

- Report 1 : https://docs.google.com/document/d/1zyozyGBz4Ju9nyZnXSbaxoKkr-Bh3R1O/edit?usp=sharing&ouid=101707835012972035195&rtpof=true&sd=true

- Report 2 : https://docs.google.com/document/d/1vLmd9FRKg114FZ_8GFwp1NHvShTycNay/edit?usp=sharing&ouid=101707835012972035195&rtpof=true&sd=true
