package logico;

import java.util.ArrayList;

public class Principal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<String> opciones = new ArrayList<>();
		opciones.add("Coca Cola");
		opciones.add("Pepsi");
		opciones.add("Chocolate");
		opciones.add("Doritos");
		
		ArrayList<Integer> precios = new ArrayList<>();
		precios.add(35);
		precios.add(30);
		precios.add(45);
		precios.add(25);
		
		Maquina maq = new Maquina(opciones, precios);
		maq.start();
	}

}
