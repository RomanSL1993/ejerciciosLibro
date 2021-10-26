package actividadesPropuestas;

public class Cliente {

	private String dni;
	private String apellidos;
	private String cp;
	
	public Cliente() {
	}
	

	public Cliente(String dni, String apellidos, String cp) {
		super();
		this.dni = dni;
		this.apellidos = apellidos;
		this.cp = cp;
	}
	
	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", apellidos=" + apellidos + ", cp=" + cp + "]";
	}
	/**
	 * @return the dni
	 */
	public String getDni() {
		return dni;
	}
	/**
	 * @param dni the dni to set
	 */
	public void setDni(String dni) {
		this.dni = dni;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/**
	 * @return the cp
	 */
	public String getCp() {
		return cp;
	}
	/**
	 * @param cp the cp to set
	 */
	public void setCp(String cp) {
		this.cp = cp;
	}
	
	

}
