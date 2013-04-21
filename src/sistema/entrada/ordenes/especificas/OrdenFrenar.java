package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.OrdenParaCiclista;

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
}
