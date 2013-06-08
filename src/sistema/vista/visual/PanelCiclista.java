package sistema.vista.visual;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.text.MaskFormatter;

import sistema.controladores.ListenerCadenciaPeriodo;
import sistema.controladores.ListenerFrenar;
import sistema.controladores.ListenerPinhon;
import sistema.controladores.ListenerPlato;
import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.parseadores.ParseadorComandos;
import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.manager.Presentador;

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
	private JFormattedTextField tnombreCiclista;
	private JFormattedTextField tVelocidad;
	private JFormattedTextField tDistancia;
	private JFormattedTextField tCantidad;
	private JFormattedTextField tTiempo;
	private JProgressBar PBfuerza;
	
	private Dispatcher micomandero;
	private JFormattedTextField ftCadencia;
	private JFormattedTextField ftPeriodo;
	private JFormattedTextField ftPlato;
	private JFormattedTextField ftPinhon;

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
	public Double getCantidadFrenado() {

		Double cantidad = null;
		
		try {
			cantidad = Double.valueOf(tCantidad.getText());
			
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
		return (Integer)ftPlato.getValue();
	}
	
	/**
	 * Pone el plato en el panel.
	 * @param plato
	 */
	public void setPlato(Integer plato) {
		ftPlato.setValue(plato);
	}
	
	/**
	 * Obtiene el piñón del ciclista.
	 * 
	 * @return Piñón del ciclista.
	 */
	public Integer getPinhon() {
		return (Integer)ftPinhon.getValue();
	}
	
	public void setPinhon(Integer pinhon) {
		ftPinhon.setValue(pinhon);
	}
	
	/**
	 * Obtiene la cadencia del ciclista.
	 * 
	 * @return Cadencia del ciclista.
	 */
	public Integer getCadencia() {
		return (Integer)ftCadencia.getValue();
	}
	
	/**
	 * Obtiene el periodo del ciclista.
	 * 
	 * @return El periodo del ciclista.
	 */
	public Double getPeriodo() {
		return (Double)ftPeriodo.getValue();
	}
	
	/**
	 * Obtiene el tiempo de frenado para el ciclista.
	 * 
	 * @return El tiempo de frenado.
	 */
	public Double getTiempoFrenado() {
		
		Double tiempo = null;
		
		try {
			tiempo = Double.valueOf(tTiempo.getText());
			
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
		
		tnombreCiclista.setText(nombre);
		PBfuerza.setValue(fuerza);
		ftCadencia.setValue(cadencia);
		ftPeriodo.setValue(periodo);
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
		
		tVelocidad.setText(velocidad);
		tDistancia.setText(distancia + " m");
		ftPinhon.setValue(pinhon);
		ftPlato.setValue(plato);
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
		
		MaskFormatter mask = null;
		MaskFormatter maskvelocidad = null;
		try {
			mask = new MaskFormatter("#.##");
			maskvelocidad = new MaskFormatter("#.## m/s");
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		JLabel lblNombre = new JLabel("Nombre:");
		panel_1.add(lblNombre, "2, 2, right, default");
		
		tnombreCiclista = new JFormattedTextField();
		panel_1.add(tnombreCiclista, "4, 2, fill, default");
		tnombreCiclista.setColumns(10);
		
		JLabel lblVelocidad = new JLabel("Velocidad:");
		panel_1.add(lblVelocidad, "2, 4, right, default");
		
		tVelocidad = new JFormattedTextField(maskvelocidad);
		panel_1.add(tVelocidad, "4, 4, fill, default");
		tVelocidad.setColumns(10);
		
		JLabel lblDistancia = new JLabel("Distancia:");
		panel_1.add(lblDistancia, "2, 6, right, default");
		
		tDistancia = new JFormattedTextField();
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
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_COLSPEC,},
			new RowSpec[] {
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,
				FormFactory.RELATED_GAP_ROWSPEC,
				FormFactory.DEFAULT_ROWSPEC,}));
		
		JLabel lblCadencia = new JLabel("Cadencia:");
		panel_2.add(lblCadencia, "2, 2, right, default");
		
		ftCadencia = new JFormattedTextField();
		ftCadencia.setMinimumSize(new Dimension(5, 28));
		ftCadencia.setPreferredSize(new Dimension(5, 28));
		ftCadencia.setColumns(10);
		ftCadencia.setEditable(false);
		panel_2.add(ftCadencia, "4, 2, fill, default");
		
		JPanel panelBotonesCadencia = new JPanel();
		panel_2.add(panelBotonesCadencia, "6, 2, fill, fill");
		panelBotonesCadencia.setLayout(new BorderLayout(0, 0));
		
		JButton btnMasCadencia = new JButton("+");
		btnMasCadencia.setMinimumSize(new Dimension(20, 15));
		btnMasCadencia.setPreferredSize(new Dimension(10, 15));
		btnMasCadencia.setActionCommand("+ cadencia");
		btnMasCadencia.addActionListener(new ListenerCadenciaPeriodo(micomandero, this));
		panelBotonesCadencia.add(btnMasCadencia, BorderLayout.NORTH);
		
		JButton btnMenosCadencia = new JButton("-");
		btnMenosCadencia.setPreferredSize(new Dimension(10, 15));
		btnMenosCadencia.setMinimumSize(new Dimension(20, 15));
		btnMenosCadencia.setActionCommand("- cadencia");
		btnMenosCadencia.addActionListener(new ListenerCadenciaPeriodo(micomandero, this));
		panelBotonesCadencia.add(btnMenosCadencia, BorderLayout.SOUTH);
		
		JLabel lblPlato = new JLabel("Plato:");
		panel_2.add(lblPlato, "8, 2, center, default");
		
		JButton btnFrenar = new JButton("Frenar");
		btnFrenar.addActionListener(new ListenerFrenar(micomandero, this, false));
		
		ftPlato = new JFormattedTextField();
		ftPlato.setPreferredSize(new Dimension(100, 28));
		ftPlato.setEditable(false);
		panel_2.add(ftPlato, "10, 2, fill, default");
		
		JPanel panelBotonoesPlato = new JPanel();
		panel_2.add(panelBotonoesPlato, "12, 2, fill, fill");
		panelBotonoesPlato.setLayout(new BorderLayout(0, 0));
		
		JButton btnMasPlato = new JButton("+");
		btnMasPlato.setPreferredSize(new Dimension(10, 15));
		btnMasPlato.setMinimumSize(new Dimension(20, 15));
		btnMasPlato.setActionCommand("+");
		btnMasPlato.addActionListener(new ListenerPlato(micomandero, this));
		panelBotonoesPlato.add(btnMasPlato, BorderLayout.NORTH);
		
		JButton btnMenosPlato = new JButton("-");
		btnMenosPlato.setPreferredSize(new Dimension(10, 15));
		btnMenosPlato.setMinimumSize(new Dimension(20, 15));
		btnMenosPlato.setActionCommand("-");
		btnMenosPlato.addActionListener(new ListenerPlato(micomandero, this));
		panelBotonoesPlato.add(btnMenosPlato, BorderLayout.SOUTH);
		
		JLabel lblPeriodo = new JLabel("Periodo:");
		panel_2.add(lblPeriodo, "2, 4, right, default");
		
		ftPeriodo = new JFormattedTextField();
		ftPeriodo.setPreferredSize(new Dimension(5, 28));
		ftPeriodo.setMinimumSize(new Dimension(5, 28));
		ftPeriodo.setColumns(10);
		ftPeriodo.setEditable(false);
		panel_2.add(ftPeriodo, "4, 4, fill, default");
		
		JPanel panelBotonesPeriodo = new JPanel();
		panel_2.add(panelBotonesPeriodo, "6, 4, fill, fill");
		panelBotonesPeriodo.setLayout(new BorderLayout(0, 0));
		
		JButton btnMasPeriodo = new JButton("+");
		btnMasPeriodo.setPreferredSize(new Dimension(10, 15));
		btnMasPeriodo.setMinimumSize(new Dimension(20, 15));
		btnMasPeriodo.setActionCommand("+ periodo");
		btnMasPeriodo.addActionListener(new ListenerCadenciaPeriodo(micomandero, this));
		panelBotonesPeriodo.add(btnMasPeriodo, BorderLayout.NORTH);
		
		JButton btnMenosPeriodo = new JButton("-");
		btnMenosPeriodo.setPreferredSize(new Dimension(10, 15));
		btnMenosPeriodo.setMinimumSize(new Dimension(20, 15));
		btnMenosPeriodo.setActionCommand("- periodo");
		btnMenosPeriodo.addActionListener(new ListenerCadenciaPeriodo(micomandero, this));
		panelBotonesPeriodo.add(btnMenosPeriodo, BorderLayout.SOUTH);
		
		JLabel lblPin = new JLabel("Piñón:");
		panel_2.add(lblPin, "8, 4, center, default");
		
		ftPinhon = new JFormattedTextField();
		ftPinhon.setPreferredSize(new Dimension(100, 28));
		ftPinhon.setEditable(false);
		panel_2.add(ftPinhon, "10, 4, fill, default");
		
		JPanel panel = new JPanel();
		panel_2.add(panel, "12, 4, fill, fill");
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnMasPinhon = new JButton("+");
		btnMasPinhon.setPreferredSize(new Dimension(10, 15));
		btnMasPinhon.setMinimumSize(new Dimension(20, 15));
		btnMasPinhon.setActionCommand("+");
		btnMasPinhon.addActionListener(new ListenerPinhon(micomandero, this));
		panel.add(btnMasPinhon, BorderLayout.NORTH);
		
		JButton btnMenosPinhon = new JButton("-");
		btnMenosPinhon.setPreferredSize(new Dimension(10, 15));
		btnMenosPinhon.setMinimumSize(new Dimension(20, 15));
		btnMenosPinhon.setActionCommand("-");
		btnMenosPinhon.addActionListener(new ListenerPinhon(micomandero, this));
		panel.add(btnMenosPinhon, BorderLayout.SOUTH);
		
		btnFrenar.setPreferredSize(new Dimension(80, 29));
		btnFrenar.setMinimumSize(new Dimension(70, 29));
		panel_2.add(btnFrenar, "2, 6");
		
		JLabel lblCantidad = new JLabel("Cantidad:");
		panel_2.add(lblCantidad, "4, 6, 4, 1, center, default");
		
		tCantidad = new JFormattedTextField(mask);
		panel_2.add(tCantidad, "8, 6, fill, default");
		tCantidad.setColumns(10);
		
		JButton btnFs = new JButton("Frenar S");
		btnFs.setPreferredSize(new Dimension(80, 29));
		btnFs.setMinimumSize(new Dimension(70, 29));
		btnFs.setToolTipText("Frena en seco al ciclista");
		// Añadimos el controlador para frenar
		btnFs.addActionListener(new ListenerFrenar(micomandero, this, true));
		panel_2.add(btnFs, "2, 8");
		
		JLabel lblTiempo = new JLabel("Tiempo:");
		panel_2.add(lblTiempo, "4, 8, 4, 1, center, default");
		
		tTiempo = new JFormattedTextField(mask);
		panel_2.add(tTiempo, "8, 8, fill, default");
		tTiempo.setColumns(10);
	}
	
	public static void main(String[] args) {
		
		JFrame v = new JFrame("Prueba Listeners");
		
		ParseadorComandos p = new ParseadorComandos();
		
		List<Ciclista> ciclistas = new ArrayList<>();
		List<ObjetosConSalidaDeDatos> objetosamostarenvista = new ArrayList<>();
		
		Map<Integer, MiViento> vientoporhoras = new HashMap<Integer, MiViento>();
		
		Presentador presentadorsistema = new Presentador(ciclistas, objetosamostarenvista, vientoporhoras, p.getOrdenes(), Reloj.getInstance(), new ArrayList<Curva>());
		
		FormateadorDatosVista formateador = null;
		
		Dispatcher d = new Dispatcher(presentadorsistema, p, formateador);
		
		Ventana vv = new Ventana(d);
		
		formateador = new FormateadorDatosVista(objetosamostarenvista, vv);
		
		PanelCiclista pa = new PanelCiclista(d);
		
		v.getContentPane().add(pa);
		
		pa.setCiclistaData("0 Alfredo", 100, 60, 1d);
		
		v.pack();
		v.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		v.setVisible(true);
	}
}
