package sistema.entrada.ordenes.especificas;

import sistema.controlador.ordenes.OrdenParaCiclista;

public class OrdenSubirPinhon extends OrdenParaCiclista {
	
	public OrdenSubirPinhon(){
		
	}
	
	@Override
	public void ejecutarOrden() {
		getCiclista().aumentarPinhon();
		mostrarMensaje();
	}
	
	@Override
	public String mostrarMensaje() {
		return this.getClass().getName() + ": Subiendo de piñón";
	}
}
