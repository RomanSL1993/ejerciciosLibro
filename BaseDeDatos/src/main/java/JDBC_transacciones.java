package BaseDeDatos.src.main.java;

//5
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class JDBC_transacciones {

  public static void muestraErrorSQL(SQLException e) {
    System.err.println("SQL ERROR mensaje: " + e.getMessage());
    System.err.println("SQL Estado: " + e.getSQLState());
    System.err.println("SQL codigo especifico: " + e.getErrorCode());
  }

  public static void main(String[] args) {

    String basedatos = "prueba";
    String host = "localhost";
    String port = "3306";
    String parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    String urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
    String user = "root";
    String pwd = "";

    try (
            Connection c = DriverManager.getConnection(urlConnection, user, pwd)) {
      try (
              PreparedStatement sInsert = c.prepareStatement("INSERT INTO CLIENTES(DNI,APELLIDOS,CP) VALUES (?,?,?);")) {

        c.setAutoCommit(false);

        int i = 0;
        sInsert.setString(++i, "54099912V");
        sInsert.setString(++i, "CARVAJAL");
        sInsert.setString(++i, "10109");
        sInsert.executeUpdate();

        sInsert.setString(i = 1, "7549910S");
        sInsert.setString(++i, "MARQUEZ");
        sInsert.setString(++i, "46987");
        sInsert.executeUpdate();

        sInsert.setString(i = 1, "99923456A");
        sInsert.setString(++i, "MOLINA");
        sInsert.setString(++i, "35153");
        sInsert.executeUpdate();

        c.commit();

      } catch (SQLException e) {
        muestraErrorSQL(e);
        try {
          c.rollback();
        } catch (Exception er) {
          System.err.println("ERROR haciendo ROLLBACK");
          er.printStackTrace(System.err);
        }
      }
    } catch (Exception e) {
      System.err.println("ERROR de conexión");
      e.printStackTrace(System.err);
    }
  }

}