package application;

import java.util.Date;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class ProveedorModel {
	private SimpleStringProperty cif_proveedor;
	private SimpleStringProperty raz_proveedor;
	private SimpleIntegerProperty reg_notarial;
	private SimpleIntegerProperty seg_responsabilidad;
	private SimpleFloatProperty seg_importe;
	private SimpleObjectProperty<Date> fec_homologacion;
	
	//Constructores
	public ProveedorModel() {
		this.cif_proveedor = null;
		this.raz_proveedor = null;
		this.reg_notarial = null;
		this.seg_responsabilidad = null;
		this.seg_importe = null;
		this.fec_homologacion = null;
	}

	public ProveedorModel(SimpleStringProperty cif_proveedor, SimpleStringProperty raz_proveedor,
			SimpleIntegerProperty reg_notarial, SimpleIntegerProperty seg_responsabilidad,
			SimpleFloatProperty seg_importe, SimpleObjectProperty<Date> fec_homologacion) {
		this.cif_proveedor = cif_proveedor;
		this.raz_proveedor = raz_proveedor;
		this.reg_notarial = reg_notarial;
		this.seg_responsabilidad = seg_responsabilidad;
		this.seg_importe = seg_importe;
		this.fec_homologacion = fec_homologacion;
	}

	//Getters
	public String getCif_proveedor() {
		return cif_proveedor.get();
	}

	public String getRaz_proveedor() {
		return raz_proveedor.get();
	}

	public int getReg_notarial() {
		return reg_notarial.get();
	}

	public int getSeg_responsabilidad() {
		return seg_responsabilidad.get();
	}

	public float getSeg_importe() {
		return seg_importe.get();
	}

	public Date getFec_homologacion() {
		return fec_homologacion.get();
	}

	//Setters
	public void setCif_proveedor(SimpleStringProperty cif_proveedor) {
		this.cif_proveedor = cif_proveedor;
	}

	public void setRaz_proveedor(SimpleStringProperty raz_proveedor) {
		this.raz_proveedor = raz_proveedor;
	}

	public void setReg_notarial(SimpleIntegerProperty reg_notarial) {
		this.reg_notarial = reg_notarial;
	}

	public void setSeg_responsabilidad(SimpleIntegerProperty seg_responsabilidad) {
		this.seg_responsabilidad = seg_responsabilidad;
	}

	public void setSeg_importe(SimpleFloatProperty seg_importe) {
		this.seg_importe = seg_importe;
	}

	public void setFec_homologacion(SimpleObjectProperty<Date> fec_homologacion) {
		this.fec_homologacion = fec_homologacion;
	}
	
}
