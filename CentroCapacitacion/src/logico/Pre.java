package logico;

import java.util.ArrayList;

public class Pre extends Graduado {
	private float actitud;

	public Pre(String id, String nombre, String apellidos, ArrayList<Curso> cursos, float historia, float[] promedio,
			float actitud) {
		super(id, nombre, apellidos, cursos, historia, promedio);
		this.actitud = actitud;
	}

	public float calcularPromedio() {
		float total = ((promedio[0] + promedio[1] + promedio[2]) / 3.0f) * 0.6f;
		total += ((historia + actitud) / 2.0f) * 0.4f;
		return total;
	}

	public float getActitud() {
		return actitud;
	}

	public void setActitud(float actitud) {
		this.actitud = actitud;
	}
}
