package ejemplosLibro;
import java.io.File;
import java.io.RandomAccessFile;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;


/*
 * Para poder importar javafx.util.Pair hay que seguir los pasos indicados en:
 * https://stackoverflow.com/questions/52144931/how-to-add-javafx-runtime-to-eclipse-in-java-11
 * específicamente la respuesta:
 * https://stackoverflow.com/a/52156678/14601842
 */


public class Ejercicio27 {

    private final File f;
    private final List<Pair<String, Integer>> campos;//->cada registro lo denomina campo
    private long longReg;
    private long numReg = 0;

    Ejercicio27(String nomFich, List<Pair<String, Integer>> campos) throws IOException {
    	this.campos = campos;
        this.f = new File(nomFich);
        longReg = 0;//contador de la longitud del registro
        for (Pair<String, Integer> campo : campos) {
            this.longReg += campo.getValue();
        }
        if (f.exists()) {//comprueba cuantos registros tiene dentro
            this.numReg = f.length() / this.longReg;
        }
    }

    public long getNumReg() {
        return numReg;
    }
    
    //inserta al final del registro
    public void insertar(Map<String, String> reg) throws IOException {
        insertar(reg, this.numReg++);
    }
    
    public void insertar(Map<String, String> reg, long pos) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            faa.seek(pos * this.longReg);//busca el byte para el que se queda
            for (Pair<String, Integer> campo: this.campos) {
                String nomCampo = campo.getKey();
                Integer longCampo = campo.getValue();
                String valorCampo = reg.get(nomCampo);
                if (valorCampo == null) {
                    valorCampo = "";
                }
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);
                faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
//                System.out.println(valorCampoForm);
            }
        }
    }
    
    public void leer(long pos) throws IOException {
    	try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
    		faa.seek(pos*this.longReg);
    		byte [] array = new byte[(int) longReg];//creamos el array para poder guardarlo
    		
    		String lectura="";
    		            
    		faa.read(array, 0,(int) longReg);//leemos el registro
    		lectura = new String(array, java.nio.charset.StandardCharsets.UTF_8);//volcamos todo a String
    		System.out.println(lectura);
    	}
    }
    
   

    public static void main(String[] args) {

        List<Pair<String, Integer>> campos = new ArrayList<Pair<String, Integer>>();
        campos.add(new Pair<String, Integer>("DNI", 9));
        campos.add(new Pair<String, Integer>("NOMBRE", 32));
        campos.add(new Pair<String, Integer>("CP", 5));
        long a=0;
        String n="Nombre";
        

        try {
            FicheroAccesoAleatorio faa = new FicheroAccesoAleatorio("fic_acceso_aleat.dat", campos);
            Map<String, String> reg = new HashMap<String, String>();
            int ac=0;
            
            reg.put("DNI", "56789012B");
            reg.put("NOMBRE", "roman");
            reg.put("CP", "29730");
            faa.insertar(reg);
            reg.clear();
            
            reg.put("DNI", "89012345E");
            reg.put("NOMBRE", "ROJAS");
            faa.insertar(reg);
            reg.clear();
            
            reg.put("DNI", "23456789D");
            reg.put("NOMBRE", "DORCE");
            reg.put("CP", "13700");
            faa.insertar(reg);
            reg.clear();
            
            reg.put("DNI", "78901234X");
            reg.put("NOMBRE", "NADALES");
            reg.put("CP", "44126");
            faa.insertar(reg, 1);
//      faa.insertar(reg,25);  // Probarlo, interesante
            reg.clear();
            faa.leer(3);
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
