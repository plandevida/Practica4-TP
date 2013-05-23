package sistema.factoresexternos;

import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
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
	
	//Mapa de la carretera
	private Map<Integer, TramoCarrera> carreteradecarreraciclista;
	
	public FactoresExternos(List<Bicicleta> bicis, Map<Integer, TramoCarrera> carreteradecarreraciclista, Eolo nuevoEolo, Curviolo nuevoCurviolo) {
		
		this.bicicletas = bicis;
		this.carreteradecarreraciclista = carreteradecarreraciclista;
		
		eolo = nuevoEolo != null ? nuevoEolo : new Eolo(bicicletas, this.carreteradecarreraciclista);
		curviolo = nuevoCurviolo != null ? nuevoCurviolo : new Curviolo();
	}

	@Override
	public void ejecuta() {
		eolo.ejecuta();
		curviolo.ejecuta();
	}
}
