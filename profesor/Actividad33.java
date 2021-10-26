package profesor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Actividad33 {


	 public static void muestraErrorSQL(SQLException e) {
	    System.err.println("SQL ERROR mensaje: " + e.getMessage());
	    System.err.println("SQL Estado: " + e.getSQLState());
	    System.err.println("SQL cÃ³digo especÃ­fico: " + e.getErrorCode());
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
	            ResultSet rs = s.executeQuery("SELECT * FROM CLIENTES")) {

	      int i=1;
	      if(rs.last()) {
		       do
		       {
		        System.out.println("[" + (i++) + "]");        
		        System.out.println("DNI: " + rs.getString("DNI"));
		        System.out.println("Apellidos: " + rs.getString("APELLIDOS"));
		        System.out.println("CP: " + rs.getInt("CP")); // ¿se puede utilizar getInt()? Cambiar valor a mano en BD poniendo letras al CP, ¿qué debería de cambiarse?¿el tipo de dato de la BD o el código java?
		      }
		       while (rs.previous());
	      }

	    } catch (SQLException e) {
	      muestraErrorSQL(e);
	    } catch (Exception e) {
	      e.printStackTrace(System.err);
	    }
	  }


}
