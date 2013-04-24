package sistema.controladores.parseador.lexer;

import sistema.controlador.ordenes.Orden;
import sistema.entrada.ordenes.especificas.OrdenAumentarCadencia;
import sistema.entrada.ordenes.especificas.OrdenBajarPinhon;
import sistema.entrada.ordenes.especificas.OrdenBajarPlato;
import sistema.entrada.ordenes.especificas.OrdenDesconocida;
import sistema.entrada.ordenes.especificas.OrdenFrenar;
import sistema.entrada.ordenes.especificas.OrdenSubirPinhon;
import sistema.entrada.ordenes.especificas.OrdenSubirPlato;

/**
 * Instrucciones que se pueden mandar al sistema para que realize
 * las acciones deseadas
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public enum Comandos {
	
	/**
	 * Disminuye la velocidad de la bicicleta
	 */
	FRENAR(new OrdenFrenar()),
	
	/**
	 * Aumenta de piñón la bicicleta
	 */
	SUBIRPINHON(new OrdenSubirPinhon()),
	
	/**
	 * Disminuye el piñón de la bicicleta
	 */
	BAJARPINHON(new OrdenBajarPinhon()),
	
	/**
	 * Aumenta el plato de la bicicleta
	 */
	SUBIRPLATO(new OrdenSubirPlato()),
	
	/**
	 * Disminuye el plato de la bicicleta
	 */
	BAJARPLATO(new OrdenBajarPlato()),
	
	/**
	 * Aumenta la cadencia del ciclista
	 */
	AUMENTARCADENCIA(new OrdenAumentarCadencia()),
	
	/**
	 * Comando usado cuando la instrucción proporcionada
	 * no es reconocida
	 */
	DESCONOCIDO(new OrdenDesconocida());
	
	Orden orden;
	
	Comandos(Orden o) {
		orden = o;
	}
	
	/**
	 * Comprueba que el comando existe en el enumerado.
	 * No distinge mayúsculas de minúsculas.
	 * 
	 * @param comando Comando introducido.
	 * @return El comando del enumerado que coincide con
	 * el introducido. Si el introducido no existe en el
	 * enumerado se devuelve DESCONOCIDO.
	 */
	public static Comandos existe(String comando) {
		Comandos comand = DESCONOCIDO;
		
		if (comando != null && !comando.equals("")) {
			
			for (Comandos c : Comandos.values()) {
				
				if ( c.name().equalsIgnoreCase(comando) ) 
					comand = c;
			}
		}
		
		return comand;
	}
	
	/**
	 * Obtiene la orden asociada a cadad comando.
	 * @return orden del comando.
	 */
	public Orden getOrden() {
		return orden;
	}
}
