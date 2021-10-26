package libroFinalJCBD;

import java.util.Date;

public class Proyecto {

	private static int id=0;
	
	private int id_proy;
	private String nom_proy;
	private Date f_inicio;
	private Date f_fin;
	private String dni_jefe_proy;
	
	//métodos SQL
	public void nuevosProyectos(int id_proy, String nom_proy, Date f_inicio, Date f_fin, String dni_jefe_proy) {
		int n=GestorProyectos.nuevoProyecto(nom_proy, dni_jefe_proy, f_inicio, f_fin);
	}
	
	//Constructores
	public Proyecto() {
		this.id_proy = ++id;
		this.nom_proy = "";
		this.f_inicio = new Date();
		this.f_fin = null;
		this.dni_jefe_proy = "";
	}
	public Proyecto(int id_proy, String nom_proy, Date f_inicio, Date f_fin, String dni_jefe_proy) {
		super();
		this.id_proy = id_proy;
		this.nom_proy = nom_proy;
		this.f_inicio = f_inicio;
		this.f_fin = f_fin;
		this.dni_jefe_proy = dni_jefe_proy;
		nuevosProyectos(id_proy, nom_proy, f_inicio, f_fin, dni_jefe_proy);
	}
	
	//Getters y Setters
	public int getId_proy() {
		return id_proy;
	}
	public void setId_proy(int id_proy) {
		this.id_proy = id_proy;
	}
	public String getNom_proy() {
		return nom_proy;
	}
	public void setNom_proy(String nom_proy) {
		this.nom_proy = nom_proy;
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
	public String getDni_jefe_proy() {
		return dni_jefe_proy;
	}
	public void setDni_jefe_proy(String dni_jefe_proy) {
		this.dni_jefe_proy = dni_jefe_proy;
	}
	
	//Escritura de los datos
	@Override
	public String toString() {
		return "Proyecto [id_proy=" + id_proy + ", nom_proy=" + nom_proy + ", f_inicio=" + f_inicio + ", f_fin=" + f_fin
				+ ", dni_jefe_proy=" + dni_jefe_proy + "]";
	}
	
	
	
	
}
