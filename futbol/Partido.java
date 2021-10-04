package futbol;

import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Opcional: Hacer seguimiento de los jugadores sancionados y/o lesionados. Dicha informacion se pasara a los equipos/competicion tras cada partido.
 * 
 */

public class Partido {
	
	Equipo local;
	Equipo visitante;
	int [] resultado;
	String localidad;
	Futbolista mvp;
	ArrayList<Futbolista> goleadores;
	//Futbolista [] sancionados;
	//Futbolista [] lesionados;
	//Tarjeta [] listaSanciones; // ¿como hacerlo?
	
	LocalDate fecha;
	
	/*
	 * Hara los calculos estadisticos pertinentes para calcular el resultado. En base a el valor()
	 */
	public Equipo calcularGanador() {
		double l=0, v=0;
		/*
		 * Modificamos +-10% aleatoriamente el valor de cada equipo que juega el partido. El ganador del partido será el que tenga mayor valor una vez modificado el 10% aleatorio.
		 * El empate se produce cuando la diferencia de puntos es menor a diez.
		 * 
		 */
		double valorAleatorio = Math.random()*10;		
		l=Math.floor(this.local.calcValor()*(1+(valorAleatorio/100)));		
		v=Math.floor(this.visitante.calcValor()*(1-(valorAleatorio/100)));
		
		if(l>v) {
//			System.out.println("Gana Local"+l);
//			System.out.println("Pierde visitante Visitante"+v);
			local.anyadirPuntos(3);
			visitante.anyadirPuntos(0);
			return local;
		}else if(l<v){
//			System.out.println("Gana Visitante"+v);
//			System.out.println("Pierde Local"+l);
			local.anyadirPuntos(0);
			visitante.anyadirPuntos(3);
			return visitante;
		}else {
			local.anyadirPuntos(1);
			visitante.anyadirPuntos(1);
			return local;
		}
	}
	
	public void calcularMVP() {
		/*En caso de empate a cero se le asigna a suerte a un portero
		 * si hay un máximo goleador a él 
		 * si hay más de uno se sortea a partes iguales.
		 */
		
	}
/*	
	public Equipo getGanador() {//En caso de empate resultado null.
			
	}
	
	public Equipo getPerdedor() {//En caso de empate resultado null.
		
	}
	
	private Futbolista[] getGoleadores() {//Obtenemos del calculo quienes son los goleadores.
		
	}
	
	
*/	
	public void calcularGoleadores() {//Calcula quienes son los goleadores.
		/*Para la asignacion del gol el porcentaje según posición es:
		 * Delantero 70%, Centro 20%, Defensa 9% y portero 1%.
		 * Y después dividiremos entre los jugadores que hayan en esa categoría.
		 */
		
	}
	
	/*
	 * Ganador: 3puntos
	 * Empate: 1 punto cada equipo.
	 * Perdedor: 0 puntos.
	 * 
	 *  
	 */
	public void asignarPuntos () {
		// ¿Dónde se apuntan los puntos?
	    //Dentro de cada equipo aquí
		
		
	}

	public Partido(Equipo local, Equipo visitante) {
		super();
		this.local = local;
		this.visitante = visitante;
	}
	
//	public int getGolesLocal(){}
//	public int getGolesVisitante(){}
//	
//	public int getGoles(Equipo equipo) {}
	
	
	
	
	
	
}