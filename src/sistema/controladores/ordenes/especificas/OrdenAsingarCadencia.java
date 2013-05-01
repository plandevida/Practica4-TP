package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;

/**
 * Esta clase representa una orden de modificación de la cadencia
 * y el periodo de un ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class OrdenAsingarCadencia extends OrdenParaCiclista {
	private Integer aumentocadencia;
	private Integer periodo;
	private Integer idciclista;
	
	public OrdenAsingarCadencia(Integer nuevacadencia, Integer nuevoperiodo, Integer idciclista) {
		
		aumentocadencia = nuevacadencia;
		periodo = nuevoperiodo;
		this.idciclista = idciclista;
	}
	
	@Override
	public void ejecutarOrden() {
		
		if (getCiclista() != null) {
			getCiclista().setCadencia(aumentocadencia);
			getCiclista().setPeriodo(periodo);
		}
	}

	@Override
	public String mostrarMensaje() {
		
		return (new StringBuilder())
				.append("Cadencia ")
				.append("ciclista : ")
				.append(idciclista.intValue())
				.append(" cadencia : ")
				.append(aumentocadencia.intValue())
				.append(" y el periodo: ")
				.append(periodo.intValue())
				.toString();
	}

	@Override
	public Orden parse(String comando) {
		
		OrdenAsingarCadencia ordenaumentarcadencia = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 && tokens[2].equalsIgnoreCase("cadencia") ) {
			
			if ( tokens.length == 6 ) {
				try {
					idciclista = Integer.valueOf(tokens[1]);
					aumentocadencia = Integer.valueOf(tokens[3]);
					periodo = Integer.valueOf(tokens[5]);
					
				} catch (NumberFormatException ne) {
					// nada que hacer.
				}
				
				ordenaumentarcadencia = new OrdenAsingarCadencia(aumentocadencia, periodo, idciclista);
			}
		}
		
		return ordenaumentarcadencia;
	}
	
	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		
		boolean sintaxiscorrecta = false;
		
		if (tokenscomando[0].equalsIgnoreCase("ciclista") 
				&& tokenscomando[2].equalsIgnoreCase("cadencia") 
				&& tokenscomando[4].equalsIgnoreCase("periodo")) {
			
			sintaxiscorrecta = true;
		}
		
		return sintaxiscorrecta;
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		Ciclista ciclista = presentador.getciclista(this, idciclista);
		
		// Si la orden tiene permisos sobre los ciclistas
		// buscamos cual es el que le corresponde.
		if (ciclista != null) {
			
			setCiclista(ciclista);
		}
	}

	@Override
	public String help(boolean detalles) {
		
		return "ciclista <id_ciclista> cadencia <numero_cadencia> periodo <numero_periodo>";
	}
}
