package logico;

import java.util.ArrayList;

public class Aereo extends Vehiculo {
	private float alturaActual;
	private float limAlturaMin;
	private float limAlturaMax;

	public Aereo(String codigo, ArrayList<Position> ruta, float alturaActual, float limAlturaMin, float limAlturaMax) {
		super(codigo, ruta);
		this.alturaActual = alturaActual;
		this.limAlturaMin = limAlturaMin;
		this.limAlturaMax = limAlturaMax;
	}

	@Override
	public boolean verificarRuta(GPS gps) {
		int cont = 0;
		boolean correcta = true;
		Position actualPos = gps.getPosition(codigo, alturaActual);

		for (Position pos : ruta) {
			if (actualPos.distance(pos) >= 10.0f) {
				cont++;
			}
		}

		// Si todos son incorrectos, el vehículo se encuentra fuera de ruta
		// Si la altura sobrepasa su valor máximo o es menor a su mínimo...
		if (cont == ruta.size() || alturaActual > limAlturaMax || alturaActual < limAlturaMin) {
			correcta = false;
		}

		return correcta;
	}

	public float getAlturaActual() {
		return alturaActual;
	}

	public void setAlturaActual(float alturaActual) {
		this.alturaActual = alturaActual;
	}

	public float getLimAlturaMin() {
		return limAlturaMin;
	}

	public void setLimAlturaMin(float limAlturaMin) {
		this.limAlturaMin = limAlturaMin;
	}

	public float getLimAlturaMax() {
		return limAlturaMax;
	}

	public void setLimAlturaMax(float limAlturaMax) {
		this.limAlturaMax = limAlturaMax;
	}
}
