package sistema.entrada.lectura.fichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import sistema.entrada.lectura.InterfazLectura;

public class LecturaFichero implements InterfazLectura {

	private BufferedReader fileinput;
	
	/**
	 * Abre el flujo de un fichero con la ruta especificada.
	 * @param filepath Ruta del fichero a leer.
	 */
	public LecturaFichero(String filepath) {
		
		try {
			
			File file = new File("");
			
			if (filepath != null) {
				
				file = new File(filepath);
			}
			
			fileinput = new BufferedReader(new FileReader(file));
			
		} catch (FileNotFoundException f) {
			System.err.println("El fichero no existe");
		} catch (NullPointerException np) {
			System.err.println("La ruta no es correcta: " + filepath);
		}
	}
	
	/**
	 * Carga el contenido completo de un fichero, termina cuando
	 * encuentra la línea EOF.
	 * @return Contenido completo del fichero
	 */
	public String cargarFichero() {
		String cadena = new String("");
		
		try {
			
			String aux = fileinput.readLine();
			
			while( ! aux.equals("EOF")) {
				
				cadena += aux + "\n";
				aux = fileinput.readLine();
			}
		} catch (IOException io) {
			System.err.println("Error al leer el fichero de configuración de la aplicación");
		}
		
		return cadena;
	}

	/**
	 * Lectura de un fichero sin espera activa, se lee línea a línea.
	 */
	@Override
	public String leerSinEsperaActiva() {
		
		String cadena = "";
		
		try {
			if (fileinput != null && fileinput.ready()) {
				
				cadena = fileinput.readLine();
				
			}	
		} catch (IOException e) {
			System.err.println("Error al leer el fichero o no está lista la lectura por fichero");
		}
		
		return cadena;
	}
	
	/**
	 * Cierra el flujo de lectura.
	 * @throws IOException 
	 */
	public void finalizar() throws IOException {
		if (fileinput != null) {
			fileinput.close();
		}
	}
}
