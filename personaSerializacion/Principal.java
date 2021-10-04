package personaSerializacion;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class Principal {

	public void escribirObjeto(Object objeto) {
	
		FileOutputStream fichero = null;
		ObjectOutputStream serializador = null;
		
		try {
			fichero = new FileOutputStream("personaSerializada.dat");
			serializador = new ObjectOutputStream(fichero);

            serializador.writeObject(objeto);
			
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (serializador != null) {
				try {
					serializador.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		}
	}
	
	public Object leerObjeto() {
		
		FileInputStream fichero = null;
		ObjectInputStream serializador = null;
		Object objeto = null;
		
		try {
			fichero = new FileInputStream("personaSerializada.dat");
			serializador = new ObjectInputStream(fichero);
			
			objeto = serializador.readObject();
		
		} catch (FileNotFoundException fnfe) {
			System.out.println("No se encuentra el fichero especificado");
		} catch (ClassNotFoundException cnfe) {
			System.out.println("Tipo de objeto no conocido");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (serializador != null) 
				try {
					serializador.close();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
		}
		
		return objeto;
	}
	
	public Persona buscaPersona(ArrayList<Persona> listaPersonas, String nombre, String apellidos) {
		
		Persona persona = null;
		boolean encontrada = false;
		
		for (int i = 0;(i < listaPersonas.size() && !encontrada); i++) {
			
			persona = listaPersonas.get(i);
			
			if (persona.getNombre().equals(nombre) && persona.getApellidos().equals(apellidos)) {
				encontrada = true;
			}
		}
		
		if (encontrada) 
			return persona;
		
		return null;
	}
	
	
	public static void main(String args[]) {
		
		ArrayList<Persona> listaObjetos = new ArrayList<Persona>();		
		
//		int contador=25;
//		for (int i = 0; i < contador; i++) {
//			listaObjetos.add(new Persona("Nom"+i,"Ape"+i,i*10));
//		}
//		
		Principal principal = new Principal();
		
//		Buscamos a la persona Nom1
//		if (principal.buscaPersona(listaObjetos, "Nom1", "Ape1") == null) {
//			System.out.println("No encontrado");
//		} else {
//			System.out.println("Sí Encontrado");
//		}
//		
//		System.out.println("Escribimos la lista de objetos serializados en el fichero");
//		principal.escribirObjeto(listaObjetos);
		
		System.out.println("Leemos la lista de objetos serializada guardada en el fichero");
		ArrayList<Persona> listaObjetos2 = (ArrayList<Persona>) principal.leerObjeto();
		
		System.out.println("Mostramos la lista de objetos leída y deserializada desde el fichero: \n"+listaObjetos.toString());
		
		//Buscamos a la persona Nom2
		if (principal.buscaPersona(listaObjetos, "Nom2", "Ape2") == null) {
			System.out.println("No encontrado");
		} else {
			System.out.println("Sí encontrado");
		}
	}
}
