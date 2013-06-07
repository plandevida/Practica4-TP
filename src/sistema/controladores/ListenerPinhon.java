package sistema.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;

/**
 * Controlador para cambiar de piñón la bicicleta de un ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ListenerPinhon extends ListenerOrdenes implements ActionListener {

	private PanelCiclista panel;
	
	public ListenerPinhon(Dispatcher comandero, PanelCiclista panelorigendatos) {
		super(comandero);
		
		panel = panelorigendatos;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String actioncmd = e.getActionCommand();
		
		Integer offset = 0;
		
		if ( "+".equals(actioncmd) ) {
			offset = 1;
		}
		else if ( "-".equals(actioncmd) ) {
			offset = -1;
		}
		
		Integer pinhon = panel.getPinhon() + offset;
		
		String idciclista = panel.getIdCiclista();
		
		String comando = "bicicleta " + idciclista + " cambia piñon " + pinhon;
		
		getDispatcher().parsearComando(comando);
	}
}
