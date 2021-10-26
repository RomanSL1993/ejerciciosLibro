package libroFinalJCBD;

public class Empleado {

	private String dni;
	private String nom_emp;
	
	//metodos SQL
	public void insertarEmpleado(String dni, String nom_emp) {
		boolean bien=GestorProyectos.agregarEmpleado(dni, nom_emp);
	}
	
	//Constructores
	public Empleado() {
		this.dni="";
		this.nom_emp="";
	}
	
	public Empleado(String dni, String nom_emp) {
		super();
		this.dni = dni;
		this.nom_emp = nom_emp;
		insertarEmpleado(dni, nom_emp);
	}

	//Getters y Setters
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom_emp() {
		return nom_emp;
	}

	public void setNom_emp(String nom_emp) {
		this.nom_emp = nom_emp;
	}

	@Override
	public String toString() {
		return "Empleado [dni=" + dni + ", nom_emp=" + nom_emp + "]";
	}
	
	
}
