package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import sistema.controladores.ListenerCadenciaPeriodo;
import sistema.controladores.ListenerFrenar;
import sistema.controladores.ListenerPinhon;
import sistema.controladores.ListenerPlato;
import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.parseadores.ParseadorComandos;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.manager.Presentador;
import sistema.manager.VariablesDeContexto;

import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

/**
 * Panel con los datos del ciclista, parte de la vista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
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
	
	private Dispatcher micomandero;

	/**
	 * Create the panel.
	 */
	public PanelCiclista(Dispatcher comandero) {
		
		micomandero = comandero;
		
		init();
	}
	
	/**
	 * Obtiene el id (numero de mallot) del ciclista.
	 * 
	 * @return Id del ciclista asociado al panel.
	 */
	public String getIdCiclista() {
		return tnombreCiclista.getText().substring(0, 1);
	}
	
	/**
	 * Obtiene la cantidad que debe frenar el ciclista.
	 * 
	 * @return Cantidad a frenar
	 */
	public Integer getCantidadFrenado() {

		Integer cantidad = null;
		
		try {
			cantidad = Integer.valueOf(tCantidad.getText());
			
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
		
		return cantidad;
	}
	
	/**
	 * Obtien el plato del ciclista.
	 * 
	 * @return Plato del ciclista.
	 */
	public Integer getPlato() {
		return (Integer)sPlato.getValue();
	}
	
	/**
	 * Obtiene el piñón del ciclista.
	 * 
	 * @return Piñón del ciclista.
	 */
	public Integer getPinhon() {
		return (Integer)sPlato.getValue();
	}
	
	/**
	 * Obtiene la cadencia del ciclista.
	 * 
	 * @return Cadencia del ciclista.
	 */
	public Integer getCadencia() {
		return (Integer)sCadencia.getValue();
	}
	
	/**
	 * Obtiene el periodo del ciclista.
	 * 
	 * @return El periodo del ciclista.
	 */
	public Double getPeriodo() {
		return (Double)sPeriodo.getValue();
	}
	
	/**
	 * Obtiene el tiempo de frenado para el ciclista.
	 * 
	 * @return El tiempo de frenado.
	 */
	public Integer getTiempoFrenado() {
		
		Integer tiempo = null;
		
		try {
			tiempo = Integer.valueOf(tTiempo.getText());
			
		} catch (NumberFormatException ne) {
			ne.printStackTrace();
		}
		
		return tiempo;
	}
	
	/**
	 * Configura los datos de un ciclista en la vista.
	 * 
	 * @param nombre Nombre de ciclista con id (0 Pepe).
	 * @param fuerza Fuerza actual del ciclista.
	 * @param cadencia Cadencia actual del ciclista.
	 * @param periodo Periodo de pedalada del ciclista.
	 */
	public void setCiclistaData(String nombre, Integer fuerza, Integer cadencia, Double periodo) {
		
		System.out.println("Poniendo datos ciclista");
		
		tnombreCiclista.setText(nombre);
		PBfuerza.setValue(fuerza);
		sCadencia.setValue(cadencia);
		sPeriodo.setValue(periodo);
	}
	
	/**
	 * Configura los datos de la bicicleta de un ciclista.
	 * 
	 * @param velocidad Velocidad de la bicicleta.
	 * @param distancia Espacio recorrido de la bicicleta.
	 * @param pinhon Piñón actual de la bicicleta.
	 * @param plato Plato actual de la bicicleta.
	 */
	public void setBicicletaData(String velocidad, String distancia, Integer pinhon, Integer plato) {
		
		System.out.println("Poniendo datos bicicleta");
		
		tVelocidad.setText(velocidad);
		tDistancia.setText(distancia);
		sPinhon.setValue(pinhon);
		sPlato.setValue(plato);
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
		
		SpinnerNumberModel sCadenciaModel = new SpinnerNumberModel(0, 0, 120, 5);
		sCadencia = new JSpinner(sCadenciaModel);
		// Añadimos el controlador para cambiar cadencia y periodo
		sCadencia.addChangeListener(new ListenerCadenciaPeriodo(micomandero, this));
		panel_2.add(sCadencia, "4, 2");
		
		JLabel lblPlato = new JLabel("Plato:");
		panel_2.add(lblPlato, "6, 2");
		
		SpinnerNumberModel sPlatoModel = new SpinnerNumberModel(0, 0, VariablesDeContexto.PLATOS.length, 1);
		sPlato = new JSpinner(sPlatoModel);
		sPlato.setPreferredSize(new Dimension(45, 28));
		// Añadimos el controlador para cambiar de plato
		sPlato.addChangeListener(new ListenerPlato(micomandero, this));
		panel_2.add(sPlato, "8, 2");
		
		JButton btnFrenar = new JButton("Frenar");
		btnFrenar.addActionListener(new ListenerFrenar(micomandero, this, false));
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		panel_2.add(lblPeriodo, "2, 4");
		
		SpinnerNumberModel sPeriodoModel = new SpinnerNumberModel(0, 0.0, VariablesDeContexto.MAX_CADENCIA-5d, 0.5);
		sPeriodo = new JSpinner(sPeriodoModel);
		// Añadimos el controlador para cambiar cadencia y periodo
		sPeriodo.addChangeListener(new ListenerCadenciaPeriodo(micomandero, this));
		panel_2.add(sPeriodo, "4, 4");
		
		JLabel lblPin = new JLabel("Piñón:");
		panel_2.add(lblPin, "6, 4");
		
		SpinnerNumberModel sPinhonModel = new SpinnerNumberModel(0, 0, VariablesDeContexto.PINHONES.length, 1);
		sPinhon = new JSpinner(sPinhonModel);
		sPinhon.setPreferredSize(new Dimension(45, 28));
		// Añadimos el controlador para cambiar de piñón
		sPinhon.addChangeListener(new ListenerPinhon(micomandero, this));
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
		// Añadimos el controlador para frenar
		btnFs.addActionListener(new ListenerFrenar(micomandero, this, true));
		panel_2.add(btnFs, "2, 8");
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		panel_2.add(lblTiempo, "4, 8, right, default");
		
		tTiempo = new JTextField();
		panel_2.add(tTiempo, "6, 8, fill, default");
		tTiempo.setColumns(10);
	}
	
	public static void main(String[] args) {
		
		JFrame v = new JFrame("Prueba Listeners");
		
		ParseadorComandos p = new ParseadorComandos();
		
		List<Ciclista> ciclistas = new ArrayList<>();
		List<ObjetosConSalidaDeDatos> objetosamostarenvista = new ArrayList<>();
		
		Map<Integer, MiViento> vientoporhoras = new HashMap<Integer, MiViento>();
		
		Presentador presentadorsistema = new Presentador(ciclistas, objetosamostarenvista, vientoporhoras, Reloj.getInstance(), p.getOrdenes());
		
		PanelCiclista pa = new PanelCiclista(new Dispatcher(presentadorsistema, p));
		
		v.getContentPane().add(pa);
		
		pa.setCiclistaData("0 Alfredo", 100, 60, 1d);
		
		v.pack();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
	}
}
