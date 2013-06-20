package src.tests.utils;

public class TestUtilidadesCiclista {

	private double cadencia; 
	
	public TestUtilidadesCiclista (double cadenciaciclista) {
		
		cadencia = cadenciaciclista;
	}
	
	/**
	 * @param cadencianueva
	 */
	public void setCadencia(double cadencianueva) {
		
		cadencia = cadencianueva;
	}
	
	/**
	 * @return
	 */
	public double getCadencia() {
		
		return cadencia;
	}
}
