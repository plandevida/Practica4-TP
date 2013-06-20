package sistema.entrada.lectura;

import sistema.entrada.lectura.fichero.LecturaFichero;
import sistema.entrada.lectura.teclado.LecturaTeclado;

/**
 * Clase que invoca a los sub-sistemas de lectura.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class LectorManager {
	
	/**
	 * @uml.property  name="teclado"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private LecturaTeclado teclado;
	/**
	 * @uml.property  name="fichero"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private LecturaFichero fichero;
	
	/**
	 * Crea un elemento de lectura de teclado y otro de fichero.
	 * 
	 * @param file Ruta del fichero a leer.
	 * @param solofichero True si solo se quiere leer de fichero.
	 */
	public LectorManager(String file, boolean solofichero) {
		
		if (! solofichero) {
			teclado = new LecturaTeclado();
		}
		
		fichero = new LecturaFichero(file);
	}
	
	/**
	 * Lee del teclado.
	 * 
	 * @return Cadena leida.
	 */
	public String leerTeclado() {
		
		return teclado.leerSinEsperaActiva();
	}
	
	/**
	 * Lee de un fichero especificado en el constructor.
	 * 
	 * @return Linea leida.
	 */
	public String leerFichero() {
		
		return fichero.leerSinEsperaActiva();
	}
	
	/**
	 * Lee un fichero completo especificado en el constructor.
	 * 
	 * @return Contenido del fichero completo.
	 */
	public String cargarFicheroCompelto() {
		
		return fichero.cargarFichero();
	}
	
	/**
	 * Cierra todos los flujos de lecturas.
	 * 
	 * @return true si se cerró todo correctamente.
	 */
	public boolean finalizarLecturas() {
		boolean correcto = true;
		
		try {
			teclado.finalizar();
			fichero.finalizar();
		} catch (Exception e) {
			correcto = false;
		}
		
		return correcto;
	}
}
