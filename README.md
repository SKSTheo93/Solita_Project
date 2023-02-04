# Solita_Project
 
Welcome to Solita_Project. This was created for the purposes of a simple demonstration to the Solita Academy.

Little details about the project.
This project is using backend technology using Java and MySql and JSP, and front end technology using html and javascript.
Java loads csv files into a mysql database, it fetches data from the database and then using JSP it transfers the data to an html page (well, technically a jsp file is a html file).

I used the Eclipse Editor. I created a Maven project which uses jsp technologgy and through a tomcat apache server the webpages are loading.

To anyone who is curious about the basics of using Java, mysql, jsp, html and javascript and how can be connected together feel free to download the files.
Hovewer there a a few steps to do (like downloading other stuff. But it's quite easy... i think... XD)

1) Download SolitaMaven, Tomcat and MySql folders. 
2) Download Oracle JDK from here: https://www.oracle.com/java/technologies/downloads/. I always prefer the executable file. Then install it.
3) Download Eclipse IDE for Enterprise Java and Web Developers from here: https://www.eclipse.org/downloads/packages/. Then unzip it and the it is ready for use.
4) Install mysql. I used version 5.5.27 version. Yes, i know... it is quite old... Remember to set your username to root and password to root or else the project will not work. Then  also instal mysql qui tools (there are to msi files in the MySQL Folder)
5) Afterwards open mysql query browser using root as username and password. Go to File->open script and choose the 20230203 2301.sql file which is located in the MySQL folder. Then run the script (it the green button with the thunder icon). Your Database is ready.
6) Download and install Maven (you need it in order to add mysql.jar file as an dependecy in the pom.xml file). I used this guide here: https://phoenixnap.com/kb/install-maven-windows
7) Install the mysql.jar file (which is located at the MySQL folder) using the maven plugin installer. I also uses this guide here: https://testingtools.co/maven/steps-to-add-external-jar-to-local-maven-repository/ . For those who are programming in Java using Eclipse might be wondering why not just add mysql.jar file as an external library (right click on your project -> properties -> java Path File -> add external library) which is a logical conclusion. However that is not how maven projects works unfortunatelly. You have to add external jar files as a depedency using the pom.xml file. Due to mysql.jar file does not exist in the Maven repository you have to install it manually. Just in case i will inform you the the original file name of mysql.jar file was mysql-connector-java-5.1.21-bin.jar. I changed the name so i can add it more easilly. But why do we have to add this specific jar file? Well because it's the Driver which connects our database with Java. From Java then we can fetch information by writing your everyday sql statements as a String. For more information about which classes and interfaces we will use, please read these: 
 https://docs.oracle.com/javase/7/docs/api/java/sql/PreparedStatement.html ,
 https://docs.oracle.com/javase/7/docs/api/javax/sql/RowSet.html ,
 https://docs.oracle.com/javase/7/docs/api/javax/sql/rowset/RowSetProvider.html ,
 https://docs.oracle.com/javase/7/docs/api/javax/sql/rowset/JdbcRowSet.html ,
 https://docs.oracle.com/javase/7/docs/api/java/sql/ResultSetMetaData.html 
8) Open Eclipse and create a new workspace or choose an existing one
9) Create a new Maven Project. Right click -> New -> Project... -> Maven -> Maven Project and click next -> click next again -> From the Catalog choose internal and select maven-archetype-webapp. I used the 1.0 version. Click next -> The artifact Id is the name of your project. You can also input a different name for the group id but i left it as it is. Click finish. Now Eclipse well start downloading the necessary files. At some point it will stop because it is waiting for user input. Just press enter without writing anything. The project has been created successfully.
10) Import the SolitaMaven Project. Right click at the newly created maven project -> import -> General -> File System -> Choose SolitaMaven Project and tick everything. Click Finish.
11) Now you have to install a server. I used the apache tomcat version 10 server which you can find in the internet or via eclipse (which i did via eclipse). In the main folder of the repository there are tomcat servers. Instead of downloading from eclipse you can use these but they are exactly the same. Go to your project -> properties -> target runtimes -> create new server -> from apache folder choose tomcat version 10.1. Press the download and install button, accept the terms and finish. Now choose a location for your apache tomcat server. Afterwards tick newly server you created apache tomcat servet. Click the apply and close button. Now you can run the websites in your server.
12) In the pom.xml files i added these depedencies:

   
		<dependency>
	    		<groupId>javax.servlet</groupId>
	    		<artifactId>javax.servlet-api</artifactId>
	    		<version>3.1.0</version>
	    		<scope>test</scope>
    		</dependency>
    
		<dependency>
    			<groupId>mysql</groupId>
    			<artifactId>mysql</artifactId>
    			<version>1.0</version>
    		</dependency>
    
    		<dependency>
	    		<groupId>com.github.cliftonlabs</groupId>
	    		<artifactId>json-simple</artifactId>
	    		<version>4.0.1</version>
		</dependency>
	
		<dependency>
			<groupId>com.googlecode.json-simple</groupId>
			<artifactId>json-simple</artifactId>
			<version>1.1.1</version>
		</dependency>
 
the json-simple depenecy was not necessary in the end but i put just in case.
13) Also i added in the xml this plugin because the project could not work due to an erroor. This plugin fixed the issue.

		<pluginManagement>
			<plugins>
				<plugin>
					<groupId>org.apache.maven.plugins</groupId>
					<artifactId>maven-war-plugin</artifactId>
					<version>3.3.1</version>
				</plugin>
			</plugins>
		</pluginManagement>
