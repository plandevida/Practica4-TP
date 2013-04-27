
package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.controladores.parseadores.lexer.Comandos;
import sistema.manager.Presentador;

public class OrdenDesconocida extends Orden {

	@Override
	public void ejecutarOrden() {
		mostrarMensaje();
	}
	
	@Override
	public String mostrarMensaje() {
		
		StringBuilder mensaje = new StringBuilder();
		mensaje.append("NO SE RECONOCE LA ORDEN")
			.append("Ordenes del sistema: \n");
		
		Comandos[] comandos = Comandos.values();
		
		for (Comandos comando : comandos) {
			mensaje.append(comando.ordinal() + ": " + comando.name() + comando.getOrden().mostrarMensaje() + '\n');
		}
		
		return mensaje.toString();
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

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		// TODO Auto-generated method stub
		return false;
	}
}
