#questions.py

import streamlit as st
import csv
import os

# Define a Node class for a linked list
class Node:
    def __init__(self, data):
        self.data = data
        self.next = None

# Define a LinkedList class to manage questions for each company
class LinkedList:
    def __init__(self):
        self.head = None

    def append(self, company_name, question):
        new_node = Node((company_name, question))
        if not self.head:
            self.head = new_node
        else:
            current = self.head
            while current.next:
                current = current.next
            current.next = new_node

    def display_questions(self, company_name):
        st.subheader(f"Questions for {company_name}")
        current = self.head
        while current:
            if current.data[0] == company_name:
                st.write(current.data[1])
            current = current.next

# Function to add a question to the linked list
def add_question(company_name, question):
    with open('questions.csv', 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([company_name, question])

# Function to display the form for adding a question
def add_question_form():
    st.subheader("Add a Question")
    company_name = st.text_input("Enter the company name:")
    question = st.text_area("Enter the question:")
    if st.button("Add Question"):
        if company_name.strip() == "":
            st.error("Please enter a company name.")
        elif question.strip() == "":
            st.error("Please enter a question.")
        else:
            add_question(company_name, question)
            st.success("Question added successfully!")

# Main function to run the Streamlit app
def display_question_bank():
    st.title("Question Bank")
    add_question_form()
    ll = LinkedList()  # Create a linked list object
    
    # Check if the file exists, if not, create it
    if not os.path.isfile('questions.csv'):
        with open('questions.csv', 'w', newline='') as file:
            pass  # Create an empty file
    
    # Now the file should exist, proceed with reading
    with open('questions.csv', 'r') as file:
        reader = csv.reader(file)
        for row in reader:
            ll.append(row[0], row[1])
    company_name = st.text_input("Enter the company name to see questions:")
    ll.display_questions(company_name)

# Call this function from your main dashboard script
if __name__ == "__main__":
    display_question_bank()
