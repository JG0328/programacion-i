package logico;

import java.io.Serializable;

public class Jugador implements Serializable {
	private String nombre;
	private int numCamiseta;
	private float estatura;
	private float peso;
	private String posicion;

	public Jugador(String nombre, int numCamiseta, float estatura, float peso, String posicion) {
		super();
		this.nombre = nombre;
		this.numCamiseta = numCamiseta;
		this.estatura = estatura;
		this.peso = peso;
		this.posicion = posicion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getNumCamiseta() {
		return numCamiseta;
	}

	public void setNumCamiseta(int numCamiseta) {
		this.numCamiseta = numCamiseta;
	}

	public float getEstatura() {
		return estatura;
	}

	public void setEstatura(float estatura) {
		this.estatura = estatura;
	}

	public float getPeso() {
		return peso;
	}

	public void setPeso(float peso) {
		this.peso = peso;
	}

	public String getPosicion() {
		return posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
}
