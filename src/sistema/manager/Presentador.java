package sistema.manager;

import java.util.List;
import java.util.Map;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.carretera.tramocarreraciclista.Curva;
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
	/**
	 * @uml.property  name="listadeciclistas"
	 */
	private List<Ciclista> listadeciclistas;
	
	// Los elementos que se muestran en la vista.
	/**
	 * @uml.property  name="listadeobjetosamostarenvista"
	 */
	private List<ObjetosConSalidaDeDatos> listadeobjetosamostarenvista;
	
	// El viento definido por horas.
	/**
	 * @uml.property  name="mapameteorologico"
	 */
	private Map<Integer, MiViento> mapameteorologico;
	
	// Ordenes del parser y el sistema.
	/**
	 * @uml.property  name="ordenes"
	 * @uml.associationEnd  multiplicity="(0 -1)"
	 */
	private Orden[] ordenes;
	
	// El reloj del sistema.
	/**
	 * @uml.property  name="reloj"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Reloj reloj;
	
	// La lista con las curvas del sistema.
	/**
	 * @uml.property  name="listacurvas"
	 */
	private List<Curva> listacurvas;
	
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
			Orden[] ordenesparseador, Reloj relojdelsistema,
			List<Curva> listadecurvas) {
		
		listadeciclistas = ciclistas;
		listadeobjetosamostarenvista = objetosamostarenvista;
		mapameteorologico = vientoporhoras;
		ordenes = ordenesparseador;
		reloj = relojdelsistema;
		listacurvas = listadecurvas;
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
			
			if ( ciclista == null) {
				ciclista = (c.getNumeromallot() == identificadorciclista) ? c : null;
			}
		}
		
		return ciclista;
	}
	
	public List<Ciclista> getCiclistas() {
		return listadeciclistas;
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
			
			if ( ciclista == null) {
				if (c.getIdentificadorSalidaDatos().equals(identificadorciclista)) {
					ciclista = c;
				}
			}
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
	 * @return  La lista de ordenes aceptada por el sistema.
	 * @uml.property  name="ordenes"
	 */
	public Orden[] getOrdenes() {
		
		return ordenes;
	}
	
	/**
	 * Provee acceso al reloj del sistema.
	 * @return  El reloj del sistema.
	 * @uml.property  name="reloj"
	 */
	public Reloj getReloj() {
		
		return reloj;
	}
	
	/**
	 * Provee acceso a la lista de curvas del sistema.
	 * 
	 * @return Las curvas del sistema.
	 */
	public List<Curva> getListaCurvas() {
		return listacurvas;
	}

	public void setListadeciclistas(List<Ciclista> listadeciclistas) {
		this.listadeciclistas = listadeciclistas;
	}

	public void setListadeobjetosamostarenvista(
			List<ObjetosConSalidaDeDatos> listadeobjetosamostarenvista) {
		this.listadeobjetosamostarenvista = listadeobjetosamostarenvista;
	}

	public void setMapameteorologico(Map<Integer, MiViento> mapameteorologico) {
		this.mapameteorologico = mapameteorologico;
	}

	/**
	 * @param ordenes
	 * @uml.property  name="ordenes"
	 */
	public void setOrdenes(Orden[] ordenes) {
		this.ordenes = ordenes;
	}

	/**
	 * @param reloj
	 * @uml.property  name="reloj"
	 */
	public void setReloj(Reloj reloj) {
		this.reloj = reloj;
	}

	public void setListacurvas(List<Curva> listadecurvas) {
		this.listacurvas = listadecurvas;
	}
}
