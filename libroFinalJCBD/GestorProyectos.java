package libroFinalJCBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class GestorProyectos {
	
//	Database config parameters
	
	private static final String basedatos;
	private static final String host;
	private static final String port;
	private static final String user;
	private static final String pwd;
	private static final String parAdic;
	private static final String urlConnection;
	
//	Conexiones
	static {
		basedatos = "gestor_proyectos";
		host = "localhost";
		port = "3306";
		user = "root";
		pwd = "";
		parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
	}
	
//	Queries
	private static final String SQL_INTRODUCE_EMPLEADO = "INSERT INTO empleado VALUES(?, ?);";
	private static final String SQL_INTRODUCE_PROYECTO = "INSERT INTO proyecto VALUES(?, ?, ?, ?, ?);";
	private static final String SQL_IDPROYECTO = "SELECT MAX(id_proy) FROM proyecto;";
	private static final String SQL_ASIGNAR_EMPLEADO_A_PROYECTO = "INSERT INTO asig_proyecto VALUES(?, ?, ?, ?);";
	private static final String SQL_SELECT_ALL_EMPLEADOS = "SELECT dni FROM empleado;";
	private static final String SQL_MODIFICAR_EMPLEADO = "UPDATE empleado SET nombre= ? WHERE dni= ?";
	private static final String SQL_EMPLEADO_EN_PROYECTO = "SELECT * FROM asig_proyecto;";
	
//	Errors
	private static void logErrorSQL(SQLException e) {
	    System.err.println("SQL ERROR mensaje: " + e.getMessage());
	    System.err.println("SQL Estado: " + e.getSQLState());
	    System.err.println("SQL codigo especifico: " + e.getErrorCode());
//	    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
	  }
	
//	módulo para agregar nuevos empleados, devuelve boolean
	public static boolean agregarEmpleado(String dni, String apellido) {
		boolean agregado=false;
		
		try(Connection con=DriverManager.getConnection(urlConnection,user,pwd);
				PreparedStatement preparedStatement= con.prepareStatement(SQL_INTRODUCE_EMPLEADO)
						){
			
			preparedStatement.setString(1, dni);
			preparedStatement.setString(2, apellido);
			preparedStatement.executeUpdate();
			
			agregado=true;
			
		}catch (SQLException e) {
			logErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return agregado;
	}
	
	
//	módulo para crear proyectos
	public static int nuevoProyecto(String nombre, String dniJefe, Date f_inicio, Date f_final) {
		int num=0;//introducir el max(id)
		
		try(Connection con=DriverManager.getConnection(urlConnection,user,pwd);
				PreparedStatement preparado= con.prepareStatement(SQL_IDPROYECTO)
				){
			ResultSet rs = preparado.executeQuery();
			rs.next();
			num=rs.getInt(1);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		num++;
		
		try(Connection con=DriverManager.getConnection(urlConnection,user,pwd);
				PreparedStatement preparado= con.prepareStatement(SQL_INTRODUCE_PROYECTO)
						){
			
			
			
			preparado.setInt(1, num);
			preparado.setString(2, nombre);
			if(f_inicio!=null) {//da problemas el Date
				preparado.setDate(3, (java.sql.Date) f_inicio);
			}else {
				Date hoy=new Date();
				preparado.setDate(3, (java.sql.Date) hoy);
			}
			preparado.setDate(4, (java.sql.Date) f_final);
			preparado.setString(5, dniJefe);
			
			preparado.executeUpdate();			
			
			
		}catch (SQLException e) {
			logErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return num;
	}
	
	public static boolean AsignaEmpAProyecto(String emp, int proy, Date f_inicio, Date f_final) {
		boolean asignar=false;
		
		try(Connection con=DriverManager.getConnection(urlConnection,user,pwd);
				PreparedStatement preparado= con.prepareStatement(SQL_INTRODUCE_PROYECTO)
						){
			
			preparado.setString(1, emp);
			preparado.setInt(2, proy);
			if(f_inicio!=null) {
				preparado.setDate(3, (java.sql.Date) f_inicio);
			}else {
				Date hoy= new Date();
				preparado.setDate(3, (java.sql.Date) hoy);
			}
			preparado.setDate(4, (java.sql.Date) f_final);
			
			preparado.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		return asignar;
		
	}
	
	public static boolean ExisteEmpleado(String dni) {
		boolean existe = false;
		
		try (
	            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(SQL_SELECT_ALL_EMPLEADOS)) {

	      while (rs.next()) {
	    	  if(dni.equals(rs.getString("DNI"))) {
	    		  existe=true;
	    	  }
	      }
		} catch (SQLException e) {
		      logErrorSQL(e);
		} catch (Exception e) {
		      e.printStackTrace(System.err);
		}
		
		return existe;
	}
	
	public static void IntroducirEmpleado(String dni, String nombre) {
		 try (
		            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
		            Statement s = c.createStatement()) {

		      s.executeUpdate("INSERT INTO empleado VALUES ('"
		    		  +dni+"', '"+nombre+"');");
		 }catch (SQLException e) {
		      logErrorSQL(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		    }
		 System.out.println("Se ha introducido el nuevo empleado con dni "+dni+" y nombre "+nombre);
	}
	
	public static void ModificarEmpleado(String dni, String nombre) {
		
		try(Connection con=DriverManager.getConnection(urlConnection,user,pwd);
				PreparedStatement preparado= con.prepareStatement(SQL_MODIFICAR_EMPLEADO)
						){
			
			preparado.setString(1, nombre);
			preparado.setString(2, dni);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		System.out.println("Empleado con dni "+dni+" ha sido actualizado");
	}
	
	public static void EmpleadoEnProyecto(String dni) {
		
		try (
	            Connection c = DriverManager.getConnection(urlConnection, user, pwd);
	            Statement s = c.createStatement();
	            ResultSet rs = s.executeQuery(SQL_EMPLEADO_EN_PROYECTO)) {
			
			int i=0;
			System.out.print("El empleado con dni: "+dni);
			while (rs.next()) {
				if(dni.equals(rs.getString("dni"))) {
					if(i==0)
						System.out.println(" ha participado en:");
					i++;
					System.out.println(rs.getInt("id_proy")+" con inicio "+
										rs.getDate("f_inicio")+" y finaliza "+
										rs.getDate("f_fin"));					
				}
			}
			
			if(i==0) {
				System.out.println(" no ha sido asignado en ningún proyecto");
			}
			
			
		} catch (SQLException e) {
		      logErrorSQL(e);
		    } catch (Exception e) {
		      e.printStackTrace(System.err);
		}
		
		
	}
	
	
}
