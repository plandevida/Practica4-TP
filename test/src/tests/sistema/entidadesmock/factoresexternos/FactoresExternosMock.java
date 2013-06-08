package src.tests.sistema.entidadesmock.factoresexternos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosQueSeEjecutan;
import src.tests.sistema.entidadesmock.carretera.tramocarreraciclista.TramoCarreraMock;
import src.tests.sistema.entidadesmock.tiempo.RelojMock;
import src.tests.sistema.entidadesmock.vehiculos.bicicletas.BicicletaMock;

public class FactoresExternosMock implements ObjetosQueSeEjecutan {
	
	private List<BicicletaMock> bicicletas;
	private EoloMock eolo;
	private CurvioloMock curviolo;
	private PendioloMock pendiolo;
	
	//Mapa de la carretera
	private Map<Integer, TramoCarreraMock> carreteradecarreraciclista;
	
	public FactoresExternosMock(List<BicicletaMock> bicis, Map<Integer, TramoCarreraMock> carreteradecarreraciclista, EoloMock nuevoEolo, CurvioloMock nuevoCurviolo, PendioloMock nuevoPendiolo) {
		
		this.bicicletas = bicis;
		this.carreteradecarreraciclista = carreteradecarreraciclista;
		
		eolo = nuevoEolo != null ? nuevoEolo : new EoloMock(bicicletas, RelojMock.getInstance(), new HashMap<Integer, MiViento>());
		curviolo = nuevoCurviolo != null ? nuevoCurviolo : new CurvioloMock();
		pendiolo = nuevoPendiolo != null ? nuevoPendiolo : new PendioloMock(bicicletas);
	}
	/**
	 *  Busca el tramo en el que se encuentra la bici 
	 * @return devuelve el tramo
	 */
	private TramoCarreraMock tramoActual() {
		
		TramoCarreraMock tramo = new TramoCarreraMock(0, 0);
		
		for(BicicletaMock bici : bicicletas) {
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
