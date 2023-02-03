package databaseConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

import dataStructures.AVLTree;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;


public abstract class Connector extends Object {
	public static final String DRIVER = "jdbc:mysql://";
	public static final String HOST = "localhost";
	public static final String PORT = "3306";
	public static final String DATABASE = "solita";
	public static final String USERNAME = "root";
	public static final String PASSWORD = "root";
	
	
	public static Connection getDBConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(DRIVER + HOST + ":"
				+ PORT + "/" + DATABASE, USERNAME, PASSWORD);
		return connection;
	}
	
	public static void closeDBConnection(Connection connection) throws SQLException {
		connection.close();
	}
	
	/* The function  fetches data from the journey_data table using Rowsets. The queris inside are using the limit command
	 * which limis the recotds. The offset parameter is the starting record and the records parameter is
	 * is quntity of the records it will fetch. The JdbcRowSet is being used for fetching data*/
	public static AVLTree<JourneyData> fetchQueryfromJourneyData(int offset, int records) {
		final String URL = Connector.DRIVER + Connector.HOST + ":"
				+ Connector.PORT + "/" + Connector.DATABASE;
		
		final String query = "select DEPARTURE_STATION_NAME,RETURN_STATION_NAME,COVERED_DISTANCE,DURATION "
		+ "from journey_data "
		+ "limit ?, ?";
		
		AVLTree<JourneyData> tree = new AVLTree<>();
		JourneyData jd = null;
		int id = 1;

		try {
			RowSetFactory factory = null;
			factory = RowSetProvider.newFactory();
			JdbcRowSet rowSet = factory.createJdbcRowSet();
			
			rowSet.setUrl(URL);
			rowSet.setUsername(Connector.USERNAME);
			rowSet.setPassword(Connector.PASSWORD);
			
			rowSet.setCommand(query);
			rowSet.setInt(1,offset);
			rowSet.setInt(2,records);
			rowSet.execute();
			
			/* We don't want records that the coveredDistance is lower than 10 meters and duration lower than 10 seconds*/
			while(rowSet.next()) {
				int coveredDistance = rowSet.getInt(3);
				int duration = rowSet.getInt(4);
				if(coveredDistance > 10 && duration > 10) {
					jd = new JourneyData();
					jd.setId(id++);
					jd.setDepartureStationName(rowSet.getString(1));
					jd.setReturnStationName(rowSet.getString(2));
					jd.setCoveredDistance(coveredDistance);
					jd.setDuration(duration);
					tree.add(jd);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return tree;
	}
	
	/* The function  fetches data from the stations table using Rowsets. The queris inside are using the limit command
	 * which limis the recotds. The offset parameter is the starting record and the records parameter is
	 * is quntity of the records it will fetch. The JdbcRowSet is being used for fetching data*/
	public static AVLTree<Station> fetchQueryfromStations(int offset, int records) {
		final String URL = Connector.DRIVER + Connector.HOST + ":"
				+ Connector.PORT + "/" + Connector.DATABASE;
		
		final String query = "select NIMI,NAMN,NAME,OSOITE,ADRESS "
		+ "from stations "
		+ "limit ?, ?";
		
		AVLTree<Station> tree = new AVLTree<>();
		Station st = null;
		int id = 1;

		try {
			RowSetFactory factory = null;
			factory = RowSetProvider.newFactory();
			JdbcRowSet rowSet = factory.createJdbcRowSet();
			
			rowSet.setUrl(URL);
			rowSet.setUsername(Connector.USERNAME);
			rowSet.setPassword(Connector.PASSWORD);
			
			rowSet.setCommand(query);
			rowSet.setInt(1,offset);
			rowSet.setInt(2,records);
			rowSet.execute();
			
			while(rowSet.next()) {
					st = new Station();
					st.setId(id++);
					st.setNamn(rowSet.getString(1));
					st.setNimi(rowSet.getString(2));
					st.setName(rowSet.getString(3));
					st.setOsoite(rowSet.getString(4));
					st.setAdress(rowSet.getString(5));
					tree.add(st);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		
		return tree;
	}
	
	public static String getStringQuery(String station) {
		String url = Connector.DRIVER + Connector.HOST + ":" + Connector.PORT + "/" + Connector.DATABASE;
		String query = "select * from stations where NIMI = " + station;
		StringBuilder sb = new StringBuilder();
		
		try {
		
			RowSetFactory factory = null;
			factory = RowSetProvider.newFactory();
			JdbcRowSet rowSet = factory.createJdbcRowSet();
			
			rowSet.setUrl(url);
			rowSet.setUsername(Connector.USERNAME);
			rowSet.setPassword(Connector.PASSWORD);
			
			rowSet.setCommand(query);
			rowSet.execute();
			
			ResultSetMetaData metaData = rowSet.getMetaData();
			int cols = metaData.getColumnCount();
			
			for(int i = 1; i <= cols; i++)
				sb.append(String.format("%-30s", metaData.getColumnName(i)));
			sb.append("<br>");
			
			while(rowSet.next()) {
				for(int i = 1; i <= cols; i++)
					sb.append(String.format("%-30s", rowSet.getObject(i)));
				sb.append("<br>");
			}
			
			rowSet.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		//System.out.println("i'm here");
		return sb.toString();
	}
	
	/*
	 the main function that will tokenize the line.
	 The delimeter is comma(,). However when it finds prhases into quotes (" "),
	 the function will ingnore the delimeter.
	 */
	private static ArrayList<String> tokenize(String line) {
		ArrayList<String> list = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		char ch = '\0';
		boolean ignoreMode = false;
		
		for(int i = 0; i < line.length(); i++) {
			ch = line.charAt(i);
			if(ch == '"')
				ignoreMode = (ignoreMode == true) ? false : true;
			
			if(ignoreMode && ch != '"')
				sb.append(ch);
			else if(ch != ',' && ch != '"')
				sb.append(ch);
			
			if(!ignoreMode && ch == ',') {
				list.add(sb.toString().trim());
				sb.setLength(0);
			}	
		}
		list.add(sb.toString());
		
		return list;
	}
	
	/* It's parsing each line of the file which were saved into table lines as strings.
	 * It will proceed into tokenization of each line. The insertion of the lines into the database
	 * is being made via PreparedStatement*/
	private static void parseCSVLines(String[] lines, int nLines, String query, boolean strictMode) {
		ArrayList<String> tokens = null;
		
		boolean parsable = false;
		PreparedStatement statement = null;
		
		try(Connection connection = getDBConnection()) {
			statement = connection.prepareStatement(query);
			
			for(int i = 0; i < nLines; i++) {
				tokens = tokenize(lines[i]);
				
				parsable = true;
				if(strictMode) {
					for(String token : tokens) {
						if(token.length() == 0) {
							parsable = false;
							break;
						}
					}
				}
				
				
				/* if it is in scrict mode and the line is parsable, the data will be inserted into the database.
				 * The queries are using question marks (?). Using the setObject function java can determine the type of the data
				 * and the passing  it to the database */
				if(parsable) {					
					for(int x = 0; x < tokens.size(); x++)
						statement.setObject(x + 1, tokens.get(x));
					statement.executeUpdate();
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
	/*The Connector.loadCSV has 3 parameteres:
		1. the path of the file
		2. the insert into a table query
		3. This is a boolean variable which defines is the parsing od the file will be strict or not
		if strictMode is activated then the parser will check for empty fields. if it finds
		them they will not inserted it into the database. Using strict mode, the data gets validated*/
	public static void loadCSV(String filepath, String query, boolean strictMode) {
		try {
			File file = new File(filepath);
			FileInputStream fstream = new FileInputStream(file);
			BufferedReader in = new BufferedReader(new InputStreamReader(fstream, "utf-8"));
			final int records = 8000; // it will fetch the first 8000 records from a csv file
			String[] lines = new String[records]; // in this tables we will load the lines from the csv file
			int index = 0;
			String line = null;
			
			in.readLine(); // ignores the first line
			/* the while loop will terminated when if the records are 8000 or when the end of file has been reached */
			while(index < records && (line = in.readLine()) != null) {
				lines[index++] = line;
			}
			parseCSVLines(lines, index, query, strictMode);
			
			in.close();
			fstream.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		} catch(IOException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}