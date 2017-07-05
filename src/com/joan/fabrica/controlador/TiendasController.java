package com.joan.fabrica.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.omg.CORBA.PRIVATE_MEMBER;

import com.joan.fabrica.modelo.Tienda;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    	        this.clientesTiendaController = loader.getController();
    	        clientesTiendaController.setTiendasController(this);
    			root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root);
    	        clientesStage.setTitle("Clientes");
    	        clientesStage.setScene(scene);
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
		        this.pedidosController = loader.getController();
		        pedidosController.setTiendasController(this);
		        root = (AnchorPane) loader.load();
				Scene scene = new Scene(root);
		        pedidosStage.setTitle("Pedidos");
		        pedidosStage.setScene(scene);
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
    	        this.stockTiendaController = loader.getController();
    	        stockTiendaController.setTiendasController(this);
    	        root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root);
    	        stockStage.setTitle("StockTienda");
    	        stockStage.setScene(scene);
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
    	        this.ventasTiendaController = loader.getController();
    	        ventasTiendaController.setTiendasController(this);
    	        root = (AnchorPane) loader.load();
    			Scene scene = new Scene(root);
    	        ventasStage.setTitle("Ventas");
    	        ventasStage.setScene(scene);
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
    	
    	tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
    	tcLocalidad.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLocalidad()));
        
    	tvTienda.setItems(FXCollections.observableArrayList(principalController.getFabrica().getTiendas()));
    	
    	
        
        
        

    }

	public static PrincipalController getPrincipalController() {
		return principalController;
	}

	public static void setPrincipalController(PrincipalController principalController) {
		TiendasController.principalController = principalController;
	}
}
