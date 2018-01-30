package logico;

public class Alquiler {
	private String id;
	private Barco barco;
	private String fechaInicio;
	private int cantDias;
	private int posicion;
	private Cliente cliente;
	private float monto; // Se calcula automáticamente
	private String comentario; // Cuando se va a modificar...
	
	private static int totalAlquileres;

	public Alquiler(Barco barco, String fechaInicio, int cantDias, int posicion, Cliente cliente, float monto,
			String comentario) {
		super();
		// this.matricula = matricula;
		this.barco = barco;
		this.fechaInicio = fechaInicio;
		this.cantDias = cantDias;
		this.posicion = posicion;
		this.cliente = cliente;
		this.monto = monto;
		this.comentario = comentario;
		
		totalAlquileres++;
		this.id = "A-" + totalAlquileres;
	}

	public String getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getMonto() {
		return monto;
	}

	public void setMonto(float monto) {
		this.monto = monto;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Barco getBarco() {
		return barco;
	}

	public void setBarco(Barco barco) {
		this.barco = barco;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
