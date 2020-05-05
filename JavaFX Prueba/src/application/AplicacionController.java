package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class AplicacionController {
	/*
	 * Variables
	 */
	
	private AccesoBDD db = new AccesoBDD();
	private Stage stage;
	private String cif;
	private int num;
	
	//Factura > Cargar
	@FXML
	public TextField tfRutaCarga;
	
	//Factura > Ver Lista
	@FXML
	public TableView<FacturaModel> tableListaFacturas;
	@FXML
	public TableColumn<FacturaModel, Number> tcNumFact;
	@FXML
	public TableColumn<FacturaModel, String> tcCifFact;
	@FXML
	public TableColumn<FacturaModel, String> tcRazFact;
	@FXML
	public TableColumn<FacturaModel, String> tcDesFact;
	@FXML
	public TableColumn<FacturaModel, Number> tcBasFact;
	@FXML
	public TableColumn<FacturaModel, Number> tcIvaFact;
	@FXML
	public TableColumn<FacturaModel, Number> tcTotalFact;
	@FXML
	public TableColumn<FacturaModel, Date> tcFecFact;
	@FXML
	public TableColumn<FacturaModel, Date> tcVecFact;
	
	//Factura > Modificar
	@FXML
	public GridPane gpModificarFactura;
	@FXML
	public Button btGuardarFactura;
	@FXML
	public TextField tfNumFactModificarOriginal;
	@FXML
	public TextField tfCifModFactOriginal;
	
	@FXML
	public TextField tfCifModFact;
	@FXML
	public TextField tfRsModFact;
	@FXML
	public TextField tfNumModFact;
	@FXML
	public TextField tfDesModFact;
	@FXML
	public TextField tfBiModFact;
	@FXML
	public TextField tfIvaModFact;
	@FXML
	public TextField tfTotalModFact;
	@FXML
	public TextField tfFecModFact;
	@FXML
	public TextField tfVenceModFact;
	
	//Factura > Eliminar
	@FXML
	public GridPane gpEliminarFactura;
	@FXML
	public Button btEliminarFactura;
	@FXML
	public TextField tfNumFactBorrar;
	@FXML
	public TextField tfCifBorrarFact;
	
	@FXML
	public Label lbCifDelFact;
	@FXML
	public Label lbRazDelFact;
	@FXML
	public Label lbNumDelFact;
	@FXML
	public Label lbDesDelFact;
	@FXML
	public Label lbBasDelFact;
	@FXML
	public Label lbIvaDelFact;
	@FXML
	public Label lbTotalDelFact;
	@FXML
	public Label lbFecDelFact;
	@FXML
	public Label lbVenceDelFact;
	
	//Proveedor > Cargar
	@FXML
	public TextField tfRutaProv;
	
	//Proveedor > Ver Lista
	@FXML
	public TableView<ProveedorModel> tableListaProveedores;
	@FXML
	public TableColumn<ProveedorModel, String> tcCifProv;
	@FXML
	public TableColumn<ProveedorModel, String> tcRazProv;
	@FXML
	public TableColumn<ProveedorModel, Number> tcRegProv;
	@FXML
	public TableColumn<ProveedorModel, Number> tcSegResProv;
	@FXML
	public TableColumn<ProveedorModel, Number> tcSegImpProv;
	@FXML
	public TableColumn<ProveedorModel, Date> tcFecProv;
	
	//Proveedor > Modificar
	@FXML
	public GridPane gpModificarProveedor;
	@FXML
	public Button btGuardarProveedor;
	@FXML
	public TextField tfCifModProvOriginal;
	
	@FXML
	public TextField tfCifModProv;
	@FXML
	public TextField tfRazModProv;
	@FXML
	public TextField tfRegModProv;
	@FXML
	public TextField tfSegResModProv;
	@FXML
	public TextField tfSegImpModProv;
	@FXML
	public TextField tfFecModProv;
	
	//Proveedor > Eliminar
	@FXML
	public GridPane gpEliminarProveedor;
	@FXML
	public Button btEliminarProveedor;
	@FXML
	public TextField tfCifBorrarProv;
	
	@FXML
	public Label lbCifDelProv;
	@FXML
	public Label lbRazDelProv;
	@FXML
	public Label lbRegDelProv;
	@FXML
	public Label lbSegDelProv;
	@FXML
	public Label lbSegImpDelProv;
	@FXML
	public Label lbFecDelProv;
	
	
	/*
	 * Métodos
	 */
	
	public void initialize() {
		//Tabla de facturas
		tcNumFact.setCellValueFactory(new PropertyValueFactory<>("num_factura"));
		tcCifFact.setCellValueFactory(new PropertyValueFactory<>("cif_proveedor"));
		tcRazFact.setCellValueFactory(new PropertyValueFactory<>("raz_proveedor"));
		tcDesFact.setCellValueFactory(new PropertyValueFactory<>("des_factura"));
		tcBasFact.setCellValueFactory(new PropertyValueFactory<>("bas_imponible"));
		tcIvaFact.setCellValueFactory(new PropertyValueFactory<>("iva_importe"));
		tcTotalFact.setCellValueFactory(new PropertyValueFactory<>("tot_importe"));
		tcFecFact.setCellValueFactory(new PropertyValueFactory<>("fec_factura"));
		tcVecFact.setCellValueFactory(new PropertyValueFactory<>("fec_vencimiento"));
		
		//Tabla de proveedores
		tcCifProv.setCellValueFactory(new PropertyValueFactory<>("cif_proveedor"));
		tcRazProv.setCellValueFactory(new PropertyValueFactory<>("raz_proveedor"));
		tcRegProv.setCellValueFactory(new PropertyValueFactory<>("reg_notarial"));
		tcSegResProv.setCellValueFactory(new PropertyValueFactory<>("seg_responsabilidad"));
		tcSegImpProv.setCellValueFactory(new PropertyValueFactory<>("seg_importe"));
		tcFecProv.setCellValueFactory(new PropertyValueFactory<>("fec_homologacion"));
		
		actualizarTablas();
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	
	public void mostrarBusqueda(GridPane gp, Button bt) {
		gp.setVisible(true);
		bt.setVisible(true);
	}
	
	public Date StringToDate(String cad) throws ParseException {
	    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(cad);  
	    return date;
	}
	
	public void actualizarTablas() {
		ObservableList<FacturaModel> facturas = db.listaFacturas();
		ObservableList<ProveedorModel> proveedores = db.listaProveedores();
		if (facturas == null) {
			JOptionPane.showMessageDialog(null, "Error al mostrar las facturas", "Ver lista", JOptionPane.INFORMATION_MESSAGE);
			
			if (proveedores != null)
				tableListaProveedores.setItems(proveedores);
			
		}else if (proveedores == null) {
			JOptionPane.showMessageDialog(null, "Error al mostrar los proveedores", "Ver lista", JOptionPane.INFORMATION_MESSAGE);
			tableListaFacturas.setItems(facturas);
			
		}else {
			tableListaFacturas.setItems(facturas);
			tableListaProveedores.setItems(proveedores);
		}
	}
	
	int i = 0;
	public void cambioTab() {
		
		if (i < 3)
			i++;
		else {
			this.cif = "";
			this.num = 0;
	
			//Facturas
			tfRutaCarga.setText("");
			tfNumFactModificarOriginal.setText("");
			tfCifModFactOriginal.setText("");
			tfNumFactBorrar.setText("");
			tfCifBorrarFact.setText("");
			
			gpEliminarFactura.setVisible(false);
			btEliminarFactura.setVisible(false);
			gpModificarFactura.setVisible(false);
			btGuardarFactura.setVisible(false);
			
			//Proveedores
			tfRutaProv.setText("");
			tfCifModProvOriginal.setText("");
			tfCifBorrarProv.setText("");
			
			gpEliminarProveedor.setVisible(false);
			btEliminarProveedor.setVisible(false);
			gpModificarProveedor.setVisible(false);
			btGuardarProveedor.setVisible(false);
		}
	}
	
	public File abrirExplorador() {
		FileChooser fc = new FileChooser();
		fc.setTitle("Buscar archivo");
		
		//Filtros para facilitar la búsqueda
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("JSON", "*.json"),
				new FileChooser.ExtensionFilter("XML", "*.xml"),
				new FileChooser.ExtensionFilter("DAT", "*.dat"),
				new FileChooser.ExtensionFilter("TXT", "*.txt")
		);
		
		//Obtener fichero seleccionado y mostrarlo
		File fichero = fc.showOpenDialog(stage);
		
		return fichero;
	}
	
	/*
	 * Facturas
	 */
	
	public void buscarCargaFactura() {
		File fichero = abrirExplorador();
		
		if (fichero != null)
			tfRutaCarga.setText(fichero.getAbsolutePath()); //Si ha seleccionado fichero se mostrará la ruta
	}
	
	public void enviarFactura() {
		try {
			Convertidor conver = new Convertidor();
			Factura fact = conver.leerFactura(tfRutaCarga.getText());
			
			if (fact != null) {
				db.insertarFactura(fact);
				actualizarTablas();
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void buscarModificarFactura() {
		try {
			num = Integer.parseInt(tfNumFactModificarOriginal.getText());
			cif = tfCifModFactOriginal.getText();
			Factura fact = db.buscarFactura(num, cif);

			if (fact == null) {
				JOptionPane.showMessageDialog(null, "Error al obtener la factura", "Modificar Factura", JOptionPane.INFORMATION_MESSAGE);
				
			}else if (fact.getCif_proveedor().equals("")){
				JOptionPane.showMessageDialog(null, "No existe ninguna factura con esos datos", "Modificar Factura", JOptionPane.INFORMATION_MESSAGE);
			
			}else {
				mostrarBusqueda(gpModificarFactura, btGuardarFactura);
				
				tfCifModFact.setText(fact.getCif_proveedor());
				tfRsModFact.setText(fact.getRaz_proveedor());
				tfNumModFact.setText(String.valueOf(fact.getNum_factura()));
				tfDesModFact.setText(fact.getDes_factura());
				tfBiModFact.setText(String.valueOf(fact.getBas_imponible()));
				tfIvaModFact.setText(String.valueOf(fact.getIva_importe()));
				tfTotalModFact.setText(String.valueOf(fact.getTot_importe()));
				tfFecModFact.setText(String.valueOf(fact.getFec_factura()));
				tfVenceModFact.setText(String.valueOf(fact.getFec_vencimiento()));
			}
			
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Debes introducir datos correctos para poder buscar la factura", "Modificar Factura", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void buscarEliminarFactura() {
		try {
			num = Integer.parseInt(tfNumFactBorrar.getText());
			cif = tfCifBorrarFact.getText();
			Factura fact = db.buscarFactura(num, cif);

			if (fact == null) {
				JOptionPane.showMessageDialog(null, "Error al obtener la factura", "Eliminar Factura", JOptionPane.INFORMATION_MESSAGE);
				
			}else if (fact.getCif_proveedor().equals("")){
				JOptionPane.showMessageDialog(null, "No existe ninguna factura con esos datos", "Eliminar Factura", JOptionPane.INFORMATION_MESSAGE);
			
			}else {
				mostrarBusqueda(gpEliminarFactura, btEliminarFactura);
				
				lbCifDelFact.setText(fact.getCif_proveedor());
				lbRazDelFact.setText(fact.getRaz_proveedor());
				lbNumDelFact.setText(String.valueOf(fact.getNum_factura()));
				lbDesDelFact.setText(fact.getDes_factura());
				lbBasDelFact.setText(String.valueOf(fact.getBas_imponible()));
				lbIvaDelFact.setText(String.valueOf(fact.getIva_importe()));
				lbTotalDelFact.setText(String.valueOf(fact.getTot_importe()));
				lbFecDelFact.setText(String.valueOf(fact.getFec_factura()));
				lbVenceDelFact.setText(String.valueOf(fact.getFec_vencimiento()));
			}
			
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Debes introducir datos correctos para poder buscar la factura", "Eliminar Factura", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void actualizarFactura() {
		try {
			String cif_proveedor = tfCifModFact.getText();
			String raz_proveedor = tfRsModFact.getText();
			int num_factura = Integer.parseInt(tfNumModFact.getText());
			String des_factura = tfDesModFact.getText();
			float bas_imponible = Float.parseFloat(tfBiModFact.getText());
			float iva_importe = Float.parseFloat(tfIvaModFact.getText());
			float tot_importe = Float.parseFloat(tfTotalModFact.getText());
			Date fec_factura = StringToDate(tfFecModFact.getText());
			Date fec_vencimiento = StringToDate(tfVenceModFact.getText());
			
			Factura fact = new Factura(cif_proveedor, raz_proveedor, num_factura, des_factura, bas_imponible, iva_importe, tot_importe, fec_factura, fec_vencimiento);
			db.actulizarFact(fact, this.num, this.cif);
			actualizarTablas();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	public void eliminarFactura() {
		db.borrarFact(num, cif);
		actualizarTablas();
	}
	
	/*
	 * Proveedores
	 */
	
	public void buscarCargaProveedor() {
		File fichero = abrirExplorador();
		
		if (fichero != null)
			tfRutaProv.setText(fichero.getAbsolutePath()); //Si ha seleccionado fichero se mostrará la ruta
	}
	
	public void enviarProveedor() {
		Convertidor conver = new Convertidor();
		ArrayList<Proveedor> proveedores = conver.leerProveedores(tfRutaProv.getText());

		if (proveedores != null) {
			db.insertarProveedores(proveedores);
			actualizarTablas();
		}
		
	}
	
	public void buscarModificarProveedor() {
		cif = tfCifModProvOriginal.getText();
		Proveedor prov = db.buscarProveedor(cif);

		if (prov == null) {
			JOptionPane.showMessageDialog(null, "Error al obtener al proveedor", "Modificar Proveedor", JOptionPane.INFORMATION_MESSAGE);
			
		}else if (prov.getCif_proveedor().equals("")){
			JOptionPane.showMessageDialog(null, "No existe ningún proveedor con esos datos", "Modificar Proveedor", JOptionPane.INFORMATION_MESSAGE);
		
		}else {
			mostrarBusqueda(gpModificarProveedor, btGuardarProveedor);
			
			tfCifModProv.setText(prov.getCif_proveedor());
			tfRazModProv.setText(prov.getRaz_proveedor());
			tfRegModProv.setText(String.valueOf(prov.getReg_notarial()));
			tfSegResModProv.setText(String.valueOf(prov.getSeg_responsabilidad()));
			tfSegImpModProv.setText(String.valueOf(prov.getSeg_importe()));
			tfFecModProv.setText(String.valueOf(prov.getFec_homologacion()));
		}
		
	}
	
	public void buscarEliminarProveedor() {
		cif = tfCifBorrarProv.getText();
		Proveedor prov = db.buscarProveedor(cif);

		if (prov == null) {
			JOptionPane.showMessageDialog(null, "Error al obtener al proveedor", "Eliminar Proveedor", JOptionPane.INFORMATION_MESSAGE);
			
		}else if (prov.getCif_proveedor().equals("")){
			JOptionPane.showMessageDialog(null, "No existe ningún proveedor con esos datos", "Eliminar Proveedor", JOptionPane.INFORMATION_MESSAGE);
		
		}else {
			mostrarBusqueda(gpEliminarProveedor, btEliminarProveedor);
			
			lbCifDelProv.setText(prov.getCif_proveedor());
			lbRazDelProv.setText(prov.getRaz_proveedor());
			lbRegDelProv.setText(String.valueOf(prov.getReg_notarial()));
			lbSegDelProv.setText(String.valueOf(prov.getSeg_responsabilidad()));
			lbSegImpDelProv.setText(String.valueOf(prov.getSeg_importe()));
			lbFecDelProv.setText(String.valueOf(prov.getFec_homologacion()));
		}
		
	}
	
	public void actualizarProveedor() {
		try {
			String cif_proveedor = tfCifModProv.getText();
			String raz_proveedor = tfRazModProv.getText();
			int reg_notarial = Integer.parseInt(tfRegModProv.getText());
			int seg_responsabilidad = Integer.parseInt(tfSegResModProv.getText());
			float seg_importe = Float.parseFloat(tfSegImpModProv.getText());
			Date fec_homologacion = StringToDate(tfFecModProv.getText());
			
			Proveedor prov = new Proveedor(cif_proveedor, raz_proveedor, reg_notarial, seg_responsabilidad, seg_importe, fec_homologacion);
			db.actulizarProv(prov, this.cif);
			actualizarTablas();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarProveedor() {
		db.borrarProv(cif);
		actualizarTablas();
	}
}
