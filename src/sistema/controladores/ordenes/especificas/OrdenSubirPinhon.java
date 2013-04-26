package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.manager.Presentador;

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
