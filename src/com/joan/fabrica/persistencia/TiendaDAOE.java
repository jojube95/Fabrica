package com.joan.fabrica.persistencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.joan.fabrica.modelo.Tienda;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TiendaDAOE {
	private ConnectionManager connectionManager;
	public TiendaDAOE() {
		this.connectionManager = new ConnectionManager("panaderia");
	}
	
	public void crearTienda(Tienda tienda, Connection connection){
		String sql = "INSERT INTO tiendas (idTienda, localidad, nombre, contrasenya) VALUES(?,?,?,?)";
		ArrayList<Object> lista = new ArrayList<>();
		lista.add(0);
		lista.add(tienda.getLocalidad());
		lista.add(tienda.getNombre());
		lista.add(tienda.getContrasenya());
		int i = connectionManager.updateDBPS(sql, lista, true);
		tienda.setId(i); 
		
		
	}
	
	public void eliminarTienda(Tienda tienda, Connection connection){
		try {
			String sql = "SELECT * FROM tiendas WHERE idTienda = "+ tienda.getId();
			ResultSet resultSet = connectionManager.consultar(sql);
			if(resultSet.next()){
				//Obtener su idStock
				sql = "select idStock from stocktienda where idTienda = "+tienda.getId();
				resultSet = connectionManager.consultar(sql);
				resultSet.next();
				int idStock = resultSet.getInt(1);
				//Eliminar los panesStock
				sql = "delete from panesstock where idStock = "+idStock;
				connectionManager.updateDB(sql, false);
				//Eliminar ventas
				sql = "delete from panesventas where idVenta in (select idVenta from ventas where idTienda = "+tienda.getId()+")";
				connectionManager.updateDB(sql, false);
				sql = "delete from ventas where idTienda = "+tienda.getId();
				connectionManager.updateDB(sql, false);
				//eliminar los panestienda
				sql = "delete from panestienda where idPanesTienda not in (select idPanesTienda from panesstock)";
 				connectionManager.updateDB(sql, false);
				//Eliminar su stock
				sql = "DELETE FROM stocktienda WHERE idTienda = "+tienda.getId();
				connectionManager.updateDB(sql, false);
				
				//Eliminar pedido
				sql = "delete from panespedidos where idPedido in (select idPedido from pedidos where idTienda = "+ tienda.getId()+")";
				connectionManager.updateDB(sql, false);
				sql = "delete from pedidos where idTienda = "+tienda.getId();
				connectionManager.updateDB(sql, false);
				
				//Eliminar la tienda
				sql = "DELETE FROM tiendas WHERE idTienda = " + tienda.getId();
				connectionManager.updateDB(sql, false);
			}
			else{
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error");
				alerta.setContentText("La tienda a eliminar no existe.");
				alerta.show();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public ArrayList<Tienda> obtenerTiendas(Connection connection){
		ArrayList<Tienda> tiendas = new ArrayList<>();
		ResultSet rSet = null;
		try {
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
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tiendas;
	}
	
	public Tienda modificarTienda(Tienda tienda, Connection connection){
		Tienda tienda2 = new Tienda(0, "", "", "");
		
		try {
			String sql = "SELECT * FROM tiendas WHERE idTienda = "+ tienda.getId();
			ResultSet resultSet = connectionManager.consultar(sql);
			if(resultSet.next()){
				sql = "UPDATE tiendas SET idTienda = " + tienda.getId() + ", localidad = '" + tienda.getLocalidad() + 
					"', nombre = '" + tienda.getNombre() + "', contrasenya = '" + tienda.getContrasenya() +
					"' WHERE idTienda = " + tienda.getId() + "";
				connectionManager.updateDB(sql, false);
										
				//devolver el pan modificado
				sql = "SELECT * FROM tiendas WHERE idTienda = " + tienda.getId() + "";
				ResultSet rSet = connectionManager.consultar(sql);
				
				rSet.next();
				tienda2.setId(rSet.getInt(1));
				tienda2.setLocalidad(rSet.getString(2));
				tienda2.setNombre(rSet.getString(3));
				tienda2.setContrasenya(rSet.getString(4));
				
			}
			else{
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error");
				alerta.setContentText("La tienda a modificar no existe.");
				alerta.show();
			}
			
			
		
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return tienda2;
	}
}
