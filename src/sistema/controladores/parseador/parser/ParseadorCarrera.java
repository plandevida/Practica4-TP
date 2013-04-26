package sistema.controladores.parseador.parser;

import java.util.HashMap;
import java.util.Map;

import sistema.controladores.parseador.InterfazParseador;
import sistema.entidades.carretera.tramocarreraciclista.TramoCiclista;
import sistema.factoresexternos.viento.MiViento;

/**
 * Clase que parsea una cadena para construir la carretera de la carrera ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ParseadorCarrera implements InterfazParseador {
	
	private Map<Integer, TramoCiclista> mapa;
	
	/**
	 * Al invocar este constructor es necesario invocar a @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * para obtener la configuración parseada.
	 */
	public ParseadorCarrera() {
		mapa = new HashMap<Integer, TramoCiclista>();
	}
	
	/**
	 * Pasando el mapa donde se quiere almacenar la configuración no hace falta invocar al método @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * 
	 * @param configuracion
	 */
	public ParseadorCarrera(Map<Integer, TramoCiclista> configuracion) {
		mapa = configuracion;
	}
	
	/**
	 * Parsea la cadena pasada.
	 */
	@Override
	public void parse(String contenidofichero) {
		
		// Todas las líneas del fichero.
		String lineas[] = contenidofichero.split("\n");
		
		for(int i=0; i<lineas.length; i++) {
			
			try {
				String cadena[] = lineas[i].split(",");
				
				Integer kilometros = Integer.valueOf(cadena[0]);
				Integer pendiente = Integer.valueOf(cadena[1]);
				MiViento viento = MiViento.existe(cadena[2]);
				Double velocidadviento = Double.valueOf(cadena[3]);
				
				
				// Mapa con la pendiente y la dirección del viento.
				TramoCiclista tramo = new TramoCiclista(kilometros, pendiente, viento, velocidadviento);
				
				mapa.put(i+1, tramo);
				
			} catch (NumberFormatException ne) {
				
				System.err.println("Error en el formato de la línea.");
			}
		}
		
	}
	
	/**
	 * Obtiene la configuración de la carrera ciclista.
	 * 
	 * @return La configuración de la carrera ciclista.
	 */
	public Map<Integer, TramoCiclista> getConfiguracionCarrera() {
		
		return mapa;
	}
}
