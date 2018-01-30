package logico;

public class Publicacion {
	protected String id;
	protected String titulo;
	protected String materia;
	protected int cantidad;
	protected boolean estado;

	public Publicacion(String id, String titulo, String materia, int cantidad, boolean estado) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.materia = materia;
		this.cantidad = cantidad;
		this.estado = estado;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}
}
