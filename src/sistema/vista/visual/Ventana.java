package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.VariablesDeContexto;
import sistema.vista.Lienzo;
import javax.swing.JLabel;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private List<PanelCiclista> listaNombreCiclista;
	private JTextField txtReloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new Ventana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Ventana() {
		
		listaNombreCiclista = new ArrayList<PanelCiclista>();
		
		init();
		
		VariablesDeContexto.SYN_GUI = true;
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
				
				txtReloj.setText(mensajes[0] + "h " + mensajes[1] + "m " + mensajes[2] + "s");
				
				break;
			case "0 ciclista":
				
				String[] datos = (String[])mensajes;
				
				listaNombreCiclista.get(0).setCiclistaData(datos[0], Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), Double.valueOf(datos[3]));
				
				break;
			case "1 ciclista":
				
				datos = (String[])mensajes;
				
				listaNombreCiclista.get(1).setCiclistaData(datos[0], Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), Double.valueOf(datos[3]));
				
				break;
			case "2 ciclista":
				
				datos = (String[])mensajes;
				
				listaNombreCiclista.get(2).setCiclistaData(datos[0], Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), Double.valueOf(datos[3]));
				
				break;
			case "3 ciclista":
				
				datos = (String[])mensajes;
				
				listaNombreCiclista.get(3).setCiclistaData(datos[0], Integer.valueOf(datos[1]), Integer.valueOf(datos[2]), Double.valueOf(datos[3]));
				
				break;
			case "0 bicicleta":
				
				datos = (String[])mensajes;
				
				listaNombreCiclista.get(0).setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
				break;
			case "1 bicicleta":
				
				datos = (String[])mensajes;
				
				listaNombreCiclista.get(1).setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
				break;
			case "2 bicicleta":
				
				datos = (String[])mensajes;
				
				listaNombreCiclista.get(2).setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
				break;
			case "3 bicicleta":
				
				datos = (String[])mensajes;
				
				listaNombreCiclista.get(3).setBicicletaData(datos[0], datos[1], Integer.valueOf(datos[2]), Integer.valueOf(datos[3]));
				
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
		contentPane.add(panelReloj, BorderLayout.NORTH);
		
		JLabel lblReloj = new JLabel("Reloj:");
		panelReloj.add(lblReloj);
		
		txtReloj = new JTextField();
		panelReloj.add(txtReloj);
		txtReloj.setColumns(10);
		
		JPanel panelGlobal = new JPanel();
		panelGlobal.setLayout(new GridLayout(2, 1, 0, 0));
		contentPane.add(panelGlobal, BorderLayout.CENTER);

		JPanel panelsuperior = new JPanel();
		panelsuperior.setLayout(new BorderLayout());
		panelGlobal.add(panelsuperior);
		
		JPanel panelCiclistas = new JPanel();
		panelsuperior.add(panelCiclistas, BorderLayout.CENTER);
		panelCiclistas.setLayout(new GridLayout(1, 4, 0, 0));
		
		PanelCiclista panel = new PanelCiclista();
		panelCiclistas.add(panel);
		
		listaNombreCiclista.add(panel);
		
		PanelCiclista panel1 = new PanelCiclista();
		panelCiclistas.add(panel1);
		
		listaNombreCiclista.add(panel1);
		
		PanelCiclista panel2 = new PanelCiclista();
		panelCiclistas.add(panel2);
		
		listaNombreCiclista.add(panel2);
		
		PanelCiclista panel3 = new PanelCiclista();
		panelCiclistas.add(panel3);
		
		listaNombreCiclista.add(panel3);
		
		JPanel panelComandos = new JPanel();
		panelComandos.setPreferredSize(new Dimension(10, 110));
		panelComandos.setMaximumSize(new Dimension(32767, 150));
		panelsuperior.add(panelComandos, BorderLayout.SOUTH);
		panelComandos.setLayout(new GridLayout(1, 2, 0, 0));
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new TitledBorder("Comandos"));
		panelComandos.add(textArea);
		
		JTextArea textArea_1 = new JTextArea();
		textArea_1.setBorder(new TitledBorder(null, "Registro", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panelComandos.add(textArea_1);
		
		Lienzo canvas = new Lienzo(new ArrayList<Ciclista>());
		panelGlobal.add(canvas);
		
		setVisible(true);
	}
}
