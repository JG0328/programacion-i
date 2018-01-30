package logico;

public class Esferico extends Queso {

	public Esferico(String id, String nombre, float precioBase, float precioUnitario, float radio, String tipo) {
		super(id, nombre, precioBase, precioUnitario, radio, tipo);
		// TODO Auto-generated constructor stub
	}

	public float volumen() {
		float v = 0.0f;

		v = (4.0f / 3.0f) * (float) Math.PI * (float) Math.pow(radio, 3);

		return v;
	}
}
