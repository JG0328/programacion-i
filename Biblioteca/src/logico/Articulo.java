package logico;

public class Articulo extends Publicacion {
	private String arbitro;
	private String autor;

	public Articulo(String id, String titulo, String materia, int cantidad, boolean estado, String arbitro,
			String autor) {
		super(id, titulo, materia, cantidad, estado);
		this.arbitro = arbitro;
		this.autor = autor;
	}

	public String getArbitro() {
		return arbitro;
	}

	public void setArbitro(String arbitro) {
		this.arbitro = arbitro;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
}
