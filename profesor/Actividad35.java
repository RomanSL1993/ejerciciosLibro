package profesor;

import java.sql.SQLException;
import java.util.ArrayList;

public class Actividad35 {


	  public static void muestraErrorSQL(SQLException e) {
	    System.err.println("SQL ERROR mensaje: " + e.getMessage());
	    System.err.println("SQL Estado: " + e.getSQLState());
	    System.err.println("SQL c√≥digo espec√≠fico: " + e.getErrorCode());
	  }

	  public static void main(String[] args) {

	    String basedatos = "prueba";
	    String host = "localhost";
	    String port = "3306";
	    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
	    String user = "javi";
	    String pwd = "1qaz2wsx";

//	    try (
//	            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
//	            PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES2(DNI,APELLIDOS,CP) VALUES (?,?,?);")
//	    	) 
//	    {
//
//	      sInsert.setString(1, "78902344X");
//	      sInsert.setString(2, "NADALES");
//	      sInsert.setInt(3, 44126);
//
//	      sInsert.executeUpdate();
//
//	      int i=1;
//	      sInsert.setString(i++, "89214345E");
//	      sInsert.setString(i++, "ROJAS");
//	      sInsert.setNull(i++, Types.INTEGER); // se pone un integer a NULL
//
//	      sInsert.executeUpdate();
//
//	      sInsert.setString(i=1, "57899012B");//error aquÌ con el Ìndice
//	      sInsert.setString(i++, "SAMPER");
//	      sInsert.setInt(i++, 29730);
//
//	      sInsert.executeUpdate();
//
//	    } catch (SQLException e) {
//	      muestraErrorSQL(e);
//	    } catch (Exception e) {
//	      e.printStackTrace(System.err);
//	    }
	    
	    String dni = "78902344X";
	    Cliente cliente = GestorBD.getClienteByDni(dni);
	    
	    System.out.println(cliente);
	    
	    ArrayList<Cliente> clientes = GestorBD.getClientes();
	    System.out.println(clientes);
	    
	    
	    
	  }


}
