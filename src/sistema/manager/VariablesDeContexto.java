package sistema.manager;

public final class VariablesDeContexto {
	
	// Contexto de los ficheros del sistema.
	public static final String CONFIG_FOLDER_PATH = "resources/configuracion/";
	public static final String DEFAULT_CONFIG_PATH = "resources/configuracion/carrera";
	public static final String DEFAULT_SYLLABLE_FILE_PATH = "resources/miscelania/generaciondenombres/syllable";

	// El array representa los piñones de la bicicleta con el indice
	// del array y su valor es el número de dientes del piñón
	public static int[] PINHONES = new int[] { 15, 20, 25, 30 };

	// El array representa los plato de la bicicleta con el indice
	// del array y su valor es el número de dientes del plato
	public static int[] PLATOS = new int[] { 30, 40, 50 };
	
	public static final double FUERZA_GRAVEDAD = 9.80665;
    public static final double MASA_BICICLETA = 0.7138;
    public static int MAX_CICLISTAS = 6;
    public static int MAX_CADENCIA = 120;
}
