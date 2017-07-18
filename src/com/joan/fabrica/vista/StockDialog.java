package com.joan.fabrica.vista;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Optional;
import com.joan.fabrica.modelo.Cliente;
import com.joan.fabrica.modelo.Fabrica;
import com.joan.fabrica.modelo.Pan;
import com.joan.fabrica.modelo.Panes;
import com.joan.fabrica.persistencia.ClienteDAO;
import com.joan.fabrica.persistencia.ConnectionManager;
import com.joan.fabrica.persistencia.PanDAO;
import com.joan.fabrica.persistencia.StockDAO;

import javafx.application.Platform;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Control;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class StockDialog{
	private Panes resultado = null;
	public ArrayList<Pan> catalogo = new ArrayList<>();
	
	public StockDialog(ArrayList<Pan> catalogo){
		this.catalogo = catalogo;
		// Create the custom dialog.
		Dialog<Panes> dialog = new Dialog<>();
		dialog.setTitle("Nuevo stock");
		dialog.setHeaderText("Inserte el nuevo stock");
		
	
			
		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
	
		// Create the username and password labels and fields.
		BorderPane border = new BorderPane();
		border.setPadding(new Insets(10, 20, 10, 20));
		border.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
		
		TableView<Pan> tvPan = new TableView<>();
		TableColumn<Pan, String> tcNombre = new TableColumn<>();
		TableColumn<Pan, String> tcTipo = new TableColumn<>();
		TableColumn<Pan, Float> tcPrecio = new TableColumn<>();
		
		tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
		tcTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTipo()));
		tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
    	
		tcNombre.setText("Nombre");
		tcTipo.setText("Tipo");
		tcPrecio.setText("Precio");
		
    	tvPan.setItems(FXCollections.observableArrayList(this.catalogo));
    	
    	tvPan.getColumns().addAll(tcNombre, tcTipo, tcPrecio);
    	
		TextField cantidad = new TextField();
		cantidad.setPromptText("Cantidad");
		
	
		border.setTop(tvPan);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(new Label("Cantidad:"), cantidad);
		border.setBottom(hBox);
		
		
		
		
	
		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
	
		// Do some validation (using the Java 8 lambda syntax).
		cantidad.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
	
		dialog.getDialogPane().setContent(border);
	
		// Request focus on the username field by default.
		Platform.runLater(() -> cantidad.requestFocus());
	
		// Convert the result to a stock when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		    	this.setResultado(new Panes(obtenerGenerado(), tvPan.getSelectionModel().getSelectedItem(), Integer.parseInt(cantidad.getText())));
		    }
		    return null;
		});
	
		Optional<Panes> result = dialog.showAndWait();
		
	}
	
	public StockDialog(Panes panes, ArrayList<Pan> catalogo){
		this.catalogo = catalogo;
		// Create the custom dialog.
		Dialog<Panes> dialog = new Dialog<>();
		dialog.setTitle("Nuevo stock");
		dialog.setHeaderText("Inserte el nuevo stock");
		
	
			
		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
	
		// Create the username and password labels and fields.
		BorderPane border = new BorderPane();
		border.setPadding(new Insets(10, 20, 10, 20));
		border.setMinSize(Control.USE_COMPUTED_SIZE, Control.USE_COMPUTED_SIZE);
		
		TableView<Pan> tvPan = new TableView<>();
		TableColumn<Pan, String> tcNombre = new TableColumn<>();
		TableColumn<Pan, String> tcTipo = new TableColumn<>();
		TableColumn<Pan, Float> tcPrecio = new TableColumn<>();
		
		tcNombre.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getNombre()));
		tcTipo.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTipo()));
		tcPrecio.setCellValueFactory(cellData -> new SimpleFloatProperty(cellData.getValue().getPrecio()).asObject());
    	
		tcNombre.setText("Nombre");
		tcTipo.setText("Tipo");
		tcPrecio.setText("Precio");
		
    	tvPan.setItems(FXCollections.observableArrayList(this.catalogo));
    	tvPan.getColumns().addAll(tcNombre, tcTipo, tcPrecio);
    	tvPan.getSelectionModel().select(panes.getPan());
    	
    	
		TextField cantidad = new TextField();
		cantidad.setText((String.valueOf(panes.getCant())));
		
	
		border.setTop(tvPan);
		HBox hBox = new HBox();
		hBox.getChildren().addAll(new Label("Cantidad:"), cantidad);
		border.setBottom(hBox);
		
		
		
		
	
		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
	
		// Do some validation (using the Java 8 lambda syntax).
		cantidad.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
	
		dialog.getDialogPane().setContent(border);
	
		// Request focus on the username field by default.
		Platform.runLater(() -> cantidad.requestFocus());
	
		// Convert the result to a stock when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		    	this.setResultado(new Panes(obtenerGenerado(), tvPan.getSelectionModel().getSelectedItem(), Integer.parseInt(cantidad.getText())));
		    }
		    return null;
		});
	
		Optional<Panes> result = dialog.showAndWait();
		
	}
	
	private int obtenerGenerado(){
		StockDAO stockDAO = new StockDAO();
		return stockDAO.getLastGeneratedPanesTienda();
	}

	public Panes getResultado() {
		return resultado;
	}

	public void setResultado(Panes resultado) {
		this.resultado = resultado;
	}
}
	