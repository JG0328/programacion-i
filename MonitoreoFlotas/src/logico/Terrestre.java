package logico;

import java.util.ArrayList;

public class Terrestre extends Vehiculo {
	private Position anteriorPos;

	public Terrestre(String codigo, ArrayList<Position> ruta, Position anteriorPos) {
		super(codigo, ruta);
		this.anteriorPos = anteriorPos;
	}

	@Override
	public boolean verificarRuta(GPS gps) {
		// TODO Auto-generated method stub
		int cont = 0;
		boolean correcta = true;
		Position actualPos = gps.getPosition(codigo);

		for (Position pos : ruta) {
			if (actualPos.distance(pos) >= 10.0f) {
				// Si este punto est� a 10 unidades o m�s, se cuenta como
				// incorrecto
				cont++;
			}
		}

		// Si todos son incorrectos, el veh�culo se encuentra fuera de ruta
		// Adem�s, si la posici�n actual es igual a la anterior...
		if (cont == ruta.size() || actualPos.distance(anteriorPos) == 0.0f) {
			correcta = false;
		}

		return correcta;
	}

	public Position getAnteriorPos() {
		return anteriorPos;
	}

	public void setAnteriorPos(Position anteriorPos) {
		this.anteriorPos = anteriorPos;
	}
}
