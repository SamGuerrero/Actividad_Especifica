package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;

public class SampleController {
	/*
	 * Variables
	 */
	
	AccesoBDD db = new AccesoBDD();
	String cif;
	int num;
	
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
	
	
	
	/*
	 * Métodos
	 */
	
	public void initialize() {
		tableListaFacturas.setPlaceholder(new Label("¿Funcionas o qué?")); 

		//tableListaFacturas.getColumns().addAll(tcNumFact, tcCifFact, tcRazFact, tcDesFact, tcBasFact, tcIvaFact, tcTotalFact, tcFecFact, tcVecFact);
		
		tcNumFact.setCellValueFactory(new PropertyValueFactory<>("num_factura"));
		tcCifFact.setCellValueFactory(new PropertyValueFactory<>("cif_proveedor"));
		tcRazFact.setCellValueFactory(new PropertyValueFactory<>("raz_proveedor"));
		tcDesFact.setCellValueFactory(new PropertyValueFactory<>("des_factura"));
		tcBasFact.setCellValueFactory(new PropertyValueFactory<>("bas_imponible"));
		tcIvaFact.setCellValueFactory(new PropertyValueFactory<>("iva_importe"));
		tcTotalFact.setCellValueFactory(new PropertyValueFactory<>("tot_importe"));
		tcFecFact.setCellValueFactory(new PropertyValueFactory<>("fec_factura"));
		tcVecFact.setCellValueFactory(new PropertyValueFactory<>("fec_vencimiento"));
		
		tableListaFacturas.setItems(db.listaFacturas());
	}
	
	public void mostrarBusqueda(GridPane gp, Button bt) {
		gp.setVisible(true);
		bt.setVisible(true);
	}
	
	public Date StringToDate(String cad) throws ParseException {
	    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(cad);  
	    return date;
	}
	
	//Facturas
	public void enviarFactura() {
		JOptionPane.showMessageDialog(null, "Factura Cargada correctamente", "Cargar Factura", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void buscarModificarFactura() {
		try {
			this.num = Integer.parseInt(tfNumFactModificarOriginal.getText());
			this.cif = tfCifModFactOriginal.getText();
			Factura fact = db.buscarFactura(this.num, this.cif);

			if (fact.getCif_proveedor().equals("")){
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
			int numFactura = Integer.parseInt(tfNumFactBorrar.getText());
			String cif = tfCifBorrarFact.getText();
			Factura fact = db.buscarFactura(numFactura, cif);

			if (fact.getCif_proveedor().equals("")){
				JOptionPane.showMessageDialog(null, "No existe ninguna factura con esos datos", "Eliminar Factura", JOptionPane.INFORMATION_MESSAGE);
			
			}else {
				mostrarBusqueda(gpEliminarFactura, btEliminarFactura);
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
			JOptionPane.showMessageDialog(null, "Cambio realizado con éxito", "Modificar Factura", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	//Proveedores
	public void enviarProveedor() {
		JOptionPane.showMessageDialog(null, "Proveedor cargado correctamente", "Cargar Proveedor", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void buscarModificarProveedor() {
		this.cif = tfCifModProvOriginal.getText();
		Proveedor prov = db.buscarProveedor(this.cif);

		if (prov.getCif_proveedor().equals("")){
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
		String cif = tfCifBorrarProv.getText();
		Proveedor prov = db.buscarProveedor(cif);

		if (prov.getCif_proveedor().equals("")){
			JOptionPane.showMessageDialog(null, "No existe ningún proveedor con esos datos", "Eliminar Proveedor", JOptionPane.INFORMATION_MESSAGE);
		
		}else {
			mostrarBusqueda(gpEliminarProveedor, btEliminarProveedor);
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
			JOptionPane.showMessageDialog(null, "Cambio realizado con éxito", "Modificar Proveedor", JOptionPane.INFORMATION_MESSAGE);
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
