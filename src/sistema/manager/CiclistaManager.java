package sistema.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.parseadores.parser.ParseadorCarrera;
import sistema.controladores.parseadores.parser.ParseadorComandos;
import sistema.entidades.carretera.tramocarreraciclista.TramoCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.entrada.lectura.Lector;
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
	
	// Entidades del sistema.
//	private Bicicleta bicicleta0;
//	private Bicicleta bicicleta1;
//	private Bicicleta bicicleta2;
//	private Bicicleta bicicleta3;

	private FactoresExternos factoresexternos;

	private Reloj reloj;

	// Vistas del sistema.
	private VentanaJL ventana;
	private FormateadorDatosVista formateador;

	// Subsitemas del sistema.
	private Dispatcher dispatcher;
	private ParseadorComandos parser;
	private Presentador presentador;

	private Lector lectorconfiguracion;
	
	private NameGenerator generadordenombres;

	/**
	 * Carga la carretera de la carrera ciclista.
	 */
	private void cargarConfiguracion() {
		lectorconfiguracion = new Lector(
				VariablesDeContexto.DEFAULT_CONFIG_PATH, true);

		String configuracioncarreraciclista = lectorconfiguracion
				.cargarFicheroCompelto();

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
		
		parser = new ParseadorComandos();
		
		presentador = new Presentador(ciclistas, listasalidadatos, mapameteorologico, reloj, parser.getOrdenes());
		dispatcher = new Dispatcher(presentador, parser);
		
		// Bicicletas para los ciclistas.
//		bicicleta0 = new Bicicleta();
//		bicicleta1 = new Bicicleta();
//		bicicleta2 = new Bicicleta();
//		bicicleta3 = new Bicicleta();
//
//		bicicletas.add(bicicleta0);
//		bicicletas.add(bicicleta1);
//		bicicletas.add(bicicleta2);
//		bicicletas.add(bicicleta3);

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
//		ciclistas.add(new Ciclista("Pamela", 1, 120, bicicleta0, 0.5, reloj,60,80));
//		ciclistas.add(new Ciclista("Pedro", 2, 60, bicicleta1, 0.8, reloj,70,100));
//		ciclistas.add(new Ciclista("Ana", 3, 30, bicicleta2, 1.5, reloj,55,75));
//		ciclistas.add(new Ciclista("Juan", 4, 90, bicicleta3, 0.5, reloj,75,50));

		// Se registran los elementos con salida de datos en una lista.
		listasalidadatos.add(reloj);
//		listasalidadatos.add(bicicleta0);
//		listasalidadatos.add(bicicleta1);
//		listasalidadatos.add(bicicleta2);
//		listasalidadatos.add(bicicleta3);

		// Se registran los elementos ejecutables en una lista.
		listaejecutables.add(reloj);

		for (Ciclista ciclista : ciclistas) {
			listaejecutables.add(ciclista);
		}
		
		Lienzo lienzo = new Lienzo(ciclistas);
		
		ventana = new VentanaJL(dispatcher, lienzo);

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

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Integer numerociclistas = null;
		String ficherocomandos = null;
		Integer unidadtiempo = null;
		Integer cambiodeplato = null;
		Integer cambiodepinhon = null;
		Double radiorueda = null;
		try {
			numerociclistas = Integer.valueOf(args[1]);
			ficherocomandos = args[2];
			unidadtiempo = Integer.valueOf(args[3]);
			cambiodeplato = Integer.valueOf(args[4]);
			cambiodepinhon = Integer.valueOf(args[4]);
			radiorueda = Double.valueOf(args[5]);
			
		} catch (NumberFormatException ne) {
			System.out.println("Datos de entrada incorrectos.");
		}
		
		VariablesDeContexto.MAX_CICLISTAS = numerociclistas;
		VariablesDeContexto.UNIDAD_TIEMPO = unidadtiempo;
		VariablesDeContexto.CUSTOM_FILE_COMMAND_PATH = ficherocomandos;
		
		CiclistaManager manager = new CiclistaManager();

		manager.cargarConfiguracion();
		manager.iniciar();
		manager.ejecutar();
		manager.finalizar();
	}
}
