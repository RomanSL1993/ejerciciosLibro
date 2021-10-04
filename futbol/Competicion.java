package futbol;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public class Competicion implements Serializable{
	private static final long serialVersionUID = 0;
	int[] eq;
	String[][] matriz1,matriz2,jornadas,jornadas2;
	ArrayList<Equipo> equipos= new ArrayList<Equipo>();
	ArrayList<Partido> partidos=new ArrayList<Partido>();
	ArrayList<Equipo> clasificacion= new ArrayList<Equipo>();
	ArrayList<Futbolista> jugadores=new ArrayList<Futbolista>();
	ArrayList<Entrenador> entrenadores=new ArrayList<Entrenador>();
	
	
	public void ordenarPartidosCompeticion() {
		//Pasos sugeridos, no obligatorios.
		//1. Crear un ArrayList partidos en el que guardará los partidos a jugar (tamaño de "partidos" depende de tamaño de "equipos").
		//2. Computar las combinaciones de partidos posibles.
		//3. "Apuntar" las combinaciones en el ArrayList partidos.
		//4. Invocar el método jugarLiga();
		/*Orden rápido
		for(int i=0;i<equipos.size();i++) {
			int j=0;
			while(j<equipos.size()) {
				if(j==i) {
					j++;
				}else {
					Partido p=new Partido(equipos.get(i), equipos.get(j));
					partidos.add(p);
					j++;
				}
			}
		}*/
		
		//orden profesional
		
		
		jugarLiga();
	}
	
	public void Calendario(int N){
//		System.out.println("Llega calendario");
		//eq = new int[N];
		//Asigno posiciones del array a los eq
//		for (int i=0;i<N;i++){
//			eq[i] = i+1;
//		}
		
		matriz1 = new String[N-1][N/2];
		matriz2 = new String[N-1][N/2];
		jornadas = new String[N-1][N/2]; //primera vuelta
		jornadas2 = new String[N-1][N/2]; //segunda vuelta 
		
		int cont = 0;
		int cont2 = N-2;
//		System.out.println(cont+" "+cont2);
		for(int i=0;i<N-1;i++){
			for(int j=0;j<N/2;j++){
				//matriz1
				matriz1[i][j] = String.valueOf(equipos.get(cont).getNombre());
				cont++;
				if(cont==(N-1)) 
					cont=0;
				
				//matriz2
				if(j==0) {
					matriz2[i][j] = String.valueOf(equipos.get(N-1).getNombre());
				}
				else {
					matriz2[i][j] = String.valueOf(equipos.get(cont2).getNombre());
					cont2--;
					if(cont2==-1) cont2 = N-2;
				}
				
				//Elaboro la matriz final de enfrentamientos por jornada (primera vuelta)
				if(j==0){
					if(i%2==0) {
						jornadas[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
						Partido p=new Partido(equipos.get(i), equipos.get(j));
						partidos.add(p);
					}						
					else {
						jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
						Partido p=new Partido(equipos.get(i), equipos.get(j));
						partidos.add(p);
					}						
				}
				else {
					jornadas[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
					Partido p=new Partido(equipos.get(i), equipos.get(j));
					partidos.add(p);
				}
					
				
				
				//segunda vuelta - al reves que la primera
				if(j==0){
					if(i%2==0) {
						jornadas2[i][j] = matriz1[i][j] + "-" + matriz2[i][j] + " ";
						Partido p=new Partido(equipos.get(j), equipos.get(i));
						partidos.add(p);
					}						
					else {
						jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
						Partido p=new Partido(equipos.get(j), equipos.get(i));
						partidos.add(p);
					}
						
				}
				else {
					jornadas2[i][j] = matriz2[i][j] + "-" + matriz1[i][j] + " ";
					Partido p=new Partido(equipos.get(j), equipos.get(i));
					partidos.add(p);
				}					
			}
		}	
		int jorn = 1;
		int jugar=0;
		for(int i=0;i<N-1;i++){
			System.out.print("Jornada"+jorn+" ");
			for(int j=0;j<N/2;j++){
				System.out.print(jornadas[i][j]);
//				equipos.get(equipos.indexOf(partidos.get(jugar).calcularGanador()));
//				jugar++;
				if(j==(N/2)-1) System.out.println();				
			}
//			crearClasificacion();
//			mostrarClasificacion();
			jorn++;
		}
		System.out.println();
		jorn = N;
		for(int i=0;i<N-1;i++){
			System.out.print("Jornada"+jorn+" ");
			for(int j=0;j<N/2;j++){
				System.out.print(jornadas2[i][j]);
//				equipos.get(equipos.indexOf(partidos.get(jugar).calcularGanador()));
//				jugar++;
				if(j==(N/2)-1) System.out.println();
			}
//			crearClasificacion();
//			mostrarClasificacion();
			jorn++;
		}
	}
	
	public void jugarLiga() {
		//1. Procesar el ArrayList partidos jugando todos los partidos que contiene.
		//2. Recuerda actualizar puntos, etc a cada partido que es jugado.
		Calendario(equipos.size());
		for(int i=0;i<partidos.size();i++) {
			partidos.get(i).calcularGanador();			
		}		
	}
	
	
	public void crearDatosEquipos (int numEquipos) {
		this.entrenadores.addAll(crearEntrenadores(numEquipos));
		this.jugadores.addAll(crearJugadores(22*numEquipos));
//		mostrarJugadores();
		this.equipos.addAll(crearEquipos(numEquipos,entrenadores,jugadores));
//		mostrarEquipos();
	}
	
	public void crearEquipo () {
		this.entrenadores=crearEntrenadores(1);
		this.jugadores=crearJugadores(22);
		this.equipos.addAll(crearEquipos(1, entrenadores, jugadores));
	}
	

	private ArrayList<Entrenador> crearEntrenadores(int numEquipos) {
		// TODO Auto-generated method stub
		ArrayList<Entrenador> e=new ArrayList<Entrenador>();
		for(int i=0;i<numEquipos;i++) {
			String nombre="Entrenador"+i;
			LocalDate fecha=LocalDate.now();
			float altura= (float) (Math.round((Math.random()*(2.3-1.5)+1.5)*100.0)/100.0);
			float peso= (float) (Math.round((Math.random()*(110-50)+50)*100.0)/100.0);
			float sueldo=(float) (Math.round((Math.random()*(2000-1000)+1000)*100.0)/100.0);
			int experiencia=0;
			int lider=(int) Math.floor(Math.random()*(100-0+1)+0);
			int estrategia=(int) Math.floor(Math.random()*(100-0+1)+0);
			int comunic=(int) Math.floor(Math.random()*(100-0+1)+0);
			Entrenador a=new Entrenador(nombre, fecha, altura, peso, sueldo,experiencia,lider,estrategia,comunic);
			e.add(a);
//			System.out.println(a.toString());
			
		}
		return e;
	}


	private ArrayList<Futbolista> crearJugadores(int numEquipos) {
		// TODO Auto-generated method stub
		ArrayList<Futbolista> f=new ArrayList<Futbolista>();
		for(int i=0;i<numEquipos;i++) {
			String pos="";
			String nombre="Futbolista"+i;
			LocalDate fecha=LocalDate.now();
			float altura= (float) (Math.round((Math.random()*(2.3-1.5)+1.5)*100.0)/100.0);
			float peso= (float) (Math.round((Math.random()*(110-50)+50)*100.0)/100.0);
			float sueldo=(float) (Math.round((Math.random()*(2000-1000)+1000)*100.0)/100.0);
//			int experiencia=0;
			int vel=(int) Math.floor(Math.random()*(100-0+1)+0);
			int res=(int) Math.floor(Math.random()*(100-0+1)+0);
			int fue=(int) Math.floor(Math.random()*(100-0+1)+0);
			int pot=(int) Math.floor(Math.random()*(100-0+1)+0);
			int con=(int) Math.floor(Math.random()*(100-0+1)+0);
			int pase=(int) Math.floor(Math.random()*(100-0+1)+0);
			int chute=(int) Math.floor(Math.random()*(100-0+1)+0);
			String categoria="Media";
			if(i<(numEquipos-(20*(numEquipos/22)))) {
				pos="Portero";
			}else if(i<(numEquipos-(12*(numEquipos/22)))) {
				pos="Defensa";
			}else if(i<(numEquipos-(4*(numEquipos/22)))) {
				pos="Centro";
			}else {
				pos="Delantero";
			}			
			Futbolista e=new Futbolista(altura, peso, fecha,nombre,i,sueldo,vel,res,fue,pot,con,pase,chute,0,pos,0,categoria);
			f.add(e);
//			System.out.println(e.toString());
		}
		return f;
	}
	
	
	private ArrayList<Equipo> crearEquipos(int numEquipos, ArrayList<Entrenador> entrenadores,
			ArrayList<Futbolista> futbolistas) {
		// TODO Auto-generated method stub	
		ArrayList<Equipo> e=new ArrayList<Equipo>();
		ArrayList<Futbolista> jugadores= (ArrayList<Futbolista>) futbolistas.clone();
		for(int i=0; i<numEquipos;i++) {			
			ArrayList<Futbolista> f=new ArrayList<Futbolista>();
			String nombre="Equipo"+i;
			float patrimonio=(float) (Math.round((Math.random()*(200000-100000)+100000)*100.0)/100.0);
			String presidente= "Presidente"+i;
			String localidad="Localidad"+i;
			int j=0, k=0;
			while(j<jugadores.size()) {
				if(k<2 && jugadores.get(j).getPosicion().equals("Portero")) {
					f.add(jugadores.get(j));
					k++;
					jugadores.remove(j);
				}else if(k<10 && jugadores.get(j).getPosicion().equals("Defensa")) {
					f.add(jugadores.get(j));
					k++;
					jugadores.remove(j);
				}else if(k<18 && jugadores.get(j).getPosicion().equals("Centro")) {
					f.add(jugadores.get(j));
					k++;
					jugadores.remove(j);
				}else if(k<22 && jugadores.get(j).getPosicion().equals("Delantero")) {
					f.add(jugadores.get(j));
					k++;
					jugadores.remove(j);
				}else if(k==22) {
					break;
				}else {
					j++;
				}				
			}
			Equipo eq=new Equipo(nombre, f, entrenadores.get(i),localidad,patrimonio, presidente);
			e.add(eq);	
		}
		return e;
	}

	public static Comparator<Equipo>Ordenar=new Comparator<Equipo>() {
		@Override
		public int compare(Equipo e1, Equipo e2) {
			return e2.getPuntos()-e1.getPuntos();				
		}
	};	
	
	
	
	
	public void crearClasificacion() {
		//El que más puntos tenga. A empate de puntos se pasa a ver quien tiene más goles. En caso de empate de todo orden alfabético del nombre de equipo.
		Collections.sort(equipos, Competicion.Ordenar);
		clasificacion=equipos;
//		for(Equipo equipo: equipos) {
//			System.out.println(equipos.toString());
//		}
	}
	
//	public Equipo[] getClasificacion() {}
	
	//muestra por pantalla la tabla de clasificación
	public void mostrarClasificacion() {
		System.out.println("Clasificación\n"
				+ "Nombre equipo\tpuntos\tPartidos ganados\tPartidos empatados\tPartidos perdidos");
		for(int i=0;i<clasificacion.size();i++) {
			System.out.println(clasificacion.get(i).getNombre()+
					"\t\t"+clasificacion.get(i).getPuntos()+
					"\t\t"+clasificacion.get(i).getPatidosGanados()+
					"\t\t\t"+clasificacion.get(i).getPartidosEmpatados()+
					"\t\t\t"+clasificacion.get(i).getPartidosPerdidos()); 
		}
	} 

	public void guardarClasificacion() {
		FileWriter fw = null;
        PrintWriter salida = null;
        int i=0;
        try {
            boolean append = true;
            fw = new FileWriter("clasificacion.txt", append);
            salida = new PrintWriter( fw );
            salida.println("------------------------------------");
            while (equipos.size()>i) {
            	salida.println(equipos.get(i).getNombre()+","+
            			equipos.get(i).getPuntos()+";"+
            			equipos.get(i).getPatidosGanados()+";"+
            			equipos.get(i).getPartidosEmpatados()+";"+
            			equipos.get(i).getPartidosPerdidos()+";\n");
            	i++; 
            }
        } catch (IOException ex) {
            System.out.println("No se han podido cargar los datos");
        } finally {
            salida.close();
        }
	} //guarda en archivo detexto la tabla de clasificación 
	
	public void asignarTitulo(Equipo e) {}
	
	
	/*
	 * Mostrar por pantalla una tabla con las características de cada jugador 
	 * que participa en la competición. Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	public void mostrarJugadores() {
		for(Futbolista jugador: jugadores) {
			System.out.println(jugador);
		}
	} 
	
	/*
	 * Mostrar por pantalla una tabla con las características de cada entrenador 
	 * que participa en la competición.  Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	public void mostrarEntrenadores() {
		for(Entrenador entrenador:entrenadores) {
			System.out.println(entrenador);
		}
	}
	
	
	/*
	 * Mostrar por pantalla una tabla con las características de cada Equipo 
	 * que participa en la competición.  Utilizaremos este método antes y después de 
	 * jugarse la competición.
	 */
	public void mostrarEquipos() {
		for(Equipo equipo:equipos) {
			System.out.println(equipo);
		}
	}
	
	
	
	
	
	
	
}