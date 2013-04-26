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
		
		StringBuilder datos = new StringBuilder();
		
		switch (formato) {

		// Caso para el formato de la bicicleta
		case "#bicicleta#":
			datos.append("velocidad: ")
				.append(mensaje.nextToken());
			
			vista.ponerDatosEnVentana(objetoamostrar.getIdentificadorSalidaDatos(), datos.toString());
			
			break;

		// Caso para el formato del ciclista
		case "#ciclista#":
			datos.append("nombre: ")
			.append(mensaje.nextToken()).append('\n')
			.append("peso: ")
			.append(mensaje.nextToken()).append('\n')
			.append("cadencia: ")
			.append(mensaje.nextToken());
			
			vista.ponerDatosEnVentana(objetoamostrar.getIdentificadorSalidaDatos(), datos.toString());
			
			break;

		// Caso para el formato reloj
		case "#reloj#":
//			datos.append((String) mensaje.nextToken()).append("h ")
//					.append((String) mensaje.nextToken())
//					.append("m ")
//					.append((String) mensaje.nextToken())
//					.append("s ")
//					.append((String) mensaje.nextToken())
//					.append("ms ")
//					.append((String) mensaje.nextToken())
//					.append(" impulsos");
			
			Integer hora = Integer.valueOf(mensaje.nextToken());
			Integer minutos = Integer.valueOf(mensaje.nextToken());
			Integer segundos = Integer.valueOf(mensaje.nextToken());
			Integer milisegundos = Integer.valueOf(mensaje.nextToken());
			
			vista.ponerDatosEnVentana(objetoamostrar.getIdentificadorSalidaDatos(), hora, minutos, segundos, milisegundos);
			
			break;

		default:
			break;
		}
		
//		return datos.toString();
	}

	@Override
	public void mostrarDatos() {
		
		for(ObjetosConSalidaDeDatos objetoamostrar : registroobjetossalidadatos) {
//			vista.ponerDatosEnVentana(objetoamostrar.getIdentificadorSalidaDatos(), formateadorDatos(objetoamostrar));
			formateadorDatos(objetoamostrar);
		}
	}
	
	@Override
	public void ejecuta() {
		mostrarDatos();
	}
}
