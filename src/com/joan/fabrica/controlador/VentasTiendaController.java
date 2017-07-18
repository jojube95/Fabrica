package com.joan.fabrica.controlador;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Fabrica;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;
import com.joan.fabrica.modelo.Venta;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
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

public class VentasTiendaController {
	//Instancia del TiendasController que es el que abre esto
	private static TiendasController tiendasController;
	//Instancia del controler que abre
	public static ModVentaController modVentaController;
	//Stage
	public static Stage modVentaStage = new Stage();
	//Venta que se crea
	Venta venta = new Venta();
	
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
    private TableColumn<Venta, String> tcFecha;

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
    	/*try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/ModVenta.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
	        Scene scene = new Scene(root);
	        modVentaStage.initModality(Modality.WINDOW_MODAL);
			modVentaStage.initOwner(TiendasController.ventasStage);
	        modVentaStage.setTitle("Modificar venta");
	        //primaryStage.getIcons().add(new Image("file:Icono/icono.png"));
	        modVentaStage.setScene(scene);
	        this.modVentaController = loader.getController();
	        modVentaController.setVentasTiendaController(this);
	        modVentaStage.showAndWait();
		} catch(Exception e) {
			e.printStackTrace();
		}*/
    }

    @FXML
    void abrirEliminar(ActionEvent event) {
    	Venta venta = tvVentas.getSelectionModel().getSelectedItem();
    	this.tiendasController.getPrincipalController().getFabrica().getVentasTienda().get(this.tiendasController.getTiendaSeleccionada()).remove(venta);
    	actualizarVentas();
    }

    @FXML
    void abrirNuevo(ActionEvent event) {
    	modVentaStage.showAndWait();
    }
    
    public void actualizarVentas(){
    	tvVentas.setItems(FXCollections.observableArrayList(PrincipalController.getFabrica().getVentasTienda().get(this.tiendasController.getTiendaSeleccionada())));
    }
    
    private void actualizarVentas(Venta venta){
    	tvPanes.setItems(FXCollections.observableArrayList(venta.getPanes()));
    }

    @FXML
    void initialize() {
    	//Inicializar tabla ventas
    	tcIdVenta.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
    	tcCliente.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getCliente().getNombre()));
    	tcFecha.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getFecha().toString()));
    	tcPrecioVenta.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
        
    	tvVentas.setItems(FXCollections.observableArrayList(this.tiendasController.getPrincipalController().getFabrica().getVentasTienda().get(this.tiendasController.getTiendaSeleccionada())));
    	
    	tcIdPan.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getIdPanesTienda()).asObject());
    	tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPan().getNombre()));
    	tcTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getPan().getTipo()));
    	tcCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCant()).asObject());
    	tcPrecioPan.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
            	
    	//tvPanes.setItems(FXCollections.observableArrayList(tvVentas.getSelectionModel().getSelectedItem().getPanes()));
    	
    	//Listeners de las tablas
    	tvVentas.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    	    if(newSelection!= null){
    	    	actualizarVentas(newSelection);
    	    }
    	});
    	
    	//Inicializ el siguiente stage
    	try {
    		FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/ModVenta.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
	        Scene scene = new Scene(root);
	        modVentaStage.initModality(Modality.WINDOW_MODAL);
			modVentaStage.initOwner(TiendasController.ventasStage);
	        modVentaStage.setTitle("Modificar venta");
	        //primaryStage.getIcons().add(new Image("file:Icono/icono.png"));
	        modVentaStage.setScene(scene);
	        this.modVentaController = loader.getController();
	        modVentaController.setVentasTiendaController(this);
	        
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

	public static TiendasController getTiendasController() {
		return tiendasController;
	}

	public static void setTiendasController(TiendasController tiendasController) {
		VentasTiendaController.tiendasController = tiendasController;
	}
}
