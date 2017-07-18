package com.joan.fabrica.controlador;


import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;
import com.joan.fabrica.modelo.Tienda;
import com.joan.fabrica.persistencia.PedidoDAO;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ModPedidoController {
	//Instancia del controlador que abre esto
	private static PedidosController pedidosController;
	//Crear pedido por defecto
	public Pedido pedido;
	private Tienda tiendaSeleccionada = null;
	
	
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label lPedido;

    @FXML
    private DatePicker tFecha;

    @FXML
    private ChoiceBox<Tienda> mTienda;

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
    private Button bAnyadir;

    @FXML
    private Button bEditar;

    @FXML
    private Button bEliminar;

    @FXML
    void abrirAceptar(ActionEvent event) {
		this.pedidosController.pedido = new Pedido();
    	Tienda tienda = new Tienda(0, "", "", "");
    	tienda = tiendaSeleccionada;
    	this.pedido.setTienda(tienda);
    	this.pedido.setId(new PedidoDAO().getLastGenerated());
    	this.pedido.setPrecioTotal();
    	this.pedido.setFecha(Date.valueOf(tFecha.getValue()));
    	
    	this.pedidosController.pedido = this.pedido;
    	this.pedidosController.getPrincipalController().getFabrica().getPedidos().add(this.pedidosController.pedido);
    	this.pedidosController.actualizarPedidos();
    	this.pedidosController.pedido = new Pedido();
    	this.pedidosController.stageModPedido.close();
    	this.pedidosController.pedido = new Pedido();
    	initialize();
    	
    	
    	
    	
    }

    @FXML
    void abrirCancelar(ActionEvent event) {
    	
    }
    
    @FXML
    void abrirAnyadir(ActionEvent event) {
    	StockDialog stockDialog = new StockDialog(this.pedidosController.getPrincipalController().getFabrica().getCatalogo());
    	this.pedido.getPanes().add(stockDialog.getResultado());
    	actualizarPanes();
    }
    
    @FXML
    void abrirEditar(ActionEvent event) {
    	StockDialog stockDialog = new StockDialog(tvPanes.getSelectionModel().getSelectedItem(), this.pedidosController.getPrincipalController().getFabrica().getCatalogo());
    	for(int i = 0; i < this.pedido.getPanes().size(); i++){
    		if(this.pedido.getPanes().get(i).getPan().getNombre().equals(stockDialog.getResultado().getPan().getNombre())){
    			this.pedido.getPanes().set(i, stockDialog.getResultado());
    		}
    	}
    	actualizarPanes();
    }
    
    @FXML
    void abrirEliminar(ActionEvent event) {
    	this.pedido.getPanes().remove(tvPanes.getSelectionModel().getSelectedItem());
    	actualizarPanes();
    }
    
    public void actualizarPanes(){
    	tvPanes.setItems(FXCollections.observableArrayList(this.pedido.getPanes()));
    }
    
        

    @FXML
    void initialize() {
    	this.pedido = new Pedido();
    	
    	//Inicializar el menuButton de las tiendas
    	for(int i = 0; i < pedidosController.getPrincipalController().getFabrica().getTiendas().size(); i++){
    	    mTienda.getItems().add(pedidosController.getPrincipalController().getFabrica().getTiendas().get(i));
	    }
    	//Listener del choiceBox
    	mTienda.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tienda>() {

			@Override
			public void changed(ObservableValue<? extends Tienda> observable, Tienda oldValue, Tienda newValue) {
				pedidosController.getModPedidoController().tiendaSeleccionada = newValue;
				
			}
		});
    	
		//Inicializar el tableView de los panes del pedido a modificar o nuevo
		//Inicializar tablas
		tcPan.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getPan().getNombre()));
    	tcCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCant()).asObject());
    	tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
    	
    	tvPanes.setItems(FXCollections.observableArrayList(this.pedido.getPanes()));
    	
    }

	public static PedidosController getPedidosController() {
		return pedidosController;
	}

	public static void setPedidosController(PedidosController pedidosController) {
		ModPedidoController.pedidosController = pedidosController;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public Tienda getTiendaSeleccionada() {
		return tiendaSeleccionada;
	}

	public void setTiendaSeleccionada(Tienda tiendaSeleccionada) {
		this.tiendaSeleccionada = tiendaSeleccionada;
	}
}
