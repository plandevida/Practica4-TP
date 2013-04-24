package sistema.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.controlador.ordenes.Dispatcher;
import sistema.controladores.parseador.parser.ParseadorCarrera;
import sistema.controladores.parseador.parser.ParseadorComandos;
import sistema.entidades.carretera.tramocarreraciclista.TramoCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.entrada.lectura.Lector;
import sistema.factoresexternos.FactoresExternos;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.vista.visual.FormateadorDatosVista;
import sistema.vista.visual.Ventana;

/**
 * Clase principal que inicia la aplicación.
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Manager {
	
	// Contexto de los ficheros del sistema.
	public static final String CONFIG_FOLDER_PATH = "resources/configuracion/";
	public static final String DEFAULT_CONFIG_PATH = "resources/configuracion/carrera";
	
	// Listas con los elemenos del contexto del sistema
	private List<ObjetosQueSeEjecutan> listaejecutables;
	private List<ObjetosConSalidaDeDatos> listasalidadatos;
	private Map<Integer, TramoCiclista> carreteradecarreraciclsta;
	private List<Ciclista> ciclistas;
	private List<Bicicleta> bicicletas;
	
	// Entidades del sistema.
	private Bicicleta bicicleta0;
	private Bicicleta bicicleta1;
	private Bicicleta bicicleta2;
	private Bicicleta bicicleta3;
	
	private FactoresExternos factoresexternos;
	
	private Reloj reloj;
	
	// Vistas del sistema.
	private Ventana ventana;
	private FormateadorDatosVista formateador;
	
	// Subsitemas del sistema.
	private Dispatcher dispatcher;
	private ParseadorComandos parser;
	
	Lector lectorConfiguracion;
	
	/**
	 * Carga la carretera de la carrera ciclista.
	 */
	private void cargarConfiguracion() {
		lectorConfiguracion = new Lector(DEFAULT_CONFIG_PATH, true);
		
		String configuracioncarreraciclista = lectorConfiguracion.cargarFicheroCompelto();
		
		construirCarretera(configuracioncarreraciclista);
	}
	
	/**
	 * Construye el mapa con la configuración de la carrera ciclista.
	 * 
	 * @param datos Cadena con el contenido de la carrera ciclista.
	 */
	private void construirCarretera(String datos) {
		
		carreteradecarreraciclsta = new HashMap<Integer, TramoCiclista>();
		
		ParseadorCarrera parseadorcarrera = new ParseadorCarrera(carreteradecarreraciclsta);
		
		parseadorcarrera.parse(datos);
	}
	
	/**
	 * Inicializa el contexto de la aplicación.
	 */
	public void iniciar() {
		
		listaejecutables = new ArrayList<ObjetosQueSeEjecutan>();
		listasalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();
		
		dispatcher = new Dispatcher();
		parser = new ParseadorComandos(dispatcher, listaejecutables);
		
		reloj = new Reloj();
		ciclistas = new ArrayList<Ciclista>();
		bicicletas = new ArrayList<Bicicleta>();
		ventana = new Ventana(parser);
		
		// Bicicletas para los ciclistas.
		bicicleta0 = new Bicicleta();
		bicicleta1 = new Bicicleta();
		bicicleta2 = new Bicicleta();
		bicicleta3 = new Bicicleta();
		
		bicicletas.add(bicicleta0);
		bicicletas.add(bicicleta1);
		bicicletas.add(bicicleta2);
		bicicletas.add(bicicleta3);
		
		factoresexternos = new FactoresExternos(bicicletas, carreteradecarreraciclsta);
		
		ciclistas.add(new Ciclista("Pamela", 1, 0.5, bicicleta0, reloj));
		ciclistas.add(new Ciclista("Pedro", 2, 1.5, bicicleta1, reloj));
		ciclistas.add(new Ciclista("Ana", 3, 1.0, bicicleta2, reloj));
		ciclistas.add(new Ciclista("Juan", 4, 0.75, bicicleta3, reloj));
		
		// Se registran los elementos con salida de datos en una lista.
		listasalidadatos.add(reloj);
		listasalidadatos.add(bicicleta0);
		listasalidadatos.add(bicicleta1);
		listasalidadatos.add(bicicleta2);
		listasalidadatos.add(bicicleta3);
		
		// Se registran los elementos ejecutables en una lista.
		listaejecutables.add(reloj);
		
		for (Ciclista ciclista : ciclistas) {
			listaejecutables.add(ciclista);
		}
		
		formateador = new FormateadorDatosVista(listasalidadatos, ventana);
		
		listaejecutables.add(formateador);
		listaejecutables.add(factoresexternos);
	}
	
	/**
	 * Ejecuta la aplicación.
	 */
	public void ejecutar() {
		
		while ( reloj.getHoras() < 2 ) {
		
			for (ObjetosQueSeEjecutan objetoejecutable : listaejecutables) {
				objetoejecutable.ejecuta();
			}
		}
	}
	
	/**
	 * Finaliza el contexto de la aplicación.
	 */
	public void finalizar() {
		lectorConfiguracion.finalizarLecturas();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Manager manager = new Manager();
		
		manager.cargarConfiguracion();
		manager.iniciar();
		manager.ejecutar();
		manager.finalizar();
	}
}
