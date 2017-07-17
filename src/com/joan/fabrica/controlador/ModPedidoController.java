package com.joan.fabrica.controlador;


import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.modelo.Pedido;
import com.joan.fabrica.modelo.Tienda;
import com.joan.fabrica.persistencia.PedidoDAO;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModPedidoController {
	public static Stage stageAnyadirPan = new Stage();
	//Instancia del controlador que abre esto
	private static PedidosController pedidosController;
	
	//Instancia del controlador que se abre
	private static AnyadirPanController anyadirPanController;
	
	//Crear pedido por defecto
	private Pedido pedido = new Pedido();
	private Tienda tiendaSeleccionada = null;
	private ArrayList<Panes> panesPedido = new ArrayList<>();
	public boolean nuevo = false;
	//Panes que se crea o modifica en anyadirPanController
	private Panes panes = null;
	
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
    	//Comprobar si se viende de nuevo o de editar
    	if(nuevo = true){//Viende de nuevo
    		List<Panes> panes = tvPanes.getItems();
    		PedidoDAO pedidoDAO = new PedidoDAO();
    		int id = pedidoDAO.getLastGenerated();
    		this.pedido = new Pedido(id, Date.valueOf(tFecha.getValue()), tiendaSeleccionada, new ArrayList<Panes>(panes));
    		this.pedidosController.getPrincipalController().getFabrica().getPedidos().add(this.pedido);
    		this.pedidosController.getPrincipalController().getFabrica().getPedidosTienda().get(this.pedido.getTienda()).add(this.pedido);
    		this.pedidosController.actualizarPedidos();
    		
    	}else{//Viene de editar
    		for(int i = 0; i < this.pedidosController.getPrincipalController().getFabrica().getPedidos().size(); i++){
        		if(this.pedidosController.getPrincipalController().getFabrica().getPedidos().get(i).getId() == this.pedido.getId()){
        			this.pedidosController.getPrincipalController().getFabrica().getPedidos().set(i, this.pedido);
        			break;
        		}
        	}
    		this.pedidosController.actualizarPedidos();
    	}
    	nuevo = false;
    	this.pedidosController.stageModPedido.close();
    	
    	
    	//Resetear valores para la proxima
    	this.panesPedido = new ArrayList<>();
    	actualizarPanes();
    	this.nuevo = false;
    	tFecha.getEditor().clear();
    	
    	
    }

    @FXML
    void abrirCancelar(ActionEvent event) {
    	this.pedidosController.stageModPedido.close();
    }
    
    @FXML
    void abrirAnyadir(ActionEvent event) {
    	stageAnyadirPan.showAndWait();
    	this.pedido.getPanes().add(this.panes);
    }
    
    @FXML
    void abrirEditar(ActionEvent event) {
    	//Catalogo dialogo
    }
    
    @FXML
    void abrirEliminar(ActionEvent event) {
    	
    }
    
    public void actualizarPanes(){
    	tvPanes.setItems(FXCollections.observableArrayList(this.panesPedido));
    }
    

    @FXML
    void initialize() {
    	this.pedido = new Pedido();
    	this.panesPedido = new ArrayList<>();
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
    	
    	//Inicializa el stage siguiente
    	
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/AnyadirPan.fxml"));
	        AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);
	        stageAnyadirPan.initModality(Modality.WINDOW_MODAL);
			stageAnyadirPan.initOwner(TiendasController.pedidosStage);
	        stageAnyadirPan.setTitle("Panes");
	        //primaryStage.getIcons().add(new Image("file:Icono/icono.png"));
	        stageAnyadirPan.setScene(scene);
	        this.anyadirPanController = loader.getController();
	        anyadirPanController.setModPedidoController(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//Inicializar el tableView de los panes del pedido a modificar o nuevo
		//Inicializar tablas
		tcPan.setCellValueFactory(cellData ->  new ReadOnlyStringWrapper(cellData.getValue().getPan().getNombre()));
    	tcCantidad.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getCant()).asObject());
    	tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
    	
    	tvPanes.setItems(FXCollections.observableArrayList(this.panesPedido));
        
    	
    	
    	

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

	public static AnyadirPanController getAnyadirPanController() {
		return anyadirPanController;
	}

	public static void setAnyadirPanController(AnyadirPanController anyadirPanController) {
		ModPedidoController.anyadirPanController = anyadirPanController;
	}
	
	public Panes getPanes() {
		return panes;
	}

	public void setPanes(Panes panes) {
		this.panes = panes;
	}

	public ArrayList<Panes> getPanesPedido() {
		return panesPedido;
	}

	public void setPanesPedido(ArrayList<Panes> panesPedido) {
		this.panesPedido = panesPedido;
	}

	public Tienda getTiendaSeleccionada() {
		return tiendaSeleccionada;
	}

	public void setTiendaSeleccionada(Tienda tiendaSeleccionada) {
		this.tiendaSeleccionada = tiendaSeleccionada;
	}
}
