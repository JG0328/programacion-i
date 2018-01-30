package logico;

import java.util.ArrayList;
import java.util.Date;

public class Biblioteca {

	private ArrayList<Cliente> misClientes;
	private ArrayList<Publicacion> misPublicaciones;
	private ArrayList<Prestamo> misPrestamos;

	private static Biblioteca biblio = null;

	private Biblioteca() {
		super();
		misClientes = new ArrayList<>();
		misPublicaciones = new ArrayList<>();
		misPrestamos = new ArrayList<>();
	}

	public static Biblioteca getInstance() {
		if (biblio == null) {
			biblio = new Biblioteca();
		}
		return biblio;
	}

	public void insertarCliente(Cliente client) {
		misClientes.add(client);
	}

	public void insertarPublicacion(Publicacion publi) {
		misPublicaciones.add(publi);
	}

	public void insertarPrestamo(Prestamo prest) {
		misPrestamos.add(prest);
	}

	public Cliente findClientById(String cedula) {
		Cliente client = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < misClientes.size()) {
			if (misClientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				client = misClientes.get(i);
				encontrado = true;
			}
			i++;
		}

		return client;
	}

	public Cliente findClienteByPrestamo(Prestamo prest) {
		Cliente client = null;
		boolean find = false;
		int i = 0;
		while (!find && i < misClientes.size()) {
			for (int j = 0; j < misClientes.get(i).getMisPrestamos().size(); j++) {
				if (prest == misClientes.get(i).getMisPrestamos().get(j)) {
					client = misClientes.get(i);
					find = true;
				}
			}
			
			i++;
		}
		return client;
	}

	public void setPrestamo(Cliente client, String id, String fechaDevolucion) {
		Publicacion publi = findPublicacionById(id);

		Prestamo prest = new Prestamo(publi, (new Date()).toString(), fechaDevolucion);
		insertarPrestamo(prest);

		client.insertarPrestamo(prest);

		publi.setCantidad(publi.getCantidad() - 1);
	}

	public void returnPrestamo(Cliente client, Publicacion publi) {
		Prestamo prest = client.findPrestamoByPublic(publi);
		prest.setStatus(false);
		Publicacion p = findPublicacionById(prest.getPublicacion().getId());
		p.setCantidad(p.getCantidad() + 1);

		ArrayList<Prestamo> pClient = client.getMisPrestamos();
		pClient.remove(prest);
		client.setMisPrestamos(pClient);
	}

	private Cliente findClienteByCedula(String cedula) {
		return null;
	}

	public Publicacion findPublicacionById(String id) {
		Publicacion publi = null;
		int i = 0;
		boolean find = false;

		while (i < misPublicaciones.size() && !find) {
			if (misPublicaciones.get(i).getId().equalsIgnoreCase(id)) {
				publi = misPublicaciones.get(i);
				find = true;
			}
			i++;
		}
		return publi;
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Publicacion> getMisPublicaciones() {
		return misPublicaciones;
	}

	public void setMisPublicaciones(ArrayList<Publicacion> misPublicaciones) {
		this.misPublicaciones = misPublicaciones;
	}

	public ArrayList<Prestamo> getMisPrestamos() {
		return misPrestamos;
	}

	public void setMisPrestamos(ArrayList<Prestamo> misPrestamos) {
		this.misPrestamos = misPrestamos;
	}
}
