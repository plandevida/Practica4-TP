package sistema.controladores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import sistema.controladores.ListenerOrdenes;
import sistema.controladores.ordenes.Dispatcher;
import sistema.vista.visual.PanelCiclista;
import sistema.vista.visual.Ventana;

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
	public ListenerFrenar(Dispatcher comandero, Ventana ventana, PanelCiclista panelciclista, boolean enseco) {
		
		// Ponemos el dispatcher como miembro de la clase.
		super(comandero, ventana);
		
		panel = panelciclista;
		frenarenseco = enseco;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		String idciclista = panel.getIdCiclista();
		
		String comando = "";
		
		Double tiempo = null;
		Double cantidad = null;
		
		if (frenarenseco) {
			
			try {
				tiempo = panel.getTiempoFrenado();
			} catch (NumberFormatException ne) {
				getVentana().ponerEnLog("Debe especificarse el tiempo que permanecerá frenado.");
			}
			
			comando = "ciclista " + idciclista + " frena 0 en " + tiempo;
		}
		else {
			
			try {
				tiempo = panel.getTiempoFrenado();
				cantidad = panel.getCantidadFrenado();
			} catch (NumberFormatException ne) {
				getVentana().ponerEnLog("Debe especificarse el tiempo que permanecerá frenado y la cantidad a frenar.");
			}
			
			comando = "ciclista " + idciclista + " frena " + cantidad + " en " + tiempo;
		}
		
		// Se parsea el comando y si es correcto va a la cola del comandero.
		getDispatcher().parsearComando(comando);
	}
}
