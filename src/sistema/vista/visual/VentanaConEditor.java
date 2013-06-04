package sistema.vista.visual;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.ordenes.Orden;
import sistema.controladores.parseadores.parser.ParseadorComandos;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.manager.Presentador;
import sistema.vista.Lienzo;

public class VentanaConEditor {

	private JFrame frame;
	private JTextField tVelocidad;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> cbCiclistaActivo;

	private Dispatcher dispatcher;
	private Lienzo lienzo;
	private List<Ciclista> ciclistas;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ciclista a = new Ciclista("pepe", 1, 120, new Bicicleta(), 0.5, Reloj.getInstance(), 60, 100);
					
					List<Ciclista> lc = new ArrayList<Ciclista>();
					lc.add(a);
					
					Presentador p = new Presentador(lc, new ArrayList<ObjetosConSalidaDeDatos>(), new HashMap<Integer, MiViento>(), Reloj.getInstance(), new Orden[]{});
					
					VentanaConEditor window = new VentanaConEditor(new Dispatcher(p, new ParseadorComandos()), new Lienzo(lc), lc);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaConEditor(Dispatcher miDispatcher, Lienzo lienzocarrera, List<Ciclista> listaciclistas) {
		
		dispatcher = miDispatcher;
		lienzo = lienzocarrera;
		ciclistas = listaciclistas;
		
		initialize();
		
		prepararCiclistas(ciclistas);
	}
	
	/**
	 * Mete los ciclista en el combo.
	 */
	public void prepararCiclistas(List<Ciclista> ciclistaapreparar) {
		
		if ( ciclistaapreparar != null) {
			for( Ciclista c : ciclistaapreparar ) {
				cbCiclistaActivo.addItem(c.getIdentificadorSalidaDatos());
			}
			
			if ( ciclistaapreparar.size() > 0 ) {
				cbCiclistaActivo.setSelectedIndex(0);
			}
		}
	}
	
	/**
	 * Añade nueva información en un área de texto o crea una nueva
	 * si fuese necesario.
	 * 
	 * @param id Objeto que mostrará sus datos.
	 * @param mensajes Los datos formateados a poner en el área.
	 */
	public void ponerDatosEnVentana(String id, Object... mensajes) {
		JTextComponent componenteexistente = null;
		
		try {
			switch(id) {
			case "ruloj":
				
				int hora = (Integer) mensajes[0];
				int minuto = (Integer) mensajes[1];
				int segundo = (Integer) mensajes[2];
				
	//			relojGrafico.setTime(segundo, minuto, hora);
				
				break;
			case "ayudaMain":
				
	//			tFconsola.setText(tFconsola.getText()
	//					+ "CiclistaManager <número_ciclistas> <fichero_comandos> <unidad_tiempo> "
	//					+ "<número_platos> <dientes_plato (separados por espacios)> <número_piñones>"
	//					+ " <dientes_piñones (separados por espacios)> <radio_rueda>");
				break;
			default:
	//			for (JTextComponent componente : componenetescreados) {
	//				if (componente.getName().equals(id)) {
	//					componente.setText( (String) mensajes[0]);
	//					
	//					componenteexistente = componente;
	//				}
	//			}
				
				if (componenteexistente == null) {
	//				anadirTextArea(id);
					
					ponerDatosEnVentana(id, mensajes);
				}
				break;
			}
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 925, 685);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		cbCiclistaActivo = new JComboBox<String>();
		sl_panel.putConstraint(SpringLayout.NORTH, cbCiclistaActivo, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, cbCiclistaActivo, 102, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, cbCiclistaActivo, -661, SpringLayout.EAST, panel);
		panel.add(cbCiclistaActivo);
		
		JLabel lblCiclsitaActivo = new JLabel("Ciclista activo:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblCiclsitaActivo, 16, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, lblCiclsitaActivo, 10, SpringLayout.WEST, panel);
		panel.add(lblCiclsitaActivo);
		
		JSeparator separator = new JSeparator();
		sl_panel.putConstraint(SpringLayout.NORTH, separator, 161, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, separator, 10, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, separator, 899, SpringLayout.WEST, panel);
		panel.add(separator);
		
		JTextArea textArea = new JTextArea();
		sl_panel.putConstraint(SpringLayout.SOUTH, textArea, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, separator, -6, SpringLayout.NORTH, textArea);
		sl_panel.putConstraint(SpringLayout.NORTH, textArea, 169, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, textArea, 0, SpringLayout.WEST, lblCiclsitaActivo);
		sl_panel.putConstraint(SpringLayout.EAST, textArea, 291, SpringLayout.WEST, panel);
		textArea.setBorder(new TitledBorder("Comandos"));
		panel.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_panel.putConstraint(SpringLayout.NORTH, textArea_1, 6, SpringLayout.SOUTH, separator);
		sl_panel.putConstraint(SpringLayout.WEST, textArea_1, 24, SpringLayout.EAST, textArea);
		sl_panel.putConstraint(SpringLayout.SOUTH, textArea_1, 0, SpringLayout.SOUTH, textArea);
		sl_panel.putConstraint(SpringLayout.EAST, textArea_1, 0, SpringLayout.EAST, separator);
		panel.add(textArea_1);
		
		JLabel lblVelocidad = new JLabel("Velocidad:");
		panel.add(lblVelocidad);
		
		tVelocidad = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, tVelocidad, 118, SpringLayout.WEST, panel);
		tVelocidad.setEditable(false);
		panel.add(tVelocidad);
		tVelocidad.setColumns(10);
		
		JLabel lblFuerza = new JLabel("Fuerza:");
		sl_panel.putConstraint(SpringLayout.WEST, lblFuerza, 30, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblVelocidad, 0, SpringLayout.EAST, lblFuerza);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblFuerza, -6, SpringLayout.NORTH, separator);
		panel.add(lblFuerza);
		
