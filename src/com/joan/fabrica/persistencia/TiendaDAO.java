package com.joan.fabrica.persistencia;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.joan.fabrica.modelo.Tienda;

public class TiendaDAO {
	private ConnectionManager connectionManager;
	public TiendaDAO() {
		this.connectionManager = new ConnectionManager("panaderia");
	}
	
	public void crearTienda(Tienda tienda){
		try {
			connectionManager.connect();
			String sql = "INSERT INTO tiendas (idTienda, localidad, nombre, contrasenya) VALUES(?,?,?,?)";
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(tienda.getId());
			lista.add(tienda.getLocalidad());
			lista.add(tienda.getNombre());
			lista.add(tienda.getContrasenya());
			int i = connectionManager.updateDBPS(sql, lista, true);
			tienda.setId(i);
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
	
	public void eliminarTienda(Tienda tienda){
		try {
			connectionManager.connect();
			String sql = "DELETE FROM tiendas WHERE idTienda = " + tienda.getId();
			connectionManager.updateDB(sql, false);
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Tienda> obtenerTiendas(){
		ArrayList<Tienda> tiendas = new ArrayList<>();
		ResultSet rSet = null;
		try {
			connectionManager.connect();
			String sql = "SELECT * FROM tiendas";
			rSet = connectionManager.consultar(sql);
			while(rSet.next()){
				Tienda tienda = new Tienda(0, "", "", "");
				tienda.setId(rSet.getInt(1));
				tienda.setLocalidad(rSet.getString(2));
				tienda.setNombre(rSet.getString(3));
				tienda.setContrasenya(rSet.getString(4));
				
				tiendas.add(tienda);
			}
			connectionManager.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return tiendas;
	}
	
	public Tienda modificarTienda(Tienda tienda){
		Tienda tienda2 = new Tienda(0, "", "", "");
		
		try {
			connectionManager.connect();
			String sql = "UPDATE tiendas SET idTienda = " + tienda.getId() + ", localidad = '" + tienda.getLocalidad() + 
					"', nombre = '" + tienda.getNombre() + "', contrasenya = '" + tienda.getContrasenya() +
					"' WHERE idTienda = " + tienda.getId() + "";
			connectionManager.updateDB(sql, false);
									
			//devolver el pan modificado
			sql = "SELECT * FROM tiendas WHERE idTienda = " + tienda.getId() + "";
			ResultSet rSet = connectionManager.consultar(sql);
			
			while(rSet.next()){
				tienda2.setId(rSet.getInt(1));
				tienda2.setLocalidad(rSet.getString(2));
				tienda2.setNombre(rSet.getString(3));
				tienda2.setContrasenya(rSet.getString(4));
			}
			
			connectionManager.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tienda2;
	}
	
	/*public Tienda buscarTienda(int idTienda){
		Tienda tienda = new Tienda(id, nombre, localidad, contrasenya)
		
	}*/
		
}
