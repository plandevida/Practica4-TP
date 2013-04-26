package sistema.manager;

import java.util.List;
import java.util.Map;

import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;

/**
 * Clase encargada de transmitir/compartir los datos
 * con las diferentes partes del sistema.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Presentador {
	
	private List<Ciclista> listadeciclistas;
	
	private List<ObjetosConSalidaDeDatos> listadeobjetosamostarenvista;
	
	private Map<Integer, MiViento> mapameteorologico;

	public Presentador(List<Ciclista> listadeciclistas,
			List<ObjetosConSalidaDeDatos> listadeobjetosamostarenvista,
			Map<Integer, MiViento> mapametereológico) {
		
		this.listadeciclistas = listadeciclistas;
		this.listadeobjetosamostarenvista = listadeobjetosamostarenvista;
		this.mapameteorologico = mapametereológico;
	}

	/**
	 * Provee acceso a la lista de ciclistas.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @return La lista de cilistas, si no tiene permiso la clase devolverá null.
	 */
	public List<Ciclista> getListadeciclistas() {
		// TODO Hacer las comprobaciones de los permisos
		return listadeciclistas;
	}

	/**
	 * Provee acceso a la lista de objetos que se presentan en las vista.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @return La lista de objetos a mostrar,
	 * si no tiene permiso la clase devolverá null.
	 */
	public List<ObjetosConSalidaDeDatos> getListadeobjetosamostarenvista() {
		// TODO Hacer las comprobaciones de los permisos
		return listadeobjetosamostarenvista;
	}

	/**
	 * Provee acceso al mapa meteorológico del sistema.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @return El mapa meteorológico, si no tiene permiso la clase devolverá null.
	 */
	public Map<Integer, MiViento> getMapametereológico() {
		// TODO Hacer las comprobaciones de los permisos
		return mapameteorologico;
	}
}
