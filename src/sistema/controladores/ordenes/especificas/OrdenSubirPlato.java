package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.manager.Manager;

public class OrdenSubirPlato extends OrdenParaCiclista {
	
	public OrdenSubirPlato(){
		
	}

	@Override
	public void ejecutarOrden() {
		getCiclista().aumentarPlato();
		mostrarMensaje();
	}
	
	@Override
	public String mostrarMensaje() {
		return this.getClass().getName() + ": Subiendo de plato";
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
