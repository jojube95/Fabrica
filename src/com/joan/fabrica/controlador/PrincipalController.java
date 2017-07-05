package com.joan.fabrica.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.joan.fabrica.modelo.Fabrica;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PrincipalController {
	//Controladores siguientes
	private static CatalogoController catalogoController;
	private static PedidosController pedidosController;
	private static TiendasController tiendasController;
	//Controladores anteriores, en este caso la clase Fabrica
	private static Fabrica fabrica;
	//Los stages que abre
	public static Stage stageCatalogo =new Stage();
	public static Stage stagePedidos = new Stage();
	public static Stage stageTiendas = new Stage();
	
	@FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button bTiendas;

    @FXML
    private Button bPedidos;

    @FXML
    private Button bCatalogo;

    @FXML
    void abrirCatalogo(ActionEvent event) {
    	stageCatalogo.showAndWait();
    }

    @FXML
    void abrirPedidos(ActionEvent event) {
    	stagePedidos.showAndWait();
    }

    @FXML
    void abrirTiendas(ActionEvent event) {
    	stageTiendas.showAndWait();        
    }

    @FXML
    void initialize() {
        assert bTiendas != null : "fx:id=\"bTiendas\" was not injected: check your FXML file 'Principal.fxml'.";
        assert bPedidos != null : "fx:id=\"bPedidos\" was not injected: check your FXML file 'Principal.fxml'.";
        assert bCatalogo != null : "fx:id=\"bCatalogo\" was not injected: check your FXML file 'Principal.fxml'.";
        
        FXMLLoader loaderCatalogo = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/Catalogo.fxml"));
        FXMLLoader loaderPedidos = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/Pedidos.fxml"));
        FXMLLoader loaderTiendas = new FXMLLoader(getClass().getResource("/com/joan/fabrica/vista/Tiendas.fxml"));
        Scene scene = null;
		AnchorPane root = null;
        
	
			
		
		try {
			root = (AnchorPane) loaderCatalogo.load();
			stageCatalogo.initModality(Modality.WINDOW_MODAL);
			stageCatalogo.initOwner(Fabrica.primaryStage);
			stageCatalogo.setTitle("Catálogo");
			scene = new Scene(root);
		    stageCatalogo.setScene(scene);
		    this.catalogoController = loaderCatalogo.getController();
		    catalogoController.setPrincipalController(this);
		    
		    root = (AnchorPane) loaderPedidos.load();
			stagePedidos.initModality(Modality.WINDOW_MODAL);
			stagePedidos.initOwner(Fabrica.primaryStage);
			stagePedidos.setTitle("Pedidos");
	        scene = new Scene(root);
			stagePedidos.setScene(scene);
	        this.pedidosController = loaderPedidos.getController();
	        catalogoController.setPrincipalController(this);
		       
	        
			root = (AnchorPane) loaderTiendas.load();
			stageTiendas.initModality(Modality.WINDOW_MODAL);
			stageTiendas.initOwner(Fabrica.primaryStage);
			stageTiendas.setTitle("Tiendas");
	        scene = new Scene(root);
			stageTiendas.setScene(scene);
	        this.tiendasController = loaderTiendas.getController();
	        catalogoController.setPrincipalController(this);
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		    
    }

	public static Fabrica getFabrica() {
		return fabrica;
	}

	public static void setFabrica(Fabrica fabrica) {
		PrincipalController.fabrica = fabrica;
	}
}