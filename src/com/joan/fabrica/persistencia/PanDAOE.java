package com.joan.fabrica.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.joan.fabrica.modelo.Pan;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PanDAOE {
	
	private ConnectionManager connectionManager;
	public PanDAOE(){
		this.connectionManager = new ConnectionManager("panaderia");
	}
	
	public void crearPanFabrica(Pan pan, Connection conexion){
		
		String sql = "INSERT INTO panesfabrica (idPan, tipo, nombre, precio) VALUES(?,?,?,?)";
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(0);
		lista.add(pan.getTipo());
		lista.add(pan.getNombre());
		lista.add(pan.getPrecio());
		int i = connectionManager.updateDBPS(sql, lista, true);
		pan.setId(i);
				
	}
	
	public void eliminarPanFabrica(Pan pan, Connection conexion){
		try {
			String sql = "SELECT * FROM panesfabrica WHERE idPan ="+pan.getId();
			ResultSet resultSet = connectionManager.consultar(sql);
			if(resultSet.next()){
				sql = "DELETE FROM panaderia.panesfabrica WHERE idPan = " + pan.getId();
				connectionManager.updateDB(sql, false);
			}
			else{
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error");
				alerta.setContentText("El pan a eliminar no existe.");
				alerta.show();			
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setContentText("El pan a eliminar está siendo usado por alguna tienda.");
			alerta.show();		
		} 
		
		
	}
	
	public ArrayList<Pan> obtenerPanesFabrica(Connection conexion){
		ArrayList<Pan> panes = new ArrayList<>();
		ResultSet rSet = null;
		try {
			String sql = "SELECT * FROM panaderia.panesfabrica";
			rSet = connectionManager.consultar(sql);
			while(rSet.next()){
				Pan pan = new Pan(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getFloat(4));
				panes.add(pan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return panes;
	}
	
	public Pan modificarPanFabrica(Pan pan, Connection conexion){
		Pan pan2 = new Pan(0, "", "", (float) 0.0);
		
		try {
			String sql = "SELECT * FROM panesfabrica WHERE idPan ="+pan.getId();
			ResultSet rSet = connectionManager.consultar(sql);
			
			if(rSet.next()){
				sql = "UPDATE panaderia.panesfabrica SET idPan = " + pan.getId() + ", tipo = '" + pan.getTipo() + 
						"', nombre = '" + pan.getNombre() + "', precio = " + pan.getPrecio() +
						" WHERE idPan = " + pan.getId() + "";
				connectionManager.updateDB(sql, false);
										
				//devolver el pan modificado
				sql = "SELECT * FROM panaderia.panesfabrica WHERE idPan = " + pan.getId() + "";
				rSet = connectionManager.consultar(sql);
				
				rSet.next();
				pan2.setId(rSet.getInt(1));
				pan2.setTipo(rSet.getString(2));
				pan2.setNombre(rSet.getString(3));
				pan2.setPrecio(rSet.getFloat(4));
				
			}
			else{
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error");
				alerta.setContentText("El pan a modificar no existe.");
				alerta.show();	
			}
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pan2;
	}
	
	public Pan buscarPanFabrica(int idPan, Connection conexion){
		Pan pan = null;
		
		try {
			String sql = "SELECT * FROM panesfabrica WHERE idPan = "+idPan;
			ResultSet rSet = connectionManager.consultar(sql);
			
			if(rSet.next()){
				pan = new Pan(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getFloat(4));
			}
			else{
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error");
				alerta.setContentText("El pan  no existe.");
				alerta.show();	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pan;
	}
}
