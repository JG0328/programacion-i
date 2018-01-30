package logico;

import java.io.IOException;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Asociacion asoc = new Asociacion();

		Equipo equipo = new Equipo("001", 1985, "Tigres");
		Equipo equipo2 = new Equipo("002", 1996, "Santiagueros");
		Equipo equipo3 = new Equipo("003", 2005, "Monumentales");

		Jugador jug1 = new Jugador("Pepe", 1, 174, 76, "D");
		Jugador jug2 = new Jugador("José", 5, 165, 80, "F");
		Jugador jug3 = new Jugador("Mateo", 10, 180, 92, "P");
		Jugador jug4 = new Jugador("Ramón", 3, 152, 78, "P");
		Jugador jug5 = new Jugador("Ronaldo", 6, 156, 85, "D");
		Jugador jug6 = new Jugador("Juan", 10, 180, 67, "M");
		Jugador jug7 = new Jugador("Pablo", 4, 185, 90, "M");

		equipo.getMisJugadores().add(jug1);
		equipo.getMisJugadores().add(jug2);
		equipo.getMisJugadores().add(jug3);

		equipo2.getMisJugadores().add(jug4);
		equipo2.getMisJugadores().add(jug5);

		equipo3.getMisJugadores().add(jug6);
		equipo3.getMisJugadores().add(jug7);

		asoc.getMisEquipos().add(equipo);
		asoc.getMisEquipos().add(equipo2);
		asoc.getMisEquipos().add(equipo3);

		try {
			asoc.saveData();

			System.out.println("Datos guardados");

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			asoc.generarPlantilla();
			System.out.println("Plantilla generada");

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
