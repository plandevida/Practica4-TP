package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenEmpezarCarrera extends Orden{
	
	public OrdenEmpezarCarrera(){	
	}
	@Override
	public String mostrarMensaje() {
		return (new StringBuilder()
					.append("La carrera ha comenzado ")).toString();
	}

	@Override
	public void ejecutarOrden() {
		
		VariablesDeContexto.CARRERA = true;
//		VariablesDeContexto.EMPEZADA = true;
	}

	@Override
	public Orden parse(String comando) {
		
		Orden ordenpararcarrera = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if (comprobarSintaxis(tokens)) {
				
				if ( tokens.length == 1 ) {
					try {
						
						ordenpararcarrera = new OrdenEmpezarCarrera();
						}
					
					catch (NumberFormatException ne) {
						
					}
				}
			}
		}
		
		return ordenpararcarrera;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		return (tokenscomando[0].equalsIgnoreCase("start"));
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help(boolean detalles) {
		
		return "start";
	}

}
