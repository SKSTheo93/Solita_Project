<!DOCTYPE html>

<%@page import="dataStructures.AVLTree"%>
<%@page import="databaseConnection.JourneyData"%>
<%@page import="databaseConnection.Connector"%>

<%!
	String convertToKm(int metres) {
		Double km = metres / 1000.0;
		return km.toString() + " km";
	}
	
	String convertToHours_MinutesAndSeconds(int seconds) {
		Integer minutes = seconds / 60;
		Integer newSeconds = seconds % 60;
		if(minutes <= 60)
			return String.format("%s mins & %s secs", minutes.toString(), newSeconds.toString());
		else {
			Integer hours = minutes / 60;
			Integer newMinutes = minutes % 60;
			return String.format("%s hrs & %s mins & %s secs", hours.toString(), newMinutes.toString(), newSeconds.toString());
		}
			
	}
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Journey Data</title>
    <link rel = 'stylesheet' href = 'style2.css'>
</head>
<body bgcolor = '#146c62'>
	<div id = 'container'>
		<table id = 'journeyTable'>
		  <tr>
		    <th>Departure Station</th>
		    <th>Return Station</th>
		    <th>Covered Distance (km)</th>
		    <th>Duration (m)</th>
		  </tr>
		  	<%
		  	/* The instance of jdbc driver is necessary in jsp files. Without creating an instance it will not wotk */
		  	Class.forName("com.mysql.jdbc.Driver").newInstance();
		  	int records = 500;
		  	/*Fetches data from journey_data table and saves into an avl tree */
		  	AVLTree<JourneyData> tree = Connector.fetchQueryfromJourneyData(0, records);
		  	
		  	for(JourneyData jd : tree) {
		  	%>
		  		<tr>
		  		<td><%= jd.getDepartureStationName()%></td>
		  		<td><%= jd.getReturnStationName()%></td>
		  		<td><%= convertToKm(jd.getCoveredDistance())%></td>
		  		<td><%= convertToHours_MinutesAndSeconds(jd.getDuration())%></td>
		  		</tr>
		  	<%
		  	}
		  	%>
		</table>
		<button type = 'button' class = 'buttons' id = 'mainPageButton'>Main Page</button>
		<button type = 'button' class = 'buttons' id = 'stationsButton'>Stations DataSet</button>
	</div>
<script src = 'script2.js'></script>
</body>
</html>