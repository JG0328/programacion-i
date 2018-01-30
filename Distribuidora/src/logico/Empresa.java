package logico;

public class Empresa {
	private Almacen[] almacenes;
	private int cantAlmacenes;

	public Empresa() {
		super();
		almacenes = new Almacen[100];

		cantAlmacenes = 0;
	}

	public void insertarAlmacen(Almacen almacen) {
		almacenes[cantAlmacenes] = almacen;
		cantAlmacenes++;
	}

	public float estimadoGanancias(Almacen almacen) {

		float ganancias = 0.0f;

		for (int i = 0; i < almacen.getCantProductos(); i++) {
			ganancias += almacen.getProductos()[i].obtenerGanancias();
		}

		return ganancias;
	}

	public float estimadoPerdidas() {
		float perdidas = 0.0f;

		for (int i = 0; i < cantAlmacenes; i++) {
			for (int j = 0; j < almacenes[i].getCantProductos(); j++) {
				perdidas += almacenes[i].getProductos()[j].obtenerPerdidas();
			}
		}

		return -perdidas;
	}

	public int disponibilidadProducto(String tipo) {
		int cant = 0;

		for (int i = 0; i < cantAlmacenes; i++) {
			for (int j = 0; j < almacenes[i].getCantProductos(); j++) {
				if (tipo.equalsIgnoreCase(almacenes[i].getProductos()[j].getTipo())) {
					cant += almacenes[i].getProductos()[j].getStockReal();
				}
			}
		}

		return cant;
	}

	public String despachoAlmacen(String codigo, int cantidad) {
		String codAlmacen = "";
		int i = 0;
		boolean despachado = false;

		while (!despachado && i < cantAlmacenes) {
			Producto producto = almacenes[i].buscarProducto(codigo);

			if (producto != null) {
				if (producto.getStockReal() >= (producto.getCantInicial() * 0.10f)
						&& producto.getStockReal() >= cantidad) {
					codAlmacen = almacenes[i].getCodigo();
					despachado = true;
				}
			}
			i++;
		}

		return codAlmacen;
	}

	public Almacen[] getAlmacenes() {
		return almacenes;
	}

	public void setAlmacenes(Almacen[] almacenes) {
		this.almacenes = almacenes;
	}

	public int getCantAlmacenes() {
		return cantAlmacenes;
	}

	public void setCantAlmacenes(int cantAlmacenes) {
		this.cantAlmacenes = cantAlmacenes;
	}
}
