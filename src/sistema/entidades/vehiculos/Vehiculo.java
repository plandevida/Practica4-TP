package sistema.entidades.vehiculos;

/**
 * Clase que representa cualquier veículo del sistema
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Vehiculo {

	protected double velocidadactual;
	
	//Espacio que lleva recorrido
	protected double espaciorecorrido;
	
	/**
	 * Obtiene la velocidad a la que va el veículo.
	 * 
	 * @return El valor de la velocidad del veículo.
	 */
	public double getVelocidad() {
		return velocidadactual;
	}
	
	/**
	 * Incrementa la velocidad de la bicicleta.
	 * 
	 * @param incrementovelocidad Valor a aumentar la velocidad de la bicicleta.
	 */
	public void setVelocidadIncremento(double incrementovelocidad) {
		
		velocidadactual = incrementovelocidad;
	}
	
	/**
	 * Cambia la velocidad de la bicicleta.
	 * 
	 * @param velocidadnueva La nueva velocidad de la bicicleta.
	 */
	public void setVelocidad(double velocidadnueva) {
		velocidadactual = velocidadnueva;
	}
	
	/**
	 * Obtiene el espacio total recorrido por la bicicleta.
	 * 
	 * @return Espacio recorrido por la bicicleta.
	 */
	public double getEspacioRecorrido() {
		return espaciorecorrido;
	}
	
	/**
	 * Cambia el valor del espacio recorrido por la bicicleta.
	 * 
	 * @param espacioanhadido Espacio recorrido por la pedalada.
	 */
	public void setEspacioRecorrido(double espacioanhadido){
		
		espaciorecorrido += espacioanhadido;
	}
}
