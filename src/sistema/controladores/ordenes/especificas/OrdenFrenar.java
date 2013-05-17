package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenFrenar extends OrdenParaCiclista {
	
	private Integer idciclista;
	private Double cantidadfrendada, tiempofrenada;
	
	public OrdenFrenar(Double cantidad, Double tiempo, Integer idciclista) {
		
		this.idciclista = idciclista;
		cantidadfrendada = cantidad;
		tiempofrenada = tiempo;
	}

	@Override
	public void ejecutarOrden() {
		if (getCiclista() != null) {
			getCiclista().frenar();
		}
	}
	
	@Override
	public String mostrarMensaje() {
		
		return (new StringBuilder()
				.append("Plato ")
				.append("ciclista : ")
				.append(idciclista)
				.append(" cantidad: ")
				.append(cantidadfrendada)
				.append(" tiempo: ")
				.append(tiempofrenada)
				).toString();
	}

	@Override
	public Orden parse(String comando) {
		
		OrdenFrenar ordenfrenar = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 && tokens[2].equalsIgnoreCase("frena") ) {
			
			if (comprobarSintaxis(tokens)) {
				
				if ( tokens.length == 6 ) {
					try {
						
						idciclista = Integer.valueOf(tokens[1]);
						
						if (idciclista >= 0 && idciclista < VariablesDeContexto.MAX_CICLISTAS) {
							cantidadfrendada = Double.valueOf(tokens[3]);
							
							if (cantidadfrendada >= 0) {
								tiempofrenada = Double.valueOf(tokens[5]);
								
								if (tiempofrenada >= 0) {
									ordenfrenar = new OrdenFrenar(cantidadfrendada, tiempofrenada, idciclista);
								}
							}
						}
					} catch (NumberFormatException ne) {
						// nada que hacer.
					}
				}
			}
		}
		
		return ordenfrenar;
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		Ciclista ciclista = presentador.getCiclista(idciclista);
		
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
		
		if (tokenscomando[0].equalsIgnoreCase("ciclista") 
				&& tokenscomando[2].equalsIgnoreCase("frena") 
				&& tokenscomando[4].equalsIgnoreCase("en")) {
			
			sintaxiscorrecta = true;
		}
		
		return sintaxiscorrecta;
	}
}
