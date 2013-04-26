package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.manager.Manager;

public class OrdenFrenar extends OrdenParaCiclista {
	
	public OrdenFrenar() {
	
	}

	@Override
	public void ejecutarOrden() {
		getCiclista().frenar();
		mostrarMensaje();
	}
	
	@Override
	public String mostrarMensaje() {
		return this.getClass().getName() + ": Frenando";
	}

	@Override
	public Orden parse(String comando) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configurarContexto(Manager presentador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help(boolean detalles) {
		// TODO Auto-generated method stub
		return null;
	}
}
