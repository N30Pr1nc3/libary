package de.myralia.MySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Sql {

	private static ArrayList<Sql> connections = new ArrayList<Sql>();	
	
	public static void connect(String _dbhost,int _dbport,String _dbname,String _dbuser,String _dbpass){
		connections.add(new Sql(_dbhost, _dbport, _dbname, _dbuser, _dbpass));
	}
	
	public static void connect(String _dbname,String _dbuser,String _dbpass){
		connect("localhost",3306, _dbname, _dbuser, _dbpass);
	}
	
	private Connection con;
	
	private Sql(String _dbhost,int _dbport,String _dbname,String _dbuser,String _dbpass){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection("jdbc:mysql://"+_dbhost+":"+ _dbport+"/"+_dbname+"?"+"user="+_dbuser+"&"+"password="+_dbpass);
	    } catch (ClassNotFoundException e) {
	        System.out.println("Treiber nicht gefunden");
	    } catch (SQLException e) {
	        System.out.println("Verbindung nicht moglich");
	        System.out.println("SQLException: " + e.getMessage());
	        System.out.println("SQLState: " + e.getSQLState());
	        System.out.println("VendorError: " + e.getErrorCode());
	    }	
	}	
	 	
	public ResultSet getResult(String _sql){    
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