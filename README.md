# To-Do List Demo

Web application powered by Java, Spring, Hibernate that allows users to login/register and create to-do lists. Application maintains individual's to-do list using Hibernate, SQL. JSP and JSTL is used for view pages and Hibernate Validator is used for form validation.

# How to use it? 

1.Execute the sql scripts provided in the repository. 
   - create-user.sql : Creates a new user for you to create your schema and to access it.
   - todo-users.sql : Creates the default schema required by Spring Security for login and registration.
   - todo-entries.sql : Creates a entry table that stores to-do entries for each individual registered in the database.


2.Use Apache Tomcat(or similar) as a server for the Java source code and run the web application on it.

3.If you used your own sql scripts, update the database information in persistence-mysql.properties in src/main/resources

4.The application will ask you to either login or register a new user. (Check for dummy user login info in the sql scripts)
![1](https://user-images.githubusercontent.com/16450711/61991976-a9ec2b00-b075-11e9-8b3c-112b45a07a9d.PNG)

![1](https://user-images.githubusercontent.com/16450711/61992007-0cddc200-b076-11e9-9aeb-d3051daee716.PNG)

5.Once logged in you can see your to-do list entries. You can also add new entries or update existing ones. 
![todo](https://user-images.githubusercontent.com/16450711/61992024-40205100-b076-11e9-943b-6f938d322d30.png)
