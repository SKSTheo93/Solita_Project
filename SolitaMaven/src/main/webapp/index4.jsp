<!DOCTYPE html>

<%@page import="databaseConnection.SingleStation"%>
<%@page import="javax.swing.JOptionPane"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSetMetaData"%>
<%@page import="databaseConnection.Connector"%>
<%@page import="javax.sql.rowset.JdbcRowSet"%>
<%@page import="javax.sql.rowset.RowSetProvider"%>
<%@page import="javax.sql.rowset.RowSetFactory"%>


<%
	/* The instance of jdbc driver is necessary in jsp files. Without creating an instance it will not wotk */
	Class.forName("com.mysql.jdbc.Driver").newInstance(); 
%>

<%!
	/* this function returns information for a single station. It uses rowsets to fetch the data. It uses JdbcRowSets */
	SingleStation getStringQuery(String station) {
		String url = Connector.DRIVER + Connector.HOST + ":" + Connector.PORT + "/" + Connector.DATABASE;
		String query1 = "select NIMI, Adress from stations where NIMI = '"+ station +"'";
		String query2 = "select count(DEPARTURE_STATION_NAME) from journey_data where DEPARTURE_STATION_NAME = '"+ station +"'";
		String query3 = "select count(RETURN_STATION_NAME) from journey_data where RETURN_STATION_NAME = '"+ station +"'";
		
		SingleStation ss = new SingleStation();
		
		try {
			RowSetFactory factory = null;
			factory = RowSetProvider.newFactory();
			JdbcRowSet rowSet = factory.createJdbcRowSet();
			
			rowSet.setUrl(url);
			rowSet.setUsername(Connector.USERNAME);
			rowSet.setPassword(Connector.PASSWORD);
			
			rowSet.setCommand(query1);
			rowSet.execute();
			
			if(rowSet.next() != false) {
				
				ss.setName(rowSet.getString(1));
				ss.setAdress(rowSet.getString(2));
				
				rowSet.setCommand(query2);
				rowSet.execute();
				rowSet.next();
				
				ss.setSumDepartures(rowSet.getInt(1));
				
				rowSet.setCommand(query3);
				rowSet.execute();
				rowSet.next();
				
				ss.setSumReturns(rowSet.getInt(1));
			}
			
			rowSet.close();				
			
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return ss;
	}
	
	Object getIt(Object obj) {
		System.out.println("pff");
		return obj;
	}
%>


<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Single Station</title>
    <link rel= 'stylesheet' href = 'style4.css'>
</head>
<body bgcolor = '#146c62'>
	<%
		String station = JOptionPane.showInputDialog(null, "Enter Station");
		SingleStation ss = getStringQuery(station);
	%>
	<div id = 'container'>
	    <div id = 'tableContainer'>
	    	<table id = 'table'>
	    		<tr>
	    			<th>Station Name</th>
	    			<th>Address</th>
	    			<th>Total Journeys Started From the Station <%=station%></th>
	    			<th>Total Journeys Ended To The Station <%=station%></th>
	    		</tr>
	    		
	    		<tr>
	    			<td><%=ss.getName()%></td>
	    			<td><%=ss.getAdress()%></td>
	    			<td><%=ss.getSumDepartures()%></td>
	    			<td><%=ss.getSumReturns()%></td>
	    		</tr>
	    	</table>
	    </div>
	    <button class = 'buttons' id = 'mainPageButton'>Main Page</button>
		<button class = 'buttons' id = 'journeyDatasetButton'>Journey DataSet</button>
		<button class = 'buttons' id = 'stationDatasetButton'>Station Dataset</button>
		<button class = 'buttons' id = 'enterInputButton'>Enter Input</button>
		<script src = 'script4.js'></script>
	</div>
</body>
</html>