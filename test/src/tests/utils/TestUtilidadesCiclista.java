package src.tests.utils;

public class TestUtilidadesCiclista {

	private double cadencia; 
	
	
	public TestUtilidadesCiclista (double cadenciaciclista){
		
		cadencia = cadenciaciclista;
	}
	
	public void setCadencia(double cadencianueva){
		
		cadencia = cadencianueva;
	}
	
	public double getCadencia(){
		
		return cadencia;
	}
}
