package sistema.controladores;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JTextArea;

import sistema.controladores.ordenes.Dispatcher;

/**
 * Controlador para la entrada de comandos al sistema.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class ListenerComandos extends ListenerOrdenes implements KeyListener {
	
	public ListenerComandos(Dispatcher comandero) {
		super(comandero);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		// Obtenemos el código de la tecla pulsada.
		int key = e.getKeyCode();
			
		JTextArea area = (JTextArea) e.getSource();
		
		// Parseamos el comando si la tecla fue el enter.
		if (key == KeyEvent.VK_ENTER) {
			
			String texto = area.getText();
			
			if (texto.contains("\n")) {
				int index = texto.indexOf("\n");
				
				if ( index != -1) {
					texto = texto.substring(0, index);
				}
			}
			
			getDispatcher().parsearComando(texto);
			
			// Limpiamos el campo.
			area.setText("");
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {}
}
