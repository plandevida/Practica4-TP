package sistema.manager;

import java.util.List;
import java.util.Map;

import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.interfaces.ObjetosQueSeEjecutan;

public class Presentador {
	
	private List<ObjetosQueSeEjecutan> listadeobjetosqueseejecutan;
	
	private List<ObjetosConSalidaDeDatos> listadeobjetosamostarenvista;
	
	private Map<Integer, MiViento> mapametereológico;
}
