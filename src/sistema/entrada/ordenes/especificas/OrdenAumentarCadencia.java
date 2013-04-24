package sistema.entrada.ordenes.especificas;

import sistema.controlador.ordenes.Orden;
import sistema.entidades.personas.ciclistas.Ciclista;

public class OrdenAumentarCadencia extends Orden {
	
	private int aumentocadencia;
	private Ciclista ciclista;
	
	public OrdenAumentarCadencia() {
		aumentocadencia = 0;
	}
	
	@Override
	public void ejecutarOrden() {
		ciclista.setCadencia(aumentocadencia);
		mostrarMensaje();
	}
	
	/**
	 * Aumenta la cadencia del ciclista.
	 * @param aumentodecadencia El aumento de la cadencia.
	 */
	public void setAumentoCadencia(int aumentodecadencia) {
		aumentocadencia = aumentodecadencia;
	}
	
	@Override
	public String mostrarMensaje() {
		return this.getClass().getName() + ": Aumentando la cadencia en :" + aumentocadencia;
	}

	@Override
	public Orden parse(String comando) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void configurarContexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help(boolean detalles) {
		// TODO Auto-generated method stub
		return null;
	}
}
