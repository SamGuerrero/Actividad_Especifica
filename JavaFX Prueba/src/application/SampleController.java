package application;

import javax.swing.JOptionPane;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class SampleController {
	/*
	 * Variables
	 */
	
	//Factura > Modificar
	@FXML
	public GridPane gpModificarFactura;
	@FXML
	public Button btGuardarFactura;
	
	//Factura > Eliminar
	@FXML
	public GridPane gpEliminarFactura;
	@FXML
	public Button btEliminarFactura;
	
	//Proveedor > Modificar
	@FXML
	public GridPane gpModificarProveedor;
	@FXML
	public Button btGuardarProveedor;
	
	//Proveedor > Eliminar
	@FXML
	public GridPane gpEliminarProveedor;
	@FXML
	public Button btEliminarProveedor;
	
	
	
	/*
	 * Métodos
	 */
	
	public void mostrarBusqueda(GridPane gp, Button bt) {
		gp.setVisible(true);
		bt.setVisible(true);
	}
	
	//Facturas
	public void enviarFactura() {
		JOptionPane.showMessageDialog(null, "Factura Cargada correctamente", "Cargar Factura", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void buscarModificarFactura() {
		//JOptionPane.showMessageDialog(null, "No existe ese Nº", "Modificar Factura", JOptionPane.INFORMATION_MESSAGE);
		mostrarBusqueda(gpModificarFactura, btGuardarFactura);
	}
	
	public void buscarEliminarFactura() {
		//JOptionPane.showMessageDialog(null, "No existe ese Nº", "Eliminar Factura", JOptionPane.INFORMATION_MESSAGE);
		mostrarBusqueda(gpEliminarFactura, btEliminarFactura);
	}
	
	//Proveedores
	public void enviarProveedor() {
		JOptionPane.showMessageDialog(null, "Proveedor cargado correctamente", "Cargar Proveedor", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void buscarModificarProveedor() {
		//JOptionPane.showMessageDialog(null, "No existe ese CIF", "Modificar Proveedor", JOptionPane.INFORMATION_MESSAGE);
		mostrarBusqueda(gpModificarProveedor, btGuardarProveedor);
	}
	
	public void buscarEliminarProveedor() {
		//JOptionPane.showMessageDialog(null, "No existe ese CIF", "Eliminar Proveedor", JOptionPane.INFORMATION_MESSAGE);
		mostrarBusqueda(gpEliminarProveedor, btEliminarProveedor);
	}
}
