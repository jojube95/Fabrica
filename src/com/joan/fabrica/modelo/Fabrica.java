package com.joan.fabrica.modelo;
	
import java.util.ArrayList;
import java.util.HashMap;

import com.joan.fabrica.controlador.PrincipalController;
import com.joan.fabrica.persistencia.ClienteDAO;
import com.joan.fabrica.persistencia.PanDAO;
import com.joan.fabrica.persistencia.PedidoDAO;
import com.joan.fabrica.persistencia.StockDAO;
import com.joan.fabrica.persistencia.TiendaDAO;
import com.joan.fabrica.persistencia.VentaDAO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
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
	//Controladores
	private PrincipalController principalController;
	//PrimaryStage
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			/*
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,400,400);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();*/
			this.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/Principal.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("Fabrica");
	        //primaryStage.getIcons().add(new Image("file:Icono/icono.png"));
	        primaryStage.setScene(scene);
	        this.principalController = loader.getController();
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//Crear stockFabrica(UNICO)
		
		
		launch(args);
	}

	public ArrayList<Tienda> getTiendas() {
		return tiendas;
	}
	
	public Stock getStock() {
		return stock;
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
