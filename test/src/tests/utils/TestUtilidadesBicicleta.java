package src.tests.utils;




public class TestUtilidadesBicicleta {

	/**
	 * Calcula la velocidad de la bicicleta en función de la cadencia del ciclista.
	 * 
	 * @param cadenciaciclista Frecuencia con la que el ciclista da pedaladas.
	 * @return La velocidad de la bicicleta.
	 */
	public double velocidadDeBici(double cadenciaciclista, double radiorueda, int dientesPlato, int dientesPinhon, double espaciorecorrido) {

		double velocidadbici = espacioDePedalada(radiorueda, dientesPlato, dientesPinhon) / cadenciaciclista;

		return velocidadbici;
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
