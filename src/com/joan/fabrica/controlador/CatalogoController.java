package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.joan.fabrica.modelo.Pan;
import com.joan.fabrica.vista.CatalogoDialog;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CatalogoController {
	//Instancia del PrincipalControler que es el que abre esto
	private static PrincipalController principalController;
		
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
    	Pan pan = tvPanes.getSelectionModel().getSelectedItem();
    	CatalogoDialog catalogoDialog = new CatalogoDialog(pan);
    	int id = catalogoDialog.getResultado().getId();
    	ArrayList<Pan> panes = principalController.getFabrica().getCatalogo();
    	for(int i = 0; i < panes.size(); i++){
    		if(panes.get(i).getId()==id){
    			principalController.getFabrica().getCatalogo().set(i, catalogoDialog.getResultado());
    	    	actualizarPanes();
    	    	break;
    		}
    	}
    	
    }

    @FXML
    void abrirEliminar(ActionEvent event) {
    	Pan pan = tvPanes.getSelectionModel().getSelectedItem();
    	principalController.getFabrica().getCatalogo().remove(pan);
    	actualizarPanes();
    }

    @FXML
    void abrirNuevo(ActionEvent event) {
    	CatalogoDialog catalogoDialog = new CatalogoDialog();
    	principalController.getFabrica().getCatalogo().add(catalogoDialog.getResultado());
    	actualizarPanes();
    }
    
    private void actualizarPanes(){
    	tvPanes.setItems(FXCollections.observableArrayList(principalController.getFabrica().getCatalogo()));
    }
    @FXML
    void initialize() {
    	tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
    	tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
    	tcTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTipo()));
    	tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
            	
    	tvPanes.setItems(FXCollections.observableArrayList(principalController.getFabrica().getCatalogo()));
    	
    	//Inicial el tema de la busqueda
    	FilteredList<Pan> filteredList = new FilteredList<>(FXCollections.observableArrayList(principalController.getFabrica().getCatalogo()), p -> true);
    	
    	tBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(pan -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (pan.getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (pan.getTipo().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if(String.valueOf(pan.getId()).contains(lowerCaseFilter)){
                	return true;
                }
                return false; // Does not match.
            });
        });
    	
    	SortedList<Pan> sortedData = new SortedList<>(filteredList);
    	
    	sortedData.comparatorProperty().bind(tvPanes.comparatorProperty());
    	
    	tvPanes.setItems(sortedData);
    }

	public static PrincipalController getPrincipalController() {
		return principalController;
	}

	public static void setPrincipalController(PrincipalController principalController) {
		CatalogoController.principalController = principalController;
	}
}
