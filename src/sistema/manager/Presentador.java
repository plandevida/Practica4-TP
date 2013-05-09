package sistema.manager;

import java.util.List;
import java.util.Map;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
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
	private Map<Integer, Map<MiViento, Double>> mapameteorologico;
	
	// El reloj de la carrera ciclista
	private Reloj reloj;
	
	// Ordenes del parser y el sistema.
	private Orden[] ordenes;

	/**
	 * Permisos del presentador sobre los elementos compartidos.
	 * 
	 * 0 = lista de ciclistas
	 * 1 = lista de objetos que se muestran
	 * 2 = mapa meteorologico
	 * 3 = reloj del sistema
	 * 4 = ordenes del sistema
	 */
	public static final String[] permisos = {
			"LISTACICLISTAS",
			"LISTAOBJETOSVISTA",
			"MAPAMETEOROLOGICO",
			"RELOJ",
			"ORDENES"
	};
	
	/**
	 * Constructor que define todos los elementos del presentador.
	 * 
	 * @param ciclistas Los ciclistas del sistema que participane el la carrera
	 * @param objetosamostarenvista Los elementos que se muestran en la vista.
	 * @param vientoporhoras El viento definido por horas.
	 * @param relojcarrera El reloj del sistema.
	 * @param ordenesparseador Las ordenes aceptadas por el sistema.
	 */
	public Presentador(List<Ciclista> ciclistas,
			List<ObjetosConSalidaDeDatos> objetosamostarenvista,
			Map<Integer, Map<MiViento, Double>> vientoporhoras,
			Reloj relojcarrera, Orden[] ordenesparseador) {
		
		listadeciclistas = ciclistas;
		listadeobjetosamostarenvista = objetosamostarenvista;
		mapameteorologico = vientoporhoras;
		reloj = relojcarrera;
		ordenes = ordenesparseador;
	}

	/**
	 * Provee acceso a la lista de ciclistas.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @param orden La orden que solicita un ciclista
	 * @param identificadorciclista El id del ciclista (número de mallot).
	 * @return La lista de cilistas, si no tiene permiso la clase devolverá null.
	 */
	public Ciclista getciclista(Orden orden, int identificadorciclista) {
		
		String[] permisosdelaorden = orden.misPermisos();

		Ciclista ciclista = null;
		
		for (int i = 0; (i < permisosdelaorden.length) && ciclista == null; i++) {
			
			if (permisos[0].equals(permisosdelaorden[i])) {
				
				for (Ciclista c : listadeciclistas) {
					
					ciclista = (c.getNumeromallot() == identificadorciclista) ? c : null;
				}
			}
		}
		
		return ciclista;
	}

	/**
	 * Provee acceso a la lista de objetos que se presentan en las vista.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @param vista El componenete que maneja los datos en la vista.
	 * @return La lista de objetos a mostrar,
	 * si no tiene permiso la clase devolverá null.
	 */
	public List<ObjetosConSalidaDeDatos> getListadeobjetosamostarenvista(InterfaceSalidaDatos vista) {

		String[] permisosdelaorden = vista.misPermisos();
		
		boolean sepermiteelacceso = false;
		
		for (int i = 0; (i < permisosdelaorden.length) && !sepermiteelacceso; i++) {
			if ( permisos[1].equals(permisosdelaorden[i]) ) {
				sepermiteelacceso = true;
			}
		}
		
		return sepermiteelacceso ? listadeobjetosamostarenvista : null;
	}

	/**
	 * Provee acceso al mapa meteorológico del sistema.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @param orden La orden que solicita el mapa meteorológico.
	 * @return El mapa meteorológico, si no tiene permiso la clase devolverá null.
	 */
	public Map<Integer, Map<MiViento, Double>> getMapametereológico(Orden orden) {
		
		String[] permisosdelaorden = orden.misPermisos();
		
		boolean sepermiteelacceso = false;
		
		for (int i = 0; (i < permisosdelaorden.length) && !sepermiteelacceso; i++) {
			if ( permisos[2].equals(permisosdelaorden[i]) ) {
				sepermiteelacceso = true;
			}
		}
		
		return sepermiteelacceso ? mapameteorologico : null;
	}
	
	/**
	 * Provee acceso al reloj del sistema.
	 * Solo las clases con el permiso adecuado pueden acceder a la lista.
	 * 
	 * @param orden La orden que solicita el reloj.
	 * @return El reloj del sistema.
	 */
	public Reloj getReloj(Orden orden) {
		
		String[] permisosdelaorden = orden.misPermisos();
		
		boolean sepermiteelacceso = false;
		
		for (int i = 0; (i < permisosdelaorden.length) && !sepermiteelacceso; i++) {
			if ( permisos[3].equals(permisosdelaorden[i]) ) {
				sepermiteelacceso = true;
			}
		}
		
		return sepermiteelacceso ? reloj : null;
	}
	
	/**
	 * Provee acceso a la lista de ordenes del sistema.
	 * Solo las clases con el permiso adecuado pueden acceder a las ordenes.
	 * 
	 * @param orden La orden que solicita la lista de ordenes.
	 * @return La lista de ordenes aceptada por el sistema.
	 */
	public Orden[] getOrdenes(Orden orden) {
		
		String[] permisosdelaorden = orden.misPermisos();
		
		boolean sepermiteelacceso = false;
		
		for (int i = 0; (i < permisosdelaorden.length) && !sepermiteelacceso; i++) {
			if ( permisos[4].equals(permisosdelaorden[i])) {
				sepermiteelacceso = true;
			}
		}
		
		return sepermiteelacceso ? ordenes : null;
	}
}
