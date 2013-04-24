package sistema.entrada.ordenes.especificas;

import sistema.controlador.ordenes.OrdenParaCiclista;

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
}
