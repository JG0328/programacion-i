package logico;

import java.util.ArrayList;

public class Submarino extends Vehiculo {
	private float profundidadActual;
	private float limProfundidad;

	public Submarino(String codigo, ArrayList<Position> ruta, float profundidadActual, float limProfundidad) {
		super(codigo, ruta);
		this.profundidadActual = profundidadActual;
		this.limProfundidad = limProfundidad;
	}

	@Override
	public boolean verificarRuta(GPS gps) {
		int cont = 0;
		boolean correcta = true;
		Position actualPos = gps.getPosition(codigo, profundidadActual);

		for (Position pos : ruta) {
			if (actualPos.distance(pos) >= 10.0f) {
				cont++;
			}
		}
		
		// Si todos son incorrectos, el vehículo se encuentra fuera de ruta
		// Si la profundida sobrepasa su límite...
		if (cont == ruta.size() || profundidadActual > limProfundidad) {
			correcta = false;
		}

		return correcta;
	}

	public float getLimProfundidad() {
		return limProfundidad;
	}

	public void setLimProfundidad(float limProfundidad) {
		this.limProfundidad = limProfundidad;
	}

	public float getProfundidadActual() {
		return profundidadActual;
	}

	public void setProfundidadActual(float profundidadActual) {
		this.profundidadActual = profundidadActual;
	}
}
