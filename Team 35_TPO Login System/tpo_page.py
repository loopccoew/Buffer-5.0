import streamlit as st
from graph import Graph

def tpo_page(graph):
    st.title("TPO Dashboard")

    # Allow TPO to upload the shortlisting file
    st.subheader("Upload Shortlisting File")
    uploaded_file = st.file_uploader("Upload Shortlisting CSV", type=["csv"])
    if uploaded_file is not None:
        graph.update_from_shortlisting(uploaded_file)
        st.success("Shortlisting file uploaded successfully!")

    # Display TPO dashboard
    st.subheader("TPO Dashboard")
    # You can add more TPO-specific functionality here

def main():
    FILE_PATH = "buffer/compshortl.csv"  # Corrected file path
    graph = Graph()
    graph.read_excel(FILE_PATH)  # Create instance and call read_excel method

    # Assuming user name is passed from the application
    user_name = "John Doe"  # Example user name
    tpo_page(graph)

if __name__ == "__main__":
    main()
