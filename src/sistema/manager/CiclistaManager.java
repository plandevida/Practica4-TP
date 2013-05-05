package sistema.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.parseadores.parser.ParseadorCarrera;
import sistema.controladores.parseadores.parser.ParseadorComandos;
import sistema.entidades.carretera.tramocarreraciclista.TramoCiclista;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.entrada.lectura.Lector;
import sistema.factoresexternos.FactoresExternos;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.vista.visual.FormateadorDatosVista;
import sistema.vista.visual.Ventana;

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
	private Map<Integer, MiViento> mapameteorologico;

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
	private Presentador presentador;

	Lector lectorConfiguracion;

	/**
	 * Carga la carretera de la carrera ciclista.
	 */
	private void cargarConfiguracion() {
		lectorConfiguracion = new Lector(
				VariablesDeContexto.DEFAULT_CONFIG_PATH, true);

		String configuracioncarreraciclista = lectorConfiguracion
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
	 * Inicializa el contexto de la aplicación.
	 */
	public void iniciar() {

		listaejecutables = new ArrayList<ObjetosQueSeEjecutan>();
		listasalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();

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

		ciclistas.add(new Ciclista("Pamela", 1, 120, bicicleta0, 0.5, reloj));
		ciclistas.add(new Ciclista("Pedro", 2, 60, bicicleta1, 0.8, reloj));
		ciclistas.add(new Ciclista("Ana", 3, 30, bicicleta2, 1.5, reloj));
		ciclistas.add(new Ciclista("Juan", 4, 90, bicicleta3, 0.5, reloj));

		// Se registran los elementos con salida de datos en una lista.
		listasalidadatos.add(reloj);
		listasalidadatos.add(bicicleta0);
		listasalidadatos.add(bicicleta1);
		listasalidadatos.add(bicicleta2);
		listasalidadatos.add(bicicleta3);
		
		presentador = new Presentador(ciclistas, listasalidadatos, mapameteorologico);

		dispatcher = new Dispatcher(presentador);
		parser = new ParseadorComandos(dispatcher);

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

		while (reloj.getHoras() < 2) {
			
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
		CiclistaManager manager = new CiclistaManager();

		manager.cargarConfiguracion();
		manager.iniciar();
		manager.ejecutar();
		manager.finalizar();
	}
}
