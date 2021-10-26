package profesor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class GestorBD {

//	Database config parameters
	
	private static final String basedatos;
	private static final String host;
	private static final String port;
	private static final String user;
	private static final String pwd;
	private static final String parAdic;
	private static final String urlConnection;
	
	static {
		basedatos = "prueba";
		host = "localhost";
		port = "3306";
		user = "javi";
		pwd = "1qaz2wsx";
		parAdic = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		urlConnection = "jdbc:mysql://" + host + ":" + port + "/" + basedatos + parAdic;
	}
	
//	Queries
	private static final String SQL_DELETE = "DELETE FROM CLIENTES WHERE DNI=?";
	private static final String SQL_UPDATE = "UPDATE CLIENTES SET APELLIDOS=? WHERE DNI=?";
	private static final String SQL_SELECT = "SELECT * FROM CLIENTES2 WHERE DNI=?";				// OJO utilizando CLIENTES2

	private static final String SQL_SELECT_ALL= "SELECT * FROM CLIENTES2";
	
	private static void logErrorSQL(SQLException e) {
		    System.err.println("SQL ERROR mensaje: " + e.getMessage());
		    System.err.println("SQL Estado: " + e.getSQLState());
		    System.err.println("SQL codigo especifico: " + e.getErrorCode());
//		    System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		  }
	
	
	
	public static boolean deleteClienteByDni(String dni) {
		boolean deleted = false;
	    
		try (	Connection conn = DriverManager.getConnection(urlConnection, user, pwd);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE)
			) 
		{
			preparedStatement.setString(1, dni);
			int row = preparedStatement.executeUpdate();

			// rows affected
//			System.out.println(row);
			if(row!=0) {
				deleted = true;
			}

		} catch (SQLException e) {
			logErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return deleted;
	}
	
	public static boolean updateClienteApellidosByDni(String apellido , String dni) {
		boolean updated = false;
	    
		try (	Connection conn = DriverManager.getConnection(urlConnection, user, pwd);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE)
			) 
		{
			preparedStatement.setString(1, apellido);
			preparedStatement.setString(2, dni);
			int row = preparedStatement.executeUpdate();

//			rows affected
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
	
	public static void mostrarClienteByDni(String dni) {
	    
		try (	
				Connection conn = DriverManager.getConnection(urlConnection, user, pwd);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			) 
		{
			preparedStatement.setString(1, dni);
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) 
			{
				if (resultSet.next()) {
	                dni = resultSet.getString("DNI");
	                String apellidos = resultSet.getString("APELLIDOS");
	                String cp = resultSet.getString("CP");
	                
	                System.out.println("Cliente encontrado. DNI: "+dni+" APELLIDOS: "+apellidos+" CP: "+cp);
	            }
			}
		} catch (SQLException e) {
			logErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	public static Cliente getClienteByDni(String dni) {
	    
		Cliente cliente=null;
		
		try (	
				Connection conn = DriverManager.getConnection(urlConnection, user, pwd);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT);
			) 
		{
			preparedStatement.setString(1, dni);
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) 
			{
				if (resultSet.next()) {
					cliente = new Cliente	( 	
													resultSet.getString("DNI"),
													resultSet.getString("APELLIDOS"),
													resultSet.getString("CP")
													);
	            }
			}
		} catch (SQLException e) {
			logErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	
	public static ArrayList<Cliente> getClientes() {
	    
		ArrayList<Cliente> clientes= new ArrayList<>();
		
		try (	
				Connection conn = DriverManager.getConnection(urlConnection, user, pwd);
				PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ALL);
			) 
		{
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) 
			{
				
				while (resultSet.next()) {
					clientes.add( new Cliente	( 	
													resultSet.getString("DNI"),
													resultSet.getString("APELLIDOS"),
													resultSet.getString("CP")
												)
								);
	            }
			}
		} catch (SQLException e) {
			logErrorSQL(e);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	
	
//	Ejemplo utilización correcto de la apertura y cierre de la conexión. Cerrar lo antes posible.
	
//	public User find(String username, String password) throws SQLException {
//	    User user = null;
//
//	    try (
//	        Connection connection = dataSource.getConnection();
//	        PreparedStatement statement = connection.prepareStatement("SELECT id, username, email FROM user WHERE username=? AND password=md5(?)");
//	    ) {
//	        statement.setString(1, username);
//	        statement.setString(2, password);
//
//	        try (ResultSet resultSet = statement.executeQuery()) {
//	            if (resultSet.next()) {
//	                user = new User();
//	                user.setId(resultSet.getLong("id"));
//	                user.setUsername(resultSet.getString("username"));
//	                user.setEmail(resultSet.getString("email"));
//	            }
//	        }
//	    }       
//
//	    return user;
//	}
	

//	O para devolver varias entidades
	
//	public List<Entity> list() throws SQLException {
//	    List<Entity> entities = new ArrayList<Entity>();
//
//	    try (
//	        Connection connection = database.getConnection();
//	        PreparedStatement statement = connection.prepareStatement(SQL_LIST);
//	        ResultSet resultSet = statement.executeQuery();
//	    ) {
//	        while (resultSet.next()) {
//	            entities.add(map(resultSet));// el método map es aquí mapear atributo a atributo como en el caso anterior para crear cada objeto que se añade a la lista.
//	        }
//	    }
//
//	    return entities;
//	}
	
}
