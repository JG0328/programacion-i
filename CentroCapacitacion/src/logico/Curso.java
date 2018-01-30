package logico;

import java.util.ArrayList;

public class Curso {
	private String id;
	private String nombre;
	private String profesor;
	private String aula;
	private String tipo; // grupal o regular
	private ArrayList<Estudiante> estudiantes;
	private boolean abierto;

	public Curso(String id, String nombre, String profesor, String aula, String tipo, ArrayList<Estudiante> estudiantes,
			boolean abierto) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.profesor = profesor;
		this.aula = aula;
		this.tipo = tipo;
		this.estudiantes = estudiantes;
		this.abierto = abierto;
	}

	public String getProfesor() {
		return profesor;
	}

	public void setProfesor(String profesor) {
		this.profesor = profesor;
	}

	public ArrayList<Estudiante> getEstudiantes() {
		return estudiantes;
	}

	public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}

	public boolean isAbierto() {
		return abierto;
	}

	public void setAbierto(boolean abierto) {
		this.abierto = abierto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAula() {
		return aula;
	}

	public void setAula(String aula) {
		this.aula = aula;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
