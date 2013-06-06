package sistema.vista.visual;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
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
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.ordenes.Orden;
import sistema.controladores.parseadores.ParseadorComandos;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;
import sistema.vista.Lienzo;

public class VentanaConEditor {

	private JFrame frame;
	private JTextField tVelocidad;
	private JTextField tDistancia;
	private JTextField tCantidadFrenar;
	private JTextField tTiempoFrenar;
	private JComboBox<String> cbCiclistaActivo;
	private JTextArea comandosArea;
	private JTextArea registroArea;
	private JProgressBar fuerzaBar;
	private JSpinner sPlato;
	private JSpinner sPeriodo;
	private JSpinner sPinhon;
	private JSpinner sCadencia;

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
					
					new VentanaConEditor(new Dispatcher(p, new ParseadorComandos()), new Lienzo(lc));
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana.
	 * 
	 * @param miDispatcher
	 * @param lienzocarrera
	 * @param listaciclistas
	 */
	public VentanaConEditor(Dispatcher miDispatcher, Lienzo lienzocarrera, List<Ciclista> listaciclistas) {
		
		dispatcher = miDispatcher;
		lienzo = lienzocarrera;
		ciclistas = (listaciclistas != null) ? listaciclistas : new ArrayList<Ciclista>();
		
		initialize();
		
		prepararCiclistas(ciclistas);
	}
	
	public VentanaConEditor(Dispatcher miDispatcher, Lienzo lienzocarrera) {
		initialize();
	}
	
