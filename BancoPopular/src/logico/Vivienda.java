package logico;

public class Vivienda extends Cuenta {
	private int tiempoAhorro;
	private float montoMensual;

	public Vivienda(String codigo, float saldo, int meses, int corte, String estado, int puntos, String tipo,
			int tiempoAhorro, float montoMensual) {
		super(codigo, saldo, meses, corte, estado, puntos, tipo);
		this.tiempoAhorro = tiempoAhorro;
		this.montoMensual = montoMensual;
	}

	@Override
	public float obtenerInteres() {
		// TODO Auto-generated method stub
		float interes = (0.03f * montoMensual) * meses;
		return interes;
	}

	@Override
	public boolean retirarDinero(float monto) {
		// TODO Auto-generated method stub
		return false;
	}

	public int getTiempoAhorro() {
		return tiempoAhorro;
	}

	public void setTiempoAhorro(int tiempoAhorro) {
		this.tiempoAhorro = tiempoAhorro;
	}

	public float getMontoMensual() {
		return montoMensual;
	}

	public void setMontoMensual(float montoMensual) {
		this.montoMensual = montoMensual;
	}
}
