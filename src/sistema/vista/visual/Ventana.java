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
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.vista.Lienzo;

public class Ventana extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	
	private List<PanelCiclista> listaNombreCiclista;

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
				break;
			case "ciclista":
				
				
				
				break;
			default:
			}
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
	}
	
	private void init() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1220, 900);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 0, 0));

		JPanel panelsuperior = new JPanel();
		panelsuperior.setLayout(new BorderLayout());
		contentPane.add(panelsuperior);
		
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
		contentPane.add(canvas);
		
		setVisible(true);
	}
}
