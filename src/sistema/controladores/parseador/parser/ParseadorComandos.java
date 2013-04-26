package sistema.controladores.parseador.parser;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.especificas.OrdenAumentarCadencia;
import sistema.controladores.ordenes.especificas.OrdenBajarPinhon;
import sistema.controladores.ordenes.especificas.OrdenBajarPlato;
import sistema.controladores.ordenes.especificas.OrdenFrenar;
import sistema.controladores.ordenes.especificas.OrdenSubirPinhon;
import sistema.controladores.ordenes.especificas.OrdenSubirPlato;
import sistema.controladores.parseador.InterfazParseador;
import sistema.manager.Manager;

/**
 * Clase que construye la orden para el sistema a partir
 * de un comando.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ParseadorComandos implements InterfazParseador {
	
	// Distribuidor de ordenes
	private Dispatcher distribuidor;
	
	// Lista de ordenes del sistema.
	Orden[] ordenes = {
		new OrdenAumentarCadencia(),
		new OrdenBajarPinhon(),
		new OrdenBajarPlato(),
		new OrdenFrenar(),
		new OrdenSubirPinhon(),
		new OrdenSubirPlato()
	};
	
	public ParseadorComandos(Manager presentador) {
		distribuidor = new Dispatcher(presentador);
	}
	
	public ParseadorComandos(Dispatcher dispatcher) {
		distribuidor = dispatcher;
	}
	
	/**
	 * Genera una orden para un elemento del sistema,
	 * a partir de un comando recibido y la registra en el
	 * dispatcher @see {@link Dispatcher#registrarOrdenes(Orden)}
	 * 
	 * @param comando Comando recibido.
	 */
	@Override
	public void parse(String comando) {
		
		Orden nuevaorden = null;
		
		for( Orden orden : ordenes) {
			if (nuevaorden == null) {
				nuevaorden = orden.parse(comando);
			}
		}
		
		// Se registra la orden para ser distribuida
		distribuidor.registrarOrdenes(nuevaorden);
	}
	
	/**
	 * Devuelve el distribuidor de ordenes del parseador.
	 * 
	 * @return El distribuidor de ordenes.
	 */
	public Dispatcher getDispatcher() {
		return distribuidor;
	}
}
