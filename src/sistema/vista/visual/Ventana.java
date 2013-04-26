package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.text.JTextComponent;

import sistema.controladores.parseador.parser.ParseadorComandos;

/**
 * Vista del sistema en un componente de swing.
 * Con esta clase se puede mostrar una cantidad
 * "infinita" de datos en áreas de texto.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Ventana extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private List<JTextComponent> componenetescreados;
	
	private ParseadorComandos parsercomandos;
	
	private JPanel panelciclistasylog;
	private JPanel panelciclistas;
	private JTextField camporeloj;
	
	private JTextField campocomandos;
	
	private RelojSwing relojGrafico;
	
	public Ventana(ParseadorComandos parser) {
		parsercomandos = parser;
		componenetescreados = new ArrayList<JTextComponent>();
		
		Init();
	}
	
	/**
	 * Crea la interfaz de la ventana y sus componentes.
	 */
	private void Init() {
		
		setTitle("Carrera ciclista");
		setPreferredSize(new Dimension(700, 500));
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		panelciclistasylog = new JPanel();
		panelciclistasylog.setLayout(new GridLayout(2, 1, 10, 10));
		
		panelciclistas = new JPanel();
		panelciclistas.setLayout(new GridLayout(0, 2, 10, 10));
		panelciclistas.setBackground(Color.WHITE);
		
		panelciclistasylog.add(panelciclistas);
		panelciclistasylog.add(crearLogeador());
		
		panelPrincipal.add(crearRelojero(), BorderLayout.NORTH);
		panelPrincipal.add(panelciclistasylog, BorderLayout.CENTER);
		panelPrincipal.add(crearComandero(), BorderLayout.SOUTH);
		
		setContentPane(panelPrincipal);

		setVisible(true);
		pack();
		
		crearReloj().setVisible(true);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setAlwaysOnTop(true);
	}
	
	/**
	 * Crea la caja de texto para el registro del sistema.
	 * @return
	 */
	public JPanel crearLogeador() {
		
		JPanel panellog = new JPanel();
		
		JTextArea arealog = new JTextArea();
		arealog.setBorder(new TitledBorder("Log"));
		
		panellog.add(arealog);
		
		pack();
		
		return panel;
	}
	
	/**
	 * crea la caja de texto para introducir comandos al sistema.
	 * @return
	 */
	private JPanel crearRelojero() {
		JPanel panel = new JPanel();
		
		JLabel relojetiqueta = new JLabel("Reloj: ");
		
		camporeloj = new JTextField();
		camporeloj.setBounds(new Rectangle(120, 50));
		camporeloj.setName("ruloj");
		
		componenetescreados.add(camporeloj);
		
		panel.add(relojetiqueta);
		panel.add(camporeloj);
		
		return panel;
	}
	
	JPanel panel = new JPanel();
	/**
	 * Crea el campo de texto donde se insertan los comando al sistema.
	 * 
	 * @return Panel con el cuadro de texto.
	 */
	private JPanel crearComandero() {
		panel.setPreferredSize(new Dimension(120, 60));
		
		JLabel campocomandosetiqueta = new JLabel("Comandos: ");
	
		campocomandos = new JTextField();
		campocomandos.setSize(new Dimension(120, 60));
		campocomandos.setText("Escriba un comando");
		
		campocomandos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				campocomandos.setText("");
			}
		});
		
		campocomandos.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String comando = ((JTextField) e.getSource()).getText();
				
				parsercomandos.parse(comando);
//				parsercomandos.dispatch();
			}
		});
		
		panel.add(campocomandosetiqueta);
		panel.add(campocomandos);
		
		return panel;
	}
	
	private JDialog crearReloj() {
		JDialog dialogo = new JDialog();
		dialogo.setBounds(this.getWidth() + 10, this.getHeight(), 370, 390);
		
		relojGrafico = new RelojSwing();
		
		dialogo.add(relojGrafico);
		
		dialogo.setResizable(false);
		
		return dialogo;
	}
	
//	@Override
//	public void paint(Graphics g) {
//		relojGrafico.repaint();
//	}
	
	/**
	 * Añade una nueva área de texto.
	 * El nombre del área de texto es importante
	 * para determinar si se añade una nueva o se
	 * modifican sus datos.
	 * 
	 * @param nombre Nombre del area de texto.
	 */
	private void anadirTextArea(String nombre) {
		
		JTextArea nuevaTextArea = new JTextArea();
		nuevaTextArea.setName(nombre);
		nuevaTextArea.setPreferredSize(new Dimension(100, 200));
		nuevaTextArea.setEditable(false);
		nuevaTextArea.setBorder(new TitledBorder(nombre));
		
		componenetescreados.add(nuevaTextArea);
		
		panelciclistas.add(nuevaTextArea);
		panelciclistas.validate();
	}
	
	/**
	 * Añade nueva información en un área de texto o crea una nueva
	 * si fuese necesario.
	 * 
	 * @param id Objeto que mostrará sus datos.
	 * @param mensaje Los datos formateados a poner en el área.
	 */
	public void ponerDatosEnVentana(String id, Object... mensaje) {
		JTextComponent componenteexistente = null;
		
		switch(id) {
		case "ruloj":
			
			int hora = (Integer) mensaje[0];
			int minuto = (Integer) mensaje[1];
			int segundo = (Integer) mensaje[2];
			
			relojGrafico.setTime(segundo, minuto, hora);
			
			break;
		default:
			for (JTextComponent componente : componenetescreados) {
				if (componente.getName().equals(id)) {
					componente.setText( (String) mensaje[0]);
					
					componenteexistente = componente;
				}
			}
			
			if (componenteexistente == null) {
//				anadirTextArea(id);
//				
//				ponerDatosEnVentana(id, mensaje);
			}
			break;
		}
	}
	
	public void limpia() {
		for (JTextComponent area : componenetescreados) {
			area.setText("");
		}
	}
}
