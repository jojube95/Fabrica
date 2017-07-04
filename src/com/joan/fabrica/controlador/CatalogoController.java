package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Pan;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CatalogoController {
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField tBuscar;

    @FXML
    private TableView<Pan> tvPanes;

    @FXML
    private TableColumn<Pan, Integer> tcId;

    @FXML
    private TableColumn<Pan, String> tcNombre;

    @FXML
    private TableColumn<Pan, String> tcTipo;

    @FXML
    private TableColumn<Pan, Float> tcPrecio;

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
        assert tBuscar != null : "fx:id=\"tBuscar\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert tvPanes != null : "fx:id=\"tvPanes\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert tcId != null : "fx:id=\"tcId\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert tcNombre != null : "fx:id=\"tcNombre\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert tcTipo != null : "fx:id=\"tcTipo\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert tcPrecio != null : "fx:id=\"tcPrecio\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert bNuevo != null : "fx:id=\"bNuevo\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert bEditar != null : "fx:id=\"bEditar\" was not injected: check your FXML file 'Catalogo.fxml'.";
        assert bEliminar != null : "fx:id=\"bEliminar\" was not injected: check your FXML file 'Catalogo.fxml'.";

    }
}
