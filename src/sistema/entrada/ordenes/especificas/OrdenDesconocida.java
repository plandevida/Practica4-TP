
package sistema.entrada.ordenes.especificas;

import sistema.entrada.ordenes.Orden;
import sistema.entrada.parseador.lexer.Comandos;

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
	public void configurarContexto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String help(boolean detalles) {
		// TODO Auto-generated method stub
		return null;
	}
}
