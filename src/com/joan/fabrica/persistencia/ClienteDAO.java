package com.joan.fabrica.persistencia;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import com.joan.fabrica.modelo.Cliente;

public class ClienteDAO {
	private ConnectionManager connectionManager;
	public ClienteDAO(){
		this.connectionManager = new ConnectionManager("panaderia");
	}
	
	public void crearCliente(Cliente c){
		try {
			connectionManager.connect();
			String sql = "INSERT INTO panaderia.clientes (idCliente, nombre, localidad, fechaNacimiento, usuario, contrasenya, online) VALUES (?,?,?,?,?,?,?)";
			ArrayList<Object> lista = new ArrayList<>();
			lista.add(0);
			lista.add(c.getNombre());
			lista.add(c.getLocalidad());
			lista.add(c.getFechaNac());
			lista.add(c.getUsuario());
			lista.add(c.getPass());
			lista.add(true);
			int i = connectionManager.updateDBPS(sql, lista, true);
			c.setId(i);
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connectionManager.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void eliminarCliente(Cliente c){
		try {
			connectionManager.connect();
			String sql = "DELETE FROM panaderia.clientes WHERE idCliente = " + c.getId();
			connectionManager.updateDB(sql, false);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				connectionManager.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public ArrayList<Cliente> obtenerClientes(){
		ArrayList<Cliente> clientes = new ArrayList<>();
		ResultSet rSet = null;
		try {
			connectionManager.connect();
			String sql = "SELECT * FROM panaderia.clientes";
			rSet = connectionManager.consultar(sql);
			
			while(rSet.next()){
				Cliente c = new Cliente("", 0, "", new Date(10), false,"","");
				c.setId(rSet.getInt(1));
				c.setNombre(rSet.getString(2));
				c.setLocalidad(rSet.getString(3));
				c.setFechaNac(rSet.getDate(4));
				c.setUsuario(rSet.getString(5));
				c.setPass(rSet.getString(6));
				c.setOnline(rSet.getBoolean(7));
				
				clientes.add(c);
			}
					
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientes;
		
	}
	
	public Cliente modificarCliente(Cliente c){
		Cliente c2 = new Cliente("", 0, "", new Date(10), false,"","");
		
		try {
			connectionManager.connect();
			String sql = "UPDATE panaderia.clientes SET idCliente = " + c.getId() + ", nombre = '" + c.getNombre() + 
					"', localidad = '" + c.getLocalidad() + "', fechaNacimiento = '" + c.getFechaNac().toString() + 
					"', usuario = '" + c.getUsuario() + "', contrasenya = '" + c.getPass() + "', online = " + c.isOnline() + " WHERE idCliente = " + c.getId() + "";
			connectionManager.updateDB(sql, false);
			
			//UPDATE panaderia.clientes SET idCliente = 16, nombre = Ranjit, localidad = Aielo, fechaNacimiento = 3892-11-10, usuario = rajesh, contrasenya = rajesh, online = true WHERE idCliente = 16
			
			//devolver el cliente modificado
			sql = "SELECT * FROM panaderia.clientes WHERE idCliente = " + c.getId() + "";
			ResultSet rSet = connectionManager.consultar(sql);
			
			while(rSet.next()){
				c2.setId(rSet.getInt(1));
				c2.setNombre(rSet.getString(2));
				c2.setLocalidad(rSet.getString(3));
				c2.setFechaNac(rSet.getDate(4));
				c2.setUsuario(rSet.getString(5));
				c2.setPass(rSet.getString(6));
				c2.setOnline(rSet.getBoolean(7));
								
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c2;
	}
}
