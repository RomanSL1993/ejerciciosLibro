package futbol;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import escribir_aeropuerto_serializado.EscribirFicheroSerializado;
import escribir_aeropuerto_serializado.MiObjectOutputStream;

public class mainFutbol {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String serie= "futbolista";
		ArrayList <Object> futbol=new ArrayList<Object>();
		
		futbol=crear(serie, 4);
		
		
		
		try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("futbol.ddr"))){
            
			switch(serie) {
			case "Futbolista":oos.writeObject(a1);
				break;
			case "Entrenador":
				break;
			case "Equipo":
				break;
			default: System.out.println("No se ha encontrado la opción");
				break;
			}
            
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscribirFicheroSerializado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EscribirFicheroSerializado.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try (MiObjectOutputStream moos = new MiObjectOutputStream(new FileOutputStream("aeropuertos.ddr", true))){
            
            moos.writeObject(a2);
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(EscribirFicheroSerializado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(EscribirFicheroSerializado.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	private static ArrayList<Object> crear(String serie, int i) {
		// TODO Auto-generated method stub
		
		switch(serie) {
		case "Futbolista": ArrayList <Futbolista> futbo=new ArrayList<Futbolista>();
			for(int j=0;j<i;j++) {
				Futbolista f=new Futbolista();
				futbo.add(f);
			}
			break;
		case "Entrenador": ArrayList <Entrenador> entre=new ArrayList<Entrenador>();
		for(int j=0;j<i;j++) {
			Entrenador e=new Entrenador();
			entre.add(e);
		}
			break;
		case "Equipo": ArrayList <Equipo> equipo=new ArrayList<Equipo>();
		for(int j=0;j<i;j++) {
			Equipo eq=new Equipo();
			equipo.add(eq);
		}
		return (ArrayList<Object>)equipo;
			break;
		default: System.out.println("No se ha encontrado la opción");
			break;
		}
		return null;
	}

}
