package sistema.entidades.vehiculos;

import sistema.manager.VariablesDeContexto;

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
		return redondear(velocidadactual/VariablesDeContexto.AUMENTO_VELOCIDAD,2);
	}
	
	/**
	 * Cambia la velocidad de la bicicleta.
	 * 
	 * @param velocidadnueva La nueva velocidad de la bicicleta.
	 */
	public void setVelocidad(double velocidadnueva) {
		velocidadactual = redondear(velocidadnueva*VariablesDeContexto.AUMENTO_VELOCIDAD,2)+0.00d;
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
		espaciorecorrido = redondear (espaciorecorrido,2);
	}
	
	public double redondear( double numero, int decimales ) {
	    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	}
}
