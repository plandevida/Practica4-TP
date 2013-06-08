package src.tests.utils;




public class TestUtilidadesBicicleta {

	/**
	 * Calcula la velocidad de la bicicleta en función de la cadencia del ciclista.
	 * 
	 * @param cadenciaciclista Frecuencia con la que el ciclista da pedaladas.
	 * @return La velocidad de la bicicleta.
	 */	
	public double velocidadDeBici(double velocidadanterior, double tiempopedalada, int peso, double radiorueda, int dientesPlato, int dientesPinhon) {
		
		// Variables no calculadas por que no hay factores externos
		int impulso = 1;
		int aceleracionpendiente = 0;
		int aceleracionviento = 0;
		
		double aceleracion = espacioDePedalada(radiorueda, dientesPlato, dientesPinhon) / (tiempopedalada*tiempopedalada);
		
		double aceleracionfactores = aceleracionpendiente + aceleracionviento;
		double velocidad = velocidadanterior + aceleracion*impulso; 
		
		if (velocidad > velocidadmaxima(tiempopedalada, radiorueda, dientesPlato, dientesPinhon)) {
			velocidad = velocidadmaxima(tiempopedalada, radiorueda, dientesPlato, dientesPinhon);
		}
		
		velocidad = (velocidad + aceleracionfactores*impulso);
		
		return UtilidadesNumericas.redondear(velocidad, 2);
	}
	
	private double velocidadmaxima(double tiempopedalada, double radiorueda, int dientesPlato, int dientesPinhon) {
		double velocidadmax = espacioDePedalada(radiorueda, dientesPlato, dientesPinhon) / tiempopedalada;
		return velocidadmax;
	}
	
	/**
	 * Calcula el espacio recorrido por cada pedalada que se da.
	 * 
	 * @return El valor del espacio recorrido.
	 */
	public double espacioDePedalada(double radiorueda, int dientesPlato, int dientesPinhon) {

		double espaciodepedalada = recorridoLinealDeLaRueda(radiorueda) * relacionDeTransmision(dientesPlato, dientesPinhon);

		return espaciodepedalada;
	}

	/**
	 * Relación entre el plato y piñón que se están usando actualmente.
	 * 
	 * @return Un entero que es relación entre ambos valores.
	 */
	public int relacionDeTransmision(int dientesPlato, int dientesPinhon) {

		int relaciondetrasminsion = dientesPlato / dientesPinhon;

		return relaciondetrasminsion;
	}
	
	/**
	 * Devuelve el la longitud de la rueda.
	 * 
	 * @return El valor de la longitud de la rueda.
	 */
	public double recorridoLinealDeLaRueda(double radiorueda) {

		double recorridolinealdelarueda = Math.PI * radiorueda;

		return recorridolinealdelarueda;

	}
	

}
