<!DOCTYPE html>

<%@page import="databaseConnection.Station"%>
<%@page import="dataStructures.AVLTree"%>
<%@page import="databaseConnection.Connector"%>

<%!
	String convertToKm(int metres) {
		Double km = metres / 1000.0;
		return km.toString() + " km";
	}
	
	String convertToMinutesAndSeconds(int seconds) {
		Integer minutes = seconds / 60;
		Integer newSeconds = seconds % 60;
		return String.format("%s mins & %s secs", minutes.toString(), newSeconds.toString());
	}
%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Journey Data</title>
    <link rel = 'stylesheet' href = 'style3.css'>
</head>
<body bgcolor = '#146c62'>
	<div id = 'container'>
		<table id = 'stationsTable'>
		  <tr>
		    <th>Nimi</th>
		    <th>Namn</th>
		    <th>Name</th>
		    <th>Osoite</th>
		    <th>Adress</th>
		  </tr>
		  	<%
		  	/* The instance of jdbc driver is necessary in jsp files. Without creating an instance it will not wotk */
		  	Class.forName("com.mysql.jdbc.Driver").newInstance();
		  	int records = 1000;
		  	/*Fetches data from journey_data table and saves into an avl tree */
		  	AVLTree<Station> tree = Connector.fetchQueryfromStations(0, records);
		  	
		  	for(Station st : tree) {
		  	%>
		  		<tr>
		  		<td><%= st.getNamn()%></td>
		  		<td><%= st.getNimi()%></td>
		  		<td><%= st.getName()%></td>
		  		<td><%= st.getOsoite()%></td>
		  		<td><%= st.getAdress() %></td> 
		  		</tr>
		  	<%
		  	}
		  	%>
		</table>
		<button type = 'button' class = 'buttons' onclick = "location.href = 'index.jsp'">Main Page</button>
		<button type = 'button' class = 'buttons' onclick = "location.href = 'index2.jsp'">Journey DataSet</button>
		<button type = 'button' class = 'buttons' onclick = "location.href = 'index4.jsp'">Station Search</button>
	</div>
</body>
</html>