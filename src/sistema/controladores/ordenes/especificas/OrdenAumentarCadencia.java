package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.ordenes.OrdenParaCiclista;
import sistema.manager.Presentador;

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
		
		// TODO parsear el comando de verdad XD y he puesto esto por que soy un friki quitando warnings
		int aumentodecadencia = 5;
		
		setAumentoCadencia(aumentodecadencia);
		
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
}
