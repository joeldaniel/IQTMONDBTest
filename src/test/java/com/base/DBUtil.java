package com.base;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil extends Testbase{
	Connection con;
	
	public DBUtil(String url, String user, String password) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = DriverManager.getConnection(url, user, password);
		System.out.println(url +" : "+ user +" : "+ password);
		System.out.println("Connected to sql db");	
	}
	
	
	public ResultSet RunQuery(String query) throws ClassNotFoundException, SQLException {
		Statement stmt = con.createStatement();
		ResultSet rs=stmt.executeQuery(query);
		return rs;
		
	}
	
	public void Closeconnection() throws SQLException {
		con.close();
	}
	
}
