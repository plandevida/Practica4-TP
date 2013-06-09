package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrderReanudarCarrera extends Orden{
	public OrderReanudarCarrera(){
		
	}
	@Override
	public String mostrarMensaje() {
		return (new StringBuilder()
					.append("La carrera se ha reanudado ")).toString();
	}

	@Override
	public void ejecutarOrden() {
		VariablesDeContexto.CARRERA = true;
		
	}

	@Override
	public Orden parse(String comando) {
		
		Orden ordenreanudarcarrera = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if (comprobarSintaxis(tokens)) {
				
				if ( tokens.length == 1 ) {
					try {
						
						ordenreanudarcarrera = new OrderReanudarCarrera();
						}
					
					catch (NumberFormatException ne) {
						
					}
				}
			}
		}
		
		return ordenreanudarcarrera;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		return (tokenscomando[0].equalsIgnoreCase("reanudar"));
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help(boolean detalles) {
		
		return "reanudar";
	}

}
