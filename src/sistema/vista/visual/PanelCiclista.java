package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.manager.VariablesDeContexto;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class PanelCiclista extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField tnombreCiclista;
	private JTextField tVelocidad;
	private JTextField tDistancia;
	private JTextField tCantidad;
	private JTextField tTiempo;
	private JProgressBar PBfuerza;
	private JSpinner sCadencia;
	private JSpinner sPlato;
	private JSpinner sPeriodo;
	private JSpinner sPinhon;

	/**
	 * Create the panel.
	 */
	public PanelCiclista() {
		init();
	}
	
	public String getNombreCiclista() {
		return tnombreCiclista.getText();
	}
	
	public void setCiclistaData(Ciclista ciclista) {
		
		tnombreCiclista.setText(ciclista.getNombre());
		tVelocidad.setText( String.valueOf( ciclista.getBicicletamontada().getVelocidad() ) );
		tDistancia.setText( String.valueOf( ciclista.getBicicletamontada().getEspacioRecorrido() ) );
		PBfuerza.setValue( ciclista.getFuerzaAsInt() );
		sCadencia.setValue( ciclista.getCadencia() );
		sPlato.setValue( ciclista.getBicicletamontada().getPlatoactual() );
		sPinhon.setValue( ciclista.getBicicletamontada().getPinhonactual() );
		sPeriodo.setValue( ciclista.getTiempopedalada() );
	}

	/**
	 * Construye la interfaz.
	 */
	private void init() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		JScrollPane scrolPane = new JScrollPane(panel_1);
		add(scrolPane, BorderLayout.CENTER);
		panel_1.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblNombre = new JLabel("Nombre:");
		panel_1.add(lblNombre, "2, 2, right, default");
		
		tnombreCiclista = new JTextField();
		panel_1.add(tnombreCiclista, "4, 2, fill, default");
		tnombreCiclista.setColumns(10);
		
		JLabel lblVelocidad = new JLabel("Velocidad:");
		panel_1.add(lblVelocidad, "2, 4, right, default");
		
		tVelocidad = new JTextField();
		panel_1.add(tVelocidad, "4, 4, fill, default");
		tVelocidad.setColumns(10);
		
		JLabel lblDistancia = new JLabel("Distancia:");
		panel_1.add(lblDistancia, "2, 6, right, default");
		
		tDistancia = new JTextField();
		panel_1.add(tDistancia, "4, 6, fill, default");
		tDistancia.setColumns(10);
		
		JLabel lblFuerza = new JLabel("Fuerza:");
		panel_1.add(lblFuerza, "2, 8");
		
		PBfuerza = new JProgressBar();
		panel_1.add(PBfuerza, "4, 8");
		
		JPanel panel_2 = new JPanel();
		panel_2.setPreferredSize(new Dimension(10, 150));
		add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new FormLayout(new ColumnSpec[] {
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				FormFactory.DEFAULT_COLSPEC,
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblCadencia = new JLabel("Cadencia:");
		panel_2.add(lblCadencia, "2, 2");
		
		SpinnerNumberModel sCadenciaModel = new SpinnerNumberModel(0, 0.0, 120, 5);
		sCadencia = new JSpinner(sCadenciaModel);
		panel_2.add(sCadencia, "4, 2");
		
		JLabel lblPlato = new JLabel("Plato:");
		panel_2.add(lblPlato, "6, 2");
		
		SpinnerNumberModel sPlatoModel = new SpinnerNumberModel(0, 0, VariablesDeContexto.PLATOS.length, 1);
		sPlato = new JSpinner(sPlatoModel);
//		sPlato = new JSpinner();
		sPlato.setPreferredSize(new Dimension(45, 28));
		panel_2.add(sPlato, "8, 2");
		
		JButton btnFrenar = new JButton("Frenar");
		btnFrenar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		panel_2.add(lblPeriodo, "2, 4");
		
		SpinnerNumberModel sPeriodoModel = new SpinnerNumberModel(0, 0.0, VariablesDeContexto.MAX_CADENCIA-10, 1);
		sPeriodo = new JSpinner(sPeriodoModel);
		panel_2.add(sPeriodo, "4, 4");
		
		JLabel lblPin = new JLabel("Piñón:");
		panel_2.add(lblPin, "6, 4");
		
		SpinnerNumberModel sPinhonModel = new SpinnerNumberModel(0, 0, VariablesDeContexto.PINHONES.length, 1);
		sPinhon = new JSpinner(sPinhonModel);
		sPinhon.setPreferredSize(new Dimension(45, 28));
		panel_2.add(sPinhon, "8, 4");
		btnFrenar.setPreferredSize(new Dimension(78, 29));
		btnFrenar.setMinimumSize(new Dimension(78, 29));
		panel_2.add(btnFrenar, "2, 6");
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		panel_2.add(lblCantidad, "4, 6, right, default");
		
		tCantidad = new JTextField();
		panel_2.add(tCantidad, "6, 6, fill, default");
		tCantidad.setColumns(10);
		
		JButton btnFs = new JButton("Frenar S");
		btnFs.setToolTipText("Frena en seco al ciclista");
		panel_2.add(btnFs, "2, 8");
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		panel_2.add(lblTiempo, "4, 8, right, default");
		
		tTiempo = new JTextField();
		panel_2.add(tTiempo, "6, 8, fill, default");
		tTiempo.setColumns(10);
	}
}
