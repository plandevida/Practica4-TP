package sistema.controladores.ordenes.especificas;

import java.util.Map;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.tiempo.Reloj;
import sistema.factoresexternos.viento.MiViento;
import sistema.manager.Presentador;

public class OrdenCambiaViento extends Orden {

	// El viento definido por horas.
	/**
	 * @uml.property  name="mapameteorologico"
	 */
	private Map<Integer, MiViento> mapameteorologico;
	
	// Hora en la que el viento cambiará
	/**
	 * @uml.property  name="hora"
	 * @uml.associationEnd  qualifier="this:sistema.controladores.ordenes.especificas.OrdenCambiaViento sistema.factoresexternos.viento.MiViento"
	 */
	private Integer hora;
	
	// El nuevo viento en la hora indicada
	/**
	 * @uml.property  name="viento"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private MiViento viento;
	
	// El reloj, para controlar si nos hemos pasado de la hora.
	/**
	 * @uml.property  name="reloj"
	 * @uml.associationEnd  
	 */
	private Reloj reloj;
	
	// La velocidad con la que el viento sopla.
	/**
	 * @uml.property  name="velocidadviento"
	 */
	private Double velocidadviento;
	
	public OrdenCambiaViento(MiViento nuevoviento, Integer enlahora, Double velocidad) {
		mapameteorologico = null;
		reloj = null;
		
		viento = nuevoviento;
		hora = enlahora;
		velocidadviento = velocidad;
	}
	
	@Override
	public String mostrarMensaje() {
		
		return (new StringBuilder()
				.append("viento: ")
				.append(viento.name())
				.append(" hora: ")
				.append(hora)
				.append(" velocidad: ")
				.append(velocidadviento)
				.append(" km/h"))
				.toString();
	}

	@Override
	public void ejecutarOrden() {
		if (mapameteorologico != null && viento != null && hora > 0) {
			
			if ( reloj.getHoras() < hora ) {
				
				mapameteorologico.put(hora, viento);
			}
		}
	}

	@Override
	public Orden parse(String comando) {

		OrdenCambiaViento ordencambiaviento = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if ( comprobarSintaxis(tokens) ) {
				
				if ( tokens.length == 4 ) {
					try {
						hora = Integer.valueOf(tokens[1]);
						
						if ( hora >= 0 && hora <23 ) {
						
							// Si el viento introducido no es correcto en tipo
							// no hace falta comprobarlo por que el enumerado
							// nos devuelve un viendo DESCONOCIDO y sin efecto
							viento = MiViento.existe(tokens[2]);
							
							velocidadviento = Double.valueOf(tokens[3]);
							
							if ( velocidadviento >= 0 ) {
							
								viento.setVelocidad(velocidadviento);
								
								ordencambiaviento = new OrdenCambiaViento(viento, hora, velocidadviento);
							}
						}
					} catch (NumberFormatException ne) {
					}
				}
			}
		}
		
		return ordencambiaviento;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		
		return ( tokenscomando[0].equalsIgnoreCase("viento") );
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		mapameteorologico = presentador.getMapametereológico();
		reloj = presentador.getReloj();
	}

	@Override
	public String help(boolean detalles) {
		
		return "viento <HORA> <TIPO> <KM/H>";
	}
}
