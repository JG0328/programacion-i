package logico;

public class Producto {
	private String codigo;
	private String nombre;
	private String tipo;
	private float precioCompra;
	private float precioVenta;
	private int diasRestantes;
	private int stockReal;
	private int cantInicial;
	private Almacen origen;

	public Producto(String codigo, String nombre, String tipo, float precioCompra, float precioVenta, int diasRestantes,
			int stockReal, int cantInicial, Almacen origen) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.tipo = tipo;
		this.precioCompra = precioCompra;
		this.precioVenta = precioVenta;
		this.diasRestantes = diasRestantes;
		this.stockReal = stockReal;
		this.cantInicial = cantInicial;
		this.origen = origen;
	}

	public float obtenerGanancias() {
		float ganancias = 0.0f;
		int cantVentas = cantInicial - stockReal; // Cantidad de ventas
													// realizadas
		float gasto = cantInicial * precioCompra;

		ganancias = (precioVenta * cantVentas) - gasto;

		return ganancias;
	}

	public float obtenerPerdidas() {
		float perdidas = 0.0f;
		float descuento = 0.0f;

		if (tipo.equalsIgnoreCase("comestible") && diasRestantes <= 60) {
			descuento = 0.50f;

		} else if (tipo.equalsIgnoreCase("atuendo") && diasRestantes <= 75) {
			descuento = 0.35f;

		} else if (tipo.equalsIgnoreCase("electrónico") && diasRestantes <= 90) {
			descuento = 0.20f;
		}

		// Si se le aplicó el descuento...
		if (diasRestantes <= 90) {
			perdidas = precioVenta * descuento * stockReal;
		}

		return perdidas;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public float getPrecioCompra() {
		return precioCompra;
	}

	public void setPrecioCompra(float precioCompra) {
		this.precioCompra = precioCompra;
	}

	public float getPrecioVenta() {
		return precioVenta;
	}

	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}

	public int getDiasRestantes() {
		return diasRestantes;
	}

	public void setDiasRestantes(int diasRestantes) {
		this.diasRestantes = diasRestantes;
	}

	public int getStockReal() {
		return stockReal;
	}

	public void setStockReal(int stockReal) {
		this.stockReal = stockReal;
	}

	public int getCantInicial() {
		return cantInicial;
	}

	public void setCantInicial(int cantInicial) {
		this.cantInicial = cantInicial;
	}

	public Almacen getOrigen() {
		return origen;
	}

	public void setOrigen(Almacen origen) {
		this.origen = origen;
	}
}
