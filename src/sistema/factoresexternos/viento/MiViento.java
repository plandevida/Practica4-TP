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
	int factor;
	
	MiViento(int factorViento) {
		factor = factorViento;
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
}
