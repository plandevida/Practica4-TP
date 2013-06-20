package sistema.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import sistema.controladores.ordenes.Dispatcher;
import sistema.manager.VariablesDeContexto;
import sistema.vista.visual.Ventana;

public class ListenerEmpezarParar extends ListenerOrdenes implements ActionListener {
	
	private boolean cont;
	private JButton boton;
	
	public ListenerEmpezarParar(Dispatcher comandero, Ventana ventana, JButton botonorigen) {
		super(comandero, ventana);
		
		cont = false;
		boton = botonorigen;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String comando = "";
		
		if (VariablesDeContexto.CARRERA) {
			comando = "stop";
			
			boton.setText("Reanudar");
		}
		else {
			
			if (cont) {
				comando = "reanudar";
				boton.setText("Parar");
			}
			else {
				comando = "start";
				boton.setText("Parar");
				
				cont = true;
			}
		}
		
		
		getDispatcher().parsearComando(comando);
	}
}