	/**
	 * Mete los ciclista en el combo.
	 */
	private void prepararCiclistas(List<Ciclista> ciclistaapreparar) {
		
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
	 * Coloca los ciclistas en el combobox
	 * 
	 * @param ciclistas Lista de ciclistas.
	 */
	public void setCiclistas(List<Ciclista> ciclistas) {
		this.ciclistas = ciclistas;
		
		prepararCiclistas(ciclistas);
	}
	
	/**
	 * Actualiza los datos del ciclista.
	 */
	public void update() {
		bindCiclista();
	}
	
	/**
	 * Rellena los campos con el ciclista indicado.
	 * 
	 * @param identificador Ciclista
	 */
	private void bindCiclista() {
		
		String ciclistaidentificador = cbCiclistaActivo.getItemAt(cbCiclistaActivo.getSelectedIndex());
		
		Ciclista ciclista = dispatcher.getPresentador().getCiclista(ciclistaidentificador);
		
		if (ciclista != null) {
			
			tVelocidad.setText( String.valueOf( ciclista.getBicicletamontada().getVelocidad() ) );
			tDistancia.setText( String.valueOf( ciclista.getBicicletamontada().getEspacioRecorrido() ) );
			fuerzaBar.setValue( ciclista.getFuerzaAsInt() );
			sPinhon.setValue( ciclista.getBicicletamontada().getPinhonactual() );
			sPlato.setValue( ciclista.getBicicletamontada().getPlatoactual() );
			sCadencia.setValue( ciclista.getCadencia() );
			sPeriodo.setValue( ciclista.getTiempopedalada() );
		}
	}
	
	private void unbind() {
		
		tVelocidad.setText("");
		tDistancia.setText("");
		fuerzaBar.setValue(0);
		sPinhon.setValue(0);
		sPlato.setValue(0);
		sCadencia.setValue(0);
		sPeriodo.setValue(0);
		
//		cbCiclistaActivo
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
		frame.setMinimumSize(new Dimension(925, 685));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		frame.setAlwaysOnTop(true);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		cbCiclistaActivo = new JComboBox<String>();
		cbCiclistaActivo.setModel(new DefaultComboBoxModel<String>());
		cbCiclistaActivo.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				bindCiclista();
			}
		});
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
		
		comandosArea = new JTextArea();
		sl_panel.putConstraint(SpringLayout.SOUTH, comandosArea, -10, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, separator, -6, SpringLayout.NORTH, comandosArea);
		sl_panel.putConstraint(SpringLayout.NORTH, comandosArea, 169, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, comandosArea, 0, SpringLayout.WEST, lblCiclsitaActivo);
		sl_panel.putConstraint(SpringLayout.EAST, comandosArea, 291, SpringLayout.WEST, panel);
		comandosArea.setBorder(new TitledBorder("Comandos"));
		panel.add(comandosArea);
		
		registroArea = new JTextArea();
		registroArea.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		sl_panel.putConstraint(SpringLayout.NORTH, registroArea, 6, SpringLayout.SOUTH, separator);
		sl_panel.putConstraint(SpringLayout.WEST, registroArea, 24, SpringLayout.EAST, comandosArea);
		sl_panel.putConstraint(SpringLayout.SOUTH, registroArea, 0, SpringLayout.SOUTH, comandosArea);
		sl_panel.putConstraint(SpringLayout.EAST, registroArea, 0, SpringLayout.EAST, separator);
		panel.add(registroArea);
		
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
		
		fuerzaBar = new JProgressBar();
		sl_panel.putConstraint(SpringLayout.WEST, fuerzaBar, 43, SpringLayout.EAST, lblFuerza);
		sl_panel.putConstraint(SpringLayout.EAST, tVelocidad, 0, SpringLayout.EAST, fuerzaBar);
		sl_panel.putConstraint(SpringLayout.SOUTH, fuerzaBar, -6, SpringLayout.NORTH, separator);
		panel.add(fuerzaBar);
		
		JLabel lblDistancia = new JLabel("Distancia:");
		sl_panel.putConstraint(SpringLayout.WEST, lblDistancia, 12, SpringLayout.WEST, panel);
		panel.add(lblDistancia);
		
		tDistancia = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, tDistancia, 43, SpringLayout.EAST, lblDistancia);
		sl_panel.putConstraint(SpringLayout.SOUTH, tVelocidad, 0, SpringLayout.NORTH, tDistancia);
		sl_panel.putConstraint(SpringLayout.NORTH, lblDistancia, 6, SpringLayout.NORTH, tDistancia);
		sl_panel.putConstraint(SpringLayout.SOUTH, tDistancia, -6, SpringLayout.NORTH, fuerzaBar);
		tDistancia.setEditable(false);
		panel.add(tDistancia);
		tDistancia.setColumns(10);
		
		JLabel lblPin = new JLabel("Piñón:");
		sl_panel.putConstraint(SpringLayout.WEST, lblPin, 0, SpringLayout.WEST, registroArea);
		panel.add(lblPin);
		
		JLabel lblPlato = new JLabel("Plato:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPlato, 0, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, lblPlato, 0, SpringLayout.WEST, registroArea);
		panel.add(lblPlato);
		
		JLabel lblCadencia = new JLabel("Cadencia:");
		sl_panel.putConstraint(SpringLayout.EAST, fuerzaBar, -62, SpringLayout.WEST, lblCadencia);
		sl_panel.putConstraint(SpringLayout.WEST, lblCadencia, 0, SpringLayout.WEST, registroArea);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblCadencia, -6, SpringLayout.NORTH, separator);
		panel.add(lblCadencia);
		
		SpinnerNumberModel scadenciamodel = new SpinnerNumberModel(0, 0, 120, 1);
		
		sCadencia = new JSpinner(scadenciamodel);
		sl_panel.putConstraint(SpringLayout.WEST, sCadencia, 13, SpringLayout.EAST, lblCadencia);
		sl_panel.putConstraint(SpringLayout.EAST, tDistancia, -136, SpringLayout.WEST, sCadencia);
		sl_panel.putConstraint(SpringLayout.SOUTH, sCadencia, -6, SpringLayout.NORTH, separator);
		panel.add(sCadencia);
		
		SpinnerNumberModel spinhonmodel = new SpinnerNumberModel(0, 0, VariablesDeContexto.PINHONES.length, 1);
		
		sPinhon = new JSpinner(spinhonmodel);
		sl_panel.putConstraint(SpringLayout.NORTH, lblPin, 6, SpringLayout.NORTH, sPinhon);
		sl_panel.putConstraint(SpringLayout.WEST, sPinhon, 0, SpringLayout.WEST, sCadencia);
		sl_panel.putConstraint(SpringLayout.SOUTH, sPinhon, -37, SpringLayout.NORTH, sCadencia);
		sl_panel.putConstraint(SpringLayout.EAST, sPinhon, 0, SpringLayout.EAST, sCadencia);
		panel.add(sPinhon);
		
		JButton btnFrenarEnSeco = new JButton("Frenar en seco");
		btnFrenarEnSeco.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnFrenarEnSeco, -5, SpringLayout.NORTH, lblPin);
		sl_panel.putConstraint(SpringLayout.WEST, btnFrenarEnSeco, 29, SpringLayout.EAST, sPinhon);
		panel.add(btnFrenarEnSeco);
		
		JButton btnFrenar = new JButton("Frenar");
		btnFrenar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		sl_panel.putConstraint(SpringLayout.NORTH, btnFrenar, -5, SpringLayout.NORTH, lblPin);
		sl_panel.putConstraint(SpringLayout.WEST, btnFrenar, 73, SpringLayout.EAST, btnFrenarEnSeco);
		panel.add(btnFrenar);
		
		JLabel lblDistancia_1 = new JLabel("Cantidad:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblDistancia_1, 0, SpringLayout.NORTH, lblDistancia);
		panel.add(lblDistancia_1);
		
		tCantidadFrenar = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, tCantidadFrenar, 658, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblDistancia_1, -6, SpringLayout.WEST, tCantidadFrenar);
		sl_panel.putConstraint(SpringLayout.NORTH, tCantidadFrenar, -6, SpringLayout.NORTH, lblDistancia);
		panel.add(tCantidadFrenar);
		tCantidadFrenar.setColumns(10);
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		sl_panel.putConstraint(SpringLayout.EAST, tCantidadFrenar, -8, SpringLayout.WEST, lblTiempo);
		sl_panel.putConstraint(SpringLayout.NORTH, lblTiempo, 0, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.EAST, lblTiempo, 0, SpringLayout.EAST, btnFrenar);
		panel.add(lblTiempo);
		
		tTiempoFrenar = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, tTiempoFrenar, -6, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, tTiempoFrenar, 6, SpringLayout.EAST, lblTiempo);
		sl_panel.putConstraint(SpringLayout.EAST, tTiempoFrenar, -92, SpringLayout.EAST, panel);
		tTiempoFrenar.setColumns(10);
		panel.add(tTiempoFrenar);
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		sl_panel.putConstraint(SpringLayout.EAST, sCadencia, -16, SpringLayout.WEST, lblPeriodo);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblPeriodo, -6, SpringLayout.NORTH, separator);
		panel.add(lblPeriodo);
		
		SpinnerNumberModel sModel = new SpinnerNumberModel(0.0,0.0 ,100.0,0.1);
		
		sPeriodo = new JSpinner(sModel);
		sl_panel.putConstraint(SpringLayout.EAST, lblPeriodo, -6, SpringLayout.WEST, sPeriodo);
		sl_panel.putConstraint(SpringLayout.WEST, sPeriodo, 515, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, sPeriodo, -357, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, sPeriodo, -6, SpringLayout.NORTH, separator);
		panel.add(sPeriodo);
		
		SpinnerNumberModel splatomodel = new SpinnerNumberModel(0, 0, VariablesDeContexto.PLATOS.length, 1);
		
		sPlato = new JSpinner(splatomodel);
		sl_panel.putConstraint(SpringLayout.WEST, sPlato, 0, SpringLayout.WEST, sCadencia);
		sl_panel.putConstraint(SpringLayout.SOUTH, sPlato, -4, SpringLayout.NORTH, sCadencia);
		sl_panel.putConstraint(SpringLayout.EAST, sPlato, 0, SpringLayout.EAST, sCadencia);
		panel.add(sPlato);
		
		JSeparator separator_2 = new JSeparator();
		sl_panel.putConstraint(SpringLayout.NORTH, separator_2, 6, SpringLayout.SOUTH, cbCiclistaActivo);
		sl_panel.putConstraint(SpringLayout.SOUTH, separator_2, -7, SpringLayout.NORTH, sPinhon);
		sl_panel.putConstraint(SpringLayout.NORTH, lblVelocidad, 30, SpringLayout.SOUTH, separator_2);
		sl_panel.putConstraint(SpringLayout.WEST, separator_2, 20, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, separator_2, 0, SpringLayout.EAST, separator);
		panel.add(separator_2);
		
		JLabel lbCiclistas = new JLabel("No hay ciclistas en carrera.");
		lbCiclistas.setEnabled(false);
		lbCiclistas.setVerifyInputWhenFocusTarget(false);
		lbCiclistas.setVisible(false);
		lbCiclistas.setForeground(Color.RED);
		sl_panel.putConstraint(SpringLayout.WEST, lbCiclistas, 6, SpringLayout.EAST, cbCiclistaActivo);
		sl_panel.putConstraint(SpringLayout.SOUTH, lbCiclistas, 0, SpringLayout.SOUTH, cbCiclistaActivo);
		panel.add(lbCiclistas);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		
		Canvas canvas = new Canvas();
		scrollPane.setViewportView(canvas);
		
		frame.setVisible(true);
	}
}
