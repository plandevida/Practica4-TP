package sistema.controlador.ordenes;

import sistema.entidades.personas.ciclistas.Ciclista;

/**
 * Orden solo para los ciclistas.
 * 
 * @author Daniel Serrano Torres
 * @author Arlvaro Quesada Pimentel
 */
public class OrdenParaCiclista extends Orden {

	private Ciclista ciclista;
	
	@Override
	public void ejecutarOrden() {
		mostrarMensaje();
	}
	
	/**
	 * Configura el ciclista.
	 * 
	 * @param ciclista
	 */
	public void setCiclista(Ciclista ciclista) {
		this.ciclista = ciclista;
	}
	
	/**
	 * Obtiene el ciclista de la orden
	 * 
	 * @return El ciclista para el que está
	 * dirigida la orden.
	 */
	public Ciclista getCiclista() {
		return ciclista;
	}
	
	/**
	 * Método para mostrar mensajes de las ordenes.
	 */
	@Override
	public String mostrarMensaje() {
		return "";
	}

	@Override
	public Orden parse(String comando) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configurarContexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help(boolean detalles) {
		// TODO Auto-generated method stub
		return null;
	}
}
