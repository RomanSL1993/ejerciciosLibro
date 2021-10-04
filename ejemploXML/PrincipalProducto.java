package ejemploXML;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class PrincipalProducto {

	public static void escribirXML(ArrayList<Producto> listaProductos) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			DOMImplementation dom = builder.getDOMImplementation();
			documento = dom.createDocument(null,  "xml", null);
			
			Element raiz = documento.createElement("Productos");
			documento.getDocumentElement().appendChild(raiz);
			
			Element nodoProducto = null, nodoDatos = null;
			Text texto = null;
			for (Producto Producto : listaProductos) {
				nodoProducto = documento.createElement("Producto");
				raiz.appendChild(nodoProducto);
				
				nodoDatos = documento.createElement("nombre");
				nodoProducto.appendChild(nodoDatos);
				
				texto = documento.createTextNode(Producto.getNombre());
				nodoDatos.appendChild(texto);
				
				nodoDatos = documento.createElement("precio");
				nodoProducto.appendChild(nodoDatos);
				
				texto = documento.createTextNode(String.valueOf(Producto.getPrecio()));
				nodoDatos.appendChild(texto);
			}
			
			Source source = new DOMSource(documento);
			Result resultado = new StreamResult(new File("archivoProductos.xml"));
			
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, resultado);
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerConfigurationException tce) {
			tce.printStackTrace();
		} catch (TransformerException te) {
			te.printStackTrace();
		}
	}
	
		
	
	
	@SuppressWarnings({ "finally", "null" })
	public static ArrayList<Producto> leerFicheroXML() {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		Document documento = null;
		ArrayList<Producto> listaProductos = new ArrayList<Producto>();
		
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			documento = builder.parse(new File("archivoProductos.xml"));
			
			// Recorre cada uno de los nodos 'Producto'
			NodeList Productos = documento.getElementsByTagName("Producto");
			for (int i = 0; i < Productos.getLength(); i++) {
				Node Producto = Productos.item(i);
				Element elemento = (Element) Producto;

				Producto p=new Producto(String.valueOf(elemento.getElementsByTagName("nombre").item(0).getChildNodes().item(0).getNodeValue()),
						Float.parseFloat(elemento.getElementsByTagName("precio").item(0).getChildNodes().item(0).getNodeValue()));
				listaProductos.add(p);
			}
			return listaProductos;
			
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} catch (SAXException saxe) {
			saxe.printStackTrace();
		}
			return listaProductos;
		
		
		
	}
	
	
	public static void main(String[] args) {

		ArrayList<Producto> listaProductos = null;
		
		Producto Producto1 = new Producto("Producto1", (float) 1.1);
		Producto Producto2 = new Producto("Producto2", (float) 4.5);
		Producto Producto3 = new Producto("Producto3", (float) 6.2);
		
		listaProductos = new ArrayList<Producto>();
		listaProductos.add(Producto1);
		listaProductos.add(Producto2);
		listaProductos.add(Producto3);
		
//		Principal principal = new Principal();
		escribirXML(listaProductos);
//		listaProductos.clear();
		listaProductos=leerFicheroXML();
		
		for(int i=0;i<listaProductos.size();i++) {
			System.out.println(listaProductos.get(i).getNombre()+"->"+listaProductos.get(i).getPrecio());
		}
	}

}
