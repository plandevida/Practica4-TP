package sistema.entidades.carretera.tramocarreraciclista;

/**
 * Clase que representa una curava de la carrera ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Curva {
	
	Double puntokilometrico;
	Double velocidadmaximacurva;
	
	public Curva( Double kilometro, Double velocidadmaxima) {
		puntokilometrico = kilometro;
		velocidadmaximacurva = velocidadmaxima;
	}

	/**
	 * Obtiene el punto kilométrico donde está la curva.
	 * 
	 * @return El punto kilometrico
	 */
	public Double getPuntokilometrico() {
		return puntokilometrico;
	}

	/**
	 * Obtiene la velocidad máxima a la que se puede pasar por la curva.
	 * 
	 * @return La velocidad máxima de la curva.
	 */
	public Double getVelocidadmaximacurva() {
		return velocidadmaximacurva;
	}
}
