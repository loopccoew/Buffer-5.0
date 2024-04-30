# -*- coding: utf-8 -*-
"""
Created on Sat Apr 27 14:30:42 2024

@author: Maitreyi
"""

schemes={}
schemes={"1.Agnipath scheme":"For recruitment of soldiers below the rank of commissioned officers into the three services of the armed forces",
         "2.Mahila samman savings certificate":" This scheme aims to empower women by providing them with a secure and growth-oriented savings avenue.",
         "3.Battery operatred TriCycle":"provide battery-operated tricycles to more than 80 percent disabled persons who live below the poverty line",
         "4.PM ujjawala yojana":"Launched to provide free LPG connections to women from below poverty line families",
         "5.PM employment generation program":"Aimed at providing employment and self-employment to unemployed"}
         
while True:
    print("\nWho wants access :")
    login=int(input("1.Admin\n2.User\n3.exit\n"))

    if login==1:
        print("-"*30)
        print("\nADMIN")
        while True:
            pass_a=input("\nEnter password\n")
            print("-"*30)
            if pass_a=="adminpassword":
                print("\nLogin successful!\n")
                while True:
                    op1=int(input("What would you like to do:\n 1.Check existing record\n 2.Add data\n 3.Exit menu\n"))
                    if op1==1:
                        print("-"*30)
                        print("\nFollowing is the list of schemes:\n")
                        for key in schemes:
                            print(key)
                        print("-"*30)
                        inf=input('\nWhose information you would like to view (enter the scheme number and name)\n')
                        if inf in schemes:
                            print('',schemes[inf])
                        else:
                            print("\nScheme not found")
                        break
                    elif op1==2:
                        print("-"*30)
                        scheme_name=input("Enter scheme name")
                        scheme_info=input("Enter scheme info")
                        schemes[scheme_name]=scheme_info
                        print("Successfully updated!")
                        print("-"*30)
                        break
                    if op1==3:
                        print("Exiting menu")
                        print("-"*30)
                        break
                    else: print("Invalid choice!")
                    print("-"*30)
                break
            else: 
                print("Incorrect password!")
                print("-"*30)
        break
    
    elif login==2:
        u_p={"maitreyi":"joshi",}
        applied=[]
        while True:
            op2=int(input("\n1.Login into existing account\n2.Sign up!\n3.Change password\n4.Exit menu\n"))
            if op2==1:
                print("-"*30)
                k=0
                j=0
                while j==0 or k==0:
                    user_ID_ex=input("\nEnter user ID:\n")
                    password_ex=input("Enter password:\n")
                    for i in u_p:
                        if i==user_ID_ex:
                            j=1
                            if u_p.get(i)==password_ex:
                                print("-"*30)
                                print("Login successful!")
                                k=1
                                name=input("Enter your full name\n")
                                age=int(input("Enter age\n"))
                                gender=input("Enter gender\n").lower()
                                nationality=input("Are you a citizen of india?\n").lower()
                                
                                if age<18 and gender=="male" or "m" and nationality=="yes":
                                    print("-"*30)
                                    print("Sorry not eligible for any schemes")
                                if age<18 and gender=="female" and nationality=="yes":
                                    print("-"*30)
                                    print("Scheme for you:",schemes[1])
                                    
                                if nationality=="yes":
                                    dis=input("Are you disabled?\n")
                                    if dis=="yes":
                                        applied.append("Battery operatred TriCycle")
                                    elif gender=="female" or "fem" or "f":
                                        applied.append("Mahila samman savings certificate")
                                        category=input("Enter your category\n").upper()
                                        if category=="SC"or"ST":
                                            applied.append("PM ujjawala yojana")
                                    emp=input("Are you employed?\n")
                                    if emp=="no":
                                        applied.append("PM employment generation program")
                                    sol=input("Are you a soldier?\n")
                                    if sol=="yes":
                                        applied.append("Agnipath scheme")
                                print("-"*30)
                                print("Your applied schemes are:",applied)            
                                
                    if j==0: 
                        print("Incorrect user ID!")
                    if k==0:
                        print("Incorrect password!")
                    
                break
        
            if op2==2:
                user_ID_new=input("\nEnter a user ID:\n")
                for i in u_p:
                    if i==user_ID_new:
                        print("-"*30)
                        print("User ID already exists")
                    elif i!=user_ID_new:
                        password_new=input("Enter a strong password: \n")
                u_p[user_ID_new]=password_new
                print("-"*30)
                print("Account successfully created!")
            
            if op2==3:
                user_ID_ch=input("\nEnter a user ID:\n")
                for i in u_p:
                    if i!=user_ID_ch:
                        print("-"*30)
                        print("User ID does not exist")
                    elif i==user_ID_ch:
                        password_ch=input("Enter new password:\n")
                        u_p[i]=password_ch
                        print("-"*30)
                        print("Password successfully changed!")
            
            if op2==4:
                print("Exiting Menu")
                print("-"*30)
                break

            else: print("Invalid choice!")
            print("-"*30)
            
    elif login==3:
        print("Exiting program")
        print("-"*30)
        break
    
    else: print("Invalid choice!")
    print("-"*30)
