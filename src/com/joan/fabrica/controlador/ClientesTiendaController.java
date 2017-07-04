package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientesTiendaController {
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
    private TableView<Cliente> tvClientes;

    @FXML
    private TableColumn<Cliente, String> tcNombre;

    @FXML
    private TableColumn<Cliente, String> tcLocalidad;

    @FXML
    private TableColumn<Cliente, Date> tcFecha;

    @FXML
    private TableColumn<Cliente, String> tcUsuario;

    @FXML
    private TableColumn<Cliente, String> tcContra;

    @FXML
    void abirrEditar(ActionEvent event) {

    }

    @FXML
    void abrirEliminar(ActionEvent event) {

    }

    @FXML
    void abrirNuevo(ActionEvent event) {

    }

    @FXML
    void initialize() {
        assert tBuscar != null : "fx:id=\"tBuscar\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert bNuevo != null : "fx:id=\"bNuevo\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert bEditar != null : "fx:id=\"bEditar\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert bEliminar != null : "fx:id=\"bEliminar\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert tvClientes != null : "fx:id=\"tvClientes\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert tcNombre != null : "fx:id=\"tcNombre\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert tcLocalidad != null : "fx:id=\"tcLocalidad\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert tcFecha != null : "fx:id=\"tcFecha\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert tcUsuario != null : "fx:id=\"tcUsuario\" was not injected: check your FXML file 'ClientesTienda.fxml'.";
        assert tcContra != null : "fx:id=\"tcContra\" was not injected: check your FXML file 'ClientesTienda.fxml'.";

    }
}
