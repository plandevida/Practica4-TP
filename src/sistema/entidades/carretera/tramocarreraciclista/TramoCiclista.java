package sistema.entidades.carretera.tramocarreraciclista;

import sistema.entidades.carretera.Carretera;
import sistema.factoresexternos.viento.MiViento;

/**
 * Clase que representa un tramo de la carrera ciclista
 * donde se conoce el viento del tramo y su distancia.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class TramoCiclista extends Carretera {
	
	private MiViento viento;
	private int pendiente;
	private double velocidadviento;
	
	/**
	 * Crea un tramo de la carretera, indicandole el tamaño del tarmo,
	 * la pendiente, y el viento.
	 * 
	 * @param kilometros Que mide el tarmo.
	 * @param pendientetramo Del tramo.
	 * @param vientodeltramo Del tramo.
	 */
	public TramoCiclista(double kilometros, int pendientetramo, MiViento vientodeltramo, double velodidaddelviento) {
		super(kilometros);
		viento = vientodeltramo;
		pendiente = pendientetramo;
		velocidadviento = velodidaddelviento;
	}
	
	/**
	 * El viento del tramo.
	 * 
	 * @return Objeto viento @see {@link MiViento}
	 */
	public MiViento getViento() {
		return viento;
	}
	
	/**
	 * Pendiente del tramo.
	 * 
	 * @return La pendiente.
	 */
	public int getPendiente() {
		return pendiente;
	}
	
	/**
	 * La velocidad del viento en el tramo.
	 * 
	 * @return La velocidad.
	 */
	public double getVelocidadViento() {
		return velocidadviento;
	}
}
