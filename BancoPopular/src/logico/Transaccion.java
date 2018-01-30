package logico;

import java.io.Serializable;
import java.util.Date;

public class Transaccion implements Serializable {
	private String fecha;
	private float monto;
	private float comision;
	private float saldoActual;

	public Transaccion(float monto, float comision, float saldoActual) {
		super();
		this.fecha = (new Date()).toString();
		this.monto = monto;
		this.comision = comision;
		this.saldoActual = saldoActual;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public float getSaldoActual() {
		return saldoActual;
	}

	public void setSaldoActual(float saldoActual) {
		this.saldoActual = saldoActual;
	}

	public float getComision() {
		return comision;
	}

	public void setComision(float comision) {
		this.comision = comision;
	}
}
