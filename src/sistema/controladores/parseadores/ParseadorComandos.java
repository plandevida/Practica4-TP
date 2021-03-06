package sistema.controladores.parseadores;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.especificas.OrdenAsingarCadencia;
import sistema.controladores.ordenes.especificas.OrdenAyuda;
import sistema.controladores.ordenes.especificas.OrdenCambiaViento;
import sistema.controladores.ordenes.especificas.OrdenCambiarPinhon;
import sistema.controladores.ordenes.especificas.OrdenCambiarPlato;
import sistema.controladores.ordenes.especificas.OrdenClembuterol;
import sistema.controladores.ordenes.especificas.OrdenCrearCurva;
import sistema.controladores.ordenes.especificas.OrdenEmpezarCarrera;
import sistema.controladores.ordenes.especificas.OrdenFrenar;
import sistema.controladores.ordenes.especificas.OrdenPararCarrera;
import sistema.controladores.ordenes.especificas.OrdenPonerMeta;
import sistema.controladores.ordenes.especificas.OrderReanudarCarrera;

/**
 * Clase que construye la orden para el sistema a partir
 * de un comando.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ParseadorComandos {
	
	// Lista de ordenes del sistema.
	private final Orden[] ordenes = {
		new OrdenAsingarCadencia(null, null, null),
		new OrdenFrenar(null, null, null),
		new OrdenCambiarPinhon(null, null),
		new OrdenCambiarPlato(null, null),
		new OrdenCambiaViento(null, null, null),
		new OrdenCrearCurva(null, null),
		new OrdenPonerMeta(null),
		new OrdenClembuterol(null,null),
		new OrdenPararCarrera(),
		new OrderReanudarCarrera(),
		new OrdenEmpezarCarrera(),
		new OrdenAyuda(null)
		
	};
	
	/**
	 * Genera una orden para un elemento del sistema,
	 * a partir de un comando recibido
	 * 
	 * @param comando Comando recibido.
	 */
	public Orden parse(String comando) {
		
		Orden nuevaorden = null;
		
		for( Orden orden : ordenes) {
			if (nuevaorden == null) {
				nuevaorden = orden.parse(comando);
			}
		}
		
		return nuevaorden;
	}
	
	/**
	 * Devuelve la lista de ordenes del sistema.
	 * @return  Lista de ordenes.
	 */
	public Orden[] getOrdenes() {
		return ordenes;
	}
}
