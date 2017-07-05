package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PedidosController {
	public static Stage stageModPedido = new Stage();
	//Instancia del PrincipalControler que es el que abre esto
	private static PrincipalController principalController;
	//Instancia del TiendasController que es el otro que abre esto
	private static TiendasController tiendasController;
	
	//Instancia del controlador que abre
	private static ModPedidoController modPedidoController;
	
	
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tBuscar;

    @FXML
    private Button bNuevo;

    @FXML
    private Button bEditar;

    @FXML
    private Button bEliminar;

    @FXML
    private TableView<Pedido> tvPedido;

    @FXML
    private TableColumn<Pedido, Integer> tcId;

    @FXML
    private TableColumn<Pedido, String> tcTienda;

    @FXML
    private TableColumn<Pedido, Date> tcFecha;

    @FXML
    private TableColumn<Pedido, Float> tcPrecio;

    @FXML
    private TableView<Panes> tvPanes;

    @FXML
    private TableColumn<Panes, String> tcNombre;

    @FXML
    private TableColumn<Panes, String> tcTipo;

    @FXML
    private TableColumn<Panes, Integer> tcCantidad;

    @FXML
    private TableColumn<Panes, Float> tcPrecioPan;

    @FXML
    void abrirEditar(ActionEvent event) {
    	stageModPedido.showAndWait();
	}

    @FXML
    void abrirEliminar(ActionEvent event) {

    }

    @FXML
    void abrirNuevo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert tBuscar != null : "fx:id=\"tBuscar\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert bNuevo != null : "fx:id=\"bNuevo\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert bEditar != null : "fx:id=\"bEditar\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert bEliminar != null : "fx:id=\"bEliminar\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tvPedido != null : "fx:id=\"tvPedido\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcId != null : "fx:id=\"tcId\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcTienda != null : "fx:id=\"tcTienda\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcFecha != null : "fx:id=\"tcFecha\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcPrecio != null : "fx:id=\"tcPrecio\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tvPanes != null : "fx:id=\"tvPanes\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcNombre != null : "fx:id=\"tcNombre\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcTipo != null : "fx:id=\"tcTipo\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcCantidad != null : "fx:id=\"tcCantidad\" was not injected: check your FXML file 'Pedidos.fxml'.";
        assert tcPrecioPan != null : "fx:id=\"tcPrecioPan\" was not injected: check your FXML file 'Pedidos.fxml'.";
        try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/ModPedido.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
	        Scene scene = new Scene(root);
	        stageModPedido.initModality(Modality.WINDOW_MODAL);
			stageModPedido.initOwner(TiendasController.pedidosStage);
	        stageModPedido.setTitle("Modificar venta");
	        //primaryStage.getIcons().add(new Image("file:Icono/icono.png"));
	        stageModPedido.setScene(scene);
	        this.modPedidoController = loader.getController();
	        modPedidoController.setPedidosController(this);
        } catch(Exception e) {
			e.printStackTrace();
		}
    }

	public static PrincipalController getPrincipalController() {
		return principalController;
	}

	public static void setPrincipalController(PrincipalController principalController) {
		PedidosController.principalController = principalController;
	}

	public static TiendasController getTiendasController() {
		return tiendasController;
	}

	public static void setTiendasController(TiendasController tiendasController) {
		PedidosController.tiendasController = tiendasController;
	}
}
