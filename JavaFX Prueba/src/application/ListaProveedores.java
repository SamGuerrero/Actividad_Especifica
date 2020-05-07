package application;

import java.util.ArrayList;

public class ListaProveedores {
    private ArrayList<Proveedor> proveedores;

	//Constructores
	public ListaProveedores() {
	}
	
	public ListaProveedores(ArrayList<Proveedor> proveedores) {
		super();
		this.proveedores = proveedores;
	}

	//Getters
	public ArrayList<Proveedor> getProveedores() {
		return proveedores;
	}

	//Setters
	public void setProveedores(ArrayList<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}

	
}
