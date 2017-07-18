package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Cliente;
import com.joan.fabrica.modelo.Tienda;
import com.joan.fabrica.vista.ClienteDialog;
import com.mysql.fabric.xmlrpc.base.Array;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ClientesTiendaController {
	//Instancia del TiendasController que es el que abre esto
	private static TiendasController tiendasController;
	
	//Tienda seleccionada anteriormente
	private Tienda tienda;
	
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
    private TableColumn<Cliente, String> tcFecha;

    @FXML
    private TableColumn<Cliente, String> tcUsuario;

    @FXML
    private TableColumn<Cliente, String> tcContra;

    @FXML
    void abirrEditar(ActionEvent event) {
    	int index = tvClientes.getSelectionModel().getSelectedIndex();
    	ClienteDialog clienteDialog = new ClienteDialog(tvClientes.getSelectionModel().getSelectedItem());
    	tiendasController.getPrincipalController().getFabrica().getClientesTienda().get(this.tienda).set(index, clienteDialog.getResultado());
    	actualizarClientes();
    }

    @FXML
    void abrirEliminar(ActionEvent event) {
    	Cliente cliente = tvClientes.getSelectionModel().getSelectedItem();
    	tiendasController.getPrincipalController().getFabrica().getClientesTienda().get(this.tienda).remove(cliente);
    	actualizarClientes();
    	
    }

    @FXML
    void abrirNuevo(ActionEvent event) {
    	ClienteDialog clienteDialog = new ClienteDialog();
    	tiendasController.getPrincipalController().getFabrica().getClientesTienda().get(this.tienda).add(clienteDialog.getResultado());
    	actualizarClientes();
    	
    }
    
    public void actualizarClientes(){
    	tvClientes.setItems(FXCollections.observableArrayList(tiendasController.getPrincipalController().getFabrica().getClientesTienda().get(this.tienda)));
    }

    @FXML
    void initialize() {
    	tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
    	tcLocalidad.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getLocalidad()));
    	tcFecha.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFechaNac().toString()));
    	tcUsuario.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getUsuario()));
    	tcContra.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPass()));
        
    	this.tienda = this.tiendasController.getTiendaSeleccionada();
    	tvClientes.setItems(FXCollections.observableArrayList(tiendasController.getPrincipalController().getFabrica().getClientesTienda().get(this.tienda)));
    	
    	tvClientes.getSelectionModel().selectFirst();
    	
    	//Lo de la busqueda padre
    	FilteredList<Cliente> filteredList = new FilteredList<>(FXCollections.observableArrayList(tiendasController.getPrincipalController().getFabrica().getClientesTienda().get(this.tienda)), c -> true);
    	
    	tBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(cliente -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(cliente.getNombre()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }else if (cliente.getLocalidad().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if(cliente.getUsuario().toLowerCase().contains(lowerCaseFilter)){
                	return true;
                }
                return false; // Does not match.
            });
        });
    	
    	SortedList<Cliente> sortedData = new SortedList<>(filteredList);
    	
    	sortedData.comparatorProperty().bind(tvClientes.comparatorProperty());
    	
    	tvClientes.setItems(sortedData);
    }

	public static TiendasController getTiendasController() {
		return tiendasController;
	}

	public static void setTiendasController(TiendasController tiendasController) {
		ClientesTiendaController.tiendasController = tiendasController;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
}
