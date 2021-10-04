package escribir_aeropuerto_serializado;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EscribirFicheroSerializado {

    /*
        Escribe en un fichero serializado un objeto aeropuerto. 
        Prueba a meter mas de uno.
     */
    public static void main(String[] args) {

        Direccion d = new Direccion("España", "mentiras", 1, "Ciudad real");

        AeropuertoPrivado a1 = new AeropuertoPrivado(5, "Quijote airport", d, 1970, 1000);

        AeropuertoPublico a2 = new AeropuertoPublico(100000, 10, "Adolfo Suarez",
                "España", "calle", 1, "Madrid", 2000, 250000);

        
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("aeropuertos.ddr"))){
            
            oos.writeObject(a1);
            
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

}
