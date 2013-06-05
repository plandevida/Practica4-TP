package sistema.vista.visual;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.interfaces.ObjetosQueSeEjecutan;
import sistema.vista.InterfaceSalidaDatos;

public class FormateadorDatosVista implements InterfaceSalidaDatos, ObjetosQueSeEjecutan {

	// Lista de objetos que se van a mostrar
	private List<ObjetosConSalidaDeDatos> registroobjetossalidadatos;
	private Ventana vista;
	
	public FormateadorDatosVista(List<ObjetosConSalidaDeDatos> listadeobjetosamostrar, Ventana ventana) {
		registroobjetossalidadatos = listadeobjetosamostrar;
		vista = ventana;
	}
	
	@Override
	public void registrarObjetoConSalidaDatos(ObjetosConSalidaDeDatos objetoconsalidadatos) {
		
		if (registroobjetossalidadatos == null) {
			registroobjetossalidadatos = new ArrayList<ObjetosConSalidaDeDatos>();
		}
		registroobjetossalidadatos.add(objetoconsalidadatos);
	}

	@Override
	public void registrarObjetoConSalidaDatos(ArrayList<ObjetosConSalidaDeDatos> listadeobjetosconsalidadatos) {
		
		for (ObjetosConSalidaDeDatos objetoaregistrar : listadeobjetosconsalidadatos) {
			registrarObjetoConSalidaDatos(objetoaregistrar);
		}
	}
	
	private void formateadorDatos(ObjetosConSalidaDeDatos objetoamostrar) {
		
		StringTokenizer mensaje = objetoamostrar.mostrarDatos();

		String formato = mensaje.nextToken();
		
		String[] datos = new String[mensaje.countTokens()];
		
		switch (formato) {

		// Caso para el formato de la bicicleta
		case "#bicicleta#":
			datos[0] = mensaje.nextToken();
			datos[1] = mensaje.nextToken();
			datos[2] = mensaje.nextToken();
			datos[3] = mensaje.nextToken();
			
			vista.ponerDatosEnVentana(objetoamostrar.getIdentificadorSalidaDatos(), (Object[])datos);
			
			break;

		// Caso para el formato del ciclista
		case "#ciclista#":
			
			datos[0] = mensaje.nextToken();
			datos[1] = mensaje.nextToken();
			datos[2] = mensaje.nextToken();
			datos[3] = mensaje.nextToken();
			
			vista.ponerDatosEnVentana(objetoamostrar.getIdentificadorSalidaDatos(), (Object[])datos);
			
			break;

		// Caso para el formato reloj
		case "#reloj#":
			
			try {
				Integer hora = Integer.valueOf(mensaje.nextToken());
				Integer minutos = Integer.valueOf(mensaje.nextToken());
				Integer segundos = Integer.valueOf(mensaje.nextToken());
				Integer milisegundos = Integer.valueOf(mensaje.nextToken());
				Integer impulsos = Integer.valueOf(mensaje.nextToken());
				
				vista.ponerDatosEnVentana(objetoamostrar.getIdentificadorSalidaDatos(), hora, minutos, segundos, milisegundos, impulsos);
				
			} catch (NumberFormatException ne) {
				vista.ponerDatosEnVentana("log", mensaje);
			}
			
			break;

		default:
			break;
		}
	}

	@Override
	public void mostrarDatos() {
		
		for(ObjetosConSalidaDeDatos objetoamostrar : registroobjetossalidadatos) {
			formateadorDatos(objetoamostrar);
		}
	}
	
	@Override
	public void ejecuta() {
		mostrarDatos();
	}
}
