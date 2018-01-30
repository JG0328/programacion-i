package logico;

import exceptions.NegativeSideException;

public class Rectangulo {
	private float ladoA;
	private float ladoB;

	public Rectangulo(float ladoA, float ladoB) {
		super();
		this.ladoA = ladoA;
		this.ladoB = ladoB;
	}

	public float getLadoA() {
		return ladoA;
	}

	public void setLadoA(float ladoA) throws NegativeSideException {
		if (ladoA > 0)
			this.ladoA = ladoA;
		else
			throw new NegativeSideException("Lado A no puede ser negativo");
	}

	public float getLadoB() {
		return ladoB;
	}

	public void setLadoB(float ladoB) throws NegativeSideException {
		if (ladoB > 0)
			this.ladoB = ladoB;
		else
			throw new NegativeSideException("Lado B no puede ser negativo");
	}
}
