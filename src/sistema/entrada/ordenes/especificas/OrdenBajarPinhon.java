package sistema.entrada.ordenes.especificas;

import sistema.controlador.ordenes.OrdenParaCiclista;

public class OrdenBajarPinhon extends OrdenParaCiclista {
	
	@Override
	public void ejecutarOrden() {
		getCiclista().aumentarPinhon();
		mostrarMensaje();
	}
	
	@Override
	public String mostrarMensaje() {
		return this.getClass().getName() + ": Aumentando de piñón";
	}
}
