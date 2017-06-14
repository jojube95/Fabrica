package com.joan.fabrica.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;




public class ConnectionManager {
	private String sourceURL;
	private Connection dbcon = null;

	public ConnectionManager(String dbname){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sourceURL = "jdbc:mysql://localhost:3306/" + dbname;
	}

	public void connect() throws SQLException {
		if (dbcon == null)
				dbcon = DriverManager.getConnection(sourceURL, "root", "");
	}

	public void close() throws SQLException {
		if (dbcon != null) {
				dbcon.close();
				dbcon = null;
		}
	}
	
	public void updateDB(String sql) throws SQLException {
		if (dbcon != null) {
				Statement sentencia = dbcon.createStatement();
				sentencia.executeUpdate(sql);
		}
	}
	
	public void updateDBPS(String sql, ArrayList<Object> list){
		try {
			String clase = "";
			PreparedStatement sentecia = dbcon.prepareStatement(sql);
			for(int i = 0; i<list.size(); i++){
				clase = list.get(i).getClass().getName();
				switch (clase){
					case "java.lang.Boolean":
						sentecia.setBoolean(i+1, (boolean) list.get(i));
					break;
					case "java.lang.Integer":
						sentecia.setInt(i+1, (int) list.get(i));
					break;
					case "java.lang.String":
						sentecia.setString(i+1, (String) list.get(i));
					break;
					case "java.sql.Date":
						sentecia.setDate(i+1, (Date) list.get(i));
					break;
					
					case "java.lang.Float":
						sentecia.setFloat(i+1, (float) list.get(i));
					break;
						
					case "java.lang.Long":
						sentecia.setLong(i+1, (long) list.get(i));
					break;
				}
			}
			sentecia.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
