
import streamlit as st
import subprocess
from myprofile import main as my_profile
from Tracking import main as application_tracking
import webbrowser



def dashboard():
    st.sidebar.title("MY DASHBOARD !")

    # Conditional rendering based on selected section
    st.write("---")
    if st.sidebar.button("My Profile", key="my_profile_button"):
        my_profile()
        
    if st.sidebar.button("Registration", key="registration_button"):
        # Add your TPO Registration content here
        st.markdown("## Registration")
        

    with st.expander("Internship and Placement"):
        st.write("This is the Internship and Placement Registration section.")
        
        # Displaying a link to the registration form
        registration_link = "https://forms.gle/GsY7MHbAtq14mMpn8"
        st.markdown(f"Click [here]({registration_link}) to register.")

    if st.sidebar.button("Scheduled Companies", key="scheduled_companies_button"):
        st.markdown("## Scheduled Companies")
        st.write("This is the Scheduled Companies section.")
        
        # Display companies horizontally with options to apply
        col1, col2 = st.columns(2)
        
        with col1:
            with st.expander("Deutsche Bank"):
                st.write("This is Deutsche Bank's information.")
                if st.button("Apply to Deutsche Bank", key="apply_to_deutsche_button"):
                    st.write("You have applied to Deutsche Bank.")
                
        with col2:
            with st.expander("Atlassian"):
                st.write("This is Atlassian's information.")
                if st.button("Apply to Atlassian", key="apply_to_atlassian_button"):
                    st.write("You have applied to Atlassian.")

    if st.sidebar.button("Application Tracking", key="application_tracking_button"):
        st.markdown("## Application Tracking")
        st.write("This is the Application Tracking section.")
        # Call the application tracking function from tracking.py
        application_tracking()
        st.write("---")

    if st.sidebar.button("Question Bank", key="question_bank_button"):
        st.markdown("## Question Bank")
        st.write("This is the Question Bank section.")
        st.write("Loading...")

        # Display questions.py using an iframe
        st.markdown('<iframe src="http://localhost:8505/" width="800" height="600" style="border:none;"></iframe>', unsafe_allow_html=True)
        st.write("---")

    if st.sidebar.button("Alumni", key="alumni_button"):
        st.markdown("## Alumni")
        st.write("This is the Alumni section.")

        # Sample alumni data
        alumni_data = [
            {"Name": "John Doe", "Roll No.": "12345", "Passout Year": "2020", "Current Company": "XYZ Corp", "LinkedIn": "[John Doe's LinkedIn Profile](https://www.linkedin.com/johndoe)"},
            {"Name": "Jane Smith", "Roll No.": "54321", "Passout Year": "2019", "Current Company": "ABC Inc", "LinkedIn": "[Jane Smith's LinkedIn Profile](https://www.linkedin.com/janesmith)"}
        ]

        # Display alumni data in a table
        st.table(alumni_data)

if __name__ == "__main__":
    dashboard()