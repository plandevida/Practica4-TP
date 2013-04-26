package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.manager.Presentador;

public class OrdenBajarPlato extends OrdenParaCiclista {
	
	public OrdenBajarPlato() {
		
	}

	@Override
	public void ejecutarOrden() {
		getCiclista().disminuirPlato();
	}
	
	@Override
	public String mostrarMensaje() {
		return this.getClass().getName() + ": Bajando de plato";
	}

	@Override
	public Orden parse(String comando) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help(boolean detalles) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] misPermisos() {
		// TODO Auto-generated method stub
		return null;
	}
}
