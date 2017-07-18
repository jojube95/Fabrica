package com.joan.fabrica.vista;

import java.sql.Date;
import java.util.Optional;

import com.joan.fabrica.modelo.Cliente;
import com.joan.fabrica.modelo.Pan;
import com.joan.fabrica.persistencia.ClienteDAO;
import com.joan.fabrica.persistencia.ConnectionManager;
import com.joan.fabrica.persistencia.PanDAO;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ClienteDialog{
	private Cliente resultado = null;
	
	public ClienteDialog(){
		// Create the custom dialog.
		Dialog<Cliente> dialog = new Dialog<>();
		dialog.setTitle("Nuevo cliente");
		dialog.setHeaderText("Inserte el nuevo cliente");
	
			
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
		TextField localidad = new TextField();
		localidad.setPromptText("Localidad");
		DatePicker fecha = new DatePicker();
		
		TextField usuario = new TextField();
		usuario.setPromptText("Usuario");
		TextField contrasenya = new TextField();
		contrasenya.setPromptText("Pass");
	
		grid.add(new Label("Nombre:"), 0, 0);
		grid.add(nombre, 1, 0);
		grid.add(new Label("Localidad:"), 0, 1);
		grid.add(localidad, 1, 1);
		grid.add(new Label("FechaNac:"), 0, 2);
		grid.add(fecha, 1, 2);
		grid.add(new Label("Usuario:"), 0, 3);
		grid.add(usuario, 1, 3);
		grid.add(new Label("Pass:"), 0, 4);
		grid.add(contrasenya, 1, 4);
		
	
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
		    	this.setResultado(new Cliente(nombre.getText(), obtenerGenerado(), localidad.getText(), Date.valueOf(fecha.getValue()), true, usuario.getText(), localidad.getText()));
		    }
		    return null;
		});
	
		Optional<Cliente> result = dialog.showAndWait();
		
	}
	
	public ClienteDialog(Cliente cliente){
		// Create the custom dialog.
		Dialog<Cliente> dialog = new Dialog<>();
		dialog.setTitle("Nuevo cliente");
		dialog.setHeaderText("Inserte el nuevo cliente");
	
			
		// Set the button types.
		ButtonType loginButtonType = new ButtonType("Aceptar", ButtonData.OK_DONE);
		dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);
	
		// Create the username and password labels and fields.
		GridPane grid = new GridPane();
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(20, 150, 10, 10));
	
		TextField nombre = new TextField();
		nombre.setText(cliente.getNombre());
		TextField localidad = new TextField();
		localidad.setText(cliente.getLocalidad());
		DatePicker fecha = new DatePicker();
		TextField usuario = new TextField();
		usuario.setText(cliente.getUsuario());
		TextField contrasenya = new TextField();
		contrasenya.setText(cliente.getPass());
	
		grid.add(new Label("Nombre:"), 0, 0);
		grid.add(nombre, 1, 0);
		grid.add(new Label("Localidad:"), 0, 1);
		grid.add(localidad, 1, 1);
		grid.add(new Label("FechaNac:"), 0, 2);
		grid.add(fecha, 1, 2);
		grid.add(new Label("Usuario:"), 0, 3);
		grid.add(usuario, 1, 3);
		grid.add(new Label("Pass:"), 0, 4);
		grid.add(contrasenya, 1, 4);
		
	
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
		    	this.setResultado(new Cliente(nombre.getText(), obtenerGenerado(), localidad.getText(), Date.valueOf(fecha.getValue()), true, usuario.getText(), localidad.getText()));
		    }
		    return null;
		});
	
		Optional<Cliente> result = dialog.showAndWait();
		
	}
	
	private int obtenerGenerado(){
		ClienteDAO clienteDAO = new ClienteDAO();
		return clienteDAO.getLastGenerated();
	}

	public Cliente getResultado() {
		return resultado;
	}

	public void setResultado(Cliente resultado) {
		this.resultado = resultado;
	}
	
}