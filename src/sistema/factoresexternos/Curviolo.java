package sistema.factoresexternos;

import java.util.List;

import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;

/**
 * Clase que se encarga de notificar a los ciclistas las
 * curvas del circuito.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Curviolo {

	List<Ciclista> ciclistas;
	List<Curva> curvas;
	
	public Curviolo(List<Ciclista> listaciclistas,List<Curva> curvas){
		
		this.ciclistas = listaciclistas;
		this.curvas = curvas;
	}
	
	public void comporvarcurvasas() {
		
		if (ciclistas != null) {
			
			for (Ciclista ciclista : ciclistas) {
				
				for (Curva curva : curvas) {
					
					double espaciorecorridoporciclista = ciclista.getBicicletamontada().getEspacioRecorrido();
				
					// TODO calcular la distancia necesaria para frenar hasta la velocidad de la curva
					// comprueba que el ciclista este 															     ^
					if (espaciorecorridoporciclista >= curva.getPuntokilometrico()) {
					
						if (curva.getCiclistarhanpasadocurva(ciclista.getNumeromallot()) !=ciclista.getNumeromallot()){
							
							if (ciclista.getBicicletamontada().getVelocidad() > curva.getVelocidadmaximacurva()) ciclista.setEstrellado(true);
						
						
							else curva.setCiclistashanpasadocurva(ciclista.getNumeromallot(),ciclista.getNumeromallot());
							
							
						}
						
					}
				}
			}
		}
	}
}