		JProgressBar progressBar = new JProgressBar();
		sl_panel.putConstraint(SpringLayout.WEST, progressBar, 43, SpringLayout.EAST, lblFuerza);
		sl_panel.putConstraint(SpringLayout.EAST, tVelocidad, 0, SpringLayout.EAST, progressBar);
		sl_panel.putConstraint(SpringLayout.SOUTH, progressBar, -6, SpringLayout.NORTH, separator);
		panel.add(progressBar);
		
		JLabel lblDistancia = new JLabel("Distancia:");
		sl_panel.putConstraint(SpringLayout.WEST, lblDistancia, 12, SpringLayout.WEST, panel);
		panel.add(lblDistancia);
		
		textField = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textField, 43, SpringLayout.EAST, lblDistancia);
		sl_panel.putConstraint(SpringLayout.SOUTH, tVelocidad, 0, SpringLayout.NORTH, textField);
		sl_panel.putConstraint(SpringLayout.NORTH, lblDistancia, 6, SpringLayout.NORTH, textField);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField, -6, SpringLayout.NORTH, progressBar);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPin = new JLabel("Piñón:");
		sl_panel.putConstraint(SpringLayout.WEST, lblPin, 0, SpringLayout.WEST, textArea_1);
		panel.add(lblPin);
		
		JLabel lblPlato = new JLabel("Plato:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPlato, 0, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, lblPlato, 0, SpringLayout.WEST, textArea_1);
		panel.add(lblPlato);
		
		JLabel lblCadencia = new JLabel("Cadencia:");
		sl_panel.putConstraint(SpringLayout.EAST, progressBar, -62, SpringLayout.WEST, lblCadencia);
		sl_panel.putConstraint(SpringLayout.WEST, lblCadencia, 0, SpringLayout.WEST, textArea_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblCadencia, -6, SpringLayout.NORTH, separator);
		panel.add(lblCadencia);
		
		JSpinner spinner = new JSpinner();
		sl_panel.putConstraint(SpringLayout.WEST, spinner, 13, SpringLayout.EAST, lblCadencia);
		sl_panel.putConstraint(SpringLayout.EAST, textField, -136, SpringLayout.WEST, spinner);
		sl_panel.putConstraint(SpringLayout.SOUTH, spinner, -6, SpringLayout.NORTH, separator);
		panel.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		sl_panel.putConstraint(SpringLayout.NORTH, lblPin, 6, SpringLayout.NORTH, spinner_1);
		sl_panel.putConstraint(SpringLayout.WEST, spinner_1, 0, SpringLayout.WEST, spinner);
		sl_panel.putConstraint(SpringLayout.SOUTH, spinner_1, -37, SpringLayout.NORTH, spinner);
		sl_panel.putConstraint(SpringLayout.EAST, spinner_1, 0, SpringLayout.EAST, spinner);
		panel.add(spinner_1);
		
		JButton btnFrenarEnSeco = new JButton("Frenar en seco");
		sl_panel.putConstraint(SpringLayout.NORTH, btnFrenarEnSeco, -5, SpringLayout.NORTH, lblPin);
		sl_panel.putConstraint(SpringLayout.WEST, btnFrenarEnSeco, 29, SpringLayout.EAST, spinner_1);
		panel.add(btnFrenarEnSeco);
		
		JButton btnFrenar = new JButton("Frenar");
		sl_panel.putConstraint(SpringLayout.NORTH, btnFrenar, -5, SpringLayout.NORTH, lblPin);
		sl_panel.putConstraint(SpringLayout.WEST, btnFrenar, 73, SpringLayout.EAST, btnFrenarEnSeco);
		panel.add(btnFrenar);
		
		JLabel lblDistancia_1 = new JLabel("Cantidad:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblDistancia_1, 0, SpringLayout.NORTH, lblDistancia);
		panel.add(lblDistancia_1);
		
		textField_1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 658, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblDistancia_1, -6, SpringLayout.WEST, textField_1);
		sl_panel.putConstraint(SpringLayout.NORTH, textField_1, -6, SpringLayout.NORTH, lblDistancia);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		sl_panel.putConstraint(SpringLayout.EAST, textField_1, -8, SpringLayout.WEST, lblTiempo);
		sl_panel.putConstraint(SpringLayout.NORTH, lblTiempo, 0, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.EAST, lblTiempo, 0, SpringLayout.EAST, btnFrenar);
		panel.add(lblTiempo);
		
		textField_2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField_2, -6, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, textField_2, 6, SpringLayout.EAST, lblTiempo);
		sl_panel.putConstraint(SpringLayout.EAST, textField_2, -92, SpringLayout.EAST, panel);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		sl_panel.putConstraint(SpringLayout.EAST, spinner, -16, SpringLayout.WEST, lblPeriodo);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblPeriodo, -6, SpringLayout.NORTH, separator);
		panel.add(lblPeriodo);
		
		JSpinner spinner_3 = new JSpinner();
		sl_panel.putConstraint(SpringLayout.WEST, spinner_3, 515, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblPeriodo, -6, SpringLayout.WEST, spinner_3);
		sl_panel.putConstraint(SpringLayout.SOUTH, spinner_3, -6, SpringLayout.NORTH, separator);
		sl_panel.putConstraint(SpringLayout.EAST, spinner_3, -365, SpringLayout.EAST, panel);
		panel.add(spinner_3);
		
		JSpinner spinner_2 = new JSpinner();
		sl_panel.putConstraint(SpringLayout.WEST, spinner_2, 0, SpringLayout.WEST, spinner);
		sl_panel.putConstraint(SpringLayout.SOUTH, spinner_2, -4, SpringLayout.NORTH, spinner);
		sl_panel.putConstraint(SpringLayout.EAST, spinner_2, 0, SpringLayout.EAST, spinner);
		panel.add(spinner_2);
		
		JSeparator separator_2 = new JSeparator();
		sl_panel.putConstraint(SpringLayout.NORTH, separator_2, 6, SpringLayout.SOUTH, cbCiclistaActivo);
		sl_panel.putConstraint(SpringLayout.SOUTH, separator_2, -7, SpringLayout.NORTH, spinner_1);
		sl_panel.putConstraint(SpringLayout.NORTH, lblVelocidad, 30, SpringLayout.SOUTH, separator_2);
		sl_panel.putConstraint(SpringLayout.WEST, separator_2, 20, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, separator_2, 0, SpringLayout.EAST, separator);
		panel.add(separator_2);
		
		JLabel lblNewLabel = new JLabel("No hay ciclistas en carrera.");
		lblNewLabel.setEnabled(false);
		lblNewLabel.setVerifyInputWhenFocusTarget(false);
		lblNewLabel.setVisible(false);
		lblNewLabel.setForeground(Color.RED);
		sl_panel.putConstraint(SpringLayout.WEST, lblNewLabel, 6, SpringLayout.EAST, cbCiclistaActivo);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblNewLabel, 0, SpringLayout.SOUTH, cbCiclistaActivo);
		panel.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		
		Canvas canvas = new Canvas();
		scrollPane.setViewportView(canvas);
	}

}
