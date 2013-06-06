package sistema.controladores;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;

/**
 * Controlador para la cadencia y el periodo.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ListenerCadenciaPeriodo extends ListenerOrdenes implements ChangeListener {

	private PanelCiclista panel;
	
	public ListenerCadenciaPeriodo(Dispatcher comandero, PanelCiclista panelorigendatos) {
		super(comandero);
		
		panel = panelorigendatos;
	}
	
	@Override
	public void stateChanged(ChangeEvent e) {
		
		String idciclista = panel.getIdCiclista();
		
		Integer cadencia = panel.getCadencia();
		Double periodo = panel.getPeriodo();
		
		String comando = "ciclista " + idciclista + " cadencia " + cadencia + " periodo " + periodo;
		
		getDispatcher().parsearComando(comando);
	}
}
