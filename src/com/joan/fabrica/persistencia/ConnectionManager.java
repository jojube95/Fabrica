package com.joan.fabrica.persistencia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public ResultSet consultar(String sql){
		ResultSet rSet = null;
		try {
			Statement sentencia = dbcon.createStatement();
			sentencia.executeQuery(sql);
			rSet = sentencia.getResultSet();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rSet;
		
	}
	
	public int getLastGenerated(String sql){
		int i = 0;
		try {
			PreparedStatement sentencia = dbcon.prepareStatement(sql);
			sentencia = dbcon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			sentencia.executeUpdate();
			ResultSet rSet = sentencia.getGeneratedKeys();
			rSet.next();
			i = rSet.getInt(1);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}
	
	public int updateDB(String sql, boolean returnId) throws SQLException {
		int id = 0;
		
		Statement sentencia = dbcon.createStatement();
		sentencia.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
		
		if(returnId){
			ResultSet rSet = sentencia.getGeneratedKeys();
			rSet.next();
			id = rSet.getInt(1);
		}
		
		return id;
	}
	
	public int updateDBPS(String sql, ArrayList<Object> list, boolean returnId){
		int id = 0;
		try {
			String clase = "";
			PreparedStatement sentencia = dbcon.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			for(int i = 0; i<list.size(); i++){
				clase = list.get(i).getClass().getName();
				switch (clase){
					case "java.lang.Boolean":
						sentencia.setBoolean(i+1, (boolean) list.get(i));
					break;
					case "java.lang.Integer":
						sentencia.setInt(i+1, (int) list.get(i));
					break;
					case "java.lang.String":
						sentencia.setString(i+1, (String) list.get(i));
					break;
					case "java.sql.Date":
						sentencia.setDate(i+1, (Date) list.get(i));
					break;
					
					case "java.lang.Float":
						sentencia.setFloat(i+1, (float) list.get(i));
					break;
						
					case "java.lang.Long":
						sentencia.setLong(i+1, (long) list.get(i));
					break;
				}
			}
			sentencia.executeUpdate();
			
			if(returnId){
				ResultSet rSet = sentencia.getGeneratedKeys();
				rSet.next();
				id = rSet.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}
	
}
