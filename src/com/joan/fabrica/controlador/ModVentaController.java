package com.joan.fabrica.controlador;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Cliente;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;
import com.joan.fabrica.modelo.Tienda;
import com.joan.fabrica.modelo.Venta;
import com.joan.fabrica.persistencia.PedidoDAO;
import com.joan.fabrica.persistencia.VentaDAO;
import com.joan.fabrica.vista.StockDialog;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class ModVentaController {
	//Instancia del controller que abre esto
	private static VentasTiendaController ventasTiendaController;
	//Venta
	public Venta venta;
	//Cliente seleccionado
	public Cliente cliente = new Cliente("", 0, "", new Date(10), true);
	
	@FXML
    private Button mAnyadir;

    @FXML
    private Button bEditar;

    @FXML
    private Button bEliminar;
    
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lVenta;

    @FXML
    private DatePicker tFecha;

    @FXML
    private ChoiceBox<Cliente> mCliente;

    @FXML
    private TableView<Panes> tvPanes;

    @FXML
    private TableColumn<Panes, String> tcPan;

    @FXML
    private TableColumn<Panes, Integer> tcCantidad;
    
    @FXML
    private TableColumn<Panes, Float> tcPrecio;
    
    @FXML
    private Button bAceptar;

    @FXML
    private Button bCancelar;

    @FXML
    void abrirAceptar(ActionEvent event) {
    	this.ventasTiendaController.venta = new Venta();
    	Tienda tienda = new Tienda(0, "", "", "");
    	tienda = this.ventasTiendaController.getTiendasController().getTiendaSeleccionada();
    	this.venta.setId(new VentaDAO().getLastGenerated());
    	this.venta.setCliente(this.cliente);
    	this.venta.setFecha(Date.valueOf(tFecha.getValue()));
    	this.venta.setTienda(tienda);
    	this.venta.setPrecio();
    	
    	this.ventasTiendaController.venta = this.venta;
    	this.ventasTiendaController.getTiendasController().getPrincipalController().getFabrica().getVentasTienda().get(tienda).add(this.ventasTiendaController.venta);
    	this.ventasTiendaController.actualizarVentas();
    	this.ventasTiendaController.venta = new Venta();
    	this.ventasTiendaController.modVentaStage.close();
    	this.ventasTiendaController.venta = new Venta();
    	initialize();
    }

    @FXML
    void abrirCancelar(ActionEvent event) {

    }
    
    @FXML
    void abrirAnyadir(ActionEvent event) {
    	StockDialog stockDialog = new StockDialog(this.ventasTiendaController.getTiendasController().getPrincipalController().getFabrica().getCatalogo());
    	this.venta.getPanes().add(stockDialog.getResultado());
    	actualizarPanes();
    }
    
    @FXML
    void abrirEditar(ActionEvent event) {

    }
    
    @FXML
    void abrirEliminar(ActionEvent event) {
    	this.venta.getPanes().remove(tvPanes.getSelectionModel().getSelectedItem());
    	actualizarPanes();
    }
    
    public void actualizarPanes(){
    	tvPanes.setItems(FXCollections.observableArrayList(this.venta.getPanes()));
    }
    
    @FXML
    void initialize() {
    	this.venta = new Venta();
    	
    	//Inicializar el menuButton de las tiendas
    	for(int i = 0; i < this.ventasTiendaController.getTiendasController().getPrincipalController().getFabrica().getClientesTienda().get(this.ventasTiendaController.getTiendasController().getTiendaSeleccionada()).size(); i++){
    	    mCliente.getItems().add(this.ventasTiendaController.getTiendasController().getPrincipalController().getFabrica().getClientesTienda().get(this.ventasTiendaController.getTiendasController().getTiendaSeleccionada()).get(i));
	    }
    	//Listener del choiceBox
    	mCliente.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Cliente>() {

			@Override
			public void changed(ObservableValue<? extends Cliente> observable, Cliente oldValue, Cliente newValue) {
				ventasTiendaController.modVentaController.cliente = newValue;
				
			}
		});
    	
		//Inicializar el tableView de los panes del pedido a modificar o nuevo
		//Inicializar tablas
		tcPan.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getPan().getNombre()));
    	tcCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCant()).asObject());
    	tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
    	
    	tvPanes.setItems(FXCollections.observableArrayList(this.venta.getPanes()));

    }

	public static VentasTiendaController getVentasTiendaController() {
		return ventasTiendaController;
	}

	public static void setVentasTiendaController(VentasTiendaController ventasTiendaController) {
		ModVentaController.ventasTiendaController = ventasTiendaController;
	}
}
