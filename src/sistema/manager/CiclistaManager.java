package sistema.manager;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import javax.swing.SwingUtilities;

import sistema.controladores.ordenes.Dispatcher;
import sistema.controladores.parseadores.ParseadorCarrera;
import sistema.controladores.parseadores.ParseadorComandos;
import sistema.entidades.carretera.tramocarreraciclista.Curva;
import sistema.entidades.carretera.tramocarreraciclista.TramoCarrera;
import sistema.entidades.personas.ciclistas.Ciclista;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.entrada.lectura.LectorManager;
import sistema.entrada.lectura.generadornombres.NameGenerator;
import sistema.factoresexternos.Eolo;
import sistema.factoresexternos.FactoresExternos;
import sistema.factoresexternos.viento.MiViento;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.vista.Lienzo;
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
	private Map<Integer, TramoCarrera> carreteradecarreraciclsta;
	private List<Ciclista> ciclistas;
	private List<Bicicleta> bicicletas;
	private Map<Integer, MiViento> mapameteorologico;
	private List<Curva> listacurvas;
	private FactoresExternos factoresexternos;

	// Medidor de tiempo del sistema
	private Reloj reloj;

	// Vistas del sistema.
	private Ventana ventana;
	private Lienzo lienzo;
	private FormateadorDatosVista formateador;

	// Subsitemas del sistema.
	private Dispatcher dispatcher;
	private ParseadorComandos parser;
	private Presentador presentador;
	private LectorManager lectorconfiguracion;
	
	private NameGenerator generadordenombres;

	private void crearGUI() {
		
		reloj = Reloj.getInstance();
		
		parser = new ParseadorComandos();
		
		ciclistas = new ArrayList<Ciclista>();
		
		listasalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();

		listacurvas = new ArrayList<Curva>();
		
		presentador = new Presentador(ciclistas, listasalidadatos, mapameteorologico, parser.getOrdenes(), reloj, listacurvas);

		formateador = new FormateadorDatosVista(listasalidadatos);
		
		dispatcher = new Dispatcher(presentador, parser, formateador);
		
		lienzo = new Lienzo(ciclistas, carreteradecarreraciclsta, listacurvas);
		
		try {
			// Con este método forzamos la "sincronización" de la vista.
			SwingUtilities.invokeAndWait(new Runnable() {
				
				@Override
				public void run() {
					
					ventana = new Ventana(dispatcher, lienzo);
				}
			});
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		formateador.setVista(ventana);
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
	 * @param datos Cadena con el contenido de la carrera ciclista.
	 */
	private void construirCarretera(String datos) {

		carreteradecarreraciclsta = new TreeMap<Integer, TramoCarrera>();

		ParseadorCarrera parseadorcarrera = new ParseadorCarrera(carreteradecarreraciclsta);

		parseadorcarrera.parse(datos);

		VariablesDeContexto.LONGITUD_CARRERA = parseadorcarrera.getLongitudCarrera();
		VariablesDeContexto.META = VariablesDeContexto.LONGITUD_CARRERA;
	}
	
	/**
	 * Construye el mapa meteorológico de la carrera ciclista.
	 */
	private void construirMapaDelTiempo() {
		
		mapameteorologico = new HashMap<Integer, MiViento>();
		
		VariablesDeContexto.velocidadvientoinicial = 0;
		VariablesDeContexto.direcionvientoinicial = MiViento.NULO;
	}
	
	/**
	 * Configura los elementos necesarios para la vista.
	 */
	public void iniciar() {
		
		listaejecutables = new ArrayList<ObjetosQueSeEjecutan>();
		
		presentador.setListacurvas(listacurvas);
		
		reloj = Reloj.getInstance();

		bicicletas = new ArrayList<Bicicleta>();
		
		try {
			// Generador de nombres basado en silabas, con prefijos y sufijos.
			generadordenombres = new NameGenerator(VariablesDeContexto.DEFAULT_SYLLABLE_FILE_PATH);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Generación aleatoria de ciclistas.
		for (int i = 0; i < VariablesDeContexto.MAX_CICLISTAS; i++) {
			
			Bicicleta bicicleta = new Bicicleta();
			
			int cadencia = new Random().nextInt(90)+30;
			int peso = new Random().nextInt(40)+40;
			
			float periodo = ((float) 60/cadencia)*10;
			int fuerza = 100;
			double tiempopedalada =(double) (new Random().nextInt((int)periodo)+3)/10;
			
			Ciclista ciclista = new Ciclista(generadordenombres.compose(3), i, cadencia, bicicleta,tiempopedalada, reloj, peso, fuerza);
			
			ciclistas.add(ciclista);
			bicicletas.add(bicicleta);
			
			listaejecutables.add(ciclista);
			
			listasalidadatos.add(ciclista);
			listasalidadatos.add(bicicleta);
		} 
		
		Eolo eolo = new Eolo(ciclistas, reloj, mapameteorologico);
		factoresexternos = new FactoresExternos(ciclistas, carreteradecarreraciclsta,listacurvas, eolo, null,null);
		
//		lectorconfiguracion = new LectorManager(VariablesDeContexto.FILE_COMMAND_PATH, true);
		
		// Se registran los elementos con salida de datos en una lista.
		listasalidadatos.add(reloj);

		listaejecutables.add(formateador);
		listaejecutables.add(factoresexternos);
//		listaejecutables.add(lectorconfiguracion);
		listaejecutables.add(dispatcher);
		listaejecutables.add(lienzo);
		
		// Se registran los elementos ejecutables en una lista.
		listaejecutables.add(reloj);
	}

	/**
	 * Ejecuta la aplicación.
	 */
	public void ejecutar() {
		
		int miliseg = 0;
		
		int milisegundospropiosantes = 0;
		int milisegundospropiosdespues = 100;
		
		while (reloj.getHoras() < 2) {

			if (miliseg != (int)(Calendar.getInstance().getTimeInMillis() % 10)) {

				// Solo se ejecuta nuestro "thread" si desde la última ejecución ha pasado como mínimo el valos de UNIDADO_TIEMPO.
				if ( Math.abs(milisegundospropiosdespues-milisegundospropiosantes) >= VariablesDeContexto.UNIDAD_TIEMPO) {

					// Obtenemos el momento de ejecución.
					milisegundospropiosantes = reloj.getMilisegundos();

					for (ObjetosQueSeEjecutan objetoejecutable : listaejecutables) {

						objetoejecutable.ejecuta();
					}

					// Obtenemos el momento de después de haber ejecutado.
					milisegundospropiosdespues = reloj.getMilisegundos();
				}
				else {
					// Si no ha pasado el tiempo suficiente, seguimos contando impulsos.
					reloj.ejecuta();
					milisegundospropiosdespues = reloj.getMilisegundos();
				}

				miliseg = (int)(Calendar.getInstance().getTimeInMillis() % 10);
			}
		}
		
//		while (reloj.getHoras() < 2) {
//			
//			if (miliseg != (int)(Calendar.getInstance().getTimeInMillis() % 10)) {
//				
//				
//					// Solo se ejecuta nuestro "thread" si desde la última ejecución ha pasado como mínimo el valos de UNIDADO_TIEMPO.
//					if ( Math.abs(milisegundospropiosdespues-milisegundospropiosantes) >= VariablesDeContexto.UNIDAD_TIEMPO) {
//						
//						// Obtenemos el momento de ejecución.
//						milisegundospropiosantes = reloj.getMilisegundos();
//						
//						if ( VariablesDeContexto.CARRERA ) {
//							for (ObjetosQueSeEjecutan objetoejecutable : listaejecutables) {
//								
//								objetoejecutable.ejecuta();
//							}
//							
//							// Obtenemos el momento de después de haber ejecutado.
////							milisegundospropiosdespues = reloj.getMilisegundos();
//						}
//						else {
//							dispatcher.ejecuta();
//							formateador.ejecuta();
//							if ( VariablesDeContexto.EMPEZADA ) {
//								reloj.ejecuta();
////								milisegundospropiosdespues = reloj.getMilisegundos();
//							}
//						}
//				}
//				else {
//					// Si no ha pasado el tiempo suficiente, seguimos contando impulsos.
//					if ( VariablesDeContexto.EMPEZADA ) {
//						reloj.ejecuta();
//					}
////					milisegundospropiosdespues = reloj.getMilisegundos();
//				}
//				dispatcher.ejecuta();
//				milisegundospropiosdespues = reloj.getMilisegundos();
//	
//				miliseg = (int)(Calendar.getInstance().getTimeInMillis() % 10);
//			}
//		}
	}

	/**
	 * Finaliza el contexto de la aplicación.
	 */
	public void finalizar() {
		lectorconfiguracion.finalizarLecturas();
	}
	
	/**
	 * Muestra en la vista la ayuda de como invocar al CiclistaManager.
	 */
	private void ayuda() {
		
		ventana.ponerDatosEnVentana("ayudaMain", "Aviso: No se proporcionaron argumentos, se usarán los que valores por defecto.\nCiclistaManager <número_ciclistas> <fichero_comandos> <unidad_tiempo> <número_platos> <dientes_plato (separados por espacios)> <número_piñones> <dientes_piñones (separados por espacios)> <radio_rueda>");
	}
	
	/**
	 * Obtiene los datos necesarios de los argumentos introducidos al sistema.
	 * Si no se hubiesen introducido estos datos correcatmente se usarán los 
	 * valores del sistema por defecto.
	 * 
	 * @param args Argumentos del sistema.
	 */
	private void prepararArgumentos(String[] args) {
		
 		Integer numerociclistas = null;
		String ficherocomandos = null;
		Integer unidadtiempo = null;
		Integer[] cambiodeplato = null;
		Integer[] cambiodepinhon = null;
		Double radiorueda = null;
		
		// Si no hay ningún argumento no se parsea nada.
		if (args.length > 0) {
			try {
				// Indice que recorre el array de argumentos.
				int indicedeargumentos = 0;
				
				// El número de platos de las bicicletas
				Integer numeroplatos = Integer.valueOf(args[3]);
				
				// El número de piñones de las bicicletas.
				//												(argumentos precedentes)	(el número de platos)
				//                                                               ^              ^
				Integer numeropinhones = Integer.valueOf(args[indicedeargumentos+3+numeroplatos+1]);
				
				// Se comprueba que los argumentos sean en número de ellos esperado.
				//				(argumentos 1ºs) (dientes y		  (dientes y  (radio de la rueda)
				//								  nº de platos)	 nº piñones)
				//					^				 ^					^   ---------^
				if ( args.length == 3 + numeroplatos+1 + numeropinhones+1 + 1) {

					numerociclistas = Integer.valueOf(args[indicedeargumentos]);
					ficherocomandos = args[++indicedeargumentos];
					unidadtiempo = Integer.valueOf(args[++indicedeargumentos]);
					
					// Array que contiene los dientes de los platos.
					cambiodeplato = new Integer[numeroplatos];
					
					indicedeargumentos++;
					
					// Indice que determina hasta donde son dientes de platos en el array de argumentos.
					int topedientes = indicedeargumentos+numeroplatos;
					
					// Indice del array de dientes de platos.
					int indicerelativo = 0;
					while ( indicedeargumentos < topedientes ) {
						
						cambiodeplato[indicerelativo] = Integer.valueOf(args[++indicedeargumentos]);
						
						indicerelativo++;
					}
					
					cambiodepinhon = new Integer[numeropinhones];
					
					indicedeargumentos++;
					
					// Indice que determina hasta donde son dientes de piónes en el array de argumentos.
					topedientes = indicedeargumentos+numeropinhones;
					
					// Indice del array de dientes de platos.
					indicerelativo = 0;
					while ( indicedeargumentos < topedientes ) {
						
						cambiodepinhon[indicerelativo] = Integer.valueOf(args[++indicedeargumentos]);
						
						indicerelativo++;
					}
		 		 	
					radiorueda = Double.valueOf(args[++indicedeargumentos]);
				}
				
				VariablesDeContexto.MAX_CICLISTAS = numerociclistas;
				VariablesDeContexto.UNIDAD_TIEMPO = unidadtiempo;
				VariablesDeContexto.FILE_COMMAND_PATH = ficherocomandos;
				VariablesDeContexto.RADIO_RUEDA = radiorueda;
			
			} catch (NumberFormatException ne) {
				System.out.println("Datos de entrada incorrectos.");
			}
		} else {
			ayuda();
		}
	}

	/**
	 * @param args Argumentos del sistema, esciba help para obtener una ayuda.
	 */
	public static void main(String[] args) {
		
		CiclistaManager manager = new CiclistaManager();
		
		manager.cargarConfiguracion();
		manager.construirMapaDelTiempo();
		
		manager.crearGUI();
		
		manager.prepararArgumentos(args);
		
		manager.iniciar();
		manager.ejecutar();
		manager.finalizar();
	}
}
