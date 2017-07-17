package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PedidosController {
	public static Stage stageModPedido = new Stage();
	//Instancia del PrincipalControler que es el que abre esto
	private static PrincipalController principalController;
	//Instancia del TiendasController que es el otro que abre esto
	private static TiendasController tiendasController;
	//Instancia del controlador que abre
	private static ModPedidoController modPedidoController;
	
		
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
    private TableColumn<Pedido, String> tcFecha;

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
    	this.modPedidoController.setPanesPedido(tvPedido.getSelectionModel().getSelectedItem().getPanes());
    	this.modPedidoController.setPedido(tvPedido.getSelectionModel().getSelectedItem());
    	stageModPedido.showAndWait();
    	/*for(int i = 0; i < this.principalController.getFabrica().getPedidos().size(); i++){
    		if(this.principalController.getFabrica().getPedidos().get(i).getId() == this.pedido.getId()){
    			this.principalController.getFabrica().getPedidos().set(i, this.pedido);
    			break;
    		}
    	}
    	actualizarPedidos();*/
    	
	}

    @FXML
    void abrirEliminar(ActionEvent event) {
    	Pedido pedido = tvPedido.getSelectionModel().getSelectedItem();
    	principalController.getFabrica().getPedidos().remove(pedido);
    	actualizarPedidos();
    }

    @FXML
    void abrirNuevo(ActionEvent event) {
    	modPedidoController.nuevo = true;
    	stageModPedido.showAndWait();
    	//principalController.getFabrica().getPedidos().add(this.pedido);
    	//actualizarPedidos();
    	
    }
    
    public void actualizarPedidos(){
    	tvPedido.setItems(FXCollections.observableArrayList(PrincipalController.getFabrica().getPedidos()));
    }
    @FXML
    void initialize() {
    	tvPedido.getSelectionModel().clearSelection();
    	tvPanes.getSelectionModel().clearSelection();
    	
    	//Inicializar tablas
    	tcId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
    	tcTienda.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTienda().getNombre()));
    	tcFecha.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFecha().toString()));
    	tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecioTotal()).asObject());
    	
    	tvPedido.setItems(FXCollections.observableArrayList(principalController.getFabrica().getPedidos()));
    	
    	tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPan().getNombre()));
    	tcTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPan().getTipo()));
    	tcCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCant()).asObject());
    	tcPrecioPan.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
            	
    	tvPanes.setItems(FXCollections.observableArrayList(principalController.getFabrica().getPanes()));
    	
    	//Listeners de las tablas
    	tvPedido.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    	    if(newSelection!= null){
    	    	actualizarPanes(newSelection);
    	    }
    		
    	    
    		
    	});
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/ModPedido.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
	        Scene scene = new Scene(root);
	        stageModPedido.initModality(Modality.WINDOW_MODAL);
			stageModPedido.initOwner(TiendasController.pedidosStage);
	        stageModPedido.setTitle("Modificar pedido");
	        //primaryStage.getIcons().add(new Image("file:Icono/icono.png"));
	        stageModPedido.setScene(scene);
	        this.modPedidoController = loader.getController();
	        modPedidoController.setPedidosController(this);
        } catch(Exception e) {
			e.printStackTrace();
		}
    	
    	//Inicial el tema de la busqueda
    	FilteredList<Pedido> filteredList = new FilteredList<>(FXCollections.observableArrayList(principalController.getFabrica().getPedidos()), p -> true);
    	
    	tBuscar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredList.setPredicate(pedido -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every person with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (String.valueOf(pedido.getId()).toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches first name.
                } else if (pedido.getTienda().getNombre().toLowerCase().contains(lowerCaseFilter)) {
                    return true; // Filter matches last name.
                } else if(String.valueOf(pedido.getFecha().toString()).contains(lowerCaseFilter)){
                	return true;
                }
                return false; // Does not match.
            });
        });
    	
    	SortedList<Pedido> sortedData = new SortedList<>(filteredList);
    	
    	sortedData.comparatorProperty().bind(tvPedido.comparatorProperty());
    	
    	tvPedido.setItems(sortedData);

    }
    
    private void actualizarPanes(Pedido pedido){
    	tvPanes.setItems(FXCollections.observableArrayList(pedido.getPanes()));
    }

	public static PrincipalController getPrincipalController() {
		return principalController;
	}

	public static void setPrincipalController(PrincipalController principalController) {
		PedidosController.principalController = principalController;
	}

	public static TiendasController getTiendasController() {
		return tiendasController;
	}

	public static void setTiendasController(TiendasController tiendasController) {
		PedidosController.tiendasController = tiendasController;
	}

	public static ModPedidoController getModPedidoController() {
		return modPedidoController;
	}

	public static void setModPedidoController(ModPedidoController modPedidoController) {
		PedidosController.modPedidoController = modPedidoController;
	}
}
