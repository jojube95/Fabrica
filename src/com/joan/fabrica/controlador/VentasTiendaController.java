package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Venta;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class VentasTiendaController {

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
    private TableView<Venta> tvVentas;

    @FXML
    private TableColumn<Venta, Integer> tcIdVenta;

    @FXML
    private TableColumn<Venta, String> tcCliente;

    @FXML
    private TableColumn<Venta, Date> tcFecha;

    @FXML
    private TableColumn<Venta, Float> tcPrecioVenta;

    @FXML
    private TableView<Panes> tvPanes;

    @FXML
    private TableColumn<Panes, Integer> tcIdPan;

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
        assert tBuscar != null : "fx:id=\"tBuscar\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert bNuevo != null : "fx:id=\"bNuevo\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert bEditar != null : "fx:id=\"bEditar\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert bEliminar != null : "fx:id=\"bEliminar\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tvVentas != null : "fx:id=\"tvVentas\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcIdVenta != null : "fx:id=\"tcIdVenta\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcCliente != null : "fx:id=\"tcCliente\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcFecha != null : "fx:id=\"tcFecha\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcPrecioVenta != null : "fx:id=\"tcPrecioVenta\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tvPanes != null : "fx:id=\"tvPanes\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcIdPan != null : "fx:id=\"tcIdPan\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcNombre != null : "fx:id=\"tcNombre\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcTipo != null : "fx:id=\"tcTipo\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcCantidad != null : "fx:id=\"tcCantidad\" was not injected: check your FXML file 'VentasTienda.fxml'.";
        assert tcPrecioPan != null : "fx:id=\"tcPrecioPan\" was not injected: check your FXML file 'VentasTienda.fxml'.";

    }
}
