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
	private TramoCarrera tramosig;		
	private TramoCarrera tramo;
	private Map<Integer, TramoCarrera> carreteradecarreraciclista;
	
	public Pendiolo(List<Ciclista> listaciclista,Map<Integer, TramoCarrera> carreteradecarreraciclista) {
	
		this.carreteradecarreraciclista = carreteradecarreraciclista;
		ciclista = listaciclista;
	}
	/**
	 *  Busca el tramo en el que se encuentra la bici 
	 * @return devuelve el tramo
	 */
	private void tramoActual(Ciclista ciclista) {
		
		TramoCarrera tramo = new TramoCarrera(0, 0);
		TramoCarrera tramosig = new TramoCarrera(0, 0);
		
			for(Integer reco : carreteradecarreraciclista.keySet()) {
	
				if ( carreteradecarreraciclista.get(reco).getKilometros() <= (int) ciclista.getBicicletamontada().getEspacioRecorrido() ) {
					tramo = carreteradecarreraciclista.get(reco);
					if (carreteradecarreraciclista.get(reco+1)!=null) tramosig = carreteradecarreraciclista.get(reco+1);
				
			}
			
		}
			this.tramo = tramo;
			this.tramosig = tramosig;
		
	}

	private double pendienteTramoActual() {
		double recorrido = 0d;
		double aceleracionpendiente = 0d;
		
		if (tramosig.getKilometros()!=0){
			
			recorrido = tramosig.getKilometros() - tramo.getKilometros();
			
			aceleracionpendiente = VariablesDeContexto.FUERZA_GRAVEDAD* (tramo.getPendiente()/recorrido); 
		}
		
		
		 
				
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
		return (aceleracionpendiente/10)*-1;
	}
	
	/**
	 * Modifica la velocidad de la bicicleta dependiendo de los factores externos de la carretera
	 * 
	 */
	public void setPendienteodificado() {

		for(Ciclista cicli: ciclista) {
			tramoActual(cicli);
			cicli.getBicicletamontada().setPendiente(pendienteTramoActual()) ;
			
		}
	}
}
