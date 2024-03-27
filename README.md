Final task of the course "Java Basic+" by GFL.

Description (in Ukrainian):

Довідник лікаря.
База хвороб: назва, симптоми, процедури, перелік рекомендованих ліків із зазначенням
необхідної кількості. База медикаментів на складі: назва, кількість, взаємозамінність.
• Формування рецепту після огляду хворого,
• перевірка наявності ліків,
• коригування запасів

Task was implemented with Java17, SpringBoot 3.2, Thymeleaf, Bootstrap, JQuery, MySQL database for data persistence and 
H2 database for testing, with simple authentication and authorization.

- Before starting the app, create schema named 'med_assistant' (or any other you prefer, but in this case you will need 
to update application.properties and start.sql files correspondingly).

- Don't forget to change the username and password and, if necessary, datasource url according to your local settings.

- Run the app. Required tables will be automatically generated because of spring.jpa.hibernate.ddl-auto=update setting 
in application.properties file.

- Insert data to the tables 'diagnostic_procedures', 'signs', 'medications', 'medications_in_storage' and 'users' from 
start.sql file located in resources folder of the app. If you changed datasource name, update sql file correspondingly. 
You can also use your own data, but notice that user's password must be in encoded by Bcrypt format.

- Now you have 3 users: one 'Admin', one 'Physician' and one 'Pharmacist'. Use credentials of the user provided in table
 'users' for authentication on login page (decoded password for all 3 users is 12345). Test accessibility of different 
endpoints in dependence of the role. Notice that a user with the role 'Admin' can create other users, even with more 
than one role.

- A user with role 'Physician' has access to the list of diseases and can prescribe medications for every disease from 
the list. The names of these medications were taken from the table 'medications' during creation of diseases by 'Admin' 
user, and their doses are being determined by prescribing person during prescription creation. This is closer to real 
life, as the decision about the amount is made in dependence of patient's individual characteristics - age, kidney and 
liver function, drug intolerance in the past, severity of illness and so on. For the sake of simplicity the amount of 
drugs is measured in abstract units.

- A user with role 'Pharmacist' has access to the list of prescriptions and can dispense prescribed medications from the
storage if they are in stock and have sufficient amount. Besides that, 'Pharmacist' user can add new medications to the 
storage, increase amount of medications in the storage, delete medications from the storage. During dispensation of a 
medication 'Pharmacist' can choose one from the list of equivalents in storage with sufficient amount. For the sake of 
simplicity, it is assumed that 1 unit of a medication is equal to 1 unit of other medication with the same active 
ingredient.

- A user with role 'Admin' has access to all endpoints that 'Physician' and 'Pharmacist' users have. Besides that, 
'Admin' can create new diseases, delete diseases from the list, create new users of the 'MedGuide' app and delete users
from the list.

- Only front-end data validation was implemented for operations with users. It is unlikely that 'Admin' will try to 
break the app. For demonstration purposes, only back-end data validation was implemented for operations with diseases.

- Separate views for 403, 404 and 500 errors and EntityNotFound exception were added to prevent application failure.

- Please, use this app only for learning purposes.

- Any comments, corrections and advice will be appreciated.

- You can use the image below as an example for creation of diseases:
  
![Diseases](https://github.com/vakser/MedGuide/assets/65089446/673e1b6d-c4c2-40e3-b2a0-ab1e846d8a53)

