package logico;

public class Hueco extends Cilindrico {
	private float radioInterior;

	public Hueco(String id, String nombre, float precioBase, float precioUnitario, float radio, String tipo,
			float longitud, float radioInterior) {
		super(id, nombre, precioBase, precioUnitario, radio, tipo, longitud);
		if (radioInterior >= radio) {
			this.radioInterior = radio;
		} else {
			this.radioInterior = radioInterior;
		}
	}

	public float volumen() {
		float v = 0.0f;

		v = (float) Math.PI * longitud * ((float) Math.pow(radio, 2) - (float) Math.pow(radioInterior, 2));

		return v;
	}

	public float getRadioInterior() {
		return radioInterior;
	}

	public void setRadioInterior(float radioInterior) {
		this.radioInterior = radioInterior;
	}

}
