package logico;

import java.util.ArrayList;

public class O18 extends Postulante {
	private int nivelacion;

	public O18(String id, String nombre, String apellidos, ArrayList<Curso> cursos, int nivelacion) {
		super(id, nombre, apellidos, cursos);
		this.nivelacion = nivelacion;
	}

	public int getNivelacion() {
		return nivelacion;
	}

	public void setNivelacion(int nivelacion) {
		this.nivelacion = nivelacion;
	}
}
