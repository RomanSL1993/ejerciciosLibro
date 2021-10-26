package actividadesPropuestas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Actividad31b {


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		
		String dniBorrar = "09876543K";
		
		if( GestorBD.deleteClienteByDni(dniBorrar) ) {
			System.out.println("Cliente encontrado y eliminado");
		}
		else {
			System.out.println("Cliente con DNI "+dniBorrar+" no encontrado");
		}
		
		
		
		String dni="89012345E";
		String apellidos ="REJAS";
		
		if(GestorBD.updateClienteApellidosByDni(apellidos, dni)) {
			System.out.println("Cliente encontrado y actualizado");
		}
		else {
			System.out.println("Cliente con DNI "+dni+" no encontrado");
		}
		
		
	}

}
