package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Equipo implements Serializable {
	private String codigo;
	private int fundacion;
	private String nombre;
	private ArrayList<Jugador> misJugadores;

	public Equipo(String codigo, int fundacion, String nombre) {
		super();
		this.codigo = codigo;
		this.fundacion = fundacion;
		this.nombre = nombre;
		this.misJugadores = new ArrayList<>();
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getFundacion() {
		return fundacion;
	}

	public void setFundacion(int fundacion) {
		this.fundacion = fundacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public ArrayList<Jugador> getMisJugadores() {
		return misJugadores;
	}

	public void setMisJugadores(ArrayList<Jugador> misJugadores) {
		this.misJugadores = misJugadores;
	}

}
