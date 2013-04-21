package sistema.entrada.ordenes;

import java.util.PriorityQueue;

/**
 * Clase que representa una orden a realizar por un elemento
 * único del sistema.
 * Es abstracta e implementa comparable para poder ser
 * añadida a colas {@link PriorityQueue}
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public abstract class Orden implements Comparable<Orden> {
	
	/**
	 * Método para mostrar mensajes de las ordenes.
	 */
	public abstract String mostrarMensaje();
	
	/**
	 * Ejecuta la orden, mandando el mensaje del
	 * comando al objeto que lo debe ejecutar.
	 */
	public abstract void ejecutarOrden();
	
	/**
	 * Parsea el comando y devuelve la orden configurada
	 * o nulo si no puede parsear el comando.
	 * 
	 * @param comando El comando a parsear.
	 * @return La orden configurada.
	 */
	public abstract Orden parse(String comando);
	
	/**
	 * Configura los parametros del comando en la orden
	 */
	public abstract void configurarContexto();
	
	/**
	 * Muestra la ayuda del comando.
	 * Si detalles es true se muestra la ayuda detallada.
	 * 
	 * @param detalles
	 * @return La información del comando
	 */
	public abstract String help(boolean detalles);
	
	@Override
	public int compareTo(Orden o) {
		int resultado = 0;
		
		if ( o != null ) {
			if (! this.equals(o)) {
				resultado = -1;
			}
		}
		
		return resultado;
	}
}
