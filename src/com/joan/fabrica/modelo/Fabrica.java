package com.joan.fabrica.modelo;
	
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import com.joan.fabrica.persistencia.ClienteDAO;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Fabrica extends Application {
	private static Stock stock;
	private static ArrayList<Tienda> tiendas;
	private static ArrayList<Pan> panes;
	//private static ArrayList<Pedido> pedidos;
	private static HashMap<Tienda, Venta> ventasTienda;
	private static HashMap<Cliente, Venta> ventasCliente;
	private static HashMap<Tienda, Pedido> pedidosTienda;
	private static HashMap<Tienda, Stock> stockTienda;
	
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Prueba crear usuario en BD
		Date date = new Date(1992, 10, 10);
		Cliente cliente = new Cliente("Ranjit", 0, "Dali", date, true, "rajesh", "rajesh");
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.crearCliente(cliente);
		
		launch(args);
		
		
	}

	public ArrayList<Tienda> getTiendas() {
		return tiendas;
	}

	public void setTiendas(ArrayList<Tienda> tiendas) {
		this.tiendas = tiendas;
	}

	public Stock getStock() {
		return stock;
	}

	public void setStock(Stock stock) {
		this.stock = stock;
	}

	public static ArrayList<Pan> getPanes() {
		return panes;
	}

	public static void setPanes(ArrayList<Pan> panes) {
		Fabrica.panes = panes;
	}

	public static HashMap<Tienda, Venta> getVentasTienda() {
		return ventasTienda;
	}

	public static void setVentasTienda(HashMap<Tienda, Venta> ventasTienda) {
		Fabrica.ventasTienda = ventasTienda;
	}

	public static HashMap<Cliente, Venta> getVentasCliente() {
		return ventasCliente;
	}

	public static void setVentasCliente(HashMap<Cliente, Venta> ventasCliente) {
		Fabrica.ventasCliente = ventasCliente;
	}

	public static HashMap<Tienda, Pedido> getPedidosTienda() {
		return pedidosTienda;
	}

	public static void setPedidosTienda(HashMap<Tienda, Pedido> pedidosTienda) {
		Fabrica.pedidosTienda = pedidosTienda;
	}

	public static HashMap<Tienda, Stock> getStockTienda() {
		return stockTienda;
	}

	public static void setStockTienda(HashMap<Tienda, Stock> stockTienda) {
		Fabrica.stockTienda = stockTienda;
	}

	
}
