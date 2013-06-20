package sistema.controladores;

import sistema.controladores.ordenes.Dispatcher;

/**
 * Interfaz para los controladores.
 * @author  Daniel Serrano Torres
 * @author  Alvaro Quesada Pimentel
 */
public interface ListenerOrden {
	
	/**
	 * Obtiene el comandero del sistema.
	 * @return   El comandero.
	 * @uml.property  name="dispatcher"
	 * @uml.associationEnd  
	 */
	public Dispatcher getDispatcher();
}
