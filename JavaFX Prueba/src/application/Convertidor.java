package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Convertidor {
	
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
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		ArrayList<Proveedor> proveedores = new ArrayList<>();
		
		 try {	 
		    DocumentBuilder builder = factory.newDocumentBuilder();
		    Document document = (Document) builder.parse(new File(ruta));
		    document.getDocumentElement().normalize();

		    //crea una lista con todos los nodos empleado  
		    NodeList empleados = ((org.w3c.dom.Document) document).getElementsByTagName("proveedor");
		    Proveedor prov;
		    
		    //recorrer la lista  
		    for (int i = 0; i < empleados.getLength(); i ++)
		    {
		    	Node emple = empleados.item(i); //obtener un nodo empleado
		    	if (emple.getNodeType() == Node.ELEMENT_NODE)//tipo de nodo
		    	{
		    		//obtener los elementos del nodo           
		    		Element elemento = (Element) emple;	
		    		prov = new Proveedor();
		    		prov.setCif_proveedor(elemento.getElementsByTagName("cif_proveedor").item(0).getTextContent());
		    		prov.setFec_homologacion(StringToDate(elemento.getElementsByTagName("fec_homologacion").item(0).getTextContent()));
		    		prov.setRaz_proveedor(elemento.getElementsByTagName("raz_proveedor").item(0).getTextContent());
		    		prov.setReg_notarial(Integer.parseInt(elemento.getElementsByTagName("reg_notarial").item(0).getTextContent()));
		    		prov.setSeg_importe(Float.parseFloat(elemento.getElementsByTagName("seg_importe").item(0).getTextContent()));
		    		prov.setSeg_responsabilidad(Integer.parseInt(elemento.getElementsByTagName("seg_responsabilidad").item(0).getTextContent()));
		    		
		    		proveedores.add(prov);
		    	}
		    }
		    
		  } catch (Exception e) {
			  e.printStackTrace();
		  }
		
		return proveedores;
		
	}
	
	public ArrayList<Proveedor> leerProveedoresJSON(String ruta){
		
		//Guardamos el Json en un String
		String fichero = "";
		try (BufferedReader br = new BufferedReader(new FileReader(ruta))) {
		    String linea;
		    while ((linea = br.readLine()) != null) {
		        fichero += linea;
		    }

		} catch (FileNotFoundException e) {
		    e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		//Del String "fichero" sacamos el array de proveedores
		JsonParser parser = new JsonParser();
		JsonArray gsonArr = parser.parse(fichero).getAsJsonArray();
		Proveedor prov;
		ArrayList<Proveedor> proveedores = new ArrayList<>();
		
		//Recorremos el array, guardamos los datos en el proveedor y el proveedor en la lista
		for (JsonElement obj : gsonArr) {
			JsonObject temp = obj.getAsJsonObject();
			
			try {
				prov = new Proveedor();
				prov.setCif_proveedor(temp.get("cif_proveedor").getAsString());
				prov.setFec_homologacion(StringToDate(temp.get("fec_homologacion").getAsString()));
				prov.setRaz_proveedor(temp.get("raz_proveedor").getAsString());
				prov.setReg_notarial(temp.get("reg_notarial").getAsInt());
				prov.setSeg_importe(temp.get("seg_importe").getAsFloat());
				prov.setSeg_responsabilidad(temp.get("seg_responsabilidad").getAsInt());
				proveedores.add(prov);
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
		}
		
		return proveedores;
		
	}
	
	public Date StringToDate(String cad) throws ParseException {
	    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(cad);  
	    return date;
	}
}
