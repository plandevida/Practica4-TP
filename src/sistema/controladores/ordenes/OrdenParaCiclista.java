package sistema.controladores.ordenes;

import sistema.entidades.personas.ciclistas.Ciclista;

/**
 * Orden solo para los ciclistas.
 * 
 * @author Daniel Serrano Torres
 * @author Arlvaro Quesada Pimentel
 */
public abstract class OrdenParaCiclista extends Orden {

	// Ciclista al que se le va a ejecutar la orden.
	/**
	 * @uml.property  name="ciclista"
	 * @uml.associationEnd  
	 */
	private Ciclista ciclista;
	
	/**
	 * Configura el ciclista.
	 * @param  ciclista
	 * @uml.property  name="ciclista"
	 */
	protected void setCiclista(Ciclista ciclista) {
		this.ciclista = ciclista;
	}
	
	/**
	 * Obtiene el ciclista de la orden
	 * @return  El ciclista para el que está  dirigida la orden.
	 * @uml.property  name="ciclista"
	 */
	protected Ciclista getCiclista() {
		return ciclista;
	}
}
