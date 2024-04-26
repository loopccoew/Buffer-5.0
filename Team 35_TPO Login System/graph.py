import csv
import pandas as pd

class Graph:
    def __init__(self):
        self.students = {}
        self.companies = {}

    def add_student(self, student_id, name):
        self.students[student_id] = {'name': name, 'applications': {}}

    def add_company(self, company_name):
        self.companies[company_name] = {'applications': {}}

    def update_application_status(self, student_id, company_name, new_status):
        if student_id in self.students and company_name in self.companies:
            self.students[student_id]['applications'][company_name] = new_status
            self.companies[company_name]['applications'][student_id] = new_status

    def write_excel(self, file_path):
        with open(file_path, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(['Student ID', 'Student Name', 'Company', 'Status'])
            for student_id, student_data in self.students.items():
                for company_name, status in student_data['applications'].items():
                    writer.writerow([student_id, student_data['name'], company_name, status])

    def read_excel(self, file_path):
        with open(file_path, 'r') as file:
            reader = csv.DictReader(file)
            for row in reader:
                student_id = row['Student ID']  
                student_name = row['Student Name']
                company_name = row['Company']
                application_status = row['Status']
                
                if student_id not in self.students:
                    self.add_student(student_id, student_name)
                if company_name not in self.companies:
                    self.add_company(company_name)
                
                self.update_application_status(student_id, company_name, application_status)

    def update_from_shortlisting(self, uploaded_file):
        shortlist_df = pd.read_csv(uploaded_file)
        # Check if 'Student Name' column exists
        if 'Student Name' in shortlist_df.columns:
            for _, row in shortlist_df.iterrows():
                student_id = str(row['Student ID'])
                company_name = row['Company']
                application_status = row['Status']
                if student_id in self.students:
                    self.update_application_status(student_id, company_name, application_status)
        else:
            print("Error: 'Student Name' column not found in the CSV file.")
