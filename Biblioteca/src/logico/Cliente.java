package logico;

import java.util.ArrayList;

public class Cliente {
	private String cedula;
	private String nombre;
	private String direccion;
	private ArrayList<Prestamo> misPrestamos;

	public Cliente(String cedula, String nombre, String direccion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.direccion = direccion;

		this.misPrestamos = new ArrayList<>();
	}
	
	public void insertarPrestamo(Prestamo prest)
	{
		misPrestamos.add(prest);
	}

	public Prestamo findPrestamoByPublic(Publicacion publi) {
		Prestamo aux = null;
		for (Prestamo prestamo : misPrestamos) {
			if (prestamo.getPublicacion().getId().equalsIgnoreCase(publi.getId())) {
				aux = prestamo;
			}
		}
		return aux;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public ArrayList<Prestamo> getMisPrestamos() {
		return misPrestamos;
	}

	public void setMisPrestamos(ArrayList<Prestamo> misPrestamos) {
		this.misPrestamos = misPrestamos;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
}
