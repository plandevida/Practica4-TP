package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.manager.Presentador;

public class OrdenAyuda extends Orden {

	@Override
	public String mostrarMensaje() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void ejecutarOrden() {
		// TODO Auto-generated method stub

	}

	@Override
	public Orden parse(String comando) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		// TODO Auto-generated method stub
		return false;
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
