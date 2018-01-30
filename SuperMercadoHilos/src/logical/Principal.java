package logical;

import java.util.ArrayList;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

public class Principal {

	public static void main(String[] args) {
		Cliente cliente1 = new Cliente("Juan");
		Cliente cliente2 = new Cliente("Pepe");
		
		ArrayList<Integer> prodCLient1 = new ArrayList<>();
		prodCLient1.add(2);
		prodCLient1.add(2);
		prodCLient1.add(3);
		prodCLient1.add(5);
		prodCLient1.add(3);
		
		cliente1.setMisProductos(prodCLient1);
		
		ArrayList<Integer> prodCLient2 = new ArrayList<>();
		prodCLient2.add(1);
		prodCLient2.add(3);
		prodCLient2.add(5);
		prodCLient2.add(1);
		prodCLient2.add(1);
		
		cliente2.setMisProductos(prodCLient2);
		
		long time = System.currentTimeMillis();
		
		Cajera cajera1 = new Cajera("Maria");
		cajera1.setClient(cliente1);
		cajera1.setTime(time);
		Cajera cajera2 = new Cajera("Fefa");
		cajera2.setClient(cliente2);
		cajera2.setTime(time);
		cajera2.setPriority(10);
		cajera1.setPriority(1);
		cajera1.start();
		cajera2.start();
		
		
		

	}

}
