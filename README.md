Goal
====
Produce a simple web-app backend to complement the supplied front-end code.

Mandatory Work
--------------
Fork this repository. Starting with the provided HTML, CSS, and JS, create a Java-based REST API that:

1. Saves expenses as entered to a database.
2. Retrieves them for display on the page. 
3. Add a new column to the table displaying the VAT amount for each expense.
4. Alter the README to contain instructions on how to build and run your app.

VAT is the UK’s sales tax. It is 20% of the value of the expense, and is included in the amount entered by the user.

Give our account `alchemytec` access to your fork, and send us an email when you’re done. Feel free to ask questions if anything is unclear, confusing, or just plain missing.

Extra Credit
------------
Calculate the VAT client-side as the user enters a new expense, before they save the expense to the database.

Questions
---------
##### What frameworks can I use?
That’s entirely up to you, as long as they’re OSS. We’ll ask you to explain the choices you’ve made. Please pick something you're familiar with, as you'll need to be able to discuss it.

##### What application servers can I use?
Anyone you like, as long as it’s available OSS. You’ll have to justify your decision. We use dropwizard and Tomcat internally. Please pick something you're familiar with, as you'll need to be able to discuss it.

##### What database should I use?
MySQL or PostgreSQL. We use MySQL in-house.

##### What will you be grading me on?
Elegance, robustness, understanding of the technologies you use, tests, security. 

##### Will I have a chance to explain my choices?
Feel free to comment your code, or put explanations in a pull request within the repo. If we proceed to a phone interview, we’ll be asking questions about why you made the choices you made. 

##### Why doesn’t the test include X?
Good question. Feel free to tell us how to make the test better. Or, you know, fork it and improve it!

Instructions to build and run the application.
---------------------------------------------

##### Pre-requisite for this project:
	- Install Java8 using the instruction mentioned https://www.ntu.edu.sg/home/ehchua/programming/howto/JDK_HowTo.html
	- Install Maven using the instruction mentioned https://maven.apache.org/install.html
	- Install Git using https://git-scm.com/book/en/v2/Getting-Started-Installing-Git
	
##### To build the application:
  	- Clone the repository using "git clone https://github.com/eralmas7/backend-coding-challenge.git" command.
  	- After cloning, goto the directory backend-coding-challenge where we checked out the project.
  	- Once done, you could create executable jar using "mvn package"
  	- Once done, you should be able to run the application using "java -jar target/*.jar"
  	
##### Changes:
	1. Exposed rest call to add new expense and to get all the expense data stored in repository.
	2. Added new column in table to store the calculated vat value in repository and to display it on GUI.
	3. VAT would be calculated on client-side as the user enters a new expense, before client saves the expense to the database.
	4. Added JBheave acceptance test, Integration test, unit tests.
	5. Added support to secure the rest endpoint using Spring security.

##### Technologies used to build backend services:

	1. Java 8
	2. Spring Boot framework
	3. JPA
	4. JBehave, Junit, Mockito, Spring-Test
	5. MySql
	6. Tomcat
	7. Maven

##### Services used or deployed to:
	- Cloud - https://expense-calculator-assignment.herokuapp.com/default.html
	- CI - https://travis-ci.org/eralmas7/backend-coding-challenge