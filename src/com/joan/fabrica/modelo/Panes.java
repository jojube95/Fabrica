package com.joan.fabrica.modelo;

public class Panes {
	private Pan pan;
	private int cant;
	private float precio;
	
	public Panes(Pan pan, int cant, float precio) {
		super();
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
	
	
}
