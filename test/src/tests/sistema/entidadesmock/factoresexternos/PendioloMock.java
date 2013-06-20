package src.tests.sistema.entidadesmock.factoresexternos;

import java.util.List;

import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;

public class PendioloMock {
	
	/**
	 * @uml.property  name="bicicletas"
	 */
	private List<BicicletaMock> bicicletas;
	public PendioloMock(List<BicicletaMock> listabicicletas) {
		
		bicicletas = listabicicletas;
	}

	private double pendienteTramoActual(TramoCarreraMock tramo) {
	
		int angulograd = 0;
		double angulorad = 0d;
		double factorpendiente = 0d;
		
		angulograd = tramo.getPendiente();
		angulorad = (angulograd * Math.PI)/180;
		
	
		factorpendiente = Math.cos(angulorad);
		
		if (angulograd < 0) {
			factorpendiente = factorpendiente + 1d;
		}
		
		return factorpendiente;
	}
	
	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 * 
	 */
	public void setPendienteodificado(TramoCarreraMock tramoActual) {

		for(BicicletaMock bici : bicicletas) {
			bici.setPendiente(pendienteTramoActual(tramoActual)) ;
			
		}
	}

}
