package sistema.factoresexternos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.factoresexternos.viento.MiViento;

/**
 * Clase que se encarga de transmitir la aceleración
 * generada por el viento que le afecta a los ciclitas
 * y se lo transmite a estos.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Eolo  {
 
	private List<Bicicleta> bicicletas;
	private Reloj relojTiempo;
	private HashMap<Integer, MiViento> mapameteorologico;
	
	public Eolo(List<Bicicleta> listabicicletas, Reloj reloj, HashMap<Integer, MiViento> mapaviento) {
		
		bicicletas = listabicicletas;
		relojTiempo = reloj;
		mapameteorologico = mapaviento;
	}
	
	/**
	 * Mira el viento que hay en el tramo actual y devuelve un factor
	 * 
	 * @return devuelve un porcentaje calculado a traves del viento y su direccion
	 */
	private double aceleracionViento() {
		
		int direccionviento = MiViento.NULO.getFactor();
		double aceleracionviento = 0;
		
		if ( mapameteorologico.containsKey( relojTiempo.getHoras() ) ) {
			
			direccionviento = mapameteorologico.get( relojTiempo.getHoras() ).getFactor();
			
			double velocidadviento = tramo.getVelocidadViento();
			aceleracionviento = Math.pow((velocidadviento / 0.837),2/3);
		}
		
		return (double) (aceleracionviento*direccionviento);
	}

	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 */
	public void setVientoModificado() {

		for(Bicicleta bici : bicicletas) {
			
			bici.setViento(aceleracionViento());
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
