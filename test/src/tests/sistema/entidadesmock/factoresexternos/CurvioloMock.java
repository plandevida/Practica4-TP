package src.tests.sistema.entidadesmock.factoresexternos;

import java.util.List;

import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.interfaces.ObjetosQueSeEjecutan;
import src.tests.sistema.entidadesmock.personas.ciclistas.CiclistaMock;

public class CurvioloMock implements ObjetosQueSeEjecutan {

	/**
	 * @uml.property  name="ciclistas"
	 */
	List<CiclistaMock> ciclistas;
	/**
	 * @uml.property  name="curvas"
	 */
	List<Curva> curvas;
	
	@Override
	public void ejecuta() {
		
		if (ciclistas != null) {
			
			for (CiclistaMock ciclista : ciclistas) {
				
				for (Curva curva : curvas) {
				
					double espaciorecorridoporciclista = ciclista.getBicicletamontada().getEspacioRecorrido();
					
					// TODO calcular la distancia necesaria para frenar hasta la velocidad de la curva
					//															     ^
					if (espaciorecorridoporciclista >= curva.getPuntokilometrico() - 5 && espaciorecorridoporciclista < curva.getPuntokilometrico()) {
					
						ciclista.setEstrellado(true);
					}
				}
			}
		}
	}
}
