<%@page import="databaseConnection.Connector"%>



<!DOCTYPE html>
<html>
<head>
	<title>Solita Database</title>
	<link rel = 'stylesheet' href = 'style.css'>
</head>
<body bgcolor = '#146c62'>
	<%
	/* The instance of jdbc driver is necessary in jsp files. Without creating an instance it will not wotk */
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	
	final String QUERY1 = "insert into stations(FID,ID,NIMI,NAMN,NAME,OSOITE,ADRESS,"
			+ "KAUPUNKI,STAD,OPERAATTOR,KAPASITEET,X,Y) "
			+ "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	final String QUERY2 = "insert into journey_data(DEPARTURE_DATE,RETURN_DATE,DEPARTURE_STATION_ID,DEPARTURE_STATION_NAME,"
			+ "RETURN_STATION_ID,RETURN_STATION_NAME,COVERED_DISTANCE,DURATION) "
			+ "VALUES(?,?,?,?,?,?,?,?)";
	
	final String stationsDataset = "C:/Users/Arthur/Eclipse Workspaces/JavaGraphics/SolitaMaven/src/main/resources/Helsinski Bike Stations.csv";
	final String journeyDataSet1 = "C:/Users/Arthur/Eclipse Workspaces/JavaGraphics/SolitaMaven/src/main/resources/2021-05.csv";
	
	/*the fetchQuryfromStations and fetchQueryFromJourneyData both return and avl tree, and from the tree we check the size.
		if(nothing was imported then new data will be imported)	*/
		
	/*The Connector.loadCSV has 3 parameteres:
		1. the path of the file
		2. the insert into a table query
		3. This is a boolean variable which defines is the parsing od the file will be strict or not
		if strictMode is activated then the parser will check for empty fields. if it finds
		them they will not inserted it into the database. Using the strict mode, the data gets validated*/
	if(Connector.fetchQueryfromStations(0, 10).size() == 0)
		Connector.loadCSV(stationsDataset, QUERY1, true);
	
	if(Connector.fetchQueryfromJourneyData(0, 10).size() == 0)
		Connector.loadCSV(journeyDataSet1, QUERY2, true);
	%>
		<div id = 'container'>
			<label id = "welcome">Welcome to the demo page. The database has been loaded!!!</label>
			<br>
			<button class = 'buttons' id = 'b1'>Journey DataSet</button>
			<button class = 'buttons' id = 'b2'>Stations Dataset</button>
		</div>
		<script src = 'script.js'></script>
</body>
</html>