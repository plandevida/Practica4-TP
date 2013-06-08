package src.tests.sistema.entidadesmock.carretera;

/**
 * Clase que representa a un trozo de carretera
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class CarreteraMock {
	
	private double kilometros;
	
	public CarreteraMock(double kilometrosdecarretera) {
		kilometros = kilometrosdecarretera;
	}
	
	/**
	 * Los kilómetros que mide el tramo.
	 * 
	 * @return El número de kilómetros.
	 */
	public double getKilometros() {
		return kilometros;
	}
	
	/**
	 * Configura los kilómetros de la carretera
	 * 
	 * @param kilometrosdecarretera
	 */
	public void setKilometros(double kilometrosdecarretera) {
		kilometros = kilometrosdecarretera;
	}
}
