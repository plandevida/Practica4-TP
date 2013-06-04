package sistema.factoresexternos.viento;

/**
 * Enumerado que representa las direcciones del viento
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public enum MiViento {
	AFAVOR(1),
	ENCONTRA(-1),
	LATERAL(0),
	NULO(0),
	DESCONOCIDO(0);
	
	/**
	 * factor que se usa para determinar la dirección de la velocidad del viento
	 */
	private int factor;
	private double velocidad;
	
	MiViento(int factorViento) {
		factor = factorViento;
		velocidad = 0;
	}
	
	public static MiViento existe(String viento) {
		MiViento wind = DESCONOCIDO;
		
		if (viento != null && !viento.equals("")) {
			
			for (MiViento c : MiViento.values()) {
				
				if ( c.name().equalsIgnoreCase(viento) ) 
					wind = c;
			}
		}
	
		return wind;
	}
	
	/**
	 * Obtiene el factor del viento por el cual hay que multiplicar la velocidad
	 * 
	 * @return El factor del viento.
	 */
	public int getFactor() {
		return factor;
	}
	
	/**
	 * Asigna una velocidad al viento.
	 * @param velocidaddelviento La nueva velocidad del viento.
	 */
	public void setVelocidad(double velocidaddelviento) {
		velocidad = velocidaddelviento;
	}
	
	/**
	 * Obtiene la velocidad del viento.
	 * @return La velocidad.
	 */
	public double getVelocidad() {
		return velocidad;
	}
	
	/**
	 * Obtiene la aceleración del viento
	 * @return La aceleración
	 */
	public double getAceleracion() {
		
		// Se calcula la aceleración del viento.
		double aceleracionviento = Math.pow((getVelocidad() / 0.837),2/3);
		
		// Se multiplica por el factor de la direccíon.
		return aceleracionviento * getFactor();
	}
}
