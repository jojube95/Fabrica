package com.joan.fabrica.modelo;
	
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.joan.fabrica.controlador.PrincipalController;
import com.joan.fabrica.persistencia.ClienteDAOE;
import com.joan.fabrica.persistencia.ConnectionManager;
import com.joan.fabrica.persistencia.PanDAOE;
import com.joan.fabrica.persistencia.PedidoDAOE;
import com.joan.fabrica.persistencia.StockDAOE;
import com.joan.fabrica.persistencia.TiendaDAOE;
import com.joan.fabrica.persistencia.VentaDAOE;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Fabrica extends Application {
	private static ArrayList<Panes> panes = new ArrayList<>();
	private static ArrayList<Tienda> tiendas = new ArrayList<>();
	private static ArrayList<Pan> catalogo = new ArrayList<>();
	private static ArrayList<Pedido> pedidos = new ArrayList<>();
	//private static ArrayList<Pedido> pedidos;
	private static HashMap<Tienda, ArrayList<Venta>> ventasTienda = new HashMap<>();
	private static HashMap<Tienda, ArrayList<Cliente>> clientesTienda = new HashMap<>();
	private static HashMap<Tienda, ArrayList<Pedido>> pedidosTienda = new HashMap<>();
	private static HashMap<Tienda, ArrayList<Panes>> panesTienda = new HashMap<>();
	//Controladores
	private static PrincipalController principalController;
	//PrimaryStage
	public static Stage primaryStage;
	
	@Override
	public void start(Stage primaryStage) {
		cargarDatos();
		try {
			
			this.primaryStage = primaryStage;
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/Principal.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
	        Scene scene = new Scene(root);
	        primaryStage.setTitle("Fabrica");
	        //primaryStage.getIcons().add(new Image("file:Icono/icono.png"));
	        primaryStage.setScene(scene);
	        this.principalController = loader.getController();
	        //Instanciar la clase Fabrica en el controler que se abre
	        principalController.setFabrica(this);
	        primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void cargarDatos(){
		//---------CARGAR DATOS--------------
		ConnectionManager connectionManager = new ConnectionManager("panaderia");
		try {
			connectionManager.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		TiendaDAOE tiendaDAOE = new TiendaDAOE(connectionManager);
		StockDAOE stockDAOE = new StockDAOE(connectionManager);
		PanDAOE panDAOE = new PanDAOE(connectionManager);
		VentaDAOE ventaDAOE = new VentaDAOE(connectionManager);
		PedidoDAOE pedidoDAOE = new PedidoDAOE(connectionManager);
				
		tiendas = tiendaDAOE.obtenerTiendas();
		panes = stockDAOE.obtenerStockFabrica();
		catalogo = panDAOE.obtenerPanesFabrica();
		pedidos = pedidoDAOE.obtenerPedidos();
		
		//Cargar los hash maps
		ArrayList<Venta> ventas = ventaDAOE.obtenerVentas();
		for(int i = 0; i < tiendas.size(); i++){
			ArrayList<Venta> ventas2 = new ArrayList<>();
			for(int j = 0; j < ventas.size(); j++){
				if(tiendas.get(i).getId()==ventas.get(j).getIdTienda()){
					ventas2.add(ventas.get(j));					
				}
			}
			ventasTienda.put(tiendas.get(i), ventas2);
		}
		
		for(int i = 0; i < tiendas.size(); i++){
			ArrayList<Cliente> clientes = new ArrayList<>();
			ArrayList<Venta> ventas3 = new ArrayList<>();
			ventas3 = ventasTienda.get(tiendas.get(i));
			for(int j = 0; j < ventas3.size(); j++){
				clientes.add(ventas3.get(j).getCliente());
			}
			clientesTienda.put(tiendas.get(i), clientes);
		}
		
		for(int i = 0; i < tiendas.size(); i++){
			ArrayList<Pedido> pedidos2 = new ArrayList<>();
			for(int j = 0; j < pedidos.size(); j++){
				if(tiendas.get(i).getId()==pedidos.get(j).getTienda().getId()){
					pedidos2.add(pedidos.get(j));
				}
			}
			pedidosTienda.put(tiendas.get(i), pedidos2);
		}
		
		for(int i = 0; i < tiendas.size(); i++){
			ArrayList<Panes> panesTienda2 = new ArrayList<>();
			panesTienda2 = stockDAOE.obtenerStockTienda(tiendas.get(i));
			panesTienda.put(tiendas.get(i), panesTienda2);
		}
		
		
	}
		

	public ArrayList<Tienda> getTiendas() {
		return tiendas;
	}
		

	public static ArrayList<Panes> getPanes() {
		return panes;
	}

	public static void setPanes(ArrayList<Panes> panes) {
		Fabrica.panes = panes;
	}

	public static ArrayList<Pan> getCatalogo() {
		return catalogo;
	}

	public static void setCatalogo(ArrayList<Pan> catalogo) {
		Fabrica.catalogo = catalogo;
	}

	

	public static ArrayList<Pedido> getPedidos() {
		return pedidos;
	}

	public static void setPedidos(ArrayList<Pedido> pedidos) {
		Fabrica.pedidos = pedidos;
	}

	public static HashMap<Tienda, ArrayList<Venta>> getVentasTienda() {
		return ventasTienda;
	}

	public static void setVentasTienda(HashMap<Tienda, ArrayList<Venta>> ventasTienda) {
		Fabrica.ventasTienda = ventasTienda;
	}

	public static HashMap<Tienda, ArrayList<Cliente>> getClientesTienda() {
		return clientesTienda;
	}

	public static void setClientesTienda(HashMap<Tienda, ArrayList<Cliente>> clientesTienda) {
		Fabrica.clientesTienda = clientesTienda;
	}

	public static HashMap<Tienda, ArrayList<Pedido>> getPedidosTienda() {
		return pedidosTienda;
	}

	public static void setPedidosTienda(HashMap<Tienda, ArrayList<Pedido>> pedidosTienda) {
		Fabrica.pedidosTienda = pedidosTienda;
	}

	public static HashMap<Tienda, ArrayList<Panes>> getPanesTienda() {
		return panesTienda;
	}

	public static void setPanesTienda(HashMap<Tienda, ArrayList<Panes>> panesTienda) {
		Fabrica.panesTienda = panesTienda;
	}

	

	
}
