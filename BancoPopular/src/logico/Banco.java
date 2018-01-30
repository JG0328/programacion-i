package logico;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Banco {
	private ArrayList<Cliente> misClientes;
	private ArrayList<Cuenta> misCuentas;

	private static Banco banco = null;

	private Banco() {
		super();
		misClientes = new ArrayList<>();
		misCuentas = new ArrayList<>();
	}

	public static Banco getInstance() {
		if (banco == null) {
			banco = new Banco();
		}
		return banco;
	}

	public void saveData() throws IOException {

		actualizarCuentas();

		if (misCuentas.size() > 0) {

			FileOutputStream cu = new FileOutputStream("Cuentas.dat");
			FileOutputStream cl = new FileOutputStream("Clientes.dat");

			ObjectOutputStream oosCuentas = new ObjectOutputStream(cu);
			ObjectOutputStream oosClientes = new ObjectOutputStream(cl);

			oosCuentas.writeInt(misCuentas.size());
			oosClientes.writeInt(misClientes.size());

			for (Cuenta cuenta : misCuentas) {
				oosCuentas.writeObject(cuenta);
			}

			for (Cliente cliente : misClientes) {
				oosClientes.writeObject(cliente);
			}

			cu.close();
			cl.close();
		}
	}

	public void loadData() throws IOException {

		File f = new File("Cuentas.dat");

		if (f.exists()) {

			FileInputStream cu = new FileInputStream("Cuentas.dat");
			FileInputStream cl = new FileInputStream("Clientes.dat");

			ObjectInputStream oisCuentas = new ObjectInputStream(cu);
			ObjectInputStream oisClientes = new ObjectInputStream(cl);

			int cantCuentas = oisCuentas.readInt();
			int cantClientes = oisClientes.readInt();

			for (int i = 0; i < cantCuentas; i++) {
				try {
					misCuentas.add((Cuenta) oisCuentas.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			for (int i = 0; i < cantClientes; i++) {
				try {
					misClientes.add((Cliente) oisClientes.readObject());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			cu.close();
			cl.close();

			actualizarCuentas();
		}
	}

	public void insertarCliente(Cliente cliente) {
		misClientes.add(cliente);
	}

	public void insertarCuenta(Cuenta cuenta) {
		misCuentas.add(cuenta);
	}

	public void actualizarCuentas() {

		misCuentas.clear();

		for (Cliente cliente : misClientes) {
			for (Cuenta cuenta : cliente.getMisCuentas()) {
				misCuentas.add(cuenta);
			}
		}
	}

	public float revisionTotal(String cedula) {
		float total = 0.0f;

		Cliente cliente = buscarCliente(cedula);

		for (Cuenta cuenta : cliente.getMisCuentas()) {
			if (cuenta.getEstado().equalsIgnoreCase("habilitada")) {
				total += cuenta.revision();
			}
		}

		return total;
	}

	public boolean retirarDinero(String cedula, String codigo, float monto) {
		boolean resultado = false;
		Cliente cliente = buscarCliente(cedula);

		resultado = cliente.retirarDinero(codigo, monto);

		return resultado;
	}

	public void ingresarDinero(String cedula, String codigo, float monto) {
		Cliente cliente = buscarCliente(cedula);
		Cuenta cuenta = cliente.buscarCuenta(codigo);

		cuenta.setSaldo(cuenta.getSaldo() + monto);

		int puntos = cuenta.getPuntos();
		puntos += (int) monto / 10;

		cuenta.setPuntos(cuenta.getPuntos() + puntos);

		cuenta.addTransaccion(new Transaccion(monto, 0.0f, cuenta.getSaldo()));
	}

	public int revisionTotalPuntos(String cedula) {
		int puntos = 0;
		Cliente cliente = buscarCliente(cedula);

		for (Cuenta cuenta : cliente.getMisCuentas()) {
			puntos += cuenta.getPuntos();
		}

		return puntos;
	}

	public Cliente buscarCliente(String cedula) {
		int i = 0;
		boolean encontrado = false;
		Cliente cliente = null;

		while (!encontrado && i < misClientes.size()) {
			if (misClientes.get(i).getCedula().equalsIgnoreCase(cedula)) {
				cliente = misClientes.get(i);
				encontrado = true;
			}
			i++;
		}

		return cliente;
	}

	public Cuenta buscarCuenta(String codigo) {
		int i = 0;
		boolean encontrado = false;
		Cuenta cuenta = null;

		while (!encontrado && i < misCuentas.size()) {
			if (misCuentas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				cuenta = misCuentas.get(i);
				encontrado = true;

				System.out.println(cuenta.getMisTransacciones().size());
			}
			i++;
		}

		return cuenta;
	}

	public Cliente buscarClienteByCuenta(String codigoCuenta) {
		int i = 0;
		boolean encontrado = false;
		Cliente cliente = null;

		while (!encontrado && i < misClientes.size()) {
			for (Cuenta cuent : misClientes.get(i).getMisCuentas()) {
				if (codigoCuenta.equalsIgnoreCase(cuent.getCodigo())) {
					cliente = misClientes.get(i);
					encontrado = true;
				}
			}
			i++;
		}

		return cliente;
	}

	public ArrayList<Cliente> getMisClientes() {
		return misClientes;
	}

	public void setMisClientes(ArrayList<Cliente> misClientes) {
		this.misClientes = misClientes;
	}

	public ArrayList<Cuenta> getMisCuentas() {
		return misCuentas;
	}

	public void setMisCuentas(ArrayList<Cuenta> misCuentas) {
		this.misCuentas = misCuentas;
	}
}
