package logico;

public class Prestamo {
	private Publicacion publicacion;
	private String fechaSolicitud;
	private String fechaDevolucion;
	private boolean status;

	public Prestamo(Publicacion publicacion, String fechaSolicitud, String fechaDevolucion) {
		super();
		this.publicacion = publicacion;
		this.fechaSolicitud = fechaSolicitud;
		this.fechaDevolucion = fechaDevolucion;
		this.status = true;
	}

	public String getFechaSolicitud() {
		return fechaSolicitud;
	}

	public void setFechaSolicitud(String fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}

	public String getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(String fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	public Publicacion getPublicacion() {
		return publicacion;
	}

	public void setPublicacion(Publicacion publicacion) {
		this.publicacion = publicacion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
}
