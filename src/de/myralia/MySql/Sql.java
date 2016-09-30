package de.myralia.MySql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Sql {
    private static Connection con = null;
	private static String dbHost = "localhost"; // Hostname test
	private static String dbPort = "3306";      // Port -- Standard: 3306
	private static String dbName = "";   // Datenbankname
	private static String dbUser = "";     // Datenbankuser
	private static String dbPass = "";      // Datenbankpasswort
	
	public static void connect(String _dbhost,String _dbport,String _dbname,String _dbuser,String _dbpass){
		dbHost = _dbhost;
		dbPort = _dbport;
		connect( _dbname, _dbuser, _dbpass);
	}
	
	public static void connect(String _dbname,String _dbuser,String _dbpass){
		dbName = _dbname;		
		dbUser = _dbuser;
		dbPass = _dbpass;
		connect();
	}
	
	public static void connect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://"+dbHost+":"+ dbPort+"/"+dbName+"?"+"user="+dbUser+"&"+"password="+dbPass);
	    } catch (ClassNotFoundException e) {
	        System.out.println("Treiber nicht gefunden");
	    } catch (SQLException e) {
	        System.out.println("Verbindung nicht moglich");
	        System.out.println("SQLException: " + e.getMessage());
	        System.out.println("SQLState: " + e.getSQLState());
	        System.out.println("VendorError: " + e.getErrorCode());
	    }	
	}
	 	
	public static ResultSet getLocations(String _sql){
		if(con == null)
		{
			connect();
		}	     
		try {
			Statement query;
			query = con.createStatement();
			ResultSet result = query.executeQuery(_sql);
			return result;	          
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
