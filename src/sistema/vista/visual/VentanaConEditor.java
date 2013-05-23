package sistema.vista.visual;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.SpringLayout;
import javax.swing.border.TitledBorder;
import java.awt.Canvas;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class VentanaConEditor {

	private JFrame frame;
	private JTextField tVelocidad;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaConEditor window = new VentanaConEditor();
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
	public VentanaConEditor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 925, 685);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new GridLayout(2, 1, 0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JComboBox cbCiclistaActivo = new JComboBox();
		sl_panel.putConstraint(SpringLayout.NORTH, cbCiclistaActivo, 10, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, cbCiclistaActivo, 102, SpringLayout.WEST, panel);
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
		sl_panel.putConstraint(SpringLayout.NORTH, lblVelocidad, 24, SpringLayout.SOUTH, lblCiclsitaActivo);
		sl_panel.putConstraint(SpringLayout.WEST, lblVelocidad, 10, SpringLayout.WEST, panel);
		panel.add(lblVelocidad);
		
		tVelocidad = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, tVelocidad, 43, SpringLayout.EAST, lblVelocidad);
		sl_panel.putConstraint(SpringLayout.EAST, tVelocidad, -661, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, cbCiclistaActivo, 0, SpringLayout.EAST, tVelocidad);
		tVelocidad.setEditable(false);
		panel.add(tVelocidad);
		tVelocidad.setColumns(10);
		
		JLabel lblFuerza = new JLabel("Fuerza:");
		sl_panel.putConstraint(SpringLayout.WEST, lblFuerza, 0, SpringLayout.WEST, lblCiclsitaActivo);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblFuerza, -35, SpringLayout.NORTH, separator);
		panel.add(lblFuerza);
		
		JProgressBar progressBar = new JProgressBar();
		sl_panel.putConstraint(SpringLayout.SOUTH, tVelocidad, -44, SpringLayout.NORTH, progressBar);
		sl_panel.putConstraint(SpringLayout.NORTH, progressBar, 0, SpringLayout.NORTH, lblFuerza);
		sl_panel.putConstraint(SpringLayout.EAST, progressBar, 0, SpringLayout.EAST, cbCiclistaActivo);
		panel.add(progressBar);
		
		JLabel lblDistancia = new JLabel("Distancia:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblDistancia, 13, SpringLayout.SOUTH, lblVelocidad);
		sl_panel.putConstraint(SpringLayout.WEST, lblDistancia, 0, SpringLayout.WEST, lblCiclsitaActivo);
		panel.add(lblDistancia);
		
		textField = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField, -3, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, textField, 0, SpringLayout.WEST, cbCiclistaActivo);
		sl_panel.putConstraint(SpringLayout.EAST, textField, 0, SpringLayout.EAST, cbCiclistaActivo);
		textField.setEditable(false);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblPin = new JLabel("Pión:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPin, 0, SpringLayout.NORTH, lblVelocidad);
		sl_panel.putConstraint(SpringLayout.EAST, lblPin, 0, SpringLayout.EAST, textArea);
		panel.add(lblPin);
		
		JLabel lblPlato = new JLabel("Plato:");
		sl_panel.putConstraint(SpringLayout.NORTH, lblPlato, 0, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, lblPlato, 0, SpringLayout.WEST, lblPin);
		panel.add(lblPlato);
		
		JLabel lblCadencia = new JLabel("Cadencia:");
		sl_panel.putConstraint(SpringLayout.WEST, lblCadencia, 0, SpringLayout.WEST, lblPin);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblCadencia, 0, SpringLayout.SOUTH, lblFuerza);
		panel.add(lblCadencia);
		
		JSpinner spinner = new JSpinner();
		sl_panel.putConstraint(SpringLayout.NORTH, spinner, -3, SpringLayout.NORTH, lblFuerza);
		panel.add(spinner);
		
		JSpinner spinner_1 = new JSpinner();
		sl_panel.putConstraint(SpringLayout.WEST, spinner, 0, SpringLayout.WEST, spinner_1);
		sl_panel.putConstraint(SpringLayout.EAST, spinner, 0, SpringLayout.EAST, spinner_1);
		sl_panel.putConstraint(SpringLayout.WEST, spinner_1, 34, SpringLayout.EAST, lblPin);
		sl_panel.putConstraint(SpringLayout.EAST, spinner_1, -547, SpringLayout.EAST, panel);
		panel.add(spinner_1);
		
		JSpinner spinner_2 = new JSpinner();
		sl_panel.putConstraint(SpringLayout.NORTH, spinner_2, 75, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, spinner_2, 30, SpringLayout.EAST, lblPlato);
		sl_panel.putConstraint(SpringLayout.EAST, spinner_2, -547, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.SOUTH, spinner_1, -7, SpringLayout.NORTH, spinner_2);
		panel.add(spinner_2);
		
		JButton btnFrenarEnSeco = new JButton("Frenar en seco");
		sl_panel.putConstraint(SpringLayout.WEST, btnFrenarEnSeco, 35, SpringLayout.EAST, spinner_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, btnFrenarEnSeco, 0, SpringLayout.SOUTH, lblVelocidad);
		panel.add(btnFrenarEnSeco);
		
		JButton btnFrenar = new JButton("Frenar");
		sl_panel.putConstraint(SpringLayout.NORTH, btnFrenar, 0, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, btnFrenar, 0, SpringLayout.WEST, btnFrenarEnSeco);
		panel.add(btnFrenar);
		
		JLabel lblDistancia_1 = new JLabel("cantidad");
		sl_panel.putConstraint(SpringLayout.WEST, lblDistancia_1, 6, SpringLayout.EAST, btnFrenar);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblDistancia_1, 0, SpringLayout.SOUTH, textField);
		panel.add(lblDistancia_1);
		
		textField_1 = new JTextField();
		sl_panel.putConstraint(SpringLayout.NORTH, textField_1, 0, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.WEST, textField_1, 6, SpringLayout.EAST, lblDistancia_1);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField_1, 20, SpringLayout.NORTH, lblDistancia);
		sl_panel.putConstraint(SpringLayout.EAST, textField_1, 55, SpringLayout.EAST, lblDistancia_1);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTiempo = new JLabel("tiempo");
		sl_panel.putConstraint(SpringLayout.NORTH, lblTiempo, 4, SpringLayout.NORTH, btnFrenar);
		panel.add(lblTiempo);
		
		textField_2 = new JTextField();
		sl_panel.putConstraint(SpringLayout.WEST, textField_2, 620, SpringLayout.WEST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, textField_2, -240, SpringLayout.EAST, panel);
		sl_panel.putConstraint(SpringLayout.EAST, lblTiempo, -6, SpringLayout.WEST, textField_2);
		sl_panel.putConstraint(SpringLayout.SOUTH, textField_2, -60, SpringLayout.NORTH, separator);
		textField_2.setColumns(10);
		panel.add(textField_2);
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		sl_panel.putConstraint(SpringLayout.WEST, lblPeriodo, 0, SpringLayout.WEST, btnFrenarEnSeco);
		sl_panel.putConstraint(SpringLayout.SOUTH, lblPeriodo, 0, SpringLayout.SOUTH, lblFuerza);
		panel.add(lblPeriodo);
		
		JSpinner spinner_3 = new JSpinner();
		sl_panel.putConstraint(SpringLayout.NORTH, spinner_3, -3, SpringLayout.NORTH, lblFuerza);
		sl_panel.putConstraint(SpringLayout.WEST, spinner_3, 6, SpringLayout.EAST, lblPeriodo);
		sl_panel.putConstraint(SpringLayout.EAST, spinner_3, 43, SpringLayout.EAST, lblPeriodo);
		panel.add(spinner_3);
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane);
		
		Canvas canvas = new Canvas();
		scrollPane.setViewportView(canvas);
	}
}
