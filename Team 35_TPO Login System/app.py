import webbrowser
import streamlit as st
from user_management import UserManager
from dashboard import dashboard

def open_dashboard(url):
    webbrowser.open_new_tab(url)  # Opens the Streamlit app in a new tab

def main():
    st.title("TRAINING AND PLACEMENT CELL CCOEW ")
    st.image("buffer/img/Cummins Logo - Copy.jpg", width=300)
    # Display image for navigation
    user_manager = UserManager("user_data.csv")

    st.header("Select Role")

    selection = st.radio("Choose Role:", ["Student", "TPO"])

    if selection == "Student":
        st.subheader("Student Login")
        username = st.text_input("Email:")
        password = st.text_input("Password:", type="password")
        name = st.text_input("Name:")
        year_options = ["First Year", "Second Year", "Third Year", "B.Tech"]
        year = st.selectbox("Year:", year_options)
        branch_options = ["Computer", "IT", "ENTC", "MECH", "INSTRUMENTATION AND CONTROL"]
        branch = st.selectbox("Branch:", branch_options)
        
        if st.button("Login"):
            if user_manager.verify_user(username, password):
                st.success("Login Successful!")
                user_manager.logged_in = True
                open_dashboard("http://localhost:8501")  # Redirect to the Streamlit app
            else:
                if name and year and branch and username:
                    if "@cumminscollege.in" in username:
                        user_manager.create_user(name, year, branch, username, password)
                        st.success("Account created successfully!")
                        user_manager.logged_in = True
                        open_dashboard("http://localhost:8501")  # Redirect to the Streamlit app
                    else:
                        st.error("Only @cumminscollege.in email addresses are allowed.")
                else:
                    st.error("Please fill in all fields.")

    elif selection == "TPO":
        st.subheader("TPO Login")
        # Add TPO specific fields if any
        username = st.text_input("Email:")
        password = st.text_input("Password:", type="password")
        name = None  # Define name variable here
        year = None  # Define year variable here
        branch = None  # Define branch variable here
        
        if st.button("Login"):
            # Add TPO login logic here
            pass
        if "@cumminscollege.in" in username:
            user_manager.create_user(name, year, branch, username, password)
            st.success("Account created successfully!")
            user_manager.logged_in = True
            open_dashboard("http://localhost:8503")  # Redirect to the Streamlit app
        else:
            st.error("Only @cumminscollege.in email addresses are allowed.")
    else:
        st.error("Please fill in all fields.")

if __name__ == "__main__":
    main()
