package sistema.controladores;

import sistema.controladores.ordenes.Dispatcher;

/**
 * Interfaz para los controladores que generan ordenes.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public abstract class ListenerOrdenes implements ListenerOrden {
	private Dispatcher comandero;
	
	/**
	 * Construye un controlador que genera ordenes.
	 * 
	 * @param comandero Comandero del sistema.
	 */
	public ListenerOrdenes(Dispatcher comandero) {
		this.comandero = comandero;
	}
	
	/**
	 * Obtiene el comandero del controlador.
	 * 
	 * @return El comandero.
	 */
	@Override
	public Dispatcher getDispatcher() {
		return comandero;
	}
}
