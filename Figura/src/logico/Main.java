package logico;

import exceptions.NegativeSideException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Rectangulo r = new Rectangulo(10, 10);

		try {
			r.setLadoA(2);
			r.setLadoB(-6);
		} catch (NegativeSideException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

}
