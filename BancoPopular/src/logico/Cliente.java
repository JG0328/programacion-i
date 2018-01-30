package logico;

import java.io.Serializable;
import java.util.ArrayList;

public class Cliente implements Serializable {
	private String cedula;
	private String nombre;
	private String apellidos;
	private String direccion;
	private String telefono;
	private ArrayList<Cuenta> misCuentas;

	public Cliente(String cedula, String nombre, String apellidos, String direccion, String telefono) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.telefono = telefono;
		this.misCuentas = new ArrayList<>();
	}

	public void insertarCuenta(Cuenta cuenta) {
		misCuentas.add(cuenta);
	}

	public boolean retirarDinero(String codigo, float monto) {
		boolean resultado = false;

		Cuenta cuenta = buscarCuenta(codigo);

		if (cuenta.getEstado().equalsIgnoreCase("habilitada")) {
			resultado = cuenta.retirarDinero(monto);
		}

		return resultado;
	}

	public Cuenta buscarCuenta(String codigo) {
		Cuenta cuenta = null;
		int i = 0;
		boolean encontrado = false;

		while (!encontrado && i < misCuentas.size()) {
			if (misCuentas.get(i).getCodigo().equalsIgnoreCase(codigo)) {
				cuenta = misCuentas.get(i);
			}
			i++;
		}

		return cuenta;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public ArrayList<Cuenta> getMisCuentas() {
		return misCuentas;
	}

	public void setMisCuentas(ArrayList<Cuenta> misCuentas) {
		this.misCuentas = misCuentas;
	}
}
