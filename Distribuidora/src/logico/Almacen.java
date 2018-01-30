package logico;

public class Almacen {
	private String codigo;
	private String ciudad;
	private String municipio;
	private float capacidad; // toneladas
	private float terreno;
	private Producto[] productos;
	private int cantProductos;

	public Almacen(String codigo, String ciudad, String municipio, float capacidad, float terreno) {
		super();
		this.codigo = codigo;
		this.ciudad = ciudad;
		this.municipio = municipio;
		this.capacidad = capacidad;
		this.terreno = terreno;

		this.cantProductos = 0;
		this.productos = new Producto[100];
	}

	public void insertarProducto(Producto producto) {
		productos[cantProductos] = producto;
		cantProductos++;
	}

	public Producto buscarProducto(String codigo) {
		Producto producto = null;
		boolean encontrado = false;
		int i = 0;

		while (!encontrado && i < cantProductos) {
			if (codigo.equalsIgnoreCase(productos[i].getCodigo())) {
				producto = productos[i];
				encontrado = true;
			}
			i++;
		}

		return producto;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public float getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(float capacidad) {
		this.capacidad = capacidad;
	}

	public float getTerreno() {
		return terreno;
	}

	public void setTerreno(float terreno) {
		this.terreno = terreno;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}

	public int getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(int cantProductos) {
		this.cantProductos = cantProductos;
	}

}
