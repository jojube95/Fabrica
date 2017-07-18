package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Cliente;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Tienda;
import com.joan.fabrica.vista.StockDialog;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class StockTiendaController {
	//Instancia del TiendasController que es el que abre esto
	private static TiendasController tiendasController;
	
	//Tienda anterior
	private Tienda tienda;
	
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
    	int index = tvStock.getSelectionModel().getSelectedIndex();
    	//yo k se
    	StockDialog stockDialog = new StockDialog(tvStock.getSelectionModel().getSelectedItem(), this.tiendasController.getPrincipalController().getFabrica().getCatalogo());
    	this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).set(index, stockDialog.getResultado());
    	actualizarStockTienda();
    }

    @FXML
    void abrirEliminar(ActionEvent event) {
    	this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).remove(tvStock.getSelectionModel().getSelectedItem());
    	actualizarStockTienda();
    }

    @FXML
    void abrirNuevo(ActionEvent event) {
    	StockDialog stockDialog = new StockDialog(this.tiendasController.getPrincipalController().getFabrica().getCatalogo());
    	//Comprobar si el nuevo panes existe en el stock de la tienda
    	if(comprobar(stockDialog.getResultado())){
    		for(int i = 0; i < this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).size(); i++){
    			if(this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).get(i).getPan().getNombre().equals(stockDialog.getResultado().getPan().getNombre())){
    				this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).get(i).sumarPanes(stockDialog.getResultado());
    				break;
    			}
    		}
    		reordenarLista();
    		actualizarStockTienda();
    		
    	}
    	else{
    		this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).add(stockDialog.getResultado());
    		reordenarLista();
        	actualizarStockTienda();
    	}
    	
    	
    }
    
    public boolean comprobar(Panes panes){
    	
    	if(this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).contains(panes)){return true;}
    	
    	
    	
    	return false;
    }
    
    public void reordenarLista(){
    	for(int i = 0; i < this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).size(); i++){
    		for(int j = 0; j < this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).size(); j++){
    			if(i!=j){
    				if(this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).get(i).getPan().getNombre().equals(this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).get(j).getPan().getNombre())){
        				this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).get(i).sumarPanes(this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).get(j));
        				this.tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda).remove(j);
        			}
    			}
    			
    		}
    	}
    	actualizarStockTienda();
    }
    @FXML
    void initialize() {
    	this.tienda = this.tiendasController.getTiendaSeleccionada();
    	
        //Inicializar tabla
    	tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdPanesTienda()).asObject());
    	tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPan().getNombre()));
    	tcTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPan().getTipo()));
    	tcCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCant()).asObject());
    	tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
    	
    	tvStock.setItems(FXCollections.observableArrayList(tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda)));
    	
    	tvStock.getSelectionModel().selectFirst();
    	
    	//Listener a cambios tvStock
    	tvStock.getItems().addListener(new ListChangeListener<Panes>() {
    	    @Override
			public void onChanged(javafx.collections.ListChangeListener.Change<? extends Panes> c) {
				actualizarStockTienda();
				
			}});
    	//Lo de la busqueda padre
    	FilteredList<Panes> filteredList = new FilteredList<>(FXCollections.observableArrayList(tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda)), p -> true);
    	
    	
    	tBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(panes -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(panes.getIdPanesTienda()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                }else if (panes.getPan().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                }else if(panes.getPan().getTipo().toLowerCase().contains(lowerCaseFilter)){
                	return true;
                }
                return false; // Does not match.
            });
        });
    	
    	SortedList<Panes> sortedData = new SortedList<>(filteredList);
    	
    	sortedData.comparatorProperty().bind(tvStock.comparatorProperty());
    	
    	tvStock.setItems(sortedData);
    }
    
    public void actualizarStockTienda(){
    	tvStock.setItems(FXCollections.observableArrayList(tiendasController.getPrincipalController().getFabrica().getPanesTienda().get(this.tienda)));
    }

	public static TiendasController getTiendasController() {
		return tiendasController;
	}

	public static void setTiendasController(TiendasController tiendasController) {
		StockTiendaController.tiendasController = tiendasController;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}
}
