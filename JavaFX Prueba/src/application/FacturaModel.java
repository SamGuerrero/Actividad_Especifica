package application;

import java.util.Date;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class FacturaModel {
	private SimpleStringProperty cif_proveedor;
	private SimpleStringProperty raz_proveedor;
	private SimpleIntegerProperty num_factura;
	private SimpleStringProperty des_factura;
	private SimpleFloatProperty bas_imponible;
	private SimpleFloatProperty iva_importe;
	private SimpleFloatProperty tot_importe;
	private SimpleObjectProperty<Date> fec_factura;
	private SimpleObjectProperty<Date> fec_vencimiento;
	
	//Constructores
	public FacturaModel() {
		this.cif_proveedor = null;
		this.raz_proveedor = null;
		this.num_factura = null;
		this.des_factura = null;
		this.bas_imponible = null;
		this.iva_importe = null;
		this.tot_importe = null;
		this.fec_factura = null;
		this.fec_vencimiento = null;
	}

	public FacturaModel(String cif_proveedor, String raz_proveedor, int num_factura,
			String des_factura, float bas_imponible, float iva_importe,
			float tot_importe, Date fec_factura, Date fec_vencimiento) {
		this.cif_proveedor = new SimpleStringProperty(cif_proveedor);
		this.raz_proveedor = new SimpleStringProperty(raz_proveedor);
		this.num_factura = new SimpleIntegerProperty(num_factura);
		this.des_factura = new SimpleStringProperty(des_factura);
		this.bas_imponible = new SimpleFloatProperty(bas_imponible);
		this.iva_importe = new SimpleFloatProperty(iva_importe);
		this.tot_importe = new SimpleFloatProperty(tot_importe);
		this.fec_factura = new SimpleObjectProperty<Date>(fec_factura);
		this.fec_vencimiento = new SimpleObjectProperty<Date>(fec_vencimiento);
	}

	//Getters
	public String getCif_proveedor() {
		return cif_proveedor.get();
	}

	public String getRaz_proveedor() {
		return raz_proveedor.get();
	}

	public int getNum_factura() {
		return num_factura.get();
	}

	public String getDes_factura() {
		return des_factura.get();
	}

	public float getBas_imponible() {
		return bas_imponible.get();
	}

	public float getIva_importe() {
		return iva_importe.get();
	}

	public float getTot_importe() {
		return tot_importe.get();
	}

	public Date getFec_factura() {
		return fec_factura.get();
	}

	public Date getFec_vencimiento() {
		return fec_vencimiento.get();
	}

	//Setters
	public void setCif_proveedor(SimpleStringProperty cif_proveedor) {
		this.cif_proveedor = cif_proveedor;
	}

	public void setRaz_proveedor(SimpleStringProperty raz_proveedor) {
		this.raz_proveedor = raz_proveedor;
	}

	public void setNum_factura(SimpleIntegerProperty num_factura) {
		this.num_factura = num_factura;
	}

	public void setDes_factura(SimpleStringProperty des_factura) {
		this.des_factura = des_factura;
	}

	public void setBas_imponible(SimpleFloatProperty bas_imponible) {
		this.bas_imponible = bas_imponible;
	}

	public void setIva_importe(SimpleFloatProperty iva_importe) {
		this.iva_importe = iva_importe;
	}

	public void setTot_importe(SimpleFloatProperty tot_importe) {
		this.tot_importe = tot_importe;
	}

	public void setFec_factura(SimpleObjectProperty<Date> fec_factura) {
		this.fec_factura = fec_factura;
	}

	public void setFec_vencimiento(SimpleObjectProperty<Date> fec_vencimiento) {
		this.fec_vencimiento = fec_vencimiento;
	}
	
	
	
}
