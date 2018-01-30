package logico;

public class Suministrador {
	
	private String identificador;
	private String nombre;
	private String pais;
    private int entrega;
    private Vino[] vinos;
    private int cantVino;
    private static int cantSuplidores = 0;
    
    public Suministrador(String identificador, String nombre, String pais, int entrega) {
		super();
		this.identificador = identificador;
		this.nombre = nombre;
		this.pais = pais;
		this.entrega = entrega;
		this.vinos = new Vino[100];
		this.cantVino = 0;
		this.cantSuplidores ++;
	}
    
    public void insertarVino(Vino v){
    	vinos[cantVino] = v;
    	cantVino++;
    }
    
    public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}
	public int getEntrega() {
		return entrega;
	}
	public void setEntrega(int entrega) {
		this.entrega = entrega;
	}
	public Vino[] getVinos() {
		return vinos;
	}
	public void setVinos(Vino[] vinos) {
		this.vinos = vinos;
	}
	public int getCantVino() {
		return cantVino;
	}
	public void setCantVino(int cantVino) {
		this.cantVino = cantVino;
	}

	public String getIdentificador() {
		return identificador;
	}

	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public static int getCantSuplidores() {
		return cantSuplidores;
	}

	public static void setCantSuplidores(int cantSuplidores) {
		Suministrador.cantSuplidores = cantSuplidores;
	}

}
