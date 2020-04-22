package application;

import java.util.Date;

public class Proveedor {
	private String cif_proveedor;
	private String raz_proveedor;
	private int reg_notarial;
	private int seg_responsabilidad;
	private float seg_importe;
	private Date fec_homologacio;
	
	//Constructor
	public Proveedor(String cif_proveedor, String raz_proveedor, int reg_notarial, int seg_responsabilidad,
			float seg_importe, Date fec_homologacio) {
		this.cif_proveedor = cif_proveedor;
		this.raz_proveedor = raz_proveedor;
		this.reg_notarial = reg_notarial;
		this.seg_responsabilidad = seg_responsabilidad;
		this.seg_importe = seg_importe;
		this.fec_homologacio = fec_homologacio;
	}
	
	//Getters
	public String getCif_proveedor() {
		return cif_proveedor;
	}
	public String getRaz_proveedor() {
		return raz_proveedor;
	}
	public int getReg_notarial() {
		return reg_notarial;
	}
	public int getSeg_responsabilidad() {
		return seg_responsabilidad;
	}
	public float getSeg_importe() {
		return seg_importe;
	}
	public Date getFec_homologacio() {
		return fec_homologacio;
	}
	
	//Setters
	public void setCif_proveedor(String cif_proveedor) {
		this.cif_proveedor = cif_proveedor;
	}
	public void setRaz_proveedor(String raz_proveedor) {
		this.raz_proveedor = raz_proveedor;
	}
	public void setReg_notarial(int reg_notarial) {
		this.reg_notarial = reg_notarial;
	}
	public void setSeg_responsabilidad(int seg_responsabilidad) {
		this.seg_responsabilidad = seg_responsabilidad;
	}
	public void setSeg_importe(float seg_importe) {
		this.seg_importe = seg_importe;
	}
	public void setFec_homologacio(Date fec_homologacio) {
		this.fec_homologacio = fec_homologacio;
	}
	
	
}
