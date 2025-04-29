import csv
import streamlit as st

FILE_PATH = "C:/Users/gsvel/OneDrive/Documents/BUFFER/buffer/application_tracking.csv"
GOOGLE_FORM_LINK = "https://forms.gle/xziBU26CeyX5mrqM9"

def read_excel(file_path):
    data = {}
    with open(file_path, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            key = row['Company']
            data[key] = {
                'status': row['status'],
                'resume_selected': row['resume_selected'],
                'test_selected': row['test_selected'],
                'interview_passed': row['interview_passed']
            }
    return data

def display_data(data):
    st.write("Data read from file:")
    for key, value in data.items():
        st.write(f"{key}: {value}")

def update_company_status(file_path, company_name, status):
    data = read_excel(file_path)
    if company_name in data:
        data[company_name]["status"] = status
        with open(file_path, 'w', newline='') as file:
            writer = csv.writer(file)
            writer.writerow(["Company", "status", "resume_selected", "test_selected", "interview_passed"])
            for key, value in data.items():
                writer.writerow([key] + [value.get("status", "Not Applied"), value.get("resume_selected", "False"), value.get("test_selected", "False"), value.get("interview_passed", "False")])
        st.write("Application tracking is updated.")

def main():
    st.title("Welcome to Application Tracking System!")

    # Read the CSV file containing company data
    company_data = read_excel(FILE_PATH)

    # Display company data and process status
    for company, details in company_data.items():
        st.markdown(f"<strong style='font-size: 20px;'>{company}</strong>", unsafe_allow_html=True)  # Bold company name with increased font size
        if details['status'] == 'Applied':
            st.write("**Application Process:**")
            if details['resume_selected'] == 'True':
                st.success("1. Resume Selected ✓")
            else:
                st.write("1. Resume Selected")
            
            if details['test_selected'] == 'True':
                st.success("2. Test Passed ✓")
            else:
                st.write("2. Test Passed")
            
            if details['interview_passed'] == 'True':
                st.success("3. Interview Passed ✓")
            else:
                st.write("3. Interview Passed")
                st.write(" ")
        else:
            st.write(f"**Apply to {company}:**")
            if st.button(f"Apply to {company}", key=f"apply_button_{company}"):
                update_company_status(FILE_PATH, company, "Applied")
                st.write(f"You have applied to {company}.")
            st.write(f"Or fill the application form [here]({GOOGLE_FORM_LINK}).")
            st.write("")

if __name__ == "__main__":
    main()