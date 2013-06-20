package sistema.controladores.parseadores;

import java.util.Map;
import java.util.TreeMap;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;

/**
 * Clase que parsea una cadena para construir la carretera de la carrera ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ParseadorCarrera {
	
	/**
	 * @uml.property  name="mapa"
	 * @uml.associationEnd  qualifier="kilometro:java.lang.Integer sistema.entidades.carretera.tramocarreraciclista.TramoCarrera"
	 */
	private Map<Integer, TramoCarrera> mapa;
	
	/**
	 * @uml.property  name="longitudcarrera"
	 */
	private Integer longitudcarrera;
	
	/**
	 * Al invocar este constructor es necesario invocar a @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * para obtener la configuración parseada.
	 */
	public ParseadorCarrera() {
		mapa = new TreeMap<Integer, TramoCarrera>();
		longitudcarrera = 0;
	}
	
	/**
	 * Pasando el mapa donde se quiere almacenar la configuración no hace falta invocar al método @see {@link ParseadorCarrera#getConfiguracionCarrera()}
	 * 
	 * @param configuracion
	 */
	public ParseadorCarrera(Map<Integer, TramoCarrera> configuracion) {
		mapa = configuracion;
		longitudcarrera = 0;
	}
	
	/**
	 * Parsea la cadena pasada.
	 */
	public void parse(String contenidofichero) {
		
		// Todas las líneas del fichero.
		String lineas[] = contenidofichero.split("\n");
		
		for(int i=0; i<lineas.length-1; i++) {
			
			try {
				String cadena[] = lineas[i].split(",");
				String cadenasiguiente[] = lineas[i+1].split(",");
				
				Integer kilometro = Integer.valueOf(cadena[0]);
				Integer pendiente = Integer.valueOf(cadena[1]);
				
				Integer kilometros = Integer.valueOf(cadenasiguiente[0]) - Integer.valueOf(cadena[0]);
				
				// Mapa con la pendiente y la dirección del viento.
				TramoCarrera tramo = new TramoCarrera(kilometros, pendiente);
				
				longitudcarrera += kilometros;
				
				mapa.put(kilometro, tramo);
				
				if (i == lineas.length-2)
					mapa.put(Integer.valueOf(cadenasiguiente[0]), new TramoCarrera(0d, 0));
				
			} catch (NumberFormatException ne) {
				
				System.err.println("Parseador de carreteras: Error en el formato de la línea.");
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
	
	/**
	 * Obtiene los kilómetros que mide la carrera
	 * @return
	 */
	public Integer getLongitudCarrera() {
		return longitudcarrera;
	}
}
