package sistema.controladores.ordenes.especificas;

import java.util.List;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenClembuterol extends Orden {
	
	private List<Ciclista> listadeciclistas;
	private Integer nmaillot;
	public OrdenClembuterol(Integer numeromaillot){
		
		nmaillot = numeromaillot;
		
	}
	@Override
	public String mostrarMensaje() {
		return (new StringBuilder()
					.append("Ciclista ")
					.append(nmaillot)
					.append(" se le ha restaurado la fuerza ¡Es un milagro! ")).toString();
	}

	@Override
	public void ejecutarOrden() {
		if(listadeciclistas != null){
			
			for (Ciclista c : listadeciclistas){
				if(c.getNumeromallot() == nmaillot) c.setFuerza(100d);
			}
		}
		
	}

	@Override
	public Orden parse(String comando) {
		Orden ordenclembuterol = null;
		
		String[] tokens = comando.split(" ");
		
		if ( tokens.length > 0 ) {
			
			if (comprobarSintaxis(tokens)) {
				
				if ( tokens.length == 2 ) {
					try {
							nmaillot = Integer.valueOf(tokens[1]);
							
							if (nmaillot >= 0 && nmaillot < VariablesDeContexto.MAX_CICLISTAS) {
							
								ordenclembuterol = new OrdenClembuterol(nmaillot);
							}
						}
						catch (NumberFormatException ne) {
						}	
					}
				}
		}
		return ordenclembuterol;
	}

	@Override
	protected boolean comprobarSintaxis(String[] tokenscomando) {
		
		 return (tokenscomando[0].equalsIgnoreCase("clembuterol"));
	}

	@Override
	public void configurarContexto(Presentador presentador) {
		listadeciclistas = presentador.getCiclistas();
		
	}

	@Override
	public String help(boolean detalles) {
	
		return "clembuterol <numero de maillot >";
	}

}
