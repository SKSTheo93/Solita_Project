# Solita_Project
 
Welcome to Solita_Project. This was created for the purposes of a simple demonstration.

Little details about the project.
This project is using backend technology using Java and MySql and JSP, and front end technology using html and javascript.
Java loads csv files into a mysql database, it fetches data from the database and then using JSP it transfers the data to an html page (well, technically a jsp file is a html file).

I used the Eclipse Editor. I created a Maven project which uses jsp technologgy and through a tomcat apache server the webpages are loading.

To anyone who is curious about the basics of using Java, mysql, jsp, html and javascript and how can be connected together feel free to download the files.
Hovewer there a a few steps to do (like downloading other stuff. But it's quite easy... i think... XD)

1) Download Oracle JDK from here: https://www.oracle.com/java/technologies/downloads/. I always prefer the executable file. Then install it.
2) Download Eclipse IDE for Enterprise Java and Web Developers from here: https://www.eclipse.org/downloads/packages/. Then unzip it and the it is ready for use.
3) Download and install Maven (you need it in order to add mysql.jar file as an dependecy in the pom file). I used this guide here: https://phoenixnap.com/kb/install-maven-windows
4) Install the mysql.jar file (which is located at the main folder) using the maven plugin installer. I also uses this guide here: https://testingtools.co/maven/steps-to-add-external-jar-to-local-maven-repository/ . For those who are programming in Java using Eclipse might be wonder why not just add mysql.jar file as an external library (right click on your project -> properties -> java Path File -> add external library) which is a logical conclusion. However that is not how maven projects works unfortunatelly. You have to add external jar files as a depedency using the pom file. Due to that mysql.jar file does not exist in the Maven repository you have to install it manually. Just in case i will inform you the the original file name of mysql.jar file was mysql-connector-java-5.1.21-bin.jar. I changed the name so i can add it more easilly.
