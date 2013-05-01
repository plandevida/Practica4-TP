package src.tests.sistema.entidadesmock.parseadores.parser;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.especificas.OrdenAsingarCadencia;
import sistema.controladores.ordenes.especificas.OrdenCambiarPinhon;
import sistema.controladores.ordenes.especificas.OrdenCambiarPlato;
import sistema.controladores.ordenes.especificas.OrdenFrenar;
import sistema.controladores.parseadores.InterfazParseador;

public class ParseadorComandosMock implements InterfazParseador {
	
	// Distribuidor de ordenes
	private Dispatcher distribuidor;
	
	// Lista de ordenes del sistema.
	Orden[] ordenes = {
		new OrdenAsingarCadencia(null, null, null),
		new OrdenFrenar(null, null, null),
		new OrdenCambiarPinhon(null, null),
		new OrdenCambiarPlato(null, null)
	};
	
	public ParseadorComandosMock(Dispatcher dispatcher) {
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
