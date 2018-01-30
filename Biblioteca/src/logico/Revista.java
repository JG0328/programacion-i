package logico;

public class Revista extends Publicacion {
	private int agno;
	private String numero;

	public Revista(String id, String titulo, String materia, int cantidad, boolean estado, int agno, String numero) {
		super(id, titulo, materia, cantidad, estado);
		this.agno = agno;
		this.numero = numero;
	}

	public int getAgno() {
		return agno;
	}

	public void setAgno(int agno) {
		this.agno = agno;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}
