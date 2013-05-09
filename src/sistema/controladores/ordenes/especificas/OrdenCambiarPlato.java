package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenCambiarPlato extends OrdenParaCiclista {
	
	private Integer idciclista;
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
							
							// No es necesario controlar el indice del plato, ya lo hace el ciclista.
							indiceplato = Integer.valueOf(tokens[4]);
							
							ordensubirplato = new OrdenCambiarPlato (indiceplato, idciclista);
						}
					} catch (NumberFormatException ne) {
						// nada que hacer.
					}
				}
			}
		}
		
		return ordensubirplato;
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		Ciclista ciclista = presentador.getciclista(this, idciclista);
		
		// Si la orden tiene permisos sobre los ciclistas
		// buscamos cual es el que le corresponde.
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
				&& tokenscomando[4].equalsIgnoreCase("plato")) {
			
			sintaxiscorrecta = true;
		}
		
		return sintaxiscorrecta;
	}
}
