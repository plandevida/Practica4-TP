package sistema.manager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.SwingUtilities;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.parseadores.parser.ParseadorCarrera;
import sistema.controladores.parseadores.parser.ParseadorComandos;
import sistema.entidades.carretera.tramocarreraciclista.TramoCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.entrada.lectura.LectorManager;
import sistema.entrada.lectura.generadornombres.NameGenerator;
import sistema.factoresexternos.FactoresExternos;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.vista.Lienzo;
import sistema.vista.VentanaJL;
import sistema.vista.visual.FormateadorDatosVista;

/**
 * Clase principal que inicia la aplicación.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class CiclistaManager {

	// Listas con los elemenos del contexto del sistema
	private List<ObjetosQueSeEjecutan> listaejecutables;
	private List<ObjetosConSalidaDeDatos> listasalidadatos;
	private Map<Integer, TramoCiclista> carreteradecarreraciclsta;
	private List<Ciclista> ciclistas;
	private List<Bicicleta> bicicletas;
	private Map<Integer, Map<MiViento, Double>> mapameteorologico;

	private FactoresExternos factoresexternos;

	// Medidor de tiempo del sistema
	private Reloj reloj;

	// Vistas del sistema.
	private VentanaJL ventana;
	private Lienzo lienzo;
	private FormateadorDatosVista formateador;

	// Subsitemas del sistema.
	private Dispatcher dispatcher;
	private ParseadorComandos parser;
	private Presentador presentador;

	private LectorManager lectorconfiguracion;
	
	private NameGenerator generadordenombres;

	private void crearGUI() {
		parser = new ParseadorComandos();
		
		presentador = new Presentador(ciclistas, listasalidadatos, mapameteorologico, reloj, parser.getOrdenes());
		dispatcher = new Dispatcher(presentador, parser);
		
		lienzo = new Lienzo(ciclistas);
		
		try {
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					
					ventana = new VentanaJL(dispatcher, lienzo);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Carga la carretera de la carrera ciclista.
	 */
	private void cargarConfiguracion() {
		lectorconfiguracion = new LectorManager(VariablesDeContexto.DEFAULT_FILE_CONFIG_PATH, true);

		String configuracioncarreraciclista = lectorconfiguracion.cargarFicheroCompelto();

		construirCarretera(configuracioncarreraciclista);
	}

	/**
	 * Construye el mapa con la configuración de la carrera ciclista.
	 * 
	 * @param datos
	 *            Cadena con el contenido de la carrera ciclista.
	 */
	private void construirCarretera(String datos) {

		carreteradecarreraciclsta = new HashMap<Integer, TramoCiclista>();

		ParseadorCarrera parseadorcarrera = new ParseadorCarrera(
				carreteradecarreraciclsta);

		parseadorcarrera.parse(datos);
	}
	
	/**
	 * Construye el mapa meteorológico de la carrera ciclista.
	 */
	private void construirMapaDelTiempo() {
		// TODO cargar de fichero el mapa de horas-viento-velocidad
		
		mapameteorologico = new HashMap<Integer, Map<MiViento, Double>>();
	}

	/**
	 * Inicializa el contexto de la aplicación.
	 */
	public void iniciar() {

		listaejecutables = new ArrayList<ObjetosQueSeEjecutan>();
		listasalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();

		reloj = new Reloj();
		ciclistas = new ArrayList<Ciclista>();
		bicicletas = new ArrayList<Bicicleta>();

		factoresexternos = new FactoresExternos(bicicletas, carreteradecarreraciclsta);

		try {
			// Generador de nombres basado en silabas, con prefijos y sufijos.
			generadordenombres = new NameGenerator(VariablesDeContexto.DEFAULT_SYLLABLE_FILE_PATH);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Generación aleatoria de ciclistas.
		for (int i = 0; i < VariablesDeContexto.MAX_CICLISTAS; i++) {
			
			Bicicleta bicicleta = new Bicicleta();
			bicicleta.setId(i);
			
			int cadencia = new Random().nextInt(120);
			int peso = new Random().nextInt(70);
			int fuerza = new Random().nextInt(100);
			
			ciclistas.add(new Ciclista(generadordenombres.compose(3), i, cadencia, bicicleta, 0.5, reloj, peso, fuerza));
			
			bicicletas.add(bicicleta);
			listasalidadatos.add(bicicleta);
		}

		// Se registran los elementos con salida de datos en una lista.
		listasalidadatos.add(reloj);

		// Se registran los elementos ejecutables en una lista.
		listaejecutables.add(reloj);

		for (Ciclista ciclista : ciclistas) {
			listaejecutables.add(ciclista);
		}

		formateador = new FormateadorDatosVista(listasalidadatos, ventana);

		listaejecutables.add(formateador);
		listaejecutables.add(factoresexternos);
		listaejecutables.add(dispatcher);
	}

	/**
	 * Ejecuta la aplicación.
	 */
	public void ejecutar() {
		
		int miliseg = 0;
		
		while (reloj.getHoras() < 2) {
			
			if (miliseg!= (int)(Calendar.getInstance().getTimeInMillis() % 10)){
				
				for (ObjetosQueSeEjecutan objetoejecutable : listaejecutables) {
					objetoejecutable.ejecuta();
				}
				
				miliseg = (int)(Calendar.getInstance().getTimeInMillis() % 10);
			}
		}
	}

	/**
	 * Finaliza el contexto de la aplicación.
	 */
	public void finalizar() {
		lectorconfiguracion.finalizarLecturas();
	}
	
	private void ayuda() {
		ventana.ponerDatosEnVentana("#ayudaMain", "");
	}
	
	private void prepararArgumentos(String[] args) {
		
		Integer numerociclistas = null;
		String ficherocomandos = null;
		Integer unidadtiempo = null;
		Integer[] cambiodeplato = null;
		Integer[] cambiodepinhon = null;
		Double radiorueda = null;
		
		if (args.length > 0) {
			try {
				numerociclistas = Integer.valueOf(args[0]);
				ficherocomandos = args[1];
				unidadtiempo = Integer.valueOf(args[2]);
				
				Integer numeroplatos = Integer.valueOf(args[3]);
				
				cambiodeplato = new Integer[numeroplatos];
				
				int i = 0;
				while ( i < numeroplatos ) {
					cambiodeplato[i] = Integer.valueOf(args[3+i+1]);
					
					i++;
				}
				
				Integer numeropinhones = Integer.valueOf(++i);
 				
				cambiodepinhon = new Integer[numeropinhones];
				
	 		 	while ( i < numeropinhones ) {
	 		 		cambiodepinhon[i] = Integer.valueOf(args[i+1]);
				 
	 		 		i++;
	 		 	}
	 		 	
				radiorueda = Double.valueOf(args[5]);
				
			} catch (NumberFormatException ne) {
				System.out.println("Datos de entrada incorrectos.");
			}
		} else {
			ayuda();
		}
		
		VariablesDeContexto.MAX_CICLISTAS = numerociclistas;
		VariablesDeContexto.UNIDAD_TIEMPO = unidadtiempo;
		VariablesDeContexto.FILE_COMMAND_PATH = ficherocomandos;
		VariablesDeContexto.RADIO_RUEDA = radiorueda;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		CiclistaManager manager = new CiclistaManager();
		
		manager.crearGUI();
		
		manager.prepararArgumentos(args);
		
		manager.cargarConfiguracion();
		manager.iniciar();
		manager.ejecutar();
		manager.finalizar();
	}
}
