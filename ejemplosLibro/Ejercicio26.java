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

//RESPUESTA: La ejecución se da sin problemas, lo que provoca es que el resto de datos
//introducidos no se tengan en cuenta hasta el límete, todo lo que se supere se irá fuera
//del registro. Para solventarlo haría falta hacer un registro variable.


public class Ejercicio26 {

    private final File f;
    private final List<Pair<String, Integer>> campos;//->cada registro lo denomina campo
    private long longReg;
    private long numReg = 0;

    Ejercicio26(String nomFich, List<Pair<String, Integer>> campos) throws IOException {
        this.campos = campos;
        this.f = new File(nomFich);
        longReg = 0;
        for (Pair<String, Integer> campo : campos) {
            this.longReg += campo.getValue();
        }
        if (f.exists()) {
            this.numReg = f.length() / this.longReg;
        }
    }

    public long getNumReg() {
        return numReg;
    }

    public void insertar(Map<String, String> reg) throws IOException {
        insertar(reg, this.numReg++);
    }

    public void insertar(Map<String, String> reg, long pos) throws IOException {
        try (RandomAccessFile faa = new RandomAccessFile(f, "rws")) {
            faa.seek(pos * this.longReg);
            for (Pair<String, Integer> campo: this.campos) {
                String nomCampo = campo.getKey();
                Integer longCampo = campo.getValue();
                String valorCampo = reg.get(nomCampo);
                if (valorCampo == null) {
                    valorCampo = "";
                }
                String valorCampoForm = String.format("%1$-" + longCampo + "s", valorCampo);
                faa.write(valorCampoForm.getBytes("UTF-8"), 0, longCampo);
            }
        }
    }

    public static void main(String[] args) {

        List campos = new ArrayList();
        campos.add(new Pair("DNI", 9));
        campos.add(new Pair("NOMBRE", 32));
        campos.add(new Pair("CP", 5));

        try {
            Ejercicio26 faa = new Ejercicio26("fic_acceso_aleat.dat", campos);
            Map reg = new HashMap();
            //el primer registro haremos que sobrepase
            reg.put("DNI", "56789012Bergfaswqfz");
            reg.put("NOMBRE", "SAMPER PRECINTO DOMINICO GERMAN DECIMMOQUINTO DE LA BASTION");
            reg.put("CP", "29730123456789");
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
        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
