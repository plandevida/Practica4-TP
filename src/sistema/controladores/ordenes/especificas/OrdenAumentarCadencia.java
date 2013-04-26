package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.manager.Manager;

public class OrdenAumentarCadencia extends OrdenParaCiclista {
	
	private int aumentocadencia;
	
	public OrdenAumentarCadencia() {
		aumentocadencia = 0;
	}
	
	@Override
	public void ejecutarOrden() {
		getCiclista().setCadencia(aumentocadencia);
		mostrarMensaje();
	}
	
	/**
	 * Aumenta la cadencia del ciclista.
	 * @param aumentodecadencia El aumento de la cadencia.
	 */
	private void setAumentoCadencia(int aumentodecadencia) {
		aumentocadencia = aumentodecadencia;
	}
	
	@Override
	public String mostrarMensaje() {
		return this.getClass().getName() + ": Aumentando la cadencia en :" + aumentocadencia;
	}

	@Override
	public Orden parse(String comando) {
		
		
		
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
