package logico;

public class Vino {
  private String identificador;	
  private String nombre;
  private int cocecha;
  private String tipo;
  private int dispMin;
  private int dispMax;
  private int dispReal;
  private int[] ventas;
  private static int codigo=0;

  
  public Vino(String identificador, String nombre, int cocecha, String tipo, int dispMin, int dispMax, int dispReal) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.cocecha = cocecha;
		this.tipo = tipo;
		this.dispMin = dispMin;
		this.dispMax = dispMax;
		this.dispReal = dispReal;
		this.ventas = new int[10];
		this.codigo++;
	}
  
  public String getNombre() {
	return nombre;
}
  
public boolean chequeo() {
		boolean aux = false;
		if(dispReal<dispMin && promedio()){
			aux  = true;
		}
		return aux;
	}
  
private boolean promedio() {
	boolean aux = false;
	int suma = 0; 
	for (int i = 6; i < ventas.length-1; i++) {
		suma+=ventas[i];
	}
	if(ventas[9]>(suma/3)){
		aux = true;
	}
	return aux;
}

public void setNombre(String nombre) {
	this.nombre = nombre;
}
public int getCocecha() {
	return cocecha;
}
public void setCocecha(int cocecha) {
	this.cocecha = cocecha;
}
public String getTipo() {
	return tipo;
}
public void setTipo(String tipo) {
	this.tipo = tipo;
}
public int getDispMin() {
	return dispMin;
}
public void setDispMin(int dispMin) {
	this.dispMin = dispMin;
}
public int getDispMax() {
	return dispMax;
}
public void setDispMax(int dispMax) {
	this.dispMax = dispMax;
}
public int getDispReal() {
	return dispReal;
}
public void setDispReal(int dispReal) {
	this.dispReal = dispReal;
}
public int[] getVentas() {
	return ventas;
}

public String getIdentificador() {
	return identificador;
}

public void setIdentificador(String identificador) {
	this.identificador = identificador;
}

public static int getCodigo() {
	return codigo;
}




}
