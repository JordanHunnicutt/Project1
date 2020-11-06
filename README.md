# Expense Reimbursement System
## Description
This is a web-application based Expense Reimbursement System. Employees registered in the system can submit reimbursement requests and view past requests. Financial managers can view all requests and approve or reject them.
## Technologies Used
- HTML/CSS
- JavaScript
- AJAX
- Java
- Maven
- JDBC
- Servlets
- PostgreSQL
## Features
These are the current features of the application:
- Users can login to the site, and are redirected based on their employment status
- Employees can submit new reimbursement requests
- Employees can view their own reimbursement requests
- Employees can edit their own reimbursement requests
- Finance Managers can view all employee requests
- Finance Managers accept or reject requests
- Finance Managers can view only pending requests
- All users can view guidelines for submitting requests  
These are to-do items for improving the application:
- Make filtering more dynamic by providing options on the table page
- Autofill form fields when editing a reimbursement
- Allow finance managers to accept or reject reimbursements without having to edit them
- Create a register function so you don't have to prepopulate the user table.
## Getting Started
1. Navigate to your desired directory in your terminal, and run this command: `git clone https://github.com/JordanHunnicutt/Project1.git`
2. Import the project into your desired IDE as a Maven project. This is confirmed to work in Eclipse.
3. Get access to a SQL database. This project was created using PostgreSQL on an Amazon AWS RDS.
     1. Change the url, username, and password variables in your desired Connection Utility file
     2. You can use the provided JUnit Tests in src/test/java to see if you have a connection between your database and the Java code.
4. Use Maven commands to build your project into a .war file. For more information on Maven, refer to documentation [here.](https://maven.apache.org/guides/index.html)
5. Deploy your application using a Server Runtime. This project originally ran on an Apache Tomcat server. You can find information on Apache Tomcat [here.](http://tomcat.apache.org/)
## Usage
1. After installing, you can navigate to the site using your server runtime. If you're using Apache Tomcat in your IDE, provided you haven't changed any settings, you can access the site by going to [http://localhost:8080/Project1/html/index.html]() in your browser.
2. You can login to the site using credentials provided in your database, and enjoy the features mentioned in the prior Features section.
3. You can make changes to the front-end by accessing files in src/main/webapp.
4. You can make changes to the middle and back-end by accessing files in src/main/java.
