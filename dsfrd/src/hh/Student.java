package hh;

public class Student extends Person {

	private String matricula;
	
	private static final long serialVersionUID = 1L;
	
	
	public Student(String name, int age, String matricula) {
		super(name, age);
		this.matricula = matricula;
		// TODO Auto-generated constructor stub
	}


	public String getMatricula() {
		return matricula;
	}


	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	/**
	 * 
	 */
	

}
