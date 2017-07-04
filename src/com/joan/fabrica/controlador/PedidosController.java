package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class PedidosController {
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

    }
}
