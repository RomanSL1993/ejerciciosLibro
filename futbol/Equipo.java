package futbol;

import java.time.LocalDate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * The Class Equipo.
 * Opcional: considerar la funcionalidad de hacer fichajes. Tener en cuenta que fichar a un futbolista implica darlo de baja en su respectivo equipo.
 * 
 * @author Javier Faus Llopis
 */
public class Equipo implements Serializable{
	private static final long serialVersionUID = 0;
	private String nombre;
	private ArrayList<Futbolista> aEquipo;
	private Entrenador entrenador;
	private String localidad;
	private LocalDate fechFundacion;
	private ArrayList<Futbolista> alineacion;//los once jugadores que seran titulares en un determinado partido
	private float patrimonio;
	private String nombrePresidente;
	private String [] equipacion; // tres equipaciones. Ej: 1."Blanco" , 2. "Azul", 3. "BlanquiAzul"  

	private int puntos=0; // representa los puntos del equipo en una competicion
	private int partidosGanados=0;
	private int partidosPerdidos=0;
	private int partidosEmpatados=0;

	
	@Override
	public String toString() {
		return "Equipo [nombre=" + nombre + ", aEquipo=" + aEquipo + ", entrenador=" + entrenador + ", localidad="
				+ localidad + ", fechFundacion=" + fechFundacion + ", alineacion=" + alineacion 
				+ ", patrimonio=" + patrimonio + ", nombrePresidente=" + nombrePresidente + ", equipacion="
				+ Arrays.toString(equipacion) + ", puntos=" + puntos + ", partidosGanados=" + partidosGanados
				+ ", partidosPerdidos=" + partidosPerdidos + ", partidosEmpatados=" + partidosEmpatados + "]";
	}

		//contructor
		public Equipo(String nombre, ArrayList<Futbolista> aEquipo, Entrenador entrenador, String localidad,
				float patrimonio,String nombrePresidente) {
			super();
			this.nombre = nombre;
			this.aEquipo = aEquipo;
			this.entrenador = entrenador;
			this.alineacion=entrenador.ponerAlineacion(aEquipo);
			this.localidad = localidad;
			this.patrimonio = patrimonio;
			this.nombrePresidente = nombrePresidente;
		}
		
	// implementar todos los métodos
	
	public Equipo() {
			// TODO Auto-generated constructor stub
		}

	public double calcValor() {//Capacidad de ganar un partido, es la media de sus jugadores alineados y su entrenador.
		double valor=0.0;
		for(int i=0;i<this.alineacion.size();i++) {
			valor+=this.alineacion.get(i).calcValor();
		}
		valor+=this.entrenador.calcValor();
		valor/=(this.alineacion.size()+1);
		return valor; 
	} 
	public  void anyadirPuntos(int puntos) {
		if(puntos==3) {
			this.partidosGanados+=1;
		}else if(puntos==1) {
			this.partidosEmpatados+=1;
		}else if(puntos==0) {
			this.partidosPerdidos+=1;
		}
		this.puntos+=puntos;
	}
	public  void actualizarPatrimonio (float dinero) {
		this.patrimonio+=dinero;
	}
	public  void anyadirFutbolista (Futbolista fichaje) {
		this.aEquipo.add(fichaje);
	}
	public  void eliminarFutbolista (Futbolista despedido) {
		this.aEquipo.remove(despedido);
	}
	public  void cambiarEntrenador (Entrenador nuevo) {
		this.entrenador=nuevo;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the aEquipo
	 */
	public ArrayList<Futbolista> getaEquipo() {
		return aEquipo;
	}
	/**
	 * @param aEquipo the aEquipo to set
	 */
	public void setaEquipo(ArrayList<Futbolista> aEquipo) {
		this.aEquipo = aEquipo;
	}
	/**
	 * @return the entrenador
	 */
	public Entrenador getEntrenador() {
		return entrenador;
	}
	/**
	 * @param entrenador the entrenador to set
	 */
	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}
	/**
	 * @return the localidad
	 */
	public String getLocalidad() {
		return localidad;
	}
	/**
	 * @param localidad the localidad to set
	 */
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	/**
	 * @return the fechFundacion
	 */
	public LocalDate getFechFundacion() {
		return fechFundacion;
	}
	/**
	 * @param fechFundacion the fechFundacion to set
	 */
	public void setFechFundacion(LocalDate fechFundacion) {
		this.fechFundacion = fechFundacion;
	}
	/**
	 * @return the alineacion
	 */
	public ArrayList<Futbolista> getAlineacion() {
		return alineacion;
	}
	/**
	 * @param alineacion the alineacion to set
	 */
	public void setAlineacion(ArrayList<Futbolista> alineacion) {
		this.alineacion = this.entrenador.ponerAlineacion(this.aEquipo);;
	}
	/**
	 * @return the patrimonio
	 */
	public float getPatrimonio() {
		return patrimonio;
	}
	/**
	 * @param patrimonio the patrimonio to set
	 */
	public void setPatrimonio(float patrimonio) {
		this.patrimonio = patrimonio;
	}
	/**
	 * @return the nombrePresidente
	 */
	public String getNombrePresidente() {
		return nombrePresidente;
	}
	/**
	 * @param nombrePresidente the nombrePresidente to set
	 */
	public void setNombrePresidente(String nombrePresidente) {
		this.nombrePresidente = nombrePresidente;
	}
	/**
	 * @return the equipacion
	 */
	public String[] getEquipacion() {
		return equipacion;
	}
	/**
	 * @param equipacion the equipacion to set
	 */
	public void setEquipacion(String[] equipacion) {
		this.equipacion = equipacion;
	}
	/**
	 * @return the puntos
	 */
	public int getPuntos() {
		return puntos;
	}
	/**
	 * @param puntos the puntos to set
	 */
	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	/**
	 * @return the patidosGanados
	 */
	public int getPatidosGanados() {
		return partidosGanados;
	}
	/**
	 * @param patidosGanados the patidosGanados to set
	 */
	public void setPatidosGanados(int patidosGanados) {
		this.partidosGanados = patidosGanados;
	}
	/**
	 * @return the partidosPerdidos
	 */
	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}
	/**
	 * @param partidosPerdidos the partidosPerdidos to set
	 */
	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}
	/**
	 * @return the partidosEmpatados
	 */
	public int getPartidosEmpatados() {
		return partidosEmpatados;
	}
	/**
	 * @param partidosEmpatados the partidosEmpatados to set
	 */
	public void setPartidosEmpatados(int partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}
	
}
