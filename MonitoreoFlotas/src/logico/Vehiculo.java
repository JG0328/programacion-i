package logico;

import java.util.ArrayList;

public abstract class Vehiculo {
	protected String codigo;
	protected ArrayList<Position> ruta;

	public Vehiculo(String codigo, ArrayList<Position> ruta) {
		super();
		this.codigo = codigo;
		this.ruta = ruta;
	}
	
	public abstract boolean verificarRuta(GPS gps);

	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public ArrayList<Position> getRuta() {
		return ruta;
	}

	public void setRuta(ArrayList<Position> ruta) {
		this.ruta = ruta;
	}
}
