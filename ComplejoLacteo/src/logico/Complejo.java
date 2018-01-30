package logico;

import java.util.ArrayList;

public class Complejo {
	private ArrayList<Cliente> misClientes;
	private ArrayList<Factura> misFacturas;
	private ArrayList<Queso> misQuesos;

	private static Complejo comp = null;

	private Complejo() {
		super();

		misClientes = new ArrayList<>();
		misFacturas = new ArrayList<>();
		misQuesos = new ArrayList<>();
	}

	public static Complejo getInstance() {
		if (comp == null) {
			comp = new Complejo();
		}

		return comp;
	}

	public void insertCliente(Cliente client) {
		misClientes.add(client);
	}

	public void insertFactura(Factura fact) {
		misFacturas.add(fact);
	}

	public void insertQueso(Queso queso) {
		misQuesos.add(queso);
	}

	public Cliente buscarCliente(String id) {
		Cliente client = null;
		int i = 0;
		boolean find = false;

		while (!find && i < misClientes.size()) {
			if (misClientes.get(i).getId().equalsIgnoreCase(id)) {
				client = misClientes.get(i);
			}
			i++;
		}

		return client;
	}

	public Queso buscarQueso(String id) {
		Queso queso = null;
		int i = 0;
		boolean find = false;

		while (!find && i < misQuesos.size()) {
			if (misQuesos.get(i).getId().equalsIgnoreCase(id)) {
				queso = misQuesos.get(i);
			}

			i++;
		}

		return queso;
	}

	public float precioQueso(Queso queso) {
		float total = 0.0f;

		total = queso.getPrecioBase() + queso.getPrecioUnitario() * queso.volumen();

		return total;
	}

	public float precioFactura(ArrayList<Queso> quesos) {
		float total = 0.0f;

		for (int i = 0; i < quesos.size(); i++) {
			total += quesos.get(i).getPrecioBase() + quesos.get(i).getPrecioUnitario() * quesos.get(i).volumen();
		}

		return total;
	}

	public ArrayList<Queso> obtenerEsfericos() {
		ArrayList<Queso> quesos = new ArrayList<>();

		for (int i = 0; i < misQuesos.size(); i++) {
			if (misQuesos.get(i) instanceof Esferico) {
				quesos.add(misQuesos.get(i));
			}
		}

		return quesos;
	}

	public ArrayList<Queso> obtenerCilindricos() {
		ArrayList<Queso> quesos = new ArrayList<>();

		for (int i = 0; i < misQuesos.size(); i++) {
			if (misQuesos.get(i) instanceof Cilindrico) {
				quesos.add(misQuesos.get(i));
			}
		}

		return quesos;
	}

	public ArrayList<Queso> obtenerHuecos() {
		ArrayList<Queso> quesos = new ArrayList<>();

		for (int i = 0; i < misQuesos.size(); i++) {
			if (misQuesos.get(i) instanceof Hueco) {
				quesos.add(misQuesos.get(i));
			}
		}

		return quesos;
	}

	public float precioMayorEsferico(Factura factura) {
		float total = 0.0f;
		float mayorVolumen = 0.0f;
		ArrayList<Queso> quesos = new ArrayList<>();
		Queso queso = null;

		for (int i = 0; i < factura.getQuesos().size(); i++) {
			if (misQuesos.get(i) instanceof Esferico) {
				if (misQuesos.get(i).volumen() > mayorVolumen) {
					mayorVolumen = misQuesos.get(i).volumen();
					queso = misQuesos.get(i);
				}
			}
		}

		if (queso != null) {
			quesos.add(queso);
			total = precioFactura(quesos);
		}

		return total;
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Factura> getMisFacturas() {
		return misFacturas;
	}

	public void setMisFacturas(ArrayList<Factura> misFacturas) {
		this.misFacturas = misFacturas;
	}

	public ArrayList<Queso> getMisQuesos() {
		return misQuesos;
	}

	public void setMisQuesos(ArrayList<Queso> misQuesos) {
		this.misQuesos = misQuesos;
	}
}
