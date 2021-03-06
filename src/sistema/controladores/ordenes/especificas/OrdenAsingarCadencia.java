package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

/**
 * Esta clase representa una orden de modificación de la cadencia
 * y el periodo de un ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class OrdenAsingarCadencia extends OrdenParaCiclista {
	
	private Integer aumentocadencia;
	private Double periodotiempopedalada;
	private Integer idciclista;
	
	/**
	 * Construye una orden que cambia la cadencia a un ciclista.
	 * 
	 * @param nuevacadencia La nueva cadencia.
	 * @param nuevoperiodo El nuevo periodo de tiempo de pedalada
	 * @param idciclista El identificador del ciclista al que cambiarle la cadencia.
	 */
	public OrdenAsingarCadencia(Integer nuevacadencia, Double nuevoperiodo, Integer idciclista) {
		
		aumentocadencia = nuevacadencia;
		periodotiempopedalada = nuevoperiodo;
		this.idciclista = idciclista;
	}
	
	@Override
	public void ejecutarOrden() {
		
		if (getCiclista() != null) {
			if (periodotiempopedalada <= getCiclista().getPeriodo()) {
				getCiclista().setCadencia(aumentocadencia);
				getCiclista().setTiempopedalada(periodotiempopedalada);
			}
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
				.append(periodotiempopedalada.doubleValue())
				.toString();
	}

	@Override
	public Orden parse(String comando) {
		
		OrdenAsingarCadencia ordenaumentarcadencia = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if (comprobarSintaxis(tokens)) {
				
				if ( tokens.length == 6 ) {
					try {
						idciclista = Integer.valueOf(tokens[1]);
						
						if (idciclista >= 0 && idciclista < VariablesDeContexto.MAX_CICLISTAS) {
						
							aumentocadencia = Integer.valueOf(tokens[3]);
							
							if ( aumentocadencia >= 0 && aumentocadencia <= VariablesDeContexto.MAX_CADENCIA ) {
								
								periodotiempopedalada = Double.valueOf(tokens[5]);
								
								if ( periodotiempopedalada >= 0) {
								
									ordenaumentarcadencia = new OrdenAsingarCadencia(aumentocadencia, periodotiempopedalada, idciclista);
								}
							}
						}
					} catch (NumberFormatException ne) {
					}
				}
			}
		}
		
		return ordenaumentarcadencia;
	}
	
	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		
		return (tokenscomando[0].equalsIgnoreCase("ciclista") 
				&& tokenscomando[2].equalsIgnoreCase("cadencia") 
				&& tokenscomando[4].equalsIgnoreCase("periodo"));
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		Ciclista ciclista = presentador.getCiclista(idciclista);
		
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
