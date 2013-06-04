package sistema.controladores.ordenes;

import java.util.PriorityQueue;

import sistema.controladores.parseadores.parser.ParseadorComandos;
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
	
	// El parseador de comandos del sistema.
	private ParseadorComandos parser;
	
	/**
	 * Crea una instancia del distribuidor con la lista de elementos
	 * y la cola de ordenes vacía.
	 */
	public Dispatcher(Presentador presentadorsistema, ParseadorComandos parseadorcomandos) {
		listadeordenes = new PriorityQueue<Orden>();
		
		presentador = presentadorsistema;
	}
	
	/**
	 * Añadir una orden a la cola.
	 * 
	 * @param orden Orden que se registrará en la cola.
	 */
	public void registrarOrdenes(Orden orden) {
		
		if (orden != null) {
			orden.configurarContexto(presentador);
			
			listadeordenes.add(orden);
		}
	}
	
	/**
	 * Parsea un texto y construye una orden.
	 * 
	 * @param comandoaparsear Texto a parsear
	 */
	public void parsearComando(String comandoaparsear) {
		registrarOrdenes(parser.parse(comandoaparsear));
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
	
	/**
	 * Obtiene el presentador del Dispatcher.
	 * 
	 * @return El presentador.
	 */
	public Presentador getPresentador() {
		return presentador;
	}

	@Override
	public void ejecuta() {
		dispatch();
	}
}
