package logico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Maquina extends Thread {
	private ArrayList<String> misOpciones;
	private ArrayList<Integer> misPrecios;
	private boolean ejecutar = true;

	public Maquina(ArrayList<String> misOpciones, ArrayList<Integer> misPrecios) {
		super();
		this.misOpciones = misOpciones;
		this.misPrecios = misPrecios;
	}

	public void run() {
		while (ejecutar) {
			proceso();
		}
	}

	private void proceso() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("¡Bienvenido a la Máquina Expendedora!");

		esperar(1500);
		
		int dinero = 0;
		int devuelta = 0;

		do {
			System.out.println("Introduzca el dinero:");
			
			dinero = 0;

			try {
				dinero = Integer.parseInt(br.readLine());
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("Procesando...");

			esperar(1500);

			if (dinero % 25 != 0) {
				System.out.println("Sólo se aceptan monedas de 25.");
				esperar(1500);
			}
		} while (dinero % 25 != 0);

		System.out.println("Elija una de las siguientes opciones:");

		int i = 0;

		for (String opcion : misOpciones) {
			System.out.println((i + 1) + "-" + opcion + " ($" + misPrecios.get(i) + ")");
			i++;
		}

		String s = "";

		try {
			s = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int eleccion = Integer.parseInt(s);

		System.out.println("Procesando...");

		esperar(1500);

		System.out.println("Su elección: " + misOpciones.get(eleccion - 1));
		System.out.println("Precio a pagar: " + "$" + misPrecios.get(eleccion - 1));

		System.out.println("Procesando...");

		esperar(1500);

		devuelta = evaluarDinero(dinero, misPrecios.get(eleccion - 1));

		if (devuelta < 0) {
			System.out.println("Dinero insuficiente");

			System.out.println("Devolviendo...");

			esperar(2000);

		} else {
			System.out.println("¡Aquí tiene su producto! Devuelta: $" + devuelta);
		}

		System.out.println("¿Desea comprar algo más? [S/N]");

		String op = "";

		try {
			op = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (op.equalsIgnoreCase("n")) {
			ejecutar = false;

			System.out.println("¡Gracias por utilizar la máquina!");
		} else if (op.equalsIgnoreCase("s")) {
			reiniciarMaquina();
		}
	}

	private int evaluarDinero(int dinero, int precio) {
		int devuelta = 0;

		if (dinero < precio) {
			devuelta = -1;
		} else {
			devuelta = dinero - precio;
		}

		return devuelta;
	}

	private void reiniciarMaquina() {
		System.out.println("Reiniciando máquina...");
		esperar(2500);
	}

	private void esperar(int milisegundos) {
		try {
			Thread.sleep(milisegundos);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> getMisOpciones() {
		return misOpciones;
	}

	public void setMisOpciones(ArrayList<String> misOpciones) {
		this.misOpciones = misOpciones;
	}

	public ArrayList<Integer> getMisPrecios() {
		return misPrecios;
	}

	public void setMisPrecios(ArrayList<Integer> misPrecios) {
		this.misPrecios = misPrecios;
	}

}
