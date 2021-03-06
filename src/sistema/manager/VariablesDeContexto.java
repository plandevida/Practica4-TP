package sistema.manager;

import java.awt.Color;

import sistema.factoresexternos.viento.MiViento;

public class VariablesDeContexto {
	
	// Contexto de los ficheros del sistema.
	public static final String CONFIG_FOLDER_PATH = "resources/configuracion/";
	public static final String DEFAULT_FILE_CONFIG_PATH = "resources/configuracion/carrera";
	public static final String DEFAULT_SYLLABLE_FILE_PATH = "resources/miscelania/generaciondenombres/syllable";
	
	public static String FILE_COMMAND_PATH = "resources/instrucciones/comandos";

	// El array representa los piñones de la bicicleta con el indice
	// del array y su valor es el número de dientes del piñón
	public static int[] PINHONES = new int[] { 15, 20, 25, 30 };

	// El array representa los plato de la bicicleta con el indice
	// del array y su valor es el número de dientes del plato
	public static int[] PLATOS = new int[] { 30, 40, 50 };
	
	public static MiViento direcionvientoinicial = MiViento.NULO;
	public static double velocidadvientoinicial = 0d;
	public static double FUERZA_GRAVEDAD = 9.80665d;
    public static double MASA_BICICLETA = 0.7138d;
    public static int MAX_CICLISTAS = 4;
    public static int MAX_CADENCIA = 120;
    
    // public static boolean ALGUN_GANADOR = false;
    // public static boolean EMPEZADA = false;
    public static boolean CARTEL = false;
    public static boolean CARRERA = true;
    public static double LONGITUD_CARRERA = 0;
    public static double META = 0;
    public static final int ANCHO_REPRESENTACION_CICLISTA = 20;
    public static final Color[] COLORES = { Color.BLUE,  Color.MAGENTA, Color.ORANGE, Color.PINK, Color.RED, Color.CYAN, Color.GREEN, Color.WHITE, Color.YELLOW, Color.BLACK, Color.DARK_GRAY, Color.GRAY, Color.LIGHT_GRAY };
    public static final int  FACTORESCALA = 2;
    public static int ALTO_VENTANA = 0;
    public static int ANCHO_VENTANA = 0;
    public static int ALTO_LIENZO = 0;
    public static int ANCHO_LIENZO = 0;
    
    public static int UNIDAD_TIEMPO = 100;
    
    public static double RADIO_RUEDA = 0.2d;
    
    //Variables para controlar la velocidad de la aplicacion 
    public static int REDUCIR_FUERZA_GASTADA = 15;	// Mientras mas alto menos fuerza GASTAN
    	
    public static int VELOCIDAD_PERIODO = 20;	// Inversamente proporcional. Aumenta la velocidad en la que dan pedalas si se reduce el valor
    
    public static int AUMENTO_VELOCIDAD = 2; //Directamente proporcional. Aumenta la velocidad del ciclista si se aumenta el valor 
}
