package sistema.controladores.ordenes.especificas;

import sistema.controladores.ordenes.Orden;
import sistema.manager.Presentador;

public class OrdenAyuda extends Orden {

	private Orden[] ordenes;
	
	public OrdenAyuda(Orden[] ordenesparseador) {
		ordenes = ordenesparseador;
	}
	
	@Override
	public String mostrarMensaje() {
		
		StringBuilder mensaje = new StringBuilder();
		
		mensaje.append("AYUDA DE COMANDOS:\n");
		
		for(Orden orden : ordenes) {
		
			mensaje.append(orden.help(false))
			.append("\n");
		}
		
		return mensaje.toString();
	}

	@Override
	public void ejecutarOrden() {
		
		System.out.println( mostrarMensaje() );
	}

	@Override
	public Orden parse(String comando) {
		OrdenAyuda ordenayuda = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if (comprobarSintaxis(tokens)) {
				
				if ( tokens.length == 1 ) {
					try {
						ordenayuda = new OrdenAyuda(null);
					} catch (NumberFormatException ne) {
						// nada que hacer.
					}
				}
			}
		}
		
		return ordenayuda;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		return (tokenscomando[0].equalsIgnoreCase("ayuda"));
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		
		ordenes = presentador.getOrdenes(this);
	}

	@Override
	public String help(boolean detalles) {
		
		return "ayuda";
	}

	@Override
	public String[] misPermisos() {
		
		return new String[]{ "ORDENES" };
	}
}
