package personaSerializacion;

import java.io.Serializable;

/**
 * Clase que representa a una persona
 * @author Santiago Faci
 * 
 */
public class Persona implements Serializable {


	/**
	 * 
	 *///identificador único para esta clase, si no lo tiene no funciona
	private static final long serialVersionUID = -8439451204828473150L;
	
	private String nombre;
	private String apellidos;
	private int puntos;
	
	transient private int edad;//sirve para no serializar, no se transferirán
	
	public Persona(String nombre, String apellidos, int puntos) {
		
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.puntos = puntos;
	}
	
	public Persona() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	
	@Override
	public String toString() {
		return ("[Nombre: "+nombre+" Apellidos: "+apellidos+" Puntos: "+puntos+"]");
	}
	
}
