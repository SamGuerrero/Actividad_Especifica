package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;


public class Convertidor {
	
	/*
	 * Factura
	 */
	static int REFERENCIA = 5;
	
	public Factura leerFactura(String ruta) throws FileNotFoundException {
		Factura fact = new Factura();
		
		try{
			File fic = new File(ruta);//declara fichero
		    BufferedReader fichero = new BufferedReader(new FileReader(fic)); 
		    
		    //Leo lineas
		    String[] lineas = new String[9];
		    String linea;
		    int i = 0;
		    while((linea = fichero.readLine())!= null) {
		    	lineas[i] = linea;
		    	i++;
		    }
		    
		    //Declaro atributos propios de Factura
		    int num_factura = Integer.parseInt(lineas[0].substring(REFERENCIA));
			String cif_proveedor = lineas[1].substring(REFERENCIA);
			String raz_proveedor = lineas[2].substring(REFERENCIA);
			String des_factura = lineas[3].substring(REFERENCIA);
			float bas_imponible = Float.parseFloat(lineas[4].substring(REFERENCIA));
			float iva_importe = Float.parseFloat(lineas[5].substring(REFERENCIA));
			float tot_importe = Float.parseFloat(lineas[6].substring(REFERENCIA));
			Date fec_factura = StringToDate(lineas[7].substring(REFERENCIA));
			Date fec_vencimiento = StringToDate(lineas[8].substring(REFERENCIA));
		    
			//Paso atributos a Factura
			fact.setNum_factura(num_factura);
			fact.setCif_proveedor(cif_proveedor );
			fact.setRaz_proveedor(raz_proveedor);
			fact.setDes_factura(des_factura);
			fact.setBas_imponible(bas_imponible);
			fact.setIva_importe(iva_importe);
			fact.setTot_importe(tot_importe);
			fact.setFec_factura(fec_factura);
			fact.setFec_vencimiento(fec_vencimiento);
			
		    fichero.close(); 
		      
		}catch (FileNotFoundException fn ){                      
			JOptionPane.showMessageDialog(null, "No se encuentra el archivo", "Cargar Factura", JOptionPane.ERROR_MESSAGE);
			return null;
        
		}catch (IOException io) {
			JOptionPane.showMessageDialog(null, "Error de Entrada", "Cargar Factura", JOptionPane.ERROR_MESSAGE);
			return null;
               
        } catch (ParseException e) {
        	JOptionPane.showMessageDialog(null, "Error con la fecha", "Cargar Factura", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return fact;
		
	}
	
	public Date StringToDate(String cad) throws ParseException {
	    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(cad);  
	    return date;
	}
	
	/*
	 * Proveedores
	 */
	
	public ArrayList<Proveedor> leerProveedores(String ruta){
		ArrayList<Proveedor> proveedores = new ArrayList<>();
		
		if (ruta.endsWith(".xml"))
			proveedores = leerProveedoresXML(ruta);
		else if (ruta.endsWith(".json"))
			proveedores = leerProveedoresJSON(ruta);
		else {
			JOptionPane.showMessageDialog(null, "El archivo cargado debe ser XML o JSON", "Cargar Proveedor", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return proveedores;
	}
	
	public ArrayList<Proveedor> leerProveedoresXML(String ruta) {

		//Inicializo XStream
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("proveedores", ListaProveedores.class);
		xstream.alias("proveedor", Proveedor.class);
		xstream.addImplicitCollection(ListaProveedores.class, "proveedores");
		
		//Obtengo la lista
		ListaProveedores lista = (ListaProveedores) xstream.fromXML(new File(ruta));
		
		return lista.getProveedores();
		
	}
	
	public ArrayList<Proveedor> leerProveedoresJSON(String ruta){
		//Guardamos el Json en un String
		String json = "";
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        json += linea;
		    }

		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		Type listType = new TypeToken<ArrayList<Proveedor>>(){}.getType();
		ArrayList<Proveedor> proveedores = gson.fromJson(json, listType);

		return proveedores;
	}
	
	public void exportarXML(File fichero) {
		//Obtengo la lista de proveedores
		AccesoBDD db = new AccesoBDD();
		ArrayList<Proveedor> proveedores = db.observableToArray(db.listaProveedores());
		ListaProveedores lista = new ListaProveedores(proveedores);
		
		//Inicializo el XStrem y la convierto a string (formato xml)
		XStream xstream = new XStream(new DomDriver());
		xstream.alias("proveedores", ListaProveedores.class);
		xstream.alias("proveedor", Proveedor.class);
		xstream.addImplicitCollection(ListaProveedores.class, "proveedores");
		String xml = xstream.toXML(lista);
		
		//Escribo el String en el fichero
		char[] cad = xml.toCharArray();
		try {
			FileWriter writer = new FileWriter(fichero);
			
			for (int i = 0; i < cad.length; i++)
				writer.write(cad[i]);
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void exportarJSON(File fichero) {
		//Obtengo la lista de proveedores
		AccesoBDD db = new AccesoBDD();
		ArrayList<Proveedor> proveedores = db.observableToArray(db.listaProveedores());
		
		//Obtengo un String (formateado en json)
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String json = gson.toJson(proveedores);
		
		//Escribo el String en el fichero
		char[] cad = json.toCharArray();
		try {
			FileWriter writer = new FileWriter(fichero);
			
			for (int i = 0; i < cad.length; i++)
				writer.write(cad[i]);
			writer.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
