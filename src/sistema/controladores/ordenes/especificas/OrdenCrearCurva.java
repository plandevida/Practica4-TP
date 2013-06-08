package sistema.controladores.ordenes.especificas;

import java.util.List;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenCrearCurva extends Orden {
	
	private Double puntoKM;
	private Double velocidaddepasomax;
	private List<Curva> listadecurvas;
	private List<Ciclista> listadeciclistas;

	public OrdenCrearCurva(Double puntokilometroco, Double velocidadmaximadecurva) {
		
		puntoKM = puntokilometroco;
		velocidaddepasomax = velocidadmaximadecurva;
	}
	
	@Override
	public String mostrarMensaje() {
		return (new StringBuilder()
				.append("Curva creada en ")
				.append(puntoKM)
				.append(" km ")
				.append("con velocidad ")
				.append(velocidaddepasomax)
				.append(" m/s")).toString();
	}

	@Override
	public void ejecutarOrden() {
		
		if (listadecurvas != null) {
			
			if (listadeciclistas != null) {
				
				Curva curva = new Curva(puntoKM, velocidaddepasomax, listadeciclistas.size());

				if (listadeciclistas != null) {
					for (Ciclista c : listadeciclistas) {
						
						if (c.getBicicletamontada().getEspacioRecorrido() > puntoKM) {
							curva.setCiclistashanpasadocurva(c.getNumeromallot(), c.getNumeromallot());
						}
					}
				}
				
				listadecurvas.add(curva);
			}
		}
	}

	@Override
	public Orden parse(String comando) {
		
		Orden ordencrearcurva = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if (comprobarSintaxis(tokens)) {
				
				if ( tokens.length == 3 ) {
					try {
						puntoKM = Double.valueOf(tokens[1]);
						
						if (puntoKM > 0 && puntoKM < VariablesDeContexto.LONGITUD_CARRERA) {
						
							velocidaddepasomax = Double.valueOf(tokens[2]);
							
							if ( velocidaddepasomax >= 0 ) {
								
								ordencrearcurva = new OrdenCrearCurva(puntoKM, velocidaddepasomax);
							}
						}
					} catch (NumberFormatException ne) {
						
					}
				}
			}
		}
		
		return ordencrearcurva;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		
		return (tokenscomando[0].equalsIgnoreCase("curva"));
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		listadecurvas = presentador.getListaCurvas();
		listadeciclistas = presentador.getCiclistas();
	}

	@Override
	public String help(boolean detalles) {
		
		return "curva <punto_kilometrico> <velocidad_de_paso>";
	}

}
