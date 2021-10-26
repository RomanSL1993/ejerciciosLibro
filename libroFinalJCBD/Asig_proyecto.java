package libroFinalJCBD;

import java.util.Date;

public class Asig_proyecto {

	private String dni_emp;
	private int id_proy;
	private Date f_inicio;
	private Date f_fin;
	
	//métodos SQL
	public void asignarEmpAProy(String dni_emp, int id_proy, Date f_inicio, Date f_fin) {
		boolean bien=GestorProyectos.AsignaEmpAProyecto(dni_emp, id_proy, f_inicio, f_fin);
	}
	
	//Constructores
	public Asig_proyecto() {
		super();
		this.dni_emp = "";
		this.id_proy = 0;
		this.f_inicio = new Date();
		this.f_fin = null;
	}
	
	public Asig_proyecto(String dni_emp, int id_proy, Date f_inicio, Date f_fin) {
		super();
		this.dni_emp = dni_emp;
		this.id_proy = id_proy;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		asignarEmpAProy(dni_emp, id_proy, f_inicio, f_fin);
	}
	
	//Getters y Setters
	public String getDni_emp() {
		return dni_emp;
	}

	public void setDni_emp(String dni_emp) {
		this.dni_emp = dni_emp;
	}

	public int getId_proy() {
		return id_proy;
	}

	public void setId_proy(int id_proy) {
		this.id_proy = id_proy;
	}

	public Date getF_inicio() {
		return f_inicio;
	}

	public void setF_inicio(Date f_inicio) {
		this.f_inicio = f_inicio;
	}

	public Date getF_fin() {
		return f_fin;
	}

	public void setF_fin(Date f_fin) {
		this.f_fin = f_fin;
	}

	@Override
	public String toString() {
		return "Asig_proyecto [dni_emp=" + dni_emp + ", id_proy=" + id_proy + ", f_inicio=" + f_inicio + ", f_fin="
				+ f_fin + "]";
	}	
	
	
}
