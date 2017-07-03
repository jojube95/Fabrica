package com.joan.fabrica.persistencia;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.joan.fabrica.modelo.Pan;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;
import com.joan.fabrica.modelo.Tienda;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PedidoDAO {
	private ConnectionManager connectionManager;
	public PedidoDAO(){
		this.connectionManager = new ConnectionManager("panaderia");
	}
	
	public void crearPedido(Pedido p){
		try {
			connectionManager.connect();
			String sql = "INSERT INTO  pedidos(idPedido, idTienda, fechaInicio, precio) VALUES (?,?,?,?)";
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(0);
			lista.add(p.getTienda().getId());
			lista.add(p.getFecha());
			lista.add(p.getPrecioTotal());
			int id = connectionManager.updateDBPS(sql, lista, true);
			p.setId(id);
			lista.clear();
			
			//para cada pan del pedido, insertar en PanesPedido
			for(int i = 0; i < p.getPanes().size(); i++){
				sql = "INSERT INTO panespedidos(idPedido, idPan, cantidad) VALUES (?,?,?)";
				lista.add(p.getId());
				lista.add(p.getPanes().get(i).getPan().getId());
				lista.add(p.getPanes().get(i).getCant());
				connectionManager.updateDBPS(sql, lista, false);
				lista.clear();
			}
			
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void eliminarPedido(Pedido p){
		try {
			connectionManager.connect();
			String sql = "select * from pedidos where idPedido = "+p.getId();
			ResultSet resultSet = connectionManager.consultar(sql);
			if(resultSet.next()){
				sql = "DELETE FROM panespedidos WHERE idPedido = " + p.getId();
				connectionManager.updateDB(sql, false);
				sql = "DELETE FROM pedidos WHERE idPedido = " + p.getId();
				connectionManager.updateDB(sql, false);
			}
			else{
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error");
				alerta.setContentText("El pedido a eliminar no existe.");
				alerta.show();	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Pedido> obtenerPedidos(){
		ArrayList<Pedido> pedidos = new ArrayList<>();
		
		
		ResultSet rSet = null;
		ResultSet rSet2 = null;
		//Atributos pedido
		int id;
		//Atributos tienda
		int idTienda;
				
		try {
			connectionManager.connect();
			String sql = "SELECT * FROM pedidos";
			rSet = connectionManager.consultar(sql);
			
			while(rSet.next()){
				ArrayList<Panes> panes = new ArrayList<>();
				id = (rSet.getInt(1));
				idTienda = (rSet.getInt(2));
							
				sql = "SELECT * FROM tiendas WHERE idTienda = "+idTienda;
				rSet2 = connectionManager.consultar(sql);
				rSet2.next();
				Tienda tienda = new Tienda(rSet.getInt(2), rSet2.getString(2), rSet2.getString(3), rSet2.getString(4));
								
				sql = "SELECT panespedidos.idPan, panespedidos.cantidad, panesfabrica.tipo, panesfabrica.nombre, panesfabrica.precio  FROM panespedidos, panesfabrica WHERE idPedido = "+id+" AND panespedidos.idPan = panesfabrica.idPan";
				rSet2 = connectionManager.consultar(sql);
				
				while (rSet2.next()) {
					Pan pan = new Pan(rSet2.getInt(1), rSet2.getString(3), rSet2.getString(4), rSet2.getFloat(5));
					Panes panes2 = new Panes(0, pan, rSet2.getInt(2));
					panes.add(panes2);
				}
				Pedido pedido = new Pedido(id, rSet.getDate(3), tienda, panes);
				pedidos.add(pedido);
				panes = new ArrayList<>();
			}
											
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connectionManager.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return pedidos;
		
	}
	
	public Pedido modificarPedido(Pedido p){
		Pedido p2 = p;
		ArrayList<Object> lista = new ArrayList<>();
		try {
			connectionManager.connect();
			String sql = "select * from pedidos where idPedido = "+p.getId();
			ResultSet resultSet = connectionManager.consultar(sql);
			if(resultSet.next()){
				sql = "UPDATE pedidos SET idPedido = " + p.getId() + ", idTienda = " + p.getTienda().getId() + 
						", fechaInicio = '" + p.getFecha() + "', precio = " + p.getPrecioTotal()+ " WHERE idPedido = " + p.getId();
				connectionManager.updateDB(sql, false);
				
				//Eliminar panes del pedido anterior
				sql = "DELETE FROM panespedidos WHERE idPedido = " + p.getId();
				connectionManager.updateDB(sql, false);
				//Anyadir los panes del pedido actual
				
				for(int i = 0;  i < p.getPanes().size(); i++){
					sql = "INSERT INTO panespedidos(idPedido, idPan, cantidad) VALUES (?,?,?)";
					lista.add(p.getId());
					lista.add(p.getPanes().get(i).getPan().getId());
					lista.add(p.getPanes().get(i).getCant());
					connectionManager.updateDBPS(sql, lista, false);
					lista.clear();
				}
				
			}
			else{
				Alert alerta = new Alert(AlertType.ERROR);
				alerta.setTitle("Error");
				alerta.setContentText("El pedido a modificar no existe.");
				alerta.show();	
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				connectionManager.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return p2;
	}
}
