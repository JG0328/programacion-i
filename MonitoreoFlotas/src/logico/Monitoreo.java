package logico;

import java.util.ArrayList;
import java.util.Iterator;

public class Monitoreo {
	private ArrayList<Vehiculo> misVehiculos;
	private GPS miGps;
	private Alarma miAlarma;

	public Monitoreo(GPS miGps, Alarma miAlarma) {
		super();
		this.misVehiculos = new ArrayList<>();
		this.miGps = miGps;
		this.miAlarma = miAlarma;
	}

	// Punto C
	public void insertVehiculo(Vehiculo vehi) {
		misVehiculos.add(vehi);
	}

	// Punto D
	public Vehiculo find(String code) {
		Vehiculo v = null;
		int i = 0;
		boolean find = false;

		while (!find && i < misVehiculos.size()) {
			if (misVehiculos.get(i).getCodigo().equalsIgnoreCase(code)) {
				v = misVehiculos.get(i);
				find = true;
			}

			i++;
		}

		return v;
	}

	// Punto E
	public void update() {
		for (Vehiculo vehiculo : misVehiculos) {
			// Si la ruta se verifica y es incorrecta...
			if (!vehiculo.verificarRuta(miGps)) {
				miAlarma.activate(vehiculo.getCodigo());
			}
		}
	}

	// Punto F
	public ArrayList<Terrestre> TerrestrialNotInRoute() {
		ArrayList<Terrestre> terrestres = new ArrayList<>();

		for (int i = 0; i < misVehiculos.size(); i++) {
			if (misVehiculos.get(i) instanceof Terrestre) {
				// Si este terrestre se encuentra fuera de ruta...
				if (!misVehiculos.get(i).verificarRuta(miGps)) {
					terrestres.add((Terrestre) misVehiculos.get(i));
				}
			}
		}

		return terrestres;
	}

	// Punto G
	public int countAerialOutOfRoute(float value) {
		int count = 0;

		for (int i = 0; i < misVehiculos.size(); i++) {
			if (misVehiculos.get(i) instanceof Aereo) {
				// Si la ruta es incorrecta o se encuentra a mayor altura de la
				// enviada...
				if (!misVehiculos.get(i).verificarRuta(miGps)
						|| ((Aereo) misVehiculos.get(i)).getAlturaActual() > value) {
					count++;
				}
			}
		}

		return count;
	}

	public ArrayList<Vehiculo> getMisVehiculos() {
		return misVehiculos;
	}

	public void setMisVehiculos(ArrayList<Vehiculo> misVehiculos) {
		this.misVehiculos = misVehiculos;
	}

	public GPS getMiGps() {
		return miGps;
	}

	public void setMiGps(GPS miGps) {
		this.miGps = miGps;
	}

	public Alarma getMiAlarma() {
		return miAlarma;
	}

	public void setMiAlarma(Alarma miAlarma) {
		this.miAlarma = miAlarma;
	}
}
