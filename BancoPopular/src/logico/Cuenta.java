package logico;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Cuenta implements Serializable {
	protected String codigo;
	protected float saldo;
	protected int meses;
	protected int corte;
	protected String estado;
	protected int puntos;
	protected String tipo;
	protected ArrayList<Transaccion> misTransacciones;

	public Cuenta(String codigo, float saldo, int meses, int corte, String estado, int puntos, String tipo) {
		super();
		this.codigo = codigo;
		this.saldo = saldo;
		this.meses = meses;
		this.corte = corte;
		this.estado = estado;
		this.puntos = puntos;
		this.tipo = tipo;
		this.misTransacciones = new ArrayList<>();
	}

	public void addTransaccion(Transaccion trans) {
		misTransacciones.add(trans);
	}

	public abstract float obtenerInteres();

	public abstract boolean retirarDinero(float monto);

	public float revision() {
		float total = 0.0f;

		total = saldo + obtenerInteres() - (3.0f * meses);

		return total;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getMeses() {
		return meses;
	}

	public void setMeses(int meses) {
		this.meses = meses;
	}

	public int getCorte() {
		return corte;
	}

	public void setCorte(int corte) {
		this.corte = corte;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public float getSaldo() {
		return saldo;
	}

	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Transaccion> getMisTransacciones() {
		return misTransacciones;
	}

	public void setMisTransacciones(ArrayList<Transaccion> misTransacciones) {
		this.misTransacciones = misTransacciones;
	}
}
