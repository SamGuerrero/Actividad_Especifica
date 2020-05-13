package application;

import java.util.Date;

public class Factura {
	private String cif_proveedor;
	private String raz_proveedor;
	private int num_factura;
	private String des_factura;
	private float bas_imponible;
	private float iva_importe;
	private float tot_importe;
	private Date fec_factura;
	private Date fec_vencimiento;
	
	//Constructores
	public Factura() {
		this.cif_proveedor = "";
		this.raz_proveedor = "";
		this.num_factura = 0;
		this.des_factura = "";
		this.bas_imponible = 0.0F;
		this.iva_importe = 0.0F;
		this.tot_importe = 0.0F;
		this.fec_factura = new Date();
		this.fec_vencimiento = new Date();
	}

	public Factura(String cif_proveedor, String raz_proveedor, int num_factura, String des_factura, float bas_imponible,
			float iva_importe, float tot_importe, Date fec_factura, Date fec_vencimiento) {
		super();
		this.cif_proveedor = cif_proveedor;
		this.raz_proveedor = raz_proveedor;
		this.num_factura = num_factura;
		this.des_factura = des_factura;
		this.bas_imponible = bas_imponible;
		this.iva_importe = iva_importe;
		this.tot_importe = tot_importe;
		this.fec_factura = fec_factura;
		this.fec_vencimiento = fec_vencimiento;
	}

	//Getters
	public String getCif_proveedor() {
		return cif_proveedor;
	}
	
	public String getRaz_proveedor() {
		return raz_proveedor;
	}

	public int getNum_factura() {
		return num_factura;
	}

	public String getDes_factura() {
		return des_factura;
	}

	public float getBas_imponible() {
		return bas_imponible;
	}

	public float getIva_importe() {
		return iva_importe;
	}

	public float getTot_importe() {
		return tot_importe;
	}

	public Date getFec_factura() {
		return fec_factura;
	}

	public Date getFec_vencimiento() {
		return fec_vencimiento;
	}

	//Setters
	public void setCif_proveedor(String cif_proveedor) {
		this.cif_proveedor = cif_proveedor;
	}

	public void setRaz_proveedor(String raz_proveedor) {
		this.raz_proveedor = raz_proveedor;
	}

	public void setNum_factura(int num_factura) {
		this.num_factura = num_factura;
	}

	public void setDes_factura(String des_factura) {
		this.des_factura = des_factura;
	}

	public void setBas_imponible(float bas_imponible) {
		this.bas_imponible = bas_imponible;
	}

	public void setIva_importe(float iva_importe) {
		this.iva_importe = iva_importe;
	}
	
	public void setTot_importe(float tot_importe) {
		this.tot_importe = tot_importe;
	}
	
	public void setFec_factura(Date fec_factura) {
		this.fec_factura = fec_factura;
	}

	public void setFec_vencimiento(Date fec_vencimiento) {
		this.fec_vencimiento = fec_vencimiento;
	}
	
	
	
	
}
