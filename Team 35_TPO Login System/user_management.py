import hashlib

class UserManager:
    def __init__(self, filename):
        self.filename = filename
        self.users = {}  # Initialize empty dictionary to store user information
        self.logged_in = False  # Initialize login status to False
    
    def _hash_password(self, password):
        # Hash the password using SHA-256 algorithm
        return hashlib.sha256(password.encode()).hexdigest()
    
    def verify_user(self, username, password):
        # Verify user credentials
        hashed_password = self._hash_password(password)
        if username in self.users:
            return self.users[username]["password"] == hashed_password
        else:
            return False
    
    def create_user(self, name, year, branch, username, password):
        # Create a new user and store their information
        hashed_password = self._hash_password(password)
        self.users[username] = {
            "name": name,
            "year": year,
            "branch": branch,
            "password": hashed_password
        }
    
    def set_logged_in(self, status):
        self.logged_in = status
    
    def get_user_name(self, username):
        # Get the user's name based on the username
        if username in self.users:
            return self.users[username]["name"]
        else:
            return None
