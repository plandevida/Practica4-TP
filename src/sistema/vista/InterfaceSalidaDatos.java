package sistema.vista;

import java.util.ArrayList;

import sistema.interfaces.ObjetosConSalidaDeDatos;

public interface InterfaceSalidaDatos {
	
	/**
	 * Registra un objeto para ser mostrada su salida.
	 * 
	 * @param objetoconsalidadatos Objeto a reistrar.
	 */
	public void registrarObjetoConSalidaDatos(ObjetosConSalidaDeDatos objetoconsalidadatos);	
	
	/**
	 * Registra todos los elementos de una colección.
	 * 
	 * @param listadeobjetosconsalidadatos
	 */
	public void registrarObjetoConSalidaDatos(ArrayList<ObjetosConSalidaDeDatos> listadeobjetosconsalidadatos);
	
	/**
	 * Muestra la salida de datos personalizada para cada tipo de elemento.
	 */
	public void mostrarDatos();
	
	/**
	 * Devuelve los permisos de la clase.
	 * 
	 * @return Clases o recursos sobre los que tiene permisos.
	 */
	public abstract String[] misPermisos();
}
