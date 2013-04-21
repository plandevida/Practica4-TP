package sistema.interfaces;

import java.util.StringTokenizer;

public interface ObjetosConSalidaDeDatos {
	
	/**
	 * Método para obtener una salida de datos de un objeto.
	 * 
	 * @return Una cadena de texto separada por tokens.
	 */
	public StringTokenizer mostrarDatos();
	
	/**
	 * Retorna un identificador para ubicar sus datos en la vista.
	 * @return El identificador.
	 */
	public String getIdentificadorSalidaDatos();
}
