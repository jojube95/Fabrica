package com.joan.fabrica.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.joan.fabrica.modelo.Tienda;

import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TiendasController {
	//Controlers que abre este controler
	public static PedidosController pedidosController;
	public static StockTiendaController stockTiendaController;
	public static VentasTiendaController ventasTiendaController;
	public static ClientesTiendaController clientesTiendaController;
	//Instancia del PrincipalControler que es el que abre esto
	private static PrincipalController principalController;
	//Stages
	public static Stage pedidosStage = new Stage();
	public static Stage stockStage = new Stage();
	public static Stage clientesStage = new Stage();
	public static Stage ventasStage = new Stage();
	//Locks
	boolean lockClientes = false;
	boolean lockPedidos = false;
	boolean lockStock = false;
	boolean lockVentas = false;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tBuscar;

    @FXML
    private TableView<Tienda> tvTienda;

    @FXML
    private TableColumn<Tienda, String> tcNombre;

    @FXML
    private TableColumn<Tienda, String> tcLocalidad;

    @FXML
    private Button bNuevo;

    @FXML
    private Button bEditar;

    @FXML
    private Button bEliminar;

    @FXML
    private Button bPedidos;

    @FXML
    private Button bStock;

    @FXML
    private Button bVentas;

    @FXML
    private Button bClientes;

    @FXML
    void abrirClientes(ActionEvent event) {
    	if(!lockClientes){
    		try {
    			lockClientes = true;
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/ClientesTienda.fxml"));
    	        AnchorPane root;
    			root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root);
    	        clientesStage.setTitle("Clientes");
    	        clientesStage.setScene(scene);
    	        this.clientesTiendaController = loader.getController();
    	        clientesStage.show();
    	        lockClientes = false;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	else{
    		clientesStage.requestFocus();
    	}
    	
    }

    @FXML
    void abrirEditar(ActionEvent event) {

    }

    @FXML
    void abrirEliminar(ActionEvent event) {

    }

    @FXML
    void abrirNuevo(ActionEvent event) {

    }

    @FXML
    void abrirPedidos(ActionEvent event) {
		
    	if(!lockPedidos){
	    	try {
	    		lockPedidos = true;
		        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/Pedidos.fxml"));
		        AnchorPane root;
				root = (AnchorPane) loader.load();
				Scene scene = new Scene(root);
		        pedidosStage.setTitle("Pedidos");
		        pedidosStage.setScene(scene);
		        this.pedidosController = loader.getController();
		        pedidosStage.show();
		        lockPedidos  = false;
	        } catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	else{
    		pedidosStage.requestFocus();
    		
    	}
    }

    @FXML
    void abrirStock(ActionEvent event) {
    	if(!lockStock){
    		try {
    			lockStock = true;
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/StockTienda.fxml"));
    	        AnchorPane root;
    			root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root);
    	        stockStage.setTitle("StockTienda");
    	        stockStage.setScene(scene);
    	        this.stockTiendaController = loader.getController();
    	        stockStage.show();
    	        lockStock = false;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	else{
    		stockStage.requestFocus();
    	}
    	
    }

    @FXML
    void abrirVentas(ActionEvent event) {
    	if(!lockVentas){
    		try {
    			lockVentas = true;
    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/VentasTienda.fxml"));
    	        AnchorPane root;
    			root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root);
    	        ventasStage.setTitle("Ventas");
    	        ventasStage.setScene(scene);
    	        this.ventasTiendaController = loader.getController();
    	        ventasStage.show();
    	        lockVentas = false;
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	else{
    		ventasStage.requestFocus();
    	}
    	
    }

    @FXML
    void initialize() {
        assert tBuscar != null : "fx:id=\"tBuscar\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert tvTienda != null : "fx:id=\"tvTienda\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert tcNombre != null : "fx:id=\"tcNombre\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert tcLocalidad != null : "fx:id=\"tcLocalidad\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert bNuevo != null : "fx:id=\"bNuevo\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert bEditar != null : "fx:id=\"bEditar\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert bEliminar != null : "fx:id=\"bEliminar\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert bPedidos != null : "fx:id=\"bPedidos\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert bStock != null : "fx:id=\"bStock\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert bVentas != null : "fx:id=\"bVentas\" was not injected: check your FXML file 'Tiendas.fxml'.";
        assert bClientes != null : "fx:id=\"bClientes\" was not injected: check your FXML file 'Tiendas.fxml'.";

    }

	public static PrincipalController getPrincipalController() {
		return principalController;
	}

	public static void setPrincipalController(PrincipalController principalController) {
		TiendasController.principalController = principalController;
	}
}
