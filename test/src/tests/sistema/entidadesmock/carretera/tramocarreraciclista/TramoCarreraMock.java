package src.tests.sistema.entidadesmock.carretera.tramocarreraciclista;

import sistema.entidades.carretera.Carretera;

/**
 * Clase que representa un tramo de la carrera ciclista
 * donde se conoce el viento del tramo y su distancia.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class TramoCarreraMock extends Carretera {
	
	private int pendiente;
	
	/**
	 * Crea un tramo de la carretera, indicandole el tamaño del tarmo,
	 * la pendiente, y el viento.
	 * 
	 * @param kilometros Que mide el tarmo.
	 * @param pendientetramo Del tramo.
	 * @param vientodeltramo Del tramo.
	 */
	public TramoCarreraMock(double kilometros, int pendientetramo) {
		super(kilometros);
		pendiente = pendientetramo;
	}
	
	/**
	 * Pendiente del tramo.
	 * 
	 * @return La pendiente.
	 */
	public int getPendiente() {
		return pendiente;
	}
}
