#myprofile.py

import os.path
import streamlit as st
import csv

# Define a custom class to represent the student profile
class StudentProfile:
    def __init__(self, name, email, phone, college, degree, branch, year, 
                 project1_name, project1_desc, project2_name, project2_desc,
                 programming_languages, frameworks, experience, 
                 activity1, activity2, cv_link, linkedin_profile, github_profile):
        self.name = name
        self.email = email
        self.phone = phone
        self.college = college
        self.degree = degree
        self.branch = branch
        self.year = year
        self.project1_name = project1_name
        self.project1_desc = project1_desc
        self.project2_name = project2_name
        self.project2_desc = project2_desc
        self.programming_languages = programming_languages
        self.frameworks = frameworks
        self.experience = experience
        self.activity1 = activity1
        self.activity2 = activity2
        self.cv_link = cv_link
        self.linkedin_profile = linkedin_profile
        self.github_profile = github_profile

# Function to save student profiles to a CSV file
def save_profiles_to_csv(profiles, filename):
    with open(filename, 'w', newline='') as csvfile:
        fieldnames = profiles[0].__dict__.keys()
        writer = csv.DictWriter(csvfile, fieldnames=fieldnames)
        writer.writeheader()
        for profile in profiles:
            writer.writerow(profile.__dict__)

# Function to load student profiles from a CSV file
def load_profiles_from_csv(filename):
    profiles = []
    if os.path.isfile(filename):
        with open(filename, newline='') as csvfile:
            reader = csv.DictReader(csvfile)
            for row in reader:
                profile = StudentProfile(**row)
                profiles.append(profile)
    else:
        st.warning("No student profiles found. You can create a new profile.")
    return profiles

# Main function
def main():
    st.title("Welcome !")

    # Define the filename for the CSV file
    filename = "student_profiles.csv"

    # Load existing student profiles from CSV file or create a new one
    student_profiles = load_profiles_from_csv(filename)

    if not student_profiles:
        # If no profiles exist, create a blank profile
        student_profile = StudentProfile(name="", email="", phone="", college="", degree="", branch="", year="",
                                         project1_name="", project1_desc="", project2_name="", project2_desc="",
                                         programming_languages="", frameworks="", experience="",
                                         activity1="", activity2="", cv_link="", linkedin_profile="", github_profile="")
        student_profiles.append(student_profile)

    st.subheader(f"Update Student Profile: {student_profiles[0].name}")

    # Update student profile fields
    student_profile = student_profiles[0]
    student_profile.name = st.text_input("Name", value=student_profile.name)
    student_profile.email = st.text_input("Email", value=student_profile.email)
    student_profile.phone = st.text_input("Phone", value=student_profile.phone)
    student_profile.college = st.text_input("College", value=student_profile.college)
    student_profile.degree = st.text_input("Degree", value=student_profile.degree)
    student_profile.branch = st.text_input("Branch", value=student_profile.branch)
    student_profile.year = st.text_input("Year of Study", value=student_profile.year)

    st.subheader("Projects")
    student_profile.project1_name = st.text_input("Project 1: Name", value=student_profile.project1_name)
    student_profile.project1_desc = st.text_area("Project 1: Description", value=student_profile.project1_desc)
    student_profile.project2_name = st.text_input("Project 2: Name", value=student_profile.project2_name)
    student_profile.project2_desc = st.text_area("Project 2: Description", value=student_profile.project2_desc)

    st.subheader("Skills and Experience")
    student_profile.programming_languages = st.text_input("Programming Languages", value=student_profile.programming_languages)
    student_profile.frameworks = st.text_input("Frameworks", value=student_profile.frameworks)
    student_profile.experience = st.text_input("Experience", value=student_profile.experience)

    st.subheader("Extracurricular Activities")
    student_profile.activity1 = st.text_input("Activity 1", value=student_profile.activity1)
    student_profile.activity2 = st.text_input("Activity 2", value=student_profile.activity2)

    student_profile.cv_link = st.text_input("CV Link", value=student_profile.cv_link)
    student_profile.linkedin_profile = st.text_input("LinkedIn Profile", value=student_profile.linkedin_profile)
    student_profile.github_profile = st.text_input("GitHub Profile", value=student_profile.github_profile)

    # Save student profiles to CSV file
    if st.button("Save"):
        save_profiles_to_csv(student_profiles, filename)
        st.success("Profile updated successfully!")

    # Display student profile information
    st.subheader("Student Profile Information:")
    profile_info = student_profile.__dict__
    for key, value in profile_info.items():
        st.write(f"**{key.capitalize()}:** {value}")

if __name__ == "__main__":
    main()
