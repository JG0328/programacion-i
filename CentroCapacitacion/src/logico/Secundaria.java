package logico;

import java.util.ArrayList;

public class Secundaria extends Graduado {
	private float matematicas;
	private float espaniol;

	public Secundaria(String id, String nombre, String apellidos, ArrayList<Curso> cursos, float historia,
			float[] promedio, float matematicas, float espaniol) {
		super(id, nombre, apellidos, cursos, historia, promedio);
		this.matematicas = matematicas;
		this.espaniol = espaniol;
	}

	public float calcularPromedio() {
		float total = ((promedio[0] + promedio[1] + promedio[2]) / 3.0f) * 0.6f;
		total += ((matematicas + espaniol + historia) * 3.0f) * 0.4f;
		return total;
	}

	public float getMatematicas() {
		return matematicas;
	}

	public void setMatematicas(float matematicas) {
		this.matematicas = matematicas;
	}

	public float getEspaniol() {
		return espaniol;
	}

	public void setEspaniol(float espaniol) {
		this.espaniol = espaniol;
	}
}
