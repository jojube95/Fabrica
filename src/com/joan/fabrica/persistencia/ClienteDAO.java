package com.joan.fabrica.persistencia;

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
			connectionManager.updateDBPS(sql, lista);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
