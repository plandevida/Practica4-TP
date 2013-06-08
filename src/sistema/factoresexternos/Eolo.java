package sistema.factoresexternos;

import java.util.List;
import java.util.Map;

import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.factoresexternos.viento.MiViento;

/**
 * Clase que se encarga de transmitir la aceleración
 * generada por el viento que le afecta a los ciclitas.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Eolo  {
 
	private List<Ciclista> ciclistas ;
	private Reloj relojTiempo;
	private Map<Integer, MiViento> mapameteorologico;
	
	public Eolo(List<Ciclista> listaciclistas, Reloj reloj, Map<Integer, MiViento> mapaviento) {
		
		ciclistas = listaciclistas;
		relojTiempo = reloj;
		mapameteorologico = mapaviento;
	}
	
	/**
	 * Mira el viento que hay en el tramo actual y devuelve un factor
	 * 
	 * @return devuelve un porcentaje calculado a traves del viento y su direccion
	 */
	private double aceleracionViento() {
		
		MiViento viento = MiViento.NULO;
		
		if ( mapameteorologico.containsKey( relojTiempo.getHoras() ) ) {
			
			viento = mapameteorologico.get( relojTiempo.getHoras() );
		}
		
		return viento.getAceleracion();
	}

	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 */
	public void setVientoModificado() {

		for(Ciclista cicli : ciclistas) {
			
			cicli.getBicicletamontada().setViento(aceleracionViento());
		}
	}
	
	/**
	 * Obtiene el reloj que usa Eolo.
	 * 
	 * @return el reloj
	 */
	public Reloj getReloj() {
		return relojTiempo;
	}
}
