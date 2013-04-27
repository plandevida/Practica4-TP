package src.tests.sistema.entidadesmock;

import java.util.List;
import java.util.Map;

import sistema.interfaces.ObjetosQueSeEjecutan;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCiclistaMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;

public class FactoresExternosMock implements ObjetosQueSeEjecutan {
	
	private List<BicicletaMock> bicicletas;
	
	//Mapa de la carretera
	private Map<Integer, TramoCiclistaMock> carreteradecarreraciclista;
	
	public FactoresExternosMock(List<BicicletaMock> bicis, Map<Integer, TramoCiclistaMock> carreteradecarreraciclista) {
		
		this.bicicletas = bicis;
		this.carreteradecarreraciclista = carreteradecarreraciclista;
	}
	
	/**
	 *  Busca el tramo en el que se encuentra la bici 
	 * @return devuelve el tramo
	 */
	private TramoCiclistaMock tramoActual() {
		
		TramoCiclistaMock tramo = new TramoCiclistaMock(0, 0, null, 0);
		
		for(BicicletaMock bici : bicicletas) {
			for(Integer reco : carreteradecarreraciclista.keySet()) {
	
				if ( carreteradecarreraciclista.get(reco).getKilometros() <= (int) bici.getEspacioRecorrido() ) {
					tramo = carreteradecarreraciclista.get(reco);
				}
			}
		}
		
		return tramo;
	}
	
	/**
	 * Calcular el factor de la pendiente, gracias al angulo de la pendiente 
	 * 
	 * @return devuelve el factorpendiente
	 */
	private double pendienteTramoActual() {
		
		int angulograd = 0;
		double angulorad = 0d;
		double factorpendiente = 0d;
		
		TramoCiclistaMock tramo = tramoActual();
		
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
	private double vientoTramoActual(){
		
		TramoCiclistaMock tramo = tramoActual();
		
		
		int direccionviento = tramo.getViento().getFactor();
		
		double velocidadviento = tramo.getVelocidadViento();
		
		return (double)(direccionviento * velocidadviento)/10;
		
	}

	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 * 
	 */
	private void setVientoPendienteModificada() {

		for(BicicletaMock bici : bicicletas) {
			bici.setPendiente(pendienteTramoActual()) ;
			
			bici.setViento(vientoTramoActual());
		}
	}


	@Override
	public void ejecuta() {
		setVientoPendienteModificada();
	}
}
