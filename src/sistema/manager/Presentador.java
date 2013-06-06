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
	
	// Ordenes del parser y el sistema.
	private Orden[] ordenes;
	
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
			Map<Integer, MiViento> vientoporhoras,
			Orden[] ordenesparseador) {
		
		listadeciclistas = ciclistas;
		listadeobjetosamostarenvista = objetosamostarenvista;
		mapameteorologico = vientoporhoras;
		ordenes = ordenesparseador;
	}

	/**
	 * Provee acceso a la lista de ciclistas.
	 * 
	 * @param identificadorciclista El id del ciclista (número de mallot).
	 * @return La lista de cilistas, si no tiene permiso la clase devolverá null.
	 */
	public Ciclista getCiclista(int identificadorciclista) {

		Ciclista ciclista = null;
				
		for (Ciclista c : listadeciclistas) {
			
			ciclista = (c.getNumeromallot() == identificadorciclista) ? c : null;
		}
		
		return ciclista;
	}
	
	/**
	 * Provee acceso a la lista de ciclistas.
	 * 
	 * @param identificadorciclista El identificador del ciclista (número de mallot y nombre).
	 * @return La lista de cilistas, si no tiene permiso la clase devolverá null.
	 */
	public Ciclista getCiclista(String identificadorciclista) {
		
		Ciclista ciclista = null;
		
		for (Ciclista c : listadeciclistas) {
			
			if (c.getIdentificadorSalidaDatos().equals(identificadorciclista))
				ciclista = c;
		}
		
		return ciclista;
	}

	/**
	 * Provee acceso a la lista de objetos que se presentan en las vista.
	 * 
	 * @param vista El componenete que maneja los datos en la vista.
	 * @return La lista de objetos a mostrar,
	 */
	public List<ObjetosConSalidaDeDatos> getListadeobjetosamostarenvista(InterfaceSalidaDatos vista) {
		
		return listadeobjetosamostarenvista;
	}

	/**
	 * Provee acceso al mapa meteorológico del sistema.
	 * 
	 * @return El mapa meteorológico, si no tiene permiso la clase devolverá null.
	 */
	public Map<Integer, MiViento> getMapametereológico() {
		
		return mapameteorologico;
	}
	
	/**
	 * Provee acceso a la lista de ordenes del sistema.
	 * 
	 * @return La lista de ordenes aceptada por el sistema.
	 */
	public Orden[] getOrdenes() {
		
		return ordenes;
	}
}
