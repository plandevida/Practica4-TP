package sistema.factoresexternos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosQueSeEjecutan;

/**
 * Clase que calcula los datos de los elementos climatológicos
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class FactoresExternos implements ObjetosQueSeEjecutan {
	
	private List<Ciclista> ciclistas;
	private List<Curva> curvas;
	private Eolo eolo;
	private Curviolo curviolo;
	private Pendiolo pendiolo;
	
	//Mapa de la carretera
	private Map<Integer, TramoCarrera> carreteradecarreraciclista;
	
	public FactoresExternos(List<Ciclista> ciclistas, Map<Integer, TramoCarrera> carreteradecarreraciclista,List<Curva> curva, Eolo nuevoEolo, Curviolo nuevoCurviolo, Pendiolo nuevoPendiolo) {
		
		this.ciclistas = ciclistas;
		this.carreteradecarreraciclista = carreteradecarreraciclista;
		this.curvas = curva;
		eolo = nuevoEolo != null ? nuevoEolo : new Eolo(this.ciclistas, Reloj.getInstance(), new HashMap<Integer, MiViento>());
		curviolo = nuevoCurviolo != null ? nuevoCurviolo : new Curviolo(this.ciclistas,curvas);
		pendiolo = nuevoPendiolo != null ? nuevoPendiolo : new Pendiolo(this.ciclistas,this.carreteradecarreraciclista);
	}
	
		
	
	@Override
	public void ejecuta() {
		eolo.setVientoModificado();
		pendiolo.setPendienteodificado();
		curviolo.comporvarcurvasas();
	}
}
