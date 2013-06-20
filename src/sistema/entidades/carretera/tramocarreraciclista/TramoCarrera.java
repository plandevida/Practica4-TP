package sistema.entidades.carretera.tramocarreraciclista;

import sistema.entidades.carretera.Carretera;

/**
 * Clase que representa un tramo de la carrera ciclista
 * donde se conoce el viento del tramo y su distancia.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class TramoCarrera extends Carretera {
	
	/**
	 * @uml.property  name="pendiente"
	 */
	private int pendiente;
	
	/**
	 * Crea un tramo de la carretera, indicandole el tamaño del tarmo,
	 * la pendiente, y el viento.
	 * 
	 * @param kilometros Que mide el tarmo.
	 * @param pendientetramo Del tramo.
	 * @param vientodeltramo Del tramo.
	 */
	public TramoCarrera(double kilometros, int pendientetramo) {
		super(kilometros);
		pendiente = pendientetramo;
	}
	
	/**
	 * Pendiente del tramo.
	 * @return  La pendiente.
	 * @uml.property  name="pendiente"
	 */
	public int getPendiente() {
		return pendiente;
	}
}
