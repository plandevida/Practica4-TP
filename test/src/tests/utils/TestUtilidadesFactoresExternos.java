package src.tests.utils;

import java.util.Map;

import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;

public class TestUtilidadesFactoresExternos {
	
	private Map<Integer, TramoCarreraMock> carreteradecarreraciclista;
	private BicicletaMock bici;
	
	
	public TestUtilidadesFactoresExternos(Map<Integer, TramoCarreraMock> carreteradecarreraciclista, BicicletaMock bici){
		this.carreteradecarreraciclista = carreteradecarreraciclista;
		this.bici = bici;
	}
	
	
	/**
	 *  Busca el tramo en el que se encuentra la bici 
	 * @return devuelve el tramo
	 */
	public TramoCarreraMock tramoActual() {
		
		TramoCarreraMock tramo = new TramoCarreraMock(0, 0, null, 0);
		
		for(Integer reco : carreteradecarreraciclista.keySet()) {

			if ( carreteradecarreraciclista.get(reco).getKilometros() <= (int) bici.getEspacioRecorrido() ) {
				tramo = carreteradecarreraciclista.get(reco);
			}
		}
		return tramo;
	}
	
	/**
	 * Calcular el factor de la pendiente, gracias al angulo de la pendiente 
	 * 
	 * @return devuelve el factorpendiente
	 */
	public double pendienteTramoActual() {
		
		int angulograd = 0;
		double angulorad = 0d;
		double factorpendiente = 0d;
		
		TramoCarreraMock tramo = tramoActual();
		
		angulograd = tramo.getPendiente();
		angulorad = (angulograd * Math.PI)/180;
		

		factorpendiente = Math.cos(angulorad);
		
		if (angulograd < 0) {
			factorpendiente = factorpendiente + 1d;

		}

		
		return factorpendiente;
	}
	
	/**
	 * Mira el viento que hay en el tramo actual y devuelve un factor
	 * 
	 * @return devuelve un porcentaje calculado a traves del viento y su direccion
	 */
	public double vientoTramoActual(){
		
		TramoCarreraMock tramo = tramoActual();
		
		
		int direccionviento = tramo.getViento().getFactor();
		
		double velocidadviento = tramo.getVelocidadViento();
		
		return (double)(direccionviento * velocidadviento)/10;
		
	}
	
	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 * 
	 */
	public void setVelocidadModificada() {

		double velocidad = bici.getVelocidad() * pendienteTramoActual();
		
		velocidad = velocidad + velocidad * vientoTramoActual();
		
		bici.setVelocidadIncremento(velocidad);
	}
	/**
	 * Ejecuta los factores externos
	 * 
	 */
	public boolean ejecutar() {
		
		setVelocidadModificada();
		
		return true;
	}
}
