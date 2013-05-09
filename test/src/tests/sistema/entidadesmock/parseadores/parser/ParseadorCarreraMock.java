package src.tests.sistema.entidadesmock.parseadores.parser;

import java.util.HashMap;
import java.util.Map;

import sistema.controladores.parseadores.parser.ParseadorCarrera;
import sistema.factoresexternos.viento.MiViento;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCiclistaMock;

public class ParseadorCarreraMock {
	
	private Map<Integer, TramoCiclistaMock> mapa;
	
	/**
	 * Al invocar este constructor es necesario invocar a @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * para obtener la configuración parseada.
	 */
	public ParseadorCarreraMock() {
		mapa = new HashMap<Integer, TramoCiclistaMock>();
	}
	
	/**
	 * Pasando el mapa donde se quiere almacenar la configuración no hace falta invocar al método @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * 
	 * @param mapa2
	 */
	public ParseadorCarreraMock(Map<Integer, TramoCiclistaMock> mapa2) {
		mapa = mapa2;
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
				MiViento viento = MiViento.existe(cadena[2]);
				Double velocidadviento = Double.valueOf(cadena[3]);
				
				
				// Mapa con la pendiente y la dirección del viento.
				TramoCiclistaMock tramo = new TramoCiclistaMock(kilometros, pendiente, viento, velocidadviento);
				
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
	public Map<Integer, TramoCiclistaMock> getConfiguracionCarrera() {
		
		return mapa;
	}
}
