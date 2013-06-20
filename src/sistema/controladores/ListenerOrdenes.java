package sistema.controladores;

import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.Ventana;

/**
 * Interfaz para los controladores que generan ordenes.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public abstract class ListenerOrdenes implements ListenerOrden {
	
	private Dispatcher comandero;
	private Ventana ventana;
	
	/**
	 * Construye un controlador que genera ordenes.
	 * 
	 * @param comandero Comandero del sistema.
	 */
	public ListenerOrdenes(Dispatcher comandero, Ventana ventana) {
		this.comandero = comandero;
		this.ventana = ventana;
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
	
	/**
	 * Obtiene la vista relacionada con el constructor.
	 */
	public Ventana getVentana() {
		return ventana;
	}
}
