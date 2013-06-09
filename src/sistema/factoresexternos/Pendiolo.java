package sistema.factoresexternos;

import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.VariablesDeContexto;


/**
 * Clase que se encarga de transmitir la aceleración
 * provocada por la pendiente que le afecta a los ciclitas.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Pendiolo {
	
	private List<Ciclista> ciclista;

		

	private Map<Integer, TramoCarrera> carreteradecarreraciclista;
	
	public Pendiolo(List<Ciclista> listaciclista,Map<Integer, TramoCarrera> carreteradecarreraciclista) {
	
		this.carreteradecarreraciclista = carreteradecarreraciclista;

		ciclista = listaciclista;
	}
	/**
	 *  Busca el tramo en el que se encuentra la bici 
	 * @return devuelve el tramo
	 */
	private TramoCarrera tramoActual(Ciclista ciclista) {
		
		TramoCarrera tramo = new TramoCarrera(0, 0);

		
			for(Integer reco : carreteradecarreraciclista.keySet()) {
	
				if ( reco <= (int) ciclista.getBicicletamontada().getEspacioRecorrido() ) {
					tramo = carreteradecarreraciclista.get(reco);
					
				
			}
			
		}
			return tramo;
		
		
	}

	private double pendienteTramoActual(TramoCarrera tramo) {
		
		double aceleracionpendiente = 0d;
		

			aceleracionpendiente = VariablesDeContexto.FUERZA_GRAVEDAD* (tramo.getPendiente()/tramo.getKilometros()); 
		
			return (aceleracionpendiente/10)*-1;
		
		 
				
//		int angulograd = 0;
//		double angulorad = 0d;
//		double factorpendiente = 0d;
//		
//		angulograd = tramo.getPendiente();
//		angulorad = (angulograd * Math.PI)/180;
//		
//	
//		factorpendiente = Math.cos(angulorad);
//		
//		if (angulograd < 0) {
//			factorpendiente = factorpendiente + 1d;
//		}
//		
		
	}
	
	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 * 
	 */
	public void setPendienteodificado() {

		for(Ciclista cicli: ciclista) {
			
			cicli.getBicicletamontada().setPendiente(pendienteTramoActual(tramoActual(cicli))) ;
			
		}
	}
}
