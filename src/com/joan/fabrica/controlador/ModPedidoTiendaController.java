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

public class ModPedidoTiendaController {
	//Instancia del pedidostiendaController que es el que abre esto
	private static PedidosTiendaController pedidosTiendasController;
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lPedido;

    @FXML
    private DatePicker tFecha;

    @FXML
    private MenuButton mTienda;

    @FXML
    private TableView<Panes> tvPanes;

    @FXML
    private TableColumn<Panes, String> tcPan;

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
        

    }

	public static PedidosTiendaController getPedidosTiendasController() {
		return pedidosTiendasController;
	}

	public static void setPedidosTiendasController(PedidosTiendaController pedidosTiendasController) {
		ModPedidoTiendaController.pedidosTiendasController = pedidosTiendasController;
	}
}
