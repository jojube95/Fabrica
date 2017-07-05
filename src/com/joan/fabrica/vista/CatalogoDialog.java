package com.joan.fabrica.vista;

import java.util.Optional;

import com.joan.fabrica.modelo.Pan;
import com.joan.fabrica.persistencia.ConnectionManager;
import com.joan.fabrica.persistencia.PanDAO;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CatalogoDialog{
	private Pan resultado = null;
	
	public CatalogoDialog(){
		// Create the custom dialog.
		Dialog<Pan> dialog = new Dialog<>();
		dialog.setTitle("Nuevo Pan");
		dialog.setHeaderText("Inserte el nuevo pan");
	
			
		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
	
		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
	
		TextField nombre = new TextField();
		nombre.setPromptText("Nombre");
		TextField tipo = new TextField();
		tipo.setPromptText("Tipo");
		TextField precio = new TextField();
		precio.setPromptText("Precio");
	
		grid.add(new Label("Nombre:"), 0, 0);
		grid.add(nombre, 1, 0);
		grid.add(new Label("Tipo:"), 0, 1);
		grid.add(tipo, 1, 1);
		grid.add(new Label("Precio:"), 0, 2);
		grid.add(precio, 1, 2);
	
		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		loginButton.setDisable(true);
	
		// Do some validation (using the Java 8 lambda syntax).
		nombre.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
	
		dialog.getDialogPane().setContent(grid);
	
		// Request focus on the username field by default.
		Platform.runLater(() -> nombre.requestFocus());
	
		// Convert the result to a pan when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		    	this.setResultado(new Pan(obtenerGenerado(), nombre.getText(), tipo.getText(), Float.parseFloat(precio.getText())));
		    }
		    return null;
		});
	
		Optional<Pan> result = dialog.showAndWait();
		
	}
	
	public CatalogoDialog(Pan pan){
		// Create the custom dialog.
		Dialog<Pan> dialog = new Dialog<>();
		dialog.setTitle("Modificar Pan");
		dialog.setHeaderText("Inserte el pan");
	
			
		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
	
		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
	
		TextField nombre = new TextField();
		nombre.setText(pan.getNombre());
		TextField tipo = new TextField();
		tipo.setText(pan.getTipo());
		TextField precio = new TextField();
		precio.setText(String.valueOf(pan.getPrecio()));
	
		grid.add(new Label("Nombre:"), 0, 0);
		grid.add(nombre, 1, 0);
		grid.add(new Label("Tipo:"), 0, 1);
		grid.add(tipo, 1, 1);
		grid.add(new Label("Precio:"), 0, 2);
		grid.add(precio, 1, 2);
	
		// Enable/Disable login button depending on whether a username was entered.
		Node loginButton = dialog.getDialogPane().lookupButton(loginButtonType);
		
	
		// Do some validation (using the Java 8 lambda syntax).
		nombre.textProperty().addListener((observable, oldValue, newValue) -> {
		    loginButton.setDisable(newValue.trim().isEmpty());
		});
	
		dialog.getDialogPane().setContent(grid);
	
		// Request focus on the username field by default.
		Platform.runLater(() -> nombre.requestFocus());
	
		// Convert the result to a pan when the login button is clicked.
		dialog.setResultConverter(dialogButton -> {
		    if (dialogButton == loginButtonType) {
		    	this.setResultado(new Pan(pan.getId(), nombre.getText(), tipo.getText(), Float.parseFloat(precio.getText())));
		    }
		    return null;
		});
	
		Optional<Pan> result = dialog.showAndWait();
		
	}
	
	private int obtenerGenerado(){
		PanDAO panDAO = new PanDAO();
		return panDAO.getLastGenerated();
	}

	public Pan getResultado() {
		return resultado;
	}

	public void setResultado(Pan resultado) {
		this.resultado = resultado;
	}
	
}
