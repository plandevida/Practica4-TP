package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sistema.controladores.ListenerComandos;
import sistema.controladores.ListenerEmpezarParar;
import sistema.controladores.ordenes.Dispatcher;
import sistema.manager.VariablesDeContexto;
import sistema.vista.Lienzo;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private JTextField txtReloj;
	private PanelCiclista panel;
	private PanelCiclista panel1;
	private PanelCiclista panel2;
	private PanelCiclista panel3;
	private JTextArea taComandos;
	private JTextArea taRegistro;
	
	private Dispatcher micomandero;
	
	private Lienzo canvas;

	/**
	 * Create the frame.
	 */
	public Ventana(Dispatcher comandero, Lienzo lienzo) {
		
		micomandero = comandero;
		canvas = lienzo;
		
		init();
		
		VariablesDeContexto.ANCHO_VENTANA = getWidth();
		VariablesDeContexto.ALTO_VENTANA = getHeight();
		
		VariablesDeContexto.ANCHO_LIENZO = VariablesDeContexto.ANCHO_VENTANA;
		VariablesDeContexto.ALTO_LIENZO = VariablesDeContexto.ALTO_VENTANA / 2;
	}
	
	/**
	 * Pone un mensaje en la ventana de log
	 * @param mensaje
	 */
	public void ponerEnLog(String mensaje) {
		
		ponerDatosEnVentana("log", mensaje);
	}
	
	/**
	 * Añade nueva información en un área de texto o crea una nueva
	 * si fuese necesario.
	 * 
	 * @param id Objeto que mostrará sus datos.
	 * @param mensajes Los datos formateados a poner en el área.
	 */
	public void ponerDatosEnVentana(String id, Object... mensajes) {
		
		try {
			switch(id) {
			case "ruloj":
				
				txtReloj.setText(mensajes[0] + "h " + mensajes[1] + "m " + mensajes[2] + "s " + mensajes[3] + "ms ");
				
				break;
			case "0 ciclista":
				
				String[] datos = (String[])mensajes;
				
				panel.setCiclistaData(VariablesDeContexto.COLORES[0], datos[0] + " " + datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]), Double.valueOf(datos[4]));
				
				break;
			case "1 ciclista":
				
				datos = (String[])mensajes;
				
				panel1.setCiclistaData(VariablesDeContexto.COLORES[1], datos[0] + " " + datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]), Double.valueOf(datos[4]));
				
				break;
			case "2 ciclista":
				
				datos = (String[])mensajes;
				
				panel2.setCiclistaData(VariablesDeContexto.COLORES[2], datos[0] + " " + datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]), Double.valueOf(datos[4]));
				
				break;
			case "3 ciclista":
				
				datos = (String[])mensajes;
				
				panel3.setCiclistaData(VariablesDeContexto.COLORES[3], datos[0] + " " + datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]), Double.valueOf(datos[4]));
				
				break;
			case "0 bicicleta":
				
				datos = (String[])mensajes;
				
				panel.setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
				break;
			case "1 bicicleta":
				
				datos = (String[])mensajes;
				
				panel1.setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
				break;
			case "2 bicicleta":
				
				datos = (String[])mensajes;
				
				panel2.setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
				break;
			case "3 bicicleta":
				
				datos = (String[])mensajes;
				
				panel3.setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
				break;
			case "ayudaMain":
				
				taRegistro.setText(taRegistro.getText() + "\n" + (String)mensajes[0]);
				
				break;
			case "log":
				
				taRegistro.setText(taRegistro.getText() + "\n" + (String)mensajes[0]);
				
				break;
			case "fincarrera":
				
				JOptionPane.showMessageDialog(this, (String)mensajes[0], "Aviso", JOptionPane.INFORMATION_MESSAGE);
				
				break;
			default:
			}
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
	}
	
	/**
	 * Construye el interfaz.
	 */
	private void init() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout());
		
		JPanel panelReloj = new JPanel();
		panelReloj.setPreferredSize(new Dimension(35, 35));
		panelReloj.setMinimumSize(new Dimension(35, 35));
		contentPane.add(panelReloj, BorderLayout.NORTH);
		SpringLayout sl_panelReloj = new SpringLayout();
		panelReloj.setLayout(sl_panelReloj);
		
		JLabel lblReloj = new JLabel("Reloj:");
		sl_panelReloj.putConstraint(SpringLayout.NORTH, lblReloj, 11, SpringLayout.NORTH, panelReloj);
		sl_panelReloj.putConstraint(SpringLayout.WEST, lblReloj, 518, SpringLayout.WEST, panelReloj);
		panelReloj.add(lblReloj);
		
		txtReloj = new JTextField();
		sl_panelReloj.putConstraint(SpringLayout.NORTH, txtReloj, 5, SpringLayout.NORTH, panelReloj);
		sl_panelReloj.putConstraint(SpringLayout.WEST, txtReloj, 558, SpringLayout.WEST, panelReloj);
		sl_panelReloj.putConstraint(SpringLayout.EAST, txtReloj, 726, SpringLayout.WEST, panelReloj);
		txtReloj.setMinimumSize(new Dimension(150, 28));
		txtReloj.setPreferredSize(new Dimension(150, 28));
		panelReloj.add(txtReloj);
		txtReloj.setColumns(10);
		
		JButton btnEmpezar = new JButton("Empezar");
		btnEmpezar.addActionListener(new ListenerEmpezarParar(micomandero,this, btnEmpezar));
		sl_panelReloj.putConstraint(SpringLayout.WEST, btnEmpezar, 10, SpringLayout.WEST, panelReloj);
		panelReloj.add(btnEmpezar);
		
		JPanel panelGlobal = new JPanel();
		panelGlobal.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(panelGlobal, BorderLayout.CENTER);

		JPanel panelsuperior = new JPanel();
		panelsuperior.setLayout(new BorderLayout());
		panelGlobal.add(panelsuperior);
		
		JPanel panelCiclistas = new JPanel();
		panelsuperior.add(panelCiclistas, BorderLayout.CENTER);
		panelCiclistas.setLayout(new GridLayout(1, 4, 0, 0));
		
		panel = new PanelCiclista(micomandero, this);
		panelCiclistas.add(panel);
		
		panel1 = new PanelCiclista(micomandero, this);
		panelCiclistas.add(panel1);
		
		panel2 = new PanelCiclista(micomandero, this);
		panelCiclistas.add(panel2);
		
		panel3 = new PanelCiclista(micomandero, this);
		panelCiclistas.add(panel3);
		
		JPanel panelComandos = new JPanel();
		panelComandos.setPreferredSize(new Dimension(10, 110));
		panelComandos.setMaximumSize(new Dimension(32767, 150));
		panelsuperior.add(panelComandos, BorderLayout.SOUTH);
		panelComandos.setLayout(new GridLayout(1, 2, 0, 0));
		
		taComandos = new JTextArea();
		taComandos.setBorder(new TitledBorder("Comandos"));
		taComandos.setAutoscrolls(true);
		taComandos.addKeyListener(new ListenerComandos(micomandero, this));
		
		JScrollPane scrollpane = new JScrollPane(taComandos);
		panelComandos.add(scrollpane);
		
		taRegistro = new JTextArea();
		taRegistro.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		taRegistro.setAutoscrolls(true);
		taRegistro.setEditable(false);
		
		JScrollPane scrollpaner = new JScrollPane(taRegistro);
		panelComandos.add(scrollpaner);
		
		panelGlobal.add(canvas);
		
		setVisible(true);
	}
}
