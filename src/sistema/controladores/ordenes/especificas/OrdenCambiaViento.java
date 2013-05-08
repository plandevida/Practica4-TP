package sistema.controladores.ordenes.especificas;

import java.util.HashMap;
import java.util.Map;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.tiempo.Reloj;
import sistema.factoresexternos.viento.MiViento;
import sistema.manager.Presentador;

public class OrdenCambiaViento extends Orden {

	// El viento definido por horas.
	private Map<Integer, Map<MiViento, Double>> mapameteorologico;
	
	// Hora en la que el viento cambiará
	private int hora;
	
	// El nuevo viento en la hora indicada
	private MiViento viento;
	
	// El reloj, para controlar si nos hemos pasado de la hora.
	private Reloj reloj;
	
	// La velocidad con la que el viento sopla.
	private double velocidadviento;
	
	public OrdenCambiaViento(MiViento nuevoviento, int enlahora, double nuevavelocidad) {
		mapameteorologico = null;
		reloj = null;
		
		viento = nuevoviento;
		hora = enlahora;
		velocidadviento = nuevavelocidad;
	}
	
	@Override
	public String mostrarMensaje() {
		
		return (new StringBuilder()
					.append("viento: "))
					.append(viento.name())
					.append(" hora: ")
					.append(hora)
					.append(" velocidad: ")
					.append(velocidadviento)
					.toString();
	}

	@Override
	public void ejecutarOrden() {
		if (mapameteorologico != null && viento != null && hora > 0 && reloj != null) {
			
			if ( reloj.getHoras() < hora ) {
				
				Map<MiViento, Double> vientovelocidad = new HashMap<MiViento, Double>();
				
				mapameteorologico.put(hora, vientovelocidad);
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
						
							viento = MiViento.existe(tokens[2]);
							
							velocidadviento = Integer.valueOf(tokens[3]);
							
							ordencambiaviento = new OrdenCambiaViento(viento, hora, velocidadviento);
						}
					} catch (NumberFormatException ne) {
						// nada que hacer.
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
		
		mapameteorologico = presentador.getMapametereológico(this);
	}

	@Override
	public String help(boolean detalles) {
		
		return "viento <HORA> <TIPO> <KM/H>";
	}

	@Override
	public String[] misPermisos() {
		
		return new String[]{ Presentador.permisos[2], Presentador.permisos[3] };
	}
}
