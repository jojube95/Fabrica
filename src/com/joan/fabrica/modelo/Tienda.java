package com.joan.fabrica.modelo;

import java.util.ArrayList;

public class Tienda {
	int id;
	String localidad;
	String nombre;
	String contrasenya;
	ArrayList<Cliente> clientes;
	ArrayList<Pedido> pedidos;
	ArrayList<Venta> ventas;
	Stock stock;
	
	public Tienda(int id, String localidad, String nombre, String contrasenya, ArrayList<Cliente> clientes,
			ArrayList<Pedido> pedidos, ArrayList<Venta> ventas, Stock stock) {
		
		this.id = id;
		this.localidad = localidad;
		this.nombre = nombre;
		this.contrasenya = contrasenya;
		this.clientes = clientes;
		this.pedidos = pedidos;
		this.ventas = ventas;
		this.stock = stock;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(ArrayList<Pedido> pedidos) {
		this.pedidos = pedidos;
	}

	public ArrayList<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(ArrayList<Venta> ventas) {
		this.ventas = ventas;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	@Override
	public String toString() {
		return "Tienda [id=" + id + ", localidad=" + localidad + ", nombre=" + nombre + "]";
	}

	
	
	
	

	
}
