package logico;

public class Corriente extends Cuenta {
	private float retiroMax;

	public Corriente(String codigo, float saldo, int meses, int corte, String estado, int puntos, String tipo,
			float retiroMax) {
		super(codigo, saldo, meses, corte, estado, puntos, tipo);
		this.retiroMax = retiroMax;
	}

	@Override
	public float obtenerInteres() {
		// TODO Auto-generated method stub
		float interes = (0.10f * retiroMax) * meses;
		return interes;
	}

	@Override
	public boolean retirarDinero(float monto) {
		// TODO Auto-generated method stub
		boolean resultado = false;

		if (monto <= saldo && monto <= retiroMax) {
			resultado = true;
			saldo -= monto;
			addTransaccion(new Transaccion(-monto, 0.0f, saldo));
		} else {
			resultado = false;
		}

		return resultado;
	}

	public float getRetiroMax() {
		return retiroMax;
	}

	public void setRetiroMax(float retiroMax) {
		this.retiroMax = retiroMax;
	}
}
