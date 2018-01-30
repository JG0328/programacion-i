package logical;


public class Cajera extends Thread{
	
	private String nombre;
	private Cliente client;
	private long time;
	
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	public Cajera(String nombre) {
		super();
		this.nombre = nombre;
	}
	
	@Override
	public void run(){
		
		System.out.println("La cajera: " + this.nombre + " Ha comenzado ha Procesar al Cliente:" + client.getnombre()+
				"En el tiempo: " + (System.currentTimeMillis()-time)/1000 + "Seg");
		
		int i = 0;
		for (Integer prod : client.getMisProductos()) {
			this.esperarSegundos(prod);
			System.out.println("Cajera :"+ this.nombre+ "Procesando el Producto: " + ++i + "->Tiempo: " + (System.currentTimeMillis()-time)/1000 + "Seg");
		}
		
		System.out.println("La cajera: " + this.nombre + "Ha terminado de Procesar al Cliente:" + client.getnombre()+
				"En el tiempo: " + (System.currentTimeMillis()-time)/1000 + "Seg");
		
	}

	private void esperarSegundos(Integer prod) {
		try {
			Thread.sleep(prod*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public Cliente getClient() {
		return client;
	}

	public void setClient(Cliente client) {
		this.client = client;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
