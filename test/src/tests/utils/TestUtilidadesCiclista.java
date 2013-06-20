package src.tests.utils;

public class TestUtilidadesCiclista {

	/**
	 * @uml.property  name="cadencia"
	 */
	private double cadencia; 
	
	
	public TestUtilidadesCiclista (double cadenciaciclista) {
		
		cadencia = cadenciaciclista;
	}
	
	/**
	 * @param cadencianueva
	 * @uml.property  name="cadencia"
	 */
	public void setCadencia(double cadencianueva) {
		
		cadencia = cadencianueva;
	}
	
	/**
	 * @return
	 * @uml.property  name="cadencia"
	 */
	public double getCadencia() {
		
		return cadencia;
	}
}
