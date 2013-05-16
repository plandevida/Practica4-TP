package sistema.factoresexternos;

import java.util.List;

import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.interfaces.ObjetosQueSeEjecutan;

/**
 * Clase que se encarga de notificar a los ciclistas las
 * curvas del circuito.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Curviolo implements ObjetosQueSeEjecutan {

	List<Ciclista> ciclistas;
	List<Curva> curvas;
	
	@Override
	public void ejecuta() {
		
		if (ciclistas != null) {
			
			for (Ciclista ciclista : ciclistas) {
				
				for (Curva curva : curvas) {
				
					double espaciorecorridoporciclista = ciclista.getBicicletamontada().getEspacioRecorrido();
					
					// TODO calcular la distancia necesaria para frenar hasta la velocidad de la curva
					//																		^
					if (espaciorecorridoporciclista >= curva.getPuntokilometrico() - Constantes.DISTANCIA_FRENADO && espaciorecorridoporciclista < curva.getPuntokilometrico()) {
					
						ciclista.setEstrellado(true);
					}
				}
			}
		}
	}
}
