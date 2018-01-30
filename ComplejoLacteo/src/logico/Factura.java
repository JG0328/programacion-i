package logico;

import java.util.ArrayList;
import java.util.Date;

public class Factura {
	private String fecha;
	private Cliente cliente;
	private ArrayList<Queso> quesos;

	public Factura(Cliente cliente) {
		super();
		this.fecha = (new Date()).toString();
		this.cliente = cliente;
		this.quesos = new ArrayList<>();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Queso> getQuesos() {
		return quesos;
	}

	public void setQuesos(ArrayList<Queso> quesos) {
		this.quesos = quesos;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
}
