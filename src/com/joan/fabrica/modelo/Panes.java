package com.joan.fabrica.modelo;


public class Panes {
	private Pan pan;
	private int idPanesTienda;
	private int cant;
	private float precio;
		
	public Panes(Integer idPanesTienda, Pan pan, int cant) {
		super();
		this.idPanesTienda = idPanesTienda;
		this.pan = pan;
		this.cant = cant;
		this.precio = calcularPrecioTotal(pan, cant);
		
	}
	
	public float calcularPrecioTotal(Pan pan, int cant){
		return pan.getPrecio() * cant;
	}

	public Pan getPan() {
		return pan;
	}

	public void setPan(Pan pan) {
		this.pan = pan;
		this.precio = calcularPrecioTotal(pan, this.cant);
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
		this.precio = calcularPrecioTotal(this.pan, cant);
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio() {
		this.precio = calcularPrecioTotal(this.pan, this.cant);
	}

	public int getIdPanesTienda() {
		return idPanesTienda;
	}

	public void setIdPanesTienda(int idPanesTienda) {
		this.idPanesTienda = idPanesTienda;
	}

	
}
