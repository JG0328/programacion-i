package logico;

public abstract class Queso {
	protected String id;
	protected String nombre;
	protected float precioBase;
	protected float precioUnitario;
	protected float radio;
	private String tipo;

	public Queso(String id, String nombre, float precioBase, float precioUnitario, float radio, String tipo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.precioBase = precioBase;
		this.precioUnitario = precioUnitario;
		this.radio = radio;
		this.tipo = tipo;
	}

	public abstract float volumen();

	public float getPrecioBase() {
		return precioBase;
	}

	public void setPrecioBase(float precioBase) {
		this.precioBase = precioBase;
	}

	public float getPrecioUnitario() {
		return precioUnitario;
	}

	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}

	public float getRadio() {
		return radio;
	}

	public void setRadio(float radio) {
		this.radio = radio;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
