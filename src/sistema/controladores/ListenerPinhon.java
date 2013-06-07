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
		
		Integer pinhon = panel.getPinhon();
		
		String idciclista = panel.getIdCiclista();
		
		String comando = "ciclista " + idciclista + "cambia piñon " + pinhon;
		
		getDispatcher().parsearComando(comando);
	}
}
