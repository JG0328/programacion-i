package logical;

import java.util.ArrayList;

public class Cliente {
	
	private String nombre;
	private ArrayList<Integer> misProductos;
	
	
	public String getnombre() {
		return nombre;
	}
	public void setnombre(String nombre) {
		nombre = nombre;
	}
	public ArrayList<Integer> getMisProductos() {
		return misProductos;
	}
	public void setMisProductos(ArrayList<Integer> misProductos) {
		this.misProductos = misProductos;
	}
	
	public Cliente(String nombre) {
		super();
		this.nombre = nombre;
		this.misProductos = new ArrayList<>();
	}

}
