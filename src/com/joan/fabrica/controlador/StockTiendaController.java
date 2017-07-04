package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Panes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockTiendaController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tBuscar;

    @FXML
    private TableView<Panes> tvStock;

    @FXML
    private TableColumn<Panes, Integer> tcId;

    @FXML
    private TableColumn<Panes, String> tcNombre;

    @FXML
    private TableColumn<Panes, String> tcTipo;

    @FXML
    private TableColumn<Panes, Integer> tcCantidad;

    @FXML
    private TableColumn<Panes, Float> tcPrecio;

    @FXML
    private Button bNuevo;

    @FXML
    private Button bEditar;

    @FXML
    private Button bEliminar;

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
        assert tBuscar != null : "fx:id=\"tBuscar\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert tvStock != null : "fx:id=\"tvStock\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert tcId != null : "fx:id=\"tcId\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert tcNombre != null : "fx:id=\"tcNombre\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert tcTipo != null : "fx:id=\"tcTipo\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert tcCantidad != null : "fx:id=\"tcCantidad\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert tcPrecio != null : "fx:id=\"tcPrecio\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert bNuevo != null : "fx:id=\"bNuevo\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert bEditar != null : "fx:id=\"bEditar\" was not injected: check your FXML file 'StockTienda.fxml'.";
        assert bEliminar != null : "fx:id=\"bEliminar\" was not injected: check your FXML file 'StockTienda.fxml'.";

    }
}
