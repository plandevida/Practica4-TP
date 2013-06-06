package sistema.controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sistema.controladores.ListenerOrdenes;
import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;

/**
 * Clase controlador que genera las ordenes de frenar de los ciclistas.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ListenerFrenar extends ListenerOrdenes implements ActionListener {

	// Panel del cual se obtienen los datos.
	private PanelCiclista panel;
	
	// Variable para distinguir el caso de frenado.
	private boolean frenarenseco;
	
	/**
	 * Construye el controlador.
	 * 
	 * @param comandero Comandero del sistema.
	 * @param panelciclista Origen de los datos.
	 * @param enseco Determina si frenar en seco o no.
	 */
	public ListenerFrenar(Dispatcher comandero, PanelCiclista panelciclista, boolean enseco) {
		
		// Ponemos el dispatcher como miembro de la clase.
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
		
		// Se parsea el comando y si es correcto va a la cola del comandero.
		getDispatcher().parsearComando(comando);
	}

	@Override
	public Dispatcher getDispatcher() {
		
		return getDispatcher();
	}
}
