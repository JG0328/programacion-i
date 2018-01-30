package logico;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Puerto {
	private Cliente[] clientes;
	private Barco[] barcos;
	private Alquiler[] alquileres;

	private int cantClientes;
	private int cantBarcos;
	private int cantAlquileres;

	public Puerto() {
		super();

		cantBarcos = 0;
		cantClientes = 0;
		cantAlquileres = 0;

		clientes = new Cliente[100];
		barcos = new Barco[100];
		alquileres = new Alquiler[100];
	}

	public void saveData() throws IOException {
		FileOutputStream c = new FileOutputStream("Clientes.dat");
		FileOutputStream b = new FileOutputStream("Barcos.dat");

		ObjectOutputStream oosC = new ObjectOutputStream(c);
		ObjectOutputStream oosB = new ObjectOutputStream(b);

		oosC.writeInt(cantClientes);
		oosB.writeInt(cantBarcos);

		for (int i = 0; i < cantClientes; i++) {
			oosC.writeObject(clientes[i]);
		}

		for (int i = 0; i < cantBarcos; i++) {
			oosB.writeObject(barcos[i]);
		}

		c.close();
		b.close();
	}

	public void loadData() throws IOException {
		FileInputStream b = new FileInputStream("Barcos.dat");
		FileInputStream c = new FileInputStream("Clientes.dat");

		ObjectInputStream oisB = new ObjectInputStream(b);
		ObjectInputStream oisC = new ObjectInputStream(c);

		cantBarcos = oisB.readInt();
		cantClientes = oisC.readInt();

		for (int i = 0; i < cantBarcos; i++) {
			try {
				Barco barco = (Barco) oisB.readObject();
				barcos[i] = barco;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 0; i < cantClientes; i++) {
			try {
				Cliente cliente = (Cliente) oisC.readObject();
				clientes[i] = cliente;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		b.close();
		c.close();
	}

	public void insertarBarco(Barco b) {
		barcos[cantBarcos] = b;
		cantBarcos++;
	}

	public void insertarCliente(Cliente c) {
		clientes[cantClientes] = c;
		cantClientes++;
	}

	public void insertarAlquiler(Alquiler a) {
		alquileres[cantAlquileres] = a;
		cantAlquileres++;
	}

	public void eliminarBarco(Barco barco) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantBarcos) {
			if (barco == barcos[i]) {
				organizarBarcos(i);
				encontrado = true;
			}

			i++;
		}
	}

	public void eliminarBarcoEnCliente(Barco barco) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantClientes) {
			if (barco == clientes[i].getBarco()) {
				clientes[i].setBarco(null);
				encontrado = true;
			}

			i++;
		}
	}

	public void eliminarBarcoEnAlquiler(Barco barco) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantAlquileres) {
			if (barco == alquileres[i].getBarco()) {
				alquileres[i].setBarco(null);
				encontrado = true;
			}

			i++;
		}
	}

	void organizarBarcos(int ini) {
		for (int i = ini; i < cantBarcos - 1; i++) {
			barcos[i] = barcos[i + 1];
		}

		cantBarcos--;
	}

	public void eliminarCliente(Cliente cliente) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantClientes) {
			if (cliente == clientes[i]) {
				organizarClientes(i);
				encontrado = true;
			}

			i++;
		}
	}

	public void eliminarClienteEnAlquiler(Cliente cliente) {
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantAlquileres) {
			if (cliente == alquileres[i].getCliente()) {
				alquileres[i].setCliente(null);
				encontrado = true;
			}

			i++;
		}
	}

	void organizarClientes(int ini) {
		for (int i = ini; i < cantClientes - 1; i++) {
			clientes[i] = clientes[i + 1];
		}

		cantClientes--;
	}

	public Barco buscarBarco(String matricula) {
		Barco barco = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantBarcos) {
			if (matricula.equalsIgnoreCase(barcos[i].getMatricula())) {
				barco = barcos[i];
				encontrado = true;
			}

			i++;
		}

		return barco;
	}

	public Alquiler buscarAlquiler(String id) {
		Alquiler alquiler = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantAlquileres) {
			if (id.equals(alquileres[i].getId())) {
				alquiler = alquileres[i];
				encontrado = true;
			}

			i++;
		}

		return alquiler;
	}

	public Cliente buscarCliente(String cedula) {
		Cliente cliente = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantClientes) {
			if (cedula.equalsIgnoreCase(clientes[i].getCedula())) {
				cliente = clientes[i];
				encontrado = true;
			}

			i++;
		}

		return cliente;
	}

	public int buscarClienteID(String cedula) {
		// Cliente cliente = null;
		boolean encontrado = false;
		int id = 0;
		int i = 0;

		while (!encontrado && i < cantClientes) {
			if (cedula.equalsIgnoreCase(clientes[i].getCedula())) {
				// cliente = clientes[i];
				id = i;
				encontrado = true;
			}

			i++;
		}

		return id;
	}

	public int buscarBarcoID(String matricula) {
		boolean encontrado = false;
		int id = 0;
		int i = 0;

		while (!encontrado && i < cantBarcos) {
			if (matricula.equalsIgnoreCase(barcos[i].getMatricula())) {
				id = i;
				encontrado = true;
			}

			i++;
		}

		return id;
	}

	public float calcularAlquiler(String matricula, int dias) {
		float alquiler = 0.00f;
		Barco b = buscarBarco(matricula);

		if (b != null) {
			alquiler = dias * (10 * b.getEslora()) * 50.25f;
		}

		return alquiler;
	}

	public float obtenerGanancias() {
		float ganancias = 0.00f;

		for (int i = 0; i < cantAlquileres; i++) {
			ganancias += calcularAlquiler(alquileres[i].getBarco().getMatricula(), alquileres[i].getCantDias());
		}

		return ganancias;
	}

	public int getCantClientes() {
		return cantClientes;
	}

	public void setCantClientes(int cantClientes) {
		this.cantClientes = cantClientes;
	}

	public int getCantBarcos() {
		return cantBarcos;
	}

	public void setCantBarcos(int cantBarcos) {
		this.cantBarcos = cantBarcos;
	}

	public Cliente[] getClientes() {
		return clientes;
	}

	public void setClientes(Cliente[] clientes) {
		this.clientes = clientes;
	}

	public Barco[] getBarcos() {
		return barcos;
	}

	public void setBarcos(Barco[] barcos) {
		this.barcos = barcos;
	}

	public Alquiler[] getAlquileres() {
		return alquileres;
	}

	public void setAlquileres(Alquiler[] alquileres) {
		this.alquileres = alquileres;
	}

	public int getCantAlquileres() {
		return cantAlquileres;
	}

	public void setCantAlquileres(int cantAlquileres) {
		this.cantAlquileres = cantAlquileres;
	}

}
