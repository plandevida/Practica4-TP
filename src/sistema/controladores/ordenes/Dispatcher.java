package sistema.controladores.ordenes;

import java.util.PriorityQueue;

import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.manager.Presentador;

/**
 * Clase que distruye ls ordenes del sistema a los elementos del sistema.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Dispatcher implements ObjetosQueSeEjecutan {
	
	// Cola de ordenes a procesar.
	private PriorityQueue<Orden> listadeordenes;
	
	// El presentador del sistema.
	private Presentador presentador;
	
	/**
	 * Crea una instancia del distribuidor con la lista de elementos
	 * y la cola de ordenes vacía.
	 */
	public Dispatcher(Presentador presentadorsistema) {
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
	private void dispatch() {
		
		while ( ! listadeordenes.isEmpty()) {
			
			Orden orden = listadeordenes.poll();
			
			if ( orden != null) {
				orden.ejecutarOrden();
			}
		}	
	}

	@Override
	public void ejecuta() {
		dispatch();
	}
}
