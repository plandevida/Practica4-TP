package sistema.controladores.ordenes.especificas;

import java.util.List;

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
public class OrdenAumentarCadencia extends OrdenParaCiclista {
	
	private int aumentocadencia;
	private int periodo;
	private int idciclista;
	
	@Override
	public void ejecutarOrden() {
		
		if (getCiclista() != null) {
			getCiclista().setCadencia(aumentocadencia);
			mostrarMensaje();
		}
	}
	
	/**
	 * Pone la cadencia del ciclista.
	 * @param aumentodecadencia El aumento de la cadencia.
	 */
	private void setAumentoCadencia(int aumentodecadencia) {
		aumentocadencia = aumentodecadencia;
	}

	/**
	 * Pone el periodo del ciclista
	 * 
	 * @param periodo
	 */
	public void setPeriodo(int periodo) {
		this.periodo = periodo;
	}

	@Override
	public String mostrarMensaje() {
		
		return (new StringBuilder())
				.append(this.getClass().getName())
				.append(": Aumentando la cadencia en : ")
				.append(aumentocadencia)
				.append(" y el periodo: ")
				.append(periodo)
				.toString();
	}

	@Override
	public Orden parse(String comando) {
		
		OrdenAumentarCadencia ordenaumentarcadencia = null;
		
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
				
				ordenaumentarcadencia = new OrdenAumentarCadencia();
				ordenaumentarcadencia.setAumentoCadencia(aumentocadencia);
				ordenaumentarcadencia.setPeriodo(periodo);
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
		
		List<Ciclista> ciclistas = presentador.getListadeciclistas(this);
		
		// Si la orden tiene permisos sobre los ciclistas
		// buscamos cual es el que le corresponde.
		if (ciclistas != null) {
			
			for ( Ciclista ciclista : ciclistas) {
				if ( ciclista.getNumeromallot() == idciclista ) {
					
					setCiclista(ciclista);
				}
			}
		}
	}

	@Override
	public String help(boolean detalles) {
		
		return "ciclista n cadencia n1 periodo n2";
	}
}
