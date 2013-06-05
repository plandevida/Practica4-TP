package sistema.controladores;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTextArea;

import sistema.controladores.ordenes.Dispatcher;

public class ListenerComandos extends KeyAdapter {
	
	private Dispatcher micomandero;
	
	public ListenerComandos(Dispatcher comandero) {
		micomandero = comandero;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
		int key = e.getKeyCode();
			
		JTextArea area = (JTextArea) e.getSource();
		
		if (key == KeyEvent.VK_ENTER) {
			
			String texto = area.getText();
			
			System.out.println(texto);
			
			micomandero.parsearComando(texto);
			area.setText("");
		}
	}
}
