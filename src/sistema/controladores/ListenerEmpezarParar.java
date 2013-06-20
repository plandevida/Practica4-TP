package sistema.controladores;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import sistema.controladores.ordenes.Dispatcher;
import sistema.manager.VariablesDeContexto;

public class ListenerEmpezarParar extends ListenerOrdenes implements ActionListener {

	/**
	 * @uml.property  name="cont"
	 */
	private boolean cont;
	/**
	 * @uml.property  name="boton"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private JButton boton;
	
	public ListenerEmpezarParar(Dispatcher comandero, JButton botonorigen) {
		super(comandero);
		
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
