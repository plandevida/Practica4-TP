package sistema.controladores;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;

/**
 * Controlador para cambiar de plato la bicicleta de un ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ListenerPlato extends ListenerOrdenes implements ChangeListener {

	private PanelCiclista panel;
	
	public ListenerPlato(Dispatcher comandero, PanelCiclista panelorigendatos) {
		super(comandero);
		
		panel = panelorigendatos;
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		
		Integer plato = panel.getPlato();
		
		String idciclista = panel.getIdCiclista();
		
		String comando = "ciclista " + idciclista + "cambia plato " + plato;
		
		getDispatcher().parsearComando(comando);
	}

}
