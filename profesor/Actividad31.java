package actividadesPropuestas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Actividad31 {

//	Database config parameters
	private static final String basedatos = "prueba";
	private static final String host = "localhost";
	private static final String port = "3306";
	private static final String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static final String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
	private static final String user = "javi";
	private static final String pwd = "1qaz2wsx";
	

	
//	Queries
	private static final String SQL_DELETE = "DELETE FROM CLIENTES WHERE DNI=?";
	private static final String SQL_UPDATE = "UPDATE CLIENTES SET APELLIDOS=? WHERE DNI=?";

	  public static void logErrorSQL(SQLException e) {
		    System.err.println("SQL ERROR mensaje: " + e.getMessage());
		    System.err.println("SQL Estado: " + e.getSQLState());
		    System.err.println("SQL codigo especifico: " + e.getErrorCode());
//		    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		  }
	
	private static boolean deleteClienteByDni(String dni) {
		boolean deleted = false;
	    
		try (	
				Connection conn = DriverManager.getConnection(urlConnection, user, pwd);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE)
			) 
		{
			preparedStatement.setString(1, dni);
			
			int rows = preparedStatement.executeUpdate();

			// rows affected
//			System.out.println(row);
			if(rows!=0) {
				deleted = true;
			}

		} catch (SQLException e) {
			logErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	
	private static boolean updateClienteApellidosByDni(String apellido , String dni) {
		boolean updated = false;
	    
		try (	Connection conn = DriverManager.getConnection(urlConnection, user, pwd);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)
			) 
		{
			preparedStatement.setString(1, apellido);
			preparedStatement.setString(2, dni);
			
			int row = preparedStatement.executeUpdate();

			// rows affected
//			System.out.println(row);
			if(row!=0) {
				updated = true;
			}

		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return updated;
	}


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String dniBorrar = "09876543K";
		if(deleteClienteByDni(dniBorrar)) {
			System.out.println("Cliente encontrado y eliminado");
		}
		else {
			System.out.println("Cliente con DNI "+dniBorrar+" no encontrado");
		}
		
		
		
		String dni="89012345E";
		String apellidos ="ROJAS";
		
		if(updateClienteApellidosByDni(apellidos, dni)) {
			System.out.println("Cliente encontrado y actualizado");
		}
		else {
			System.out.println("Cliente con DNI "+dni+" no encontrado");
		}
		
		
	}

}
