package actividadesPropuestas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad34 {


	 public static void muestraErrorSQL(SQLException e) {
	    System.err.println("SQL ERROR mensaje: " + e.getMessage());
	    System.err.println("SQL Estado: " + e.getSQLState());
	    System.err.println("SQL código específico: " + e.getErrorCode());
	  }
	  
	  public static void main(String[] args) {

	    String basedatos = "prueba";
	    String host = "localhost";
	    String port = "3306";
	    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
	    String user = "javi";
	    String pwd = "1qaz2wsx";

	    try (
	            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
	            Statement s = c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES")
	        ) 
	    {
	    	int numFilas = 0; 
	    	if(rs.last())
	    		numFilas = rs.getRow();
	    	
	    	System.out.println("N�mero de filas: "+numFilas);

	    }

	    catch (SQLException e) {
	      muestraErrorSQL(e);
	    }
	    catch (Exception e) {
	      e.printStackTrace(System.err);
	    }
	  }
}
