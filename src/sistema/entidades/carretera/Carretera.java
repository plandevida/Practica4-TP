package sistema.entidades.carretera;

/**
 * Clase que representa a un trozo de carretera
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Carretera {
	
	/**
	 * @uml.property  name="kilometros"
	 */
	private double kilometros;
	
	public Carretera(double kilometrosdecarretera) {
		kilometros = kilometrosdecarretera;
	}
	
	/**
	 * Los kilómetros que mide el tramo.
	 * @return  El número de kilómetros.
	 * @uml.property  name="kilometros"
	 */
	public double getKilometros() {
		return kilometros;
	}
	
	/**
	 * Configura los kilómetros de la carretera
	 * @param  kilometrosdecarretera
	 * @uml.property  name="kilometros"
	 */
	public void setKilometros(double kilometrosdecarretera) {
		kilometros = kilometrosdecarretera;
	}
}
