package sistema.vista.consola;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.vista.InterfaceSalidaDatos;

/**
 * Clase que muestra la información formateada de cada
 * elemento registrado en esta.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class SalidaDatos implements InterfaceSalidaDatos{

	/**
	 * Lista de objetos que se van a mostrar
	 * @uml.property  name="registroobjetossalidadatos"
	 * @uml.associationEnd  multiplicity="(0 -1)" elementType="sistema.interfaces.ObjetosConSalidaDeDatos"
	 */
	private List<ObjetosConSalidaDeDatos> registroobjetossalidadatos;

	/**
	 * Contruye la clase con un registro vacío de elementos.
	 */
	public SalidaDatos() {
		registroobjetossalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();
	}
	
	public SalidaDatos(List<ObjetosConSalidaDeDatos> listaamostrar) {
		registroobjetossalidadatos = listaamostrar;
	}
	
	/**
	 * Registra un objeto para ser mostrada su salida.
	 * 
	 * @param objetoconsalidadatos Objeto a reistrar.
	 */
	public void registrarObjetoConSalidaDatos(ObjetosConSalidaDeDatos objetoconsalidadatos) {

		registroobjetossalidadatos.add(objetoconsalidadatos);
	}
	
	/**
	 * Registra todos los elementos de una colección.
	 * 
	 * @param listadeobjetosconsalidadatos
	 */
	public void registrarObjetoConSalidaDatos(ArrayList<ObjetosConSalidaDeDatos> listadeobjetosconsalidadatos) {
		
		for (ObjetosConSalidaDeDatos objetoaregistrar : listadeobjetosconsalidadatos) {
			registroobjetossalidadatos.add(objetoaregistrar);
		}
	}

	/**
	 * Muestra la salida de datos personalizada para cada tipo de elemento.
	 */
	public void mostrarDatos() {

		if (registroobjetossalidadatos != null) {

			for (ObjetosConSalidaDeDatos objetoamostrar : registroobjetossalidadatos) {
				
				StringTokenizer mensaje = objetoamostrar.mostrarDatos();

				String formato = mensaje.nextToken();

				switch (formato) {

				// Caso para el formato de la bicicleta
				case "#bicicleta#":
					System.out.println("-- Bicicleta --");
					System.out.println("velocidad: " + mensaje.nextElement());
					
					break;

				// Caso para el formato del ciclista
				case "#ciclista#":
					System.out.println("-- Ciclista --");
					System.out.println("nombre: " + mensaje.nextElement());
					System.out.println("peso: " + mensaje.nextElement());
					System.out.println("cadencia: " + mensaje.nextElement());
					
					break;

				// Caso para el formato reloj
				case "#reloj#":
					System.out.println("-- Reloj --");

					StringBuilder datos = new StringBuilder(
							(String) mensaje.nextElement()).append("h ")
							.append((String) mensaje.nextElement())
							.append("m ")
							.append((String) mensaje.nextElement())
							.append("s ")
							.append((String) mensaje.nextElement())
							.append(" impulsos");

					System.out.println(datos.toString());
					
					break;

				default:
					while (mensaje.hasMoreElements())
						System.out.println(mensaje.nextElement());
					
					break;
				}

			}
		}
	}
}
