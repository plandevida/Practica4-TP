package sistema.controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sistema.controladores.ListenerOrdenes;
import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;


public class ListenerFrenar extends ListenerOrdenes implements ActionListener {

	private PanelCiclista panel;
	
	private boolean frenarenseco;
	
	public ListenerFrenar(Dispatcher comandero, PanelCiclista panelciclista, boolean enseco) {
		
		super(comandero);
		
		panel = panelciclista;
		frenarenseco = enseco;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String nombreciclista = panel.getNombreCiclista();
		
		String idciclista = nombreciclista.substring(0, 1);
		
		String comando = "";
		
		if (frenarenseco) {
			comando = "ciclista " + idciclista + " frena 0 en 0";
		}
		else {
			
			Integer cantidad = panel.getCantidadFrenado();
			Integer tiempo = panel.getTiempoFrenado();
			
			comando = "ciclista " + idciclista + " frena " + cantidad + " en " + tiempo;
		}
		
		getDispatcher().parsearComando(comando);
	}

	@Override
	public Dispatcher getDispatcher() {
		
		return getDispatcher();
	}
}
