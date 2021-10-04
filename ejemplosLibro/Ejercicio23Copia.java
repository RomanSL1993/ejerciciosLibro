package ejemplosLibro;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Ejercicio23Copia {

	  /**
	   * @param args the command line arguments
	   */
	  public static void main(String[] args) {
//	    if (args.length < 1) {
//	      System.out.println("Indicar por favor nombre de fichero");
//	      return;
//	    }
	    Scanner sc=new Scanner(System.in);
//	    System.out.print("Introduzca la palabra a buscar: ");
//	    String palabra=sc.nextLine();
	    String palabra="archivo";
	    String nomFich="f_texto.txt";

	    try (BufferedReader fbr = new BufferedReader(new FileReader(nomFich))) {
	      int i = 0, z=1;
	      String linea = fbr.readLine();
	      
	      while (linea != null) {
	    	  
	    	  if(linea.contains(palabra)) {
	    		  int j = linea.indexOf(palabra); 
	    			System.out.format(z+". %s está en la línea %d y posición %d, la línea es: %s",palabra, i, j, linea);
	    			System.out.println();  
	    			z++;	    			  
	    			    		  		  
	    	  }	 
	    	  
	    	  i++;
	        linea = fbr.readLine();
	      }
	      
	    } catch (FileNotFoundException e) {
	      System.out.println("No existe fichero " + nomFich);
	    } catch (IOException e) {
	      System.out.println("Error de E/S: " + e.getMessage());
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
	}
