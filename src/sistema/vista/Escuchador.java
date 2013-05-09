package sistema.vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JTextField;

import sistema.controladores.ordenes.Dispatcher;


public class Escuchador {

    private Dispatcher comandero;

    public Escuchador(Dispatcher micomandero) {
    	comandero = micomandero;
    }

    public void asignaMouseClicked(JButton bt, final String comando) {
	bt.addMouseListener(new MouseAdapter() {
	    @Override
	    public void mouseClicked(MouseEvent e) {
		comandero.parsearComando(comando);
	    }
	});
    }

    public void asignaKeyPressed(JTextField ta, final String comando) {
	final JTextField tb = ta;
	tb.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == 10) {
		    comandero.parsearComando(tb.getText());
		    tb.setText("");
		}
	    }
	});
	ta = tb;
    }

}