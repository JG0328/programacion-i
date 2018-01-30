package logico;

public class Cilindrico extends Queso {
	protected float longitud;

	public Cilindrico(String id, String nombre, float precioBase, float precioUnitario, float radio, String tipo,
			float longitud) {
		super(id, nombre, precioBase, precioUnitario, radio, tipo);
		this.longitud = longitud;
	}

	public float volumen() {
		float v = 0.0f;

		v = (float) Math.PI * (float) Math.pow(radio, 2) * longitud;

		return v;
	}

	public float getLongitud() {
		return longitud;
	}

	public void setLongitud(float longitud) {
		this.longitud = longitud;
	}
}
