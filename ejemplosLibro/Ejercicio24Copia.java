package ejemplosLibro;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class Ejercicio24Copia {
 
 private String file= "archivo.txt";
 
 public void writeFile () throws IOException{
  
  
  Writer write = null;
  
        try {
            write = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file),"UTF8"));
            write.write("Este es un archivo con codificación utf-8\n" +              
              "éste es Beta: ß\n" +
              "estos son letras con acento: áéíóú");
            
        }
        catch(Exception e){
         e.printStackTrace();
        }
        
        finally{
         write.close();
        }
 }
 
 public void readFile () throws IOException{
  
  String stringCadena = "";
        new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, true), "UTF8"));        

        BufferedReader  in = new BufferedReader (new InputStreamReader (new FileInputStream (file), "utf-8"));
        try{            
            while ((stringCadena = in.readLine())!=null) {
                  System.out.println(stringCadena);
                  
            }                                    
        }
        catch (Exception e){

        }
        finally{            
          in.close();  
        }
  
 }
 

 /**
  * @param args
  * @throws IOException 
  */
 public static void main(String[] args) throws IOException {
  Ejercicio24Copia readandWriteCodingFiles = new Ejercicio24Copia();
  readandWriteCodingFiles.writeFile(); 
  readandWriteCodingFiles.readFile();
 }

}
