package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenCambiarPlato extends OrdenParaCiclista {
	
	/**
	 * @uml.property  name="idciclista"
	 */
	private Integer idciclista;
	/**
	 * @uml.property  name="indiceplato"
	 */
	private Integer indiceplato;
	
	public OrdenCambiarPlato(Integer plato, Integer idciclista) {
		
		this.idciclista = idciclista;
		indiceplato = plato;
	}

	@Override
	public void ejecutarOrden() {
		if (getCiclista() != null) {
			getCiclista().cambiarPlato(indiceplato);
		}
	}
	
	@Override
	public String mostrarMensaje() {
		
		return (new StringBuilder()
				.append("Plato ")
				.append("ciclista : ")
				.append(idciclista)
				.append(" plato: ")
				.append(indiceplato)
				).toString();
	}

	@Override
	public Orden parse(String comando) {
		
		OrdenCambiarPlato ordensubirplato = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if (comprobarSintaxis(tokens)) {
				if ( tokens.length == 5 ) {
					try {
						idciclista = Integer.valueOf(tokens[1]);
						
						if (idciclista >= 0 && idciclista < VariablesDeContexto.MAX_CICLISTAS) {
							
							indiceplato = Integer.valueOf(tokens[4]);
							
							if (indiceplato >= 0 && indiceplato < VariablesDeContexto.PLATOS.length) {
							
								ordensubirplato = new OrdenCambiarPlato (indiceplato, idciclista);
							}
						}
					} catch (NumberFormatException ne) {
					}
				}
			}
		}
		
		return ordensubirplato;
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		Ciclista ciclista = presentador.getCiclista(idciclista);
		
		// Buscamos cual es el ciclista que le corresponde.
		if( ciclista != null ) {
			
			setCiclista(ciclista);
		}
	}

	@Override
	public String help(boolean detalles) {
		
		return "bicicleta <numero_bicicleta> cambia plato <numero_plato>";
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		
		boolean sintaxiscorrecta = false;
		
		if (tokenscomando[0].equalsIgnoreCase("bicicleta") 
				&& tokenscomando[2].equalsIgnoreCase("cambia") 
				&& tokenscomando[3].equalsIgnoreCase("plato")) {
			
			sintaxiscorrecta = true;
		}
		
		return sintaxiscorrecta;
	}
}
