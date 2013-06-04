package sistema.controladores.parseadores.parser;

import java.util.HashMap;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;

/**
 * Clase que parsea una cadena para construir la carretera de la carrera ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ParseadorCarrera {
	
	private Map<Integer, TramoCarrera> mapa;
	
	/**
	 * Al invocar este constructor es necesario invocar a @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * para obtener la configuración parseada.
	 */
	public ParseadorCarrera() {
		mapa = new HashMap<Integer, TramoCarrera>();
	}
	
	/**
	 * Pasando el mapa donde se quiere almacenar la configuración no hace falta invocar al método @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * 
	 * @param configuracion
	 */
	public ParseadorCarrera(Map<Integer, TramoCarrera> configuracion) {
		mapa = configuracion;
	}
	
	/**
	 * Parsea la cadena pasada.
	 */
	public void parse(String contenidofichero) {
		
		// Todas las líneas del fichero.
		String lineas[] = contenidofichero.split("\n");
		
		for(int i=0; i<lineas.length; i++) {
			
			try {
				String cadena[] = lineas[i].split(",");
				
				Integer kilometros = Integer.valueOf(cadena[0]);
				Integer pendiente = Integer.valueOf(cadena[1]);
				
				// Mapa con la pendiente y la dirección del viento.
				TramoCarrera tramo = new TramoCarrera(kilometros, pendiente);
				
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
	public Map<Integer, TramoCarrera> getConfiguracionCarrera() {
		
		return mapa;
	}
}
