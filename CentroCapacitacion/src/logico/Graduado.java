package logico;

import java.util.ArrayList;

public abstract class Graduado extends Postulante {
	protected float historia;
	protected float[] promedio;

	public Graduado(String id, String nombre, String apellidos, ArrayList<Curso> cursos, float historia,
			float[] promedio) {
		super(id, nombre, apellidos, cursos);
		this.historia = historia;
		this.promedio = promedio;
	}

	public abstract float calcularPromedio();

	public float getHistoria() {
		return historia;
	}

	public void setHistoria(float historia) {
		this.historia = historia;
	}

	public float[] getPromedio() {
		return promedio;
	}

	public void setPromedio(float[] promedio) {
		this.promedio = promedio;
	}
}
