package sistema.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;
import sistema.vista.visual.Ventana;

/**
 * Controlador para cambiar de plato la bicicleta de un ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ListenerPlato extends ListenerOrdenes implements ActionListener {

	private PanelCiclista panel;
	
	public ListenerPlato(Dispatcher comandero, Ventana ventana, PanelCiclista panelorigendatos) {
		
		super(comandero, ventana);
		
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
		
		Integer plato = panel.getPlato() + offset;
		
		String idciclista = panel.getIdCiclista();
		
		String comando = "bicicleta " + idciclista + " cambia plato " + plato;
		
		getDispatcher().parsearComando(comando);
	}
}
