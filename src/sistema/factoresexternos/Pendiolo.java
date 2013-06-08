package sistema.factoresexternos;

import java.util.List;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.personas.ciclistas.Ciclista;


/**
 * Clase que se encarga de transmitir la aceleración
 * provocada por la pendiente que le afecta a los ciclitas.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Pendiolo {
	
	private List<Ciclista> ciclista;
	
	public Pendiolo(List<Ciclista> listaciclista) {
		
		ciclista = listaciclista;
	}

	private double pendienteTramoActual(TramoCarrera tramo) {
	
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
	public void setPendienteodificado(TramoCarrera tramoActual) {

		for(Ciclista cicli: ciclista) {
			cicli.getBicicletamontada().setPendiente(pendienteTramoActual(tramoActual)) ;
			
		}
	}
}
