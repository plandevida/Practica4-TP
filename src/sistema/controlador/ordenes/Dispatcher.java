package sistema.controlador.ordenes;

import java.util.PriorityQueue;

/**
 * Clase que distruye ls ordenes del sistema a los elementos del sistema.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Dispatcher {
	
	// Cola de ordenes a procesar.
	PriorityQueue<Orden> listadeordenes;
	
	/**
	 * Crea una instancia del distribuidor con la lista de elementos
	 * y la cola de ordenes vacía.
	 */
	public Dispatcher() {
		listadeordenes = new PriorityQueue<Orden>();
	}
	
	/**
	 * Añadir una orden a la cola.
	 * 
	 * @param orden Orden que se registrará en la cola.
	 */
	public void registrarOrdenes(Orden orden) {
		
		listadeordenes.add(orden);
	}
	
	/**
	 * Distribuye las ordenes a los elementos del sistema.
	 */
	public void dispatch() {
		
		while ( ! listadeordenes.isEmpty()) {
			
			Orden orden = listadeordenes.poll();
			
			if ( orden != null) {
				orden.ejecutarOrden();
			}
		}	
	}
}
