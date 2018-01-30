package logico;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Asociacion {
	private ArrayList<Equipo> misEquipos;
	private ArrayList<Jugador> misJugadores;

	public Asociacion() {
		super();
		this.misEquipos = new ArrayList<>();
		this.misJugadores = new ArrayList<>();
	}

	public void saveData() throws IOException {
		FileOutputStream equipos = new FileOutputStream("Equipos.dat");
		FileOutputStream jugadores = new FileOutputStream("Jugadores.dat");

		ObjectOutputStream oosEquipos = new ObjectOutputStream(equipos);
		ObjectOutputStream oosJugadores = new ObjectOutputStream(jugadores);

		oosEquipos.writeInt(misEquipos.size());
		oosJugadores.writeInt(misJugadores.size());

		for (Equipo equipo : misEquipos) {
			oosEquipos.writeObject(equipo);
		}

		for (Jugador jugador : misJugadores) {
			oosJugadores.writeObject(jugador);
		}

		equipos.close();
		jugadores.close();

	}

	public void generarPlantilla() throws IOException, ClassNotFoundException {
		FileInputStream equipos = new FileInputStream("Equipos.dat");
		ObjectInputStream oisEquipos = new ObjectInputStream(equipos);

		File archivo = new File("Plantilla.txt");
		BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));

		int size = oisEquipos.readInt();

		for (int i = 0; i < size; i++) {
			Equipo equipo = (Equipo) oisEquipos.readObject();
			writer.write("Equipo: " + equipo.getNombre());
			writer.newLine();
			writer.newLine();
			writer.write("Cantidad de jugadores: " + equipo.getMisJugadores().size());

			for (Jugador jug : equipo.getMisJugadores()) {
				writer.newLine();
				writer.newLine();
				writer.write("Jugador con la camiseta " + jug.getNumCamiseta() + ": " + jug.getNombre()
						+ " juega en la posición " + jug.getPosicion() + ", " + "mide "
						+ String.format("%.0f cm", jug.getEstatura()) + " y pesa "
						+ String.format("%.0f kg.", jug.getPeso()));
			}

			writer.newLine();
			writer.newLine();
			writer.newLine();
		}

		writer.close();
	}

	public ArrayList<Equipo> getMisEquipos() {
		return misEquipos;
	}

	public void setMisEquipos(ArrayList<Equipo> misEquipos) {
		this.misEquipos = misEquipos;
	}

	public ArrayList<Jugador> getMisJugadores() {
		return misJugadores;
	}

	public void setMisJugadores(ArrayList<Jugador> misJugadores) {
		this.misJugadores = misJugadores;
	}
}
