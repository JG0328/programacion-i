package logico;

import java.util.ArrayList;

public class Centro {
	private ArrayList<Postulante> misPostulantes;
	private ArrayList<Curso> misCursos;

	public Centro() {
		super();
		misPostulantes = new ArrayList<>();
		misCursos = new ArrayList<>();
	}

	public void verificarCursos() {
		int cant = 0;
		for (Curso curso : misCursos) {
			for (Postulante post : misPostulantes) {
				if (post.getCursos().get(0).getId().equalsIgnoreCase(curso.getId())) {
					cant++;
				}
			}
			if (cant >= (misPostulantes.size() * 0.10f)) {
				curso.setAbierto(true);
			}
			cant = 0;
		}
	}

	public void asignarCursos() {
		verificarCursos(); // se verifican los cursos para abrirlos
		
		ArrayList<Postulante> delete = new ArrayList<>();

		for (int i = 0; i < 3; i++) {
			for (Curso curso : misCursos) {
				for (Postulante post : misPostulantes) {
					if (curso.isAbierto() && curso.getEstudiantes().size() < 30) {
						if (post.getCursos().get(i).getId().equalsIgnoreCase(curso.getId())) {
							String id = post.getId();
							String nombre = post.getNombre();
							String apellidos = post.getApellidos();
							curso.getEstudiantes().add(new Estudiante(id, nombre, apellidos));
							//misPostulantes.remove(post);
							delete.add(post);
						}
					}
				}
			}
		}
		
		for (Postulante post : delete) {
			misPostulantes.remove(post);
		}
	}

	public ArrayList<Postulante> getMisPostulantes() {
		return misPostulantes;
	}

	public void setMisPostulantes(ArrayList<Postulante> misPostulantes) {
		this.misPostulantes = misPostulantes;
	}

	public ArrayList<Curso> getMisCursos() {
		return misCursos;
	}

	public void setMisCursos(ArrayList<Curso> misCursos) {
		this.misCursos = misCursos;
	}
}
