package sistema.factoresexternos;

import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.interfaces.ObjetosQueSeEjecutan;

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

	
	public Eolo(List<Bicicleta> listabicicletas) {
		
		bicicletas = listabicicletas;
	
	}
	
	
	
	/**
	 * Calcular el factor de la pendiente, gracias al angulo de la pendiente 
	 * 
	 * @return devuelve el factorpendiente
	 */
	
	
	/**
	 * Mira el viento que hay en el tramo actual y devuelve un factor
	 * 
	 * @return devuelve un porcentaje calculado a traves del viento y su direccion
	 */
	private double aceleracionViento(TramoCarrera tramo){
		
		
		int direccionviento = tramo.getViento().getFactor();
		
		double velocidadviento = tramo.getVelocidadViento();
		double aceleracionviento = Math.pow((velocidadviento / 0.837),2/3); 
		
		
		return (double) (aceleracionviento*direccionviento);
		
	}

	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 * 
	 */
	public void setVientoModificado(TramoCarrera tramoActual) {

		for(Bicicleta bici : bicicletas) {
			
			bici.setViento(aceleracionViento(tramoActual));
		}
	}


}
