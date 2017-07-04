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
        assert lPedido != null : "fx:id=\"lPedido\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert tFecha != null : "fx:id=\"tFecha\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert mTienda != null : "fx:id=\"mTienda\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert tvPanes != null : "fx:id=\"tvPanes\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert tcPan != null : "fx:id=\"tcPan\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert tcCantidad != null : "fx:id=\"tcCantidad\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert tcPrecio != null : "fx:id=\"tcPrecio\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert bAceptar != null : "fx:id=\"bAceptar\" was not injected: check your FXML file 'ModPedido.fxml'.";
        assert bCancelar != null : "fx:id=\"bCancelar\" was not injected: check your FXML file 'ModPedido.fxml'.";

    }
}
