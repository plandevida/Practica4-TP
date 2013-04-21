package sistema.vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import net.miginfocom.swing.MigLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

public class a {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					a window = new a();
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
	public a() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 934, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JPanel paneldatos = new JPanel();
		GridBagConstraints gbc_paneldatos = new GridBagConstraints();
		gbc_paneldatos.gridheight = 3;
		gbc_paneldatos.insets = new Insets(0, 0, 5, 0);
		gbc_paneldatos.fill = GridBagConstraints.BOTH;
		gbc_paneldatos.gridx = 0;
		gbc_paneldatos.gridy = 0;
		panel.add(paneldatos, gbc_paneldatos);
		
		JPanel panelcarrera = new JPanel();
		GridBagConstraints gbc_panelcarrera = new GridBagConstraints();
		gbc_panelcarrera.gridheight = 3;
		gbc_panelcarrera.fill = GridBagConstraints.BOTH;
		gbc_panelcarrera.gridx = 0;
		gbc_panelcarrera.gridy = 3;
		panel.add(panelcarrera, gbc_panelcarrera);
	}

}
