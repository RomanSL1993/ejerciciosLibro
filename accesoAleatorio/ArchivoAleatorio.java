package accesoAleatorio;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class ArchivoAleatorio {

    public static void main(String[] args) throws IOException {

        char c;
        boolean finArchivo = false;
        RandomAccessFile archivo = null;

        try {
            archivo = new RandomAccessFile("prueba.txt", "rw");
            System.out.println("El tamaño es: " + archivo.length());
            do {
                try {
                    c = (char) archivo.readByte();
                    if (c == 'i') {
                        archivo.seek(archivo.getFilePointer() - 1);
                        archivo.writeByte(Character.toUpperCase(c));
                    }
                } catch (EOFException eof) {
                    finArchivo = true;
                    archivo.close();
                    System.out.println("Convertidas a mayusculas");
                }
            } while (!finArchivo);
        } catch (FileNotFoundException fe) {
            System.out.println("No se encontro el archivo");

        }

    }

}
