package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Panes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ModVentaController {
	//Instancia del controller que abre esto
	private static VentasTiendaController ventasTiendaController;
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lVenta;

    @FXML
    private DatePicker tFecha;

    @FXML
    private MenuButton mCliente;

    @FXML
    private TableView<Panes> tvPanes;

    @FXML
    private TableColumn<Panes, Integer> tcPan;

    @FXML
    private TableColumn<Panes, Integer> tcCantidad;
    
    @FXML
    private TableColumn<Panes, Float> tcPrecio;
    
    @FXML
    private Button bAceptar;

    @FXML
    private Button bCancelar;

    @FXML
    void abrirAceptar(ActionEvent event) {

    }

    @FXML
    void abrirCancelar(ActionEvent event) {

    }

    @FXML
    void initialize() {
    	assert lVenta != null : "fx:id=\"lVenta\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert tFecha != null : "fx:id=\"tFecha\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert mCliente != null : "fx:id=\"mCliente\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert tvPanes != null : "fx:id=\"tvPanes\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert tcPan != null : "fx:id=\"tcPan\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert tcCantidad != null : "fx:id=\"tcCantidad\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert tcPrecio != null : "fx:id=\"tcPrecio\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert bAceptar != null : "fx:id=\"bAceptar\" was not injected: check your FXML file 'ModVenta.fxml'.";
        assert bCancelar != null : "fx:id=\"bCancelar\" was not injected: check your FXML file 'ModVenta.fxml'.";

    }

	public static VentasTiendaController getVentasTiendaController() {
		return ventasTiendaController;
	}

	public static void setVentasTiendaController(VentasTiendaController ventasTiendaController) {
		ModVentaController.ventasTiendaController = ventasTiendaController;
	}
}
