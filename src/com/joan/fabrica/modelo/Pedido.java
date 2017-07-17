package com.joan.fabrica.modelo;

import java.sql.Date;
import java.util.ArrayList;

public class Pedido {
	private int id;
	private Date fecha;
	private Tienda tienda;
	private ArrayList<Panes> panes;
	private float precioTotal;
	
	public Pedido(int id, Date fecha, Tienda tienda, ArrayList<Panes> panes) {
		super();
		this.id = id;
		this.fecha = fecha;
		this.tienda = tienda;
		this.panes = panes;
		this.precioTotal = calcularPrecioTotal(panes);
	}
	
	public Pedido(){
		this.id = 0;
		this.fecha = new Date(10);
		this.tienda = null;
		this.panes = new ArrayList<>();
		this.precioTotal = (float) 0.0;
	}
	
	private float calcularPrecioTotal(ArrayList<Panes> panes){
		float res = 0;
		for(int i = 0; i<panes.size(); i++){
			res+=panes.get(i).getPrecio();
		}
		return res;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Tienda getTienda() {
		return tienda;
	}

	public void setTienda(Tienda tienda) {
		this.tienda = tienda;
	}

	public ArrayList<Panes> getPanes() {
		return panes;
	}

	public void setPanes(ArrayList<Panes> panes) {
		this.panes = panes;
		this.precioTotal = calcularPrecioTotal(panes);
	}

	public float getPrecioTotal() {
		return precioTotal;
	}

	public void setPrecioTotal() {
		this.precioTotal = calcularPrecioTotal(this.panes);
	}
	
	
	
	
}
