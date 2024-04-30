create database Buffer;

use Buffer;

create table Supplier_info(S_ID int Primary key,S_name varchar(30) Not null,Shop_name varchar(30) Not null,S_city varchar(30) Not null,S_pincode int Not null,S_pass int not null);
insert into Supplier_info values(101,"Arnav J.","Apple Store","Pune",411038,123);
insert into Supplier_info values(102,"Aryan K.","Siddhivinayak Store","Pune",411021,234);
insert into Supplier_info values(103,"Soham P.","Wellness Store","Pune",411038,345);
insert into Supplier_info values(104,"Sahil P.","Jeevan Store","Pune",411028,456);
insert into Supplier_info values(105,"Arya V.","MedPlus Store","Pune",411052,567);
insert into Supplier_info values(106,"Tejas k.","Laxmi Medico","Pune",411035,678);
insert into Supplier_info values(107,"Sachin C.","Krishna Medicals","Pune",411045,789);
insert into Supplier_info values(108,"Abhiman G.","Sai Samarth medioc","Pune",411052,890);
insert into Supplier_info values(109,"Ashutosh S.","Medico Store","Pune",411021,100);
insert into Supplier_info values(110,"Arjit P.","Prime Medicals","Pune",411035,200);


create table Medicine_info(
S_ID int Not null,
M_ID int Not null,
Med_name varchar(50) Not null,
Med_type varchar(20) Not null,
Price decimal(10,2) Not null,
Quantity int Not null,
Num_Tablets int default 0,
Volume int default 0,

Foreign key(S_ID) references Supplier_info(S_ID),
Unique (S_ID,M_ID)
);

insert into Medicine_info values(101,201,"Crocin","Tablet",33.6,20,15,0);
insert into Medicine_info values(101,202,"Ondem 4" ,"Tablet",56,30,10,0);
insert into Medicine_info values(101,203,"Ondem 8","Tablet",100,30,10,0);
insert into Medicine_info values(101,204,"Ondem Syrup","Syrup",40,20,0,30);
insert into Medicine_info values(101,205,"Dolo 650","Tablet",33.6,30,15,0);
insert into Medicine_info values(101,206,"Dolo 500","Tablet",13.8,10,15,0);
insert into Medicine_info values(101,207,"Dabur Honitus Syrup","Syrup",106,30,0,100);
insert into Medicine_info values(101,208,"Dabur Honitus logenzes","Tablet",16.3,10,10,0);
insert into Medicine_info values(102,202,"Ondem 4","Tablet",56,30,10,0);
insert into Medicine_info values(102,209,"Aspirin","Tablet",60,50,10,0);
insert into Medicine_info values(102,205,"Dolo 650","Tablet",33.6,30,15,0);
insert into Medicine_info values(102,207,"Dabur Honitus Syrup","Syrup",106,20,0,100);
insert into Medicine_info values(102,201,"Crocin","Tablet",33.6,50,15,0);
insert into Medicine_info values(103,202,"Ondem 4","Tablet",56,33,10,0);
insert into Medicine_info values(103,201,"Crocin","Tablet",33.6,20,15,0);
insert into Medicine_info values(103,201,"Crocin","Tablet",33.6,20,15,0);
insert into Medicine_info values(103,210,"Aprise D3","Tablet",100,30,15,0);
insert into Medicine_info values(103,213,"Combiflam","Tablet",50,30,15,0);
