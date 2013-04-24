package sistema.entrada.ordenes.especificas;

import sistema.controlador.ordenes.OrdenParaCiclista;

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
}
