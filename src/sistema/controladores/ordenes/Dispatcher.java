package sistema.controladores.ordenes;

import java.util.PriorityQueue;

import sistema.manager.Manager;

/**
 * Clase que distruye ls ordenes del sistema a los elementos del sistema.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Dispatcher {
	
	// Cola de ordenes a procesar.
	private PriorityQueue<Orden> listadeordenes;
	
	// El presentador del sistema.
	private Manager presentador;
	
	/**
	 * Crea una instancia del distribuidor con la lista de elementos
	 * y la cola de ordenes vacía.
	 */
	public Dispatcher(Manager presentadorsistema) {
		listadeordenes = new PriorityQueue<Orden>();
		
		presentador = presentadorsistema;
	}
	
	/**
	 * Añadir una orden a la cola.
	 * 
	 * @param orden Orden que se registrará en la cola.
	 */
	public void registrarOrdenes(Orden orden) {
		
		orden.configurarContexto(presentador);
		
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
