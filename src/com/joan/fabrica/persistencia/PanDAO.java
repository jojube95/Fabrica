package com.joan.fabrica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.joan.fabrica.modelo.Pan;

public class PanDAO {
	private ConnectionManager connectionManager;
	public PanDAO(){
		this.connectionManager = new ConnectionManager("panaderia");
	}
	
	public void crearPanFabrica(Pan pan){
		try {
			connectionManager.connect();
			String sql = "INSERT INTO "
					+ "panesfabrica (idPan, tipo, nombre, precio) VALUES(?,?,?,?)";
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(pan.getId());
			lista.add(pan.getTipo());
			lista.add(pan.getNombre());
			lista.add(pan.getPrecio());
			int i = connectionManager.updateDBPS(sql, lista, true);
			pan.setId(i);
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public void eliminarPanFabrica(Pan pan){
		try {
			connectionManager.connect();
			String sql = "DELETE FROM panaderia.panesfabrica WHERE idPan = " + pan.getId();
			connectionManager.updateDB(sql, false);
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Pan> obtenerPanesFabrica(){
		ArrayList<Pan> panes = new ArrayList<>();
		ResultSet rSet = null;
		try {
			connectionManager.connect();
			String sql = "SELECT * FROM panaderia.panesfabrica";
			rSet = connectionManager.consultar(sql);
			while(rSet.next()){
				Pan pan = new Pan(0, "", "", (float) 0.0);
				pan.setId(rSet.getInt(1));
				pan.setTipo(rSet.getString(2));
				pan.setNombre(rSet.getString(3));
				pan.setPrecio(rSet.getFloat(4));
				
				panes.add(pan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return panes;
	}
	
	public Pan modificarPanFabrica(Pan pan){
		Pan pan2 = new Pan(0, "", "", (float) 0.0);
		
		try {
			connectionManager.connect();
			String sql = "UPDATE panaderia.panesfabrica SET idPan = " + pan.getId() + ", tipo = '" + pan.getTipo() + 
					"', nombre = '" + pan.getNombre() + "', precio = " + pan.getPrecio() +
					" WHERE idPan = " + pan.getId() + "";
			connectionManager.updateDB(sql, false);
									
			//devolver el pan modificado
			sql = "SELECT * FROM panaderia.panesfabrica WHERE idPan = " + pan.getId() + "";
			ResultSet rSet = connectionManager.consultar(sql);
			
			while(rSet.next()){
				pan2.setId(rSet.getInt(1));
				pan2.setTipo(rSet.getString(2));
				pan2.setNombre(rSet.getString(3));
				pan2.setPrecio(rSet.getFloat(4));
			}
			
			connectionManager.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pan2;
	}
	
	public Pan buscarPanFabrica(int idPan){
		Pan pan = new Pan(0, "", "", (float) 0.0);
		
		try {
			connectionManager.connect();
			String sql = "SELECT * FROM panesfabrica WHERE idPan = "+idPan;
			ResultSet rSet = connectionManager.consultar(sql);
			
			if(rSet.next()){
				pan = new Pan(rSet.getInt(1), rSet.getString(2), rSet.getString(3), rSet.getFloat(4));
			}
			else{
				pan = null;
				
			}
			
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pan;
	}
	
	
	
	public void crearPanTienda(Pan pan){
		try {
			connectionManager.connect();
			String sql = "INSERT INTO panaderia.panestienda (idPan, tipo, nombre, precio) VALUES(?,?,?,?)";
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(pan.getId());
			lista.add(pan.getTipo());
			lista.add(pan.getNombre());
			lista.add(pan.getPrecio());
			int i = connectionManager.updateDBPS(sql, lista, true);
			pan.setId(i);
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}
	
	public void eliminarPanTienda(Pan pan){
		try {
			connectionManager.connect();
			String sql = "DELETE FROM panaderia.panestienda WHERE idPan = " + pan.getId();
			connectionManager.updateDB(sql, false);
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public ArrayList<Pan> obtenerPanesTienda(){
		ArrayList<Pan> panes = new ArrayList<>();
		ResultSet rSet = null;
		try {
			connectionManager.connect();
			String sql = "SELECT * FROM panaderia.panestienda";
			rSet = connectionManager.consultar(sql);
			while(rSet.next()){
				Pan pan = new Pan(0, "", "", (float) 0.0);
				pan.setId(rSet.getInt(1));
				pan.setTipo(rSet.getString(2));
				pan.setNombre(rSet.getString(3));
				pan.setPrecio(rSet.getFloat(4));
				
				panes.add(pan);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return panes;
	}
	
	public Pan modificarPanTienda(Pan pan){
		Pan pan2 = new Pan(0, "", "", (float) 0.0);
		
		try {
			connectionManager.connect();
			String sql = "UPDATE panaderia.panestienda SET idPan = " + pan.getId() + ", tipo = '" + pan.getTipo() + 
					"', nombre = '" + pan.getNombre() + "', precio = " + pan.getPrecio() +
					" WHERE idPan = " + pan.getId() + "";
			connectionManager.updateDB(sql, false);
									
			//devolver el pan modificado
			sql = "SELECT * FROM panaderia.panestienda WHERE idPan = " + pan.getId() + "";
			ResultSet rSet = connectionManager.consultar(sql);
			
			while(rSet.next()){
				pan2.setId(rSet.getInt(1));
				pan2.setTipo(rSet.getString(2));
				pan2.setNombre(rSet.getString(3));
				pan2.setPrecio(rSet.getFloat(4));
			}
			
			connectionManager.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pan2;
	}
	
	public Pan buscarPanTienda(int idPan){
		
		Pan pan = new Pan(0, "", "", (float) 0.0);
		
		try {
			connectionManager.connect();
			String sql = "SELECT * FROM panestienda WHERE idPan = "+idPan;
			ResultSet rSet = connectionManager.consultar(sql);
			
			if(rSet.next()){
				pan = new Pan(rSet.getInt(2), rSet.getString(3), rSet.getString(4), rSet.getFloat(5));
			}
			else{
				pan = null;
				
			}
			
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pan;
	}
	
	
}
