package sistema.manager;

import java.util.List;
import java.util.Map;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.vista.InterfaceSalidaDatos;

/**
 * Clase encargada de transmitir/compartir los datos
 * con las diferentes partes del sistema.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Presentador {
	
	// Los ciclistas del sistema que participane el la carrera
	private List<Ciclista> listadeciclistas;
	
	// Los elementos que se muestran en la vista.
	private List<ObjetosConSalidaDeDatos> listadeobjetosamostarenvista;
	
	// El viento definido por horas.
	private Map<Integer, MiViento> mapameteorologico;

	/**
	 * Permisos del presentador sobre los elementos compartidos.
	 * 
	 * 0 = lista de ciclistas
	 * 1 = lista de objetos que se muestran
	 * 2 = mapa meteorologico
	 */
	public static final String[] permisos = {
			"LISTACICLISTAS",
			"LISTAOBJETOSVISTA",
			"MAPAMETEOROLOGICO"
	};
	
	/**
	 * Constructor que define todos los elementos del presentador.
	 * 
	 * @param ciclistas Los ciclistas del sistema que participane el la carrera
	 * @param objetosamostarenvista Los elementos que se muestran en la vista.
	 * @param vientoporhoras El viento definido por horas.
	 */
	public Presentador(List<Ciclista> ciclistas,
			List<ObjetosConSalidaDeDatos> objetosamostarenvista,
			Map<Integer, MiViento> vientoporhoras) {
		
		listadeciclistas = ciclistas;
		listadeobjetosamostarenvista = objetosamostarenvista;
		mapameteorologico = vientoporhoras;
	}

	/**
	 * Provee acceso a la lista de ciclistas.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @return La lista de cilistas, si no tiene permiso la clase devolverá null.
	 */
	public List<Ciclista> getListadeciclistas(Orden orden) {
		
		String[] permisosdelaorden = orden.misPermisos();
		
		boolean sepermiteelacceso = false;
		
		for (int i = 0; (i < permisos.length) && !sepermiteelacceso; i++) {
			if ( permisos[i].equals(permisosdelaorden[i]) ) {
				sepermiteelacceso = true;
			}
		}
		
		return sepermiteelacceso ? listadeciclistas : null;
	}

	/**
	 * Provee acceso a la lista de objetos que se presentan en las vista.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @return La lista de objetos a mostrar,
	 * si no tiene permiso la clase devolverá null.
	 */
	public List<ObjetosConSalidaDeDatos> getListadeobjetosamostarenvista(InterfaceSalidaDatos vista) {

		String[] permisosdelaorden = vista.misPermisos();
		
		boolean sepermiteelacceso = false;
		
		for (int i = 0; (i < permisos.length) && !sepermiteelacceso; i++) {
			if ( permisos[i].equals(permisosdelaorden[i]) ) {
				sepermiteelacceso = true;
			}
		}
		
		return listadeobjetosamostarenvista;
	}

	/**
	 * Provee acceso al mapa meteorológico del sistema.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @return El mapa meteorológico, si no tiene permiso la clase devolverá null.
	 */
	public Map<Integer, MiViento> getMapametereológico(Orden orden) {
		
		String[] permisosdelaorden = orden.misPermisos();
		
		boolean sepermiteelacceso = false;
		
		for (int i = 0; (i < permisos.length) && !sepermiteelacceso; i++) {
			if ( permisos[i].equals(permisosdelaorden[i]) ) {
				sepermiteelacceso = true;
			}
		}
		
		return sepermiteelacceso ? mapameteorologico : null;
	}
}
