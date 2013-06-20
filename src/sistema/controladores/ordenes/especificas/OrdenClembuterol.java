package sistema.controladores.ordenes.especificas;

import java.util.List;

import sistema.controladores.ordenes.Orden;
import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

public class OrdenClembuterol extends Orden {
	
	/**
	 * @uml.property  name="listadeciclistas"
	 */
	private List<Ciclista> listadeciclistas;
	/**
	 * @uml.property  name="listadecurvas"
	 */
	private List<Curva> listadecurvas;
	/**
	 * @uml.property  name="nmaillot"
	 */
	private Integer nmaillot;
	/**
	 * @uml.property  name="especial"
	 */
	private Boolean especial;
	
	public OrdenClembuterol(Integer numeromaillot,Boolean especial){
		
		nmaillot = numeromaillot;
		this.especial = especial;
		
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
			if(!especial){
				
				for (Ciclista c : listadeciclistas){
					if(c.getNumeromallot() == nmaillot) c.setFuerza(100d);
				}
			}
			else {
				for (Ciclista c : listadeciclistas){
					if(c.getNumeromallot() == nmaillot) {
						
						c.setFuerza(100d);
						c.setEstrellado(false);
						
						for (Curva cu : listadecurvas){
							if(c.getBicicletamontada().getEspacioRecorrido() >= cu.getPuntokilometrico()){
								cu.setCiclistashanpasadocurva(nmaillot, nmaillot);
							}
						}
					}
				}
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
							
								ordenclembuterol = new OrdenClembuterol(nmaillot,false);
							}
						}
						catch (NumberFormatException ne) {
						}	
					}
				else if(tokens.length == 3){
					try {
						nmaillot = Integer.valueOf(tokens[2]);
						
						if (nmaillot >= 0 && nmaillot < VariablesDeContexto.MAX_CICLISTAS) {
							
							if(tokens[1].equalsIgnoreCase("plusplus")){
								ordenclembuterol = new OrdenClembuterol(nmaillot,true);
							}
							
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
		listadecurvas = presentador.getListaCurvas();
	}

	@Override
	public String help(boolean detalles) {
	
		return "clembuterol <numero de maillot>";
	}

}
