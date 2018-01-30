package logico;

import java.util.Iterator;

public class Almacen {

	private Vino[] misVinos;
	private Suministrador[] misSuminis;
	private int cantVino;
	private int cantSumi;
	
	public Almacen() {
		super();
		cantSumi = 0;
		cantVino = 0;
		misSuminis = new Suministrador[100];
		misVinos = new Vino[100];
	}
	
	public void insertVino(Vino v){
		misVinos[cantVino] = v;
		cantVino++;
		
	}
	
	public void insertSuministrador(Suministrador s){
		misSuminis[cantSumi] = s;
		cantSumi++;
		
	}
	
	public Vino buscarVino(String nombreVino){
		Vino v = null;
		int i = 0;
		String aux;
		boolean encontrado = false;
		while(!encontrado && i<cantVino){
		 if(misVinos[i].getNombre().equalsIgnoreCase(nombreVino)){
			v = misVinos[i];
			encontrado = true;
		 }
		 i++;
		}
		
		return v;	
	}
	
	public Suministrador buscarSuminitrador(String indentificador){
		Suministrador sumi = null;
		int i = 0;
		String aux;
		boolean encontrado = false;
		while(!encontrado && i<cantSumi){
		 if(misSuminis[i].getIdentificador().equalsIgnoreCase(indentificador)){
			sumi = misSuminis[i];
			encontrado = true;
		 }
		 i++;
		}
		
		return sumi;	
	}
	
	public Suministrador buscarSuministrador(String nombreVino){
		Suministrador v = null;
		int i = 0;
		int j =0;
		boolean encontrado = false;
		while(!encontrado && i<cantSumi){
			for(;j< misSuminis[i].getCantVino();j++){
		       if(misVinos[j].getNombre().equalsIgnoreCase(nombreVino)){
			    v = misSuminis[i];
			    encontrado = true;
		      }
		 i++;
		}
		}
		return v;	
	}	
	
	public boolean hacerPedido(String nombreVino){
		boolean pedido = false;
		Suministrador sumi = buscarSuministrador(nombreVino);
		Vino vino = buscarVino(nombreVino);
		if(sumi!=null && vino!=null){
			if(vino.chequeo()&&sumi.getEntrega()<30){
				pedido = true;
			}
		}
		
		return pedido;
	}
	
	
	
	public Vino[] getMisVinos() {
		return misVinos;
	}
	
	public Suministrador[] getMisSuminis() {
		return misSuminis;
	}
	
	public int getCantVino() {
		return cantVino;
	}
	
	public int getCantSumi() {
		return cantSumi;
	}

	public void eliminaSuministrador(Suministrador sumi) {
		EliminarVinoDeSuministrador(sumi);
		int index = buscarIndexSuministrador(sumi);
		for (int i = index; i < cantSumi-1; i++) {
			misSuminis[index] = misSuminis[index+1];
			
		}
		cantSumi--;
		
	}

	private int buscarIndexSuministrador(Suministrador sumi) {
		int index = -1;
		boolean encontrado = false;
		int i=0;
		while (!encontrado && i< cantSumi) {
			if(misSuminis[i].getIdentificador().equalsIgnoreCase(sumi.getIdentificador())){
				index = i;
				encontrado = true;
			}
			i++;
		}
		return index;
	}

	private void EliminarVinoDeSuministrador(Suministrador sumi) {
		int cantVinoDelSuministrador = sumi.getCantVino();
		while(cantVinoDelSuministrador>0){
		    for (int i = 0; i < sumi.getCantVino(); i++) {
				EliminarVinoAlmacen(sumi.getVinos()[i]);
			}
		    cantVinoDelSuministrador--;
		}
		
		
	}

	private void EliminarVinoAlmacen(Vino vino) {
		int index = buscarIndiceVino(vino);
		if(index!=-1){
			for (int i = index; i < cantVino-1; i++) {
				misVinos[index] = misVinos[index+1];
			}
		  cantVino--;	
		}
		
	}

	private int buscarIndiceVino(Vino vino) {
		int i = 0;
		int index = -1;
		boolean encontrado = false;
		while (!encontrado && i< cantVino) {
			if(misVinos[i].getIdentificador().equalsIgnoreCase(vino.getIdentificador())){
				index = i;
				encontrado = true;
			}
			i++;
		}
		return index;
	}

	public void modificarSuministrador(Suministrador sumi) {
		int index = buscarIndexSuministrador(sumi);
		misSuminis[index] = sumi;
		
	}
	
	
	
	
}
