package sistema.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;

/**
 * Controlador para la cadencia y el periodo.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ListenerCadenciaPeriodo extends ListenerOrdenes implements ActionListener {

	private PanelCiclista panel;
	
	public ListenerCadenciaPeriodo(Dispatcher comandero, PanelCiclista panelorigendatos) {
		super(comandero);
		
		panel = panelorigendatos;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actioncmd = e.getActionCommand();
		
		Integer offsetCadencia = 0;
		Double offsetPeriodo = 0d;
		
		if ( "+ cadencia".equals(actioncmd) ) {
			offsetCadencia = 1;
		}
		else if ("+ periodo".equals(actioncmd)) {
			offsetPeriodo = .1d;
		}
		else if ( "- cadencia".equals(actioncmd) ) {
			offsetCadencia = -1;
		}
		else if ( "- periodo".equals(actioncmd) ) {
			offsetPeriodo = -.1d;
		}
		
		String idciclista = panel.getIdCiclista();
		Integer cadencia = panel.getCadencia() + offsetCadencia;
		Double periodo = panel.getPeriodo() + offsetPeriodo;
		
		String comando = "ciclista " + idciclista + " cadencia " + cadencia + " periodo " + periodo;
		
		getDispatcher().parsearComando(comando);
	}
}
