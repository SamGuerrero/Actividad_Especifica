package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AccesoBDD {
	static String BDPer = "jdbc:mysql://192.168.0.14/cesa_facturacion";
    Connection db;
    
    public void conectarBDD(){
         try{
               Class.forName("com.mysql.jdbc.Driver");
               db = DriverManager.getConnection(BDPer, "usuario", "usuario");
         }
         catch(ClassNotFoundException e){
               e.printStackTrace();
         }
         catch(SQLException e){
               e.printStackTrace();
         }
    }
    
    private static java.sql.Date UtilToSql(java.util.Date uDate) {
        java.sql.Date sDate = new java.sql.Date(uDate.getTime());
        return sDate;
    }
    
    /*
     * Métodos con Facturas
     */
    
    public ObservableList<FacturaModel> listaFacturas() {
    	conectarBDD();
    	ObservableList<FacturaModel> listaFacturas = FXCollections.observableArrayList();
       
        try{
        	Statement stmt = db.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM FACT_PROV");
        	
        	FacturaModel fact;
        	while(rs.next()){
        		fact = new FacturaModel();
        		
        		fact.setCif_proveedor(new SimpleStringProperty(rs.getString("cif_proveedor")));
        		fact.setRaz_proveedor(new SimpleStringProperty(rs.getString("raz_proveedor")));
        		fact.setNum_factura(new SimpleIntegerProperty(rs.getInt("num_factura")));
        		fact.setDes_factura(new SimpleStringProperty(rs.getString("des_factura")));
        		fact.setBas_imponible(new SimpleFloatProperty(rs.getFloat("bas_imponible")));
        		fact.setIva_importe(new SimpleFloatProperty(rs.getFloat("iva_importe")));
        		fact.setTot_importe(new SimpleFloatProperty(rs.getFloat("tot_importe")));
        		fact.setFec_factura(new SimpleObjectProperty<Date>(rs.getDate("fec_factura")));
        		fact.setFec_vencimiento(new SimpleObjectProperty<Date>(rs.getDate("fec_vencimiento")));
                   
        		listaFacturas.add(fact);
        	}
        	
        	rs.close();
        	stmt.close();
        	db.close();
        	
        }catch(SQLException e){
              e.printStackTrace();
        }
       
        return listaFacturas;
    }
    
    public Factura buscarFactura(int numFactura, String cif) {
    	conectarBDD();
    	Factura fact = new Factura();
    	
        try{
        	Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM FACT_PROV WHERE (num_Factura = " + numFactura + " AND cif_proveedor = \"" + cif + "\");");
            
            while(rs.next()){
            	fact.setCif_proveedor(rs.getString("cif_proveedor"));
        		fact.setRaz_proveedor(rs.getString("raz_proveedor"));
        		fact.setNum_factura(rs.getInt("num_factura"));
        		fact.setDes_factura(rs.getString("des_factura"));
        		fact.setBas_imponible(rs.getFloat("bas_imponible"));
        		fact.setIva_importe(rs.getFloat("iva_importe"));
        		fact.setFec_factura(rs.getDate("fec_factura"));
        		fact.setFec_vencimiento(rs.getDate("fec_vencimiento"));
            }
            
            rs.close();
            stmt.close();
            db.close();
            
        }catch(SQLException e){
              e.printStackTrace();
        }
        
        return fact;
    }
    
    public void actulizarFact(Factura fac, int numOriginal, String cifOriginal) {
        try{
        	conectarBDD();
        	Factura fact = fac;
            db.setAutoCommit(false);
            
            PreparedStatement ps = db.prepareStatement("UPDATE FACT_PROV SET"
            		+ " cif_proveedor = ?,"
            		+ "	raz_proveedor = ?,"
            		+ " num_factura = ?,"
            		+ " des_factura = ?,"
            		+ " bas_imponible = ?,"
            		+ " iva_importe = ?,"
            		+ " fec_factura = ?,"
            		+ " fec_vencimiento = ?"
            		+ " WHERE num_factura = ? AND cif_proveedor = ?");
            
            ps.setString(1, fact.getCif_proveedor());
            ps.setString(2, fact.getRaz_proveedor());
            ps.setInt(3, fact.getNum_factura());
            ps.setString(4, fact.getDes_factura());
            ps.setFloat(5, fact.getBas_imponible());
            ps.setFloat(6, fact.getIva_importe());
            ps.setDate(7, UtilToSql(fact.getFec_factura()));
            ps.setDate(8, UtilToSql(fact.getFec_vencimiento()));
            
            ps.setInt(9, numOriginal);
            ps.setString(10, cifOriginal);

            ps.executeUpdate();
            
            db.commit();
            db.setAutoCommit(true);
            
            ps.close();
            db.close();
        }
        catch(SQLException e){
              try{
                   db.rollback();
              }
              catch(SQLException ex){
                   ex.printStackTrace();
              }
        }
        
    }
    
    public void borrarFact(int numFact, String cif) {
    	try{
    		conectarBDD();
    		db.setAutoCommit(false);
    		
    		PreparedStatement ps = db.prepareStatement("DELETE FROM FACT_PROV WHERE num_factura = (?) AND cif_proveedor = (?)");
            ps.setInt(1, numFact);
            ps.setString(2, cif);
            ps.executeUpdate();
            
            db.commit();
            db.setAutoCommit(true);
            
            ps.close();
            db.close();
            
    	}catch(SQLException e){
            try{
                 db.rollback();
                 
            }catch(SQLException ex){
                 ex.printStackTrace();
            }
            
    	}
    	
    }
    
    
    /*
     * Métodos con Proveedores
     */
    
    public ArrayList<Proveedor> listaProveedores() {
    	conectarBDD();
        ArrayList<Proveedor> listaProveedores = new ArrayList<Proveedor>();
       
        try{
        	Statement stmt = db.createStatement();
        	ResultSet rs = stmt.executeQuery("SELECT * FROM PROV_COMP");
        	
        	Proveedor prov;
        	while(rs.next()){
        		prov = new Proveedor();
        		
        		prov.setCif_proveedor(rs.getString("cif_proveedor"));
        		prov.setRaz_proveedor(rs.getString("raz_proveedor"));
        		prov.setReg_notarial(rs.getInt("reg_notarial"));
        		prov.setSeg_responsabilidad(rs.getInt("seg_responsabilidad"));
        		prov.setSeg_importe(rs.getFloat("seg_importe"));
        		prov.setFec_homologacion(rs.getDate("fec_homologacion"));
                   
        		listaProveedores.add(prov);
        	}
        	
        	rs.close();
        	stmt.close();
        	db.close();
        	
        }catch(SQLException e){
              e.printStackTrace();
        }
       
        return listaProveedores;
    }
    
    public Proveedor buscarProveedor(String cif) {
    	conectarBDD();
    	Proveedor prov = new Proveedor();
    	
        try{
        	Statement stmt = db.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PROV_COMP WHERE cif_proveedor = \"" + cif + "\";");
            
            while(rs.next()){
            	prov.setCif_proveedor(rs.getString("cif_proveedor"));
        		prov.setRaz_proveedor(rs.getString("raz_proveedor"));
        		prov.setReg_notarial(rs.getInt("reg_notarial"));
        		prov.setSeg_responsabilidad(rs.getInt("seg_responsabilidad"));
        		prov.setSeg_importe(rs.getFloat("seg_importe"));
        		prov.setFec_homologacion(rs.getDate("fec_homologacio"));
            }
            
            rs.close();
            stmt.close();
            db.close();
            
        }catch(SQLException e){
              e.printStackTrace();
        }
        
        return prov;
    }
    
    public void actulizarProv(Proveedor proveedor, String cifOriginal) {
        try{
        	conectarBDD();
        	Proveedor prov = proveedor;
            db.setAutoCommit(false);
            
            PreparedStatement ps = db.prepareStatement("UPDATE PROV_COMP SET"
            		+ " cif_proveedor = ?,"
            		+ "	raz_proveedor = ?,"
            		+ " reg_notarial = ?,"
            		+ " seg_responsabilidad = ?,"
            		+ " seg_importe = ?,"
            		+ " fec_homologacio = ?"
            		+ " WHERE cif_proveedor = ?");
            
            ps.setString(1, prov.getCif_proveedor());
            ps.setString(2, prov.getRaz_proveedor());
            ps.setInt(3, prov.getReg_notarial());
            ps.setInt(4, prov.getSeg_responsabilidad());
            ps.setFloat(5, prov.getSeg_importe());
            ps.setDate(6, UtilToSql(prov.getFec_homologacion()));
            
            ps.setString(7, cifOriginal);

            ps.executeUpdate();
            
            db.commit();
            db.setAutoCommit(true);
            
            ps.close();
            db.close();
        }
        catch(SQLException e){
              try{
                   db.rollback();
              }
              catch(SQLException ex){
                   ex.printStackTrace();
              }
        }
        
    }
    
    public void borrarProv(String CIF) {
    	try{
    		conectarBDD();
    		db.setAutoCommit(false);
    		
    		PreparedStatement ps = db.prepareStatement("DELETE FROM PROV_COMP WHERE cif_proveedor = (?)");
            ps.setString(1, CIF);
            ps.executeUpdate();
            
            db.commit();
            db.setAutoCommit(true);
            
            ps.close();
            db.close();
            
    	}catch(SQLException e){
            try{
                 db.rollback();
                 
            }catch(SQLException ex){
                 ex.printStackTrace();
            }
            
    	}
    	
    }
}
