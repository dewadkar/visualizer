# visualizer
localhost:8080/Visualizer1/
Download a code from this site:

https://github.com/dewadkar/visualizer 

This is maven project : follow these steps while creating a project in eclipse

http://crunchify.com/how-to-create-dynamic-web-project-using-maven-in-eclipse/ 


Place the Visualizer1.war file from above and place it in TOMCAT_HOME/webapp/ folder and start manually to load it. 

Once done check the link "localhost:8080/Visualizer1/" 
You should see Hello message on screen.

Then 
"localhost:8080/Visualizer1/rest/ikl/one"
It will return you the JSON file. 

You need to have Neo4j 2.3.0 to run this command. Install it and start the server with Data folder on your machine.
In your part all HTML/JS and other things needs to go in parallel with index.jp file in src/main/webapp/ folder.

IF you need to access files from local folder, you should put it into
src/main/resources/ 










Dnyaneshwar Dewadkar
