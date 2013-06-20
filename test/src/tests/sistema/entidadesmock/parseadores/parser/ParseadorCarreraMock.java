package src.tests.sistema.entidadesmock.parseadores.parser;

import java.util.HashMap;
import java.util.Map;

import sistema.controladores.parseadores.ParseadorCarrera;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock;

public class ParseadorCarreraMock {
	
	private Map<Integer, TramoCarreraMock> mapa;
	
	/**
	 * Al invocar este constructor es necesario invocar a @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * para obtener la configuración parseada.
	 */
	public ParseadorCarreraMock() {
		mapa = new HashMap<Integer, TramoCarreraMock>();
	}
	
	/**
	 * Pasando el mapa donde se quiere almacenar la configuración no hace falta invocar al método @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * 
	 * @param configuracion
	 */
	public ParseadorCarreraMock(Map<Integer, TramoCarreraMock> configuracion) {
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
				TramoCarreraMock tramo = new TramoCarreraMock(kilometros, pendiente);
				
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
	public Map<Integer, TramoCarreraMock> getConfiguracionCarrera() {
		
		return mapa;
	}
}
