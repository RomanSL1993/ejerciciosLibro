package ejerciciosPropios;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CrearFIcheroConfiguracion {


	static void crearConfig(String eleccion) {
	
		Properties configuracion = new Properties();
		
		configuracion.setProperty("Serializacion", eleccion);
		
		try  {
			
			configuracion.store(new FileOutputStream("futbol.props"),"Fichero de config");
			
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace(); 
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}

		
	}
	
	
	static void leerConfig() {
		Properties configuracion = new Properties();
		  String usuario="";
		
		try {
		  configuracion.load(new FileInputStream("configuracion.props"));
		  usuario = configuracion.getProperty("Serializacion");
		} catch (FileNotFoundException fnfe ) { 
		  fnfe.printStackTrace();
		} catch (IOException ioe) { 
		  ioe.printStackTrace();
		}
	
		System.out.println("Serializacion: "+ usuario);
		
}
		
		

		public static void main(String[] args) {
			String eleccion="Futbolista";

			crearConfig(eleccion);
			
			leerConfig();
			
		}

}
