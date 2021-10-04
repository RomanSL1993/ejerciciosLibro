package ejemplosLibro;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

// Este programa crea un fichero y escribe un texto en él.
// Después lo vuelve a abrir para añadir un texto al final de él.
// Si el fichero ya existe, sale sin hacer nada.

public class EscribeEnFlujoSalida {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    
    String nomFichero="f_texto.txt";
    File f=new File(nomFichero);
    if(f.exists()) {
      System.out.println("Fichero "+nomFichero+" ya existe. No se hace nada");
      return;
    }
    
    try {
      BufferedWriter bfw=new BufferedWriter(new FileWriter(f));
      bfw.write("  Este   es un fichero de texto. ");     
      bfw.newLine();
      bfw.write(" quiz� no est�   del todo bien.");
      bfw.newLine();
      bfw.close();
      bfw=new BufferedWriter(new FileWriter(f, true));
      bfw.write(" Pero     se puede arreglar.");
      bfw.newLine();
      bfw.close();
      System.out.println("Generado fichero " + nomFichero);
    }
    catch(IOException e) {
      System.out.println(e.getMessage());
    }
    
    catch(Exception e) {
      e.printStackTrace();
    }
    File archivo = null;
    FileReader fr = null;
    BufferedReader br = null;

    try {
       // Apertura del fichero y creacion de BufferedReader para poder
       // hacer una lectura comoda (disponer del metodo readLine()).
       archivo = new File ("f_texto.txt");
       fr = new FileReader (archivo);
       br = new BufferedReader(fr);

       // Lectura del fichero
       String linea;
       while((linea=br.readLine())!=null)
          System.out.println(linea);
    }
    catch(Exception e){
       e.printStackTrace();
    }finally{
       // En el finally cerramos el fichero, para asegurarnos
       // que se cierra tanto si todo va bien como si salta 
       // una excepcion.
       try{                    
          if( null != fr ){   
             fr.close();     
          }                  
       }catch (Exception e2){ 
          e2.printStackTrace();
       }
    }
    
  }
  
}
