package logico;

public class Inversion extends Cuenta {
	private float interesFijo;

	public Inversion(String codigo, float saldo, int meses, int corte, String estado, int puntos, String tipo,
			float interesFijo) {
		super(codigo, saldo, meses, corte, estado, puntos, tipo);
		this.interesFijo = interesFijo;
	}

	@Override
	public float obtenerInteres() {
		// TODO Auto-generated method stub
		float interes = (interesFijo / 100.0f) * meses;
		return interes;
	}

	@Override
	public boolean retirarDinero(float monto) {
		// TODO Auto-generated method stub

		float comision = 0.0f;

		if (monto > 500.0f) {
			comision = monto * 0.01f;
			saldo -= monto + comision;
		} else {
			saldo -= monto;
		}
		
		System.out.println(comision);

		addTransaccion(new Transaccion(-monto, comision, saldo));

		return true;
	}

	public float getInteresFijo() {
		return interesFijo;
	}

	public void setInteresFijo(float interesFijo) {
		this.interesFijo = interesFijo;
	}
}
