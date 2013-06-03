package sistema.factoresexternos;

import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.interfaces.ObjetosQueSeEjecutan;

/**
 * Clase que calcula los datos de los elementos climatológicos
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class FactoresExternos implements ObjetosQueSeEjecutan {
	
	private List<Bicicleta> bicicletas;
	private Eolo eolo;
	private Curviolo curviolo;
	private Pendiolo pendiolo;
	
	//Mapa de la carretera
	private Map<Integer, TramoCarrera> carreteradecarreraciclista;
	
	public FactoresExternos(List<Bicicleta> bicis, Map<Integer, TramoCarrera> carreteradecarreraciclista, Eolo nuevoEolo, Curviolo nuevoCurviolo, Pendiolo nuevoPendiolo) {
		
		this.bicicletas = bicis;
		this.carreteradecarreraciclista = carreteradecarreraciclista;
		
		eolo = nuevoEolo != null ? nuevoEolo : new Eolo(bicicletas, Reloj.getInstance());
		curviolo = nuevoCurviolo != null ? nuevoCurviolo : new Curviolo();
		pendiolo = nuevoPendiolo != null ? nuevoPendiolo : new Pendiolo(bicicletas);
	}
	/**
	 *  Busca el tramo en el que se encuentra la bici 
	 * @return devuelve el tramo
	 */
	private TramoCarrera tramoActual() {
		
		TramoCarrera tramo = new TramoCarrera(0, 0, null, 0);
		
		for(Bicicleta bici : bicicletas) {
			for(Integer reco : carreteradecarreraciclista.keySet()) {
	
				if ( carreteradecarreraciclista.get(reco).getKilometros() <= (int) bici.getEspacioRecorrido() ) {
					tramo = carreteradecarreraciclista.get(reco);
				}
			}
		}
		
		return tramo;
	}
	@Override
	public void ejecuta() {
		eolo.setVientoModificado();
		pendiolo.setPendienteodificado(tramoActual());
		curviolo.ejecuta();
	}
}
