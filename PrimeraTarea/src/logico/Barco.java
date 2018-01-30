package logico;

import java.io.Serializable;

public class Barco implements Serializable {
	private String matricula;
	private String nombre;
	private int fabricacion;
	private String tipo;
	private int eslora;

	public Barco(String matricula, String nombre, int fabricacion, String tipo, int eslora) {
		super();
		this.matricula = matricula;
		this.nombre = nombre;
		this.fabricacion = fabricacion;
		this.tipo = tipo;
		this.eslora = eslora;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getFabricacion() {
		return fabricacion;
	}

	public void setFabricacion(int fabricacion) {
		this.fabricacion = fabricacion;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public int getEslora() {
		return eslora;
	}

	public void setEslora(int eslora) {
		this.eslora = eslora;
	}
}
