package com.joan.fabrica.modelo;
	
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.IllegalBlockSizeException;

import com.joan.fabrica.persistencia.ClienteDAO;
import com.joan.fabrica.persistencia.PanDAO;
import com.joan.fabrica.persistencia.StockDAO;
import com.joan.fabrica.persistencia.TiendaDAO;
import com.mysql.fabric.xmlrpc.base.Array;

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
		//Crear stockFabrica(UNICO)
		
		//------PRUEBAS CRUD-------------------------------
		Tienda tienda = new Tienda(0, "localidad", "Nombre 1", "contrasenya");
		TiendaDAO tiendaDAO = new TiendaDAO();
		tienda = tiendaDAO.obtenerTiendas().get(5);
		Pan pan = new Pan(11, "tipo1", "nombre1", (float)0.5);
		Panes pan2 = new Panes(0, pan, 5);
		ArrayList<Panes> panes = new ArrayList<>();
		panes.add(pan2);
		
		StockDAO stockDAO = new StockDAO();
		
		stockDAO.crearStockTienda(tienda, panes);
		panes = stockDAO.obtenerStockTienda(tienda);
		panes.get(0).setCant(23);
		pan2 = stockDAO.modificarStockTienda(tienda, panes.get(0));
		stockDAO.eliminarStockTienda(tienda, pan2);
		
		/*
		Tienda tienda = new Tienda(0, "localidad", "Nombre 1", "contrasenya");
		Tienda tienda2 = new Tienda(0, "localidad", "Nombre 1", "contrasenya");
		ArrayList<Tienda> tiendas = new ArrayList<>();
		TiendaDAO tiendaDAO = new TiendaDAO();
		
		tiendaDAO.crearTienda(tienda);
		tiendas = tiendaDAO.obtenerTiendas();
		tienda.setNombre("tienda nosek");
		tienda2 = tiendaDAO.modificarTienda(tienda);
		tiendaDAO.eliminarTienda(tienda2);
		*/
		/*
		panDAO.crearPanTienda(pan);
		panes = panDAO.obtenerPanesTienda();
		pan.setPrecio((float)2.5);
		pan2 = panDAO.modificarPanTienda(pan);
		panDAO.eliminarPanTienda(pan2);
		*/
		
		
		
		
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
