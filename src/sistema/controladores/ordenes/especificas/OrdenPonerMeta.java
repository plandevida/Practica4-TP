package sistema.controladores.ordenes.especificas;

import java.util.List;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenPonerMeta extends Orden {

	private Double meta;
	private List<Ciclista> listadeciclistas;
	
	public OrdenPonerMeta(Double meta){
		this.meta = meta;
	}
	@Override
	public String mostrarMensaje() {
		return (new StringBuilder()
					.append("Meta posicionada en el km ")
					.append(meta)
					.append(" Km")).toString();
	}

	@Override
	public void ejecutarOrden() {
		if (listadeciclistas != null) {
			
			 for (Ciclista c : listadeciclistas){
				 
				 if(c.getBicicletamontada().getEspacioRecorrido() >= meta){
					 VariablesDeContexto.CARRERA = false;
					 c.setGanador(true);
				 }
			 }
		}
			VariablesDeContexto.META = meta;
		
		
	}

	@Override
	public Orden parse(String comando) {
		
		Orden ordenponermeta = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
					
					if (comprobarSintaxis(tokens)) {
						
						if ( tokens.length == 2 ) {
							try {
								meta = Double.valueOf(tokens[1]);
								
								if (meta > 0 && meta < VariablesDeContexto.LONGITUD_CARRERA) {
									
									ordenponermeta = new OrdenPonerMeta(meta);
									
								}
							} catch (NumberFormatException ne) {
								
							}
						}
					}
				}
		
		return ordenponermeta;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		return (tokenscomando[0].equalsIgnoreCase("meta"));
		
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		listadeciclistas = presentador.getCiclistas();
		
	}

	@Override
	public String help(boolean detalles) {
		return "meta <punto_kilometrico>";
	}

}
