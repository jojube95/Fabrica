package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import com.joan.fabrica.modelo.Pan;
import com.joan.fabrica.modelo.Panes;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;

public class AnyadirPanController {
	//Instancia del controller que abre esto
	private ModPedidoController modPedidoController;
	//Pan de esta clase
	private Panes panes = null;
	
	private Pan pan = null;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<Pan> tvPanes;

    @FXML
    private TableColumn<Pan, String> tcNombre;

    @FXML
    private TableColumn<Pan, String> tcTipo;

    @FXML
    private TableColumn<Pan, Float> tcPrecio;

    @FXML
    private Button bAceptar;

    @FXML
    private Button bCancelar;

    @FXML
    void abrirAceptar(ActionEvent event) {
    	int cantidad = 0;
    	boolean numero = false;
    	while(!numero){
    		TextInputDialog dialog = new TextInputDialog("0");
    		dialog.setTitle("Introduce una cantidad");
        	dialog.setHeaderText("Cantidad:");
        	dialog.setContentText("Inserte una cantidad: ");

        	Optional<String> result = dialog.showAndWait();
        	if (result.isPresent()){
        	    try{
        	    	cantidad = Integer.parseInt(result.get());
        	    	numero = true;
        	    }catch(NumberFormatException e){
        	    	numero = false;
        	    }
        	}
    	}
    	 
    	this.panes = new Panes(0, this.pan, cantidad);
		modPedidoController.getPanesPedido().add(this.panes);
		modPedidoController.actualizarPanes();
		this.panes = null;
    	this.modPedidoController.stageAnyadirPan.close();
    	
    }

    @FXML
    void abrirCancelar(ActionEvent event) {

    }

    @FXML
    void initialize() {
        //Inicializar la tabla
    	//Inicializar tablas
    	tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
    	tcTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTipo()));
    	tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
    	
    	tvPanes.setItems(FXCollections.observableArrayList(modPedidoController.getPedidosController().getPrincipalController().getFabrica().getCatalogo()));
    	
    	//Listener seleccionado
    	tvPanes.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    	    this.pan = newSelection;
    	});
    }

	public ModPedidoController getModPedidoController() {
		return modPedidoController;
	}

	public void setModPedidoController(ModPedidoController modPedidoController) {
		this.modPedidoController = modPedidoController;
	}

	public Pan getPan() {
		return pan;
	}

	public void setPan(Pan pan) {
		this.pan = pan;
	}

	public Panes getPanes() {
		return panes;
	}

	public void setPanes(Panes panes) {
		this.panes = panes;
	}
}
