import csv

FILE_PATH = "C:/Users/gsvel/OneDrive/Documents/BUFFER/buffer/application_tracking.csv"

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

    print("Data read from file:")
    for key, value in data.items():
        print(f"{key}: {value}")

    return data

def display_data(data):
    print("Company,status,resume_selected,test_selected,interview_passed")
    for key, value in data.items():
        print(f"{key},{value['status']},{value['resume_selected']},{value['test_selected']},{value['interview_passed']}")

def check_for_new_companies(file_path, existing_companies):
    with open(file_path, 'r') as file:
        reader = csv.DictReader(file)
        for row in reader:
            companyName = row['Company']
            if companyName not in existing_companies:
                print("New company found:", companyName)
                add_new_company(file_path, companyName)

def add_new_company(file_path, company_name):
    with open(file_path, 'a', newline='') as file:
        writer = csv.writer(file)
        writer.writerow([company_name, "Not Applied", "False", "False", "False"])

def main():
    file_path = "D:/Buffer/application_tracking.csv"
    data = read_excel(file_path)
    display_data(data)
    check_for_new_companies(file_path, set(data.keys()))

if __name__ == "__main__":
    main()