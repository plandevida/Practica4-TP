package sistema.entidades.tiempo;

import java.util.StringTokenizer;

import sistema.interfaces.ObjetosConSalidaDeDatos;

public class Reloj extends Contador implements ObjetosConSalidaDeDatos {
	
	// Los milisegundos que lleva.
	private int milisegundos;
	
	// Los segundos que lleva, es una unidad mas pequeña que los minutos
	private int segundos;
	
	// Los minutos que lleva, es una unidad mas pequeña que las horas
	private int minutos;
	
	// Las horas que lleva, es la unidad mas grande del programa
	private int horas;
	
	// Instancia del reloj usando el patrón singleton
	private static Reloj INSTANCIA = null;
	
	private int totalImpulsos;
	
	private Reloj() {
		totalImpulsos = 0;
		milisegundos = 0;
		segundos = 0;
		minutos = 0;
		horas = 0;
	}
	
	public static Reloj getInstance() {
		
		if ( INSTANCIA == null ) {
			INSTANCIA = new Reloj();
		}
		
		return INSTANCIA;
	}
	
	/**
	 * Metodo que sirve para contabilizar el tiempo.
	 * 
	 * Funciona aumentan los impulsos, cuando estos llegan a 100 
	 * se añade un segundo, y los impulsos vuelven a cero.
	 * 
	 * Cuando los segundos llegan a 60 se añade un minuto 
	 * y los segundos vuelven a 0.
	 * 
	 * Cuando los minutos llegan a 60 se añade una hora
	 * y los minutos vuelven a 0.
	 * 
	 * Las horas no tienen limite, como la vida misma.
	 */
	@Override
	public void nuevoImpulso() {
		
		//if(VariablesDeContexto.carrera){
			totalImpulsos +=1;
			
			if (++impulsos >= 1) {
				impulsos = 0;
				if (++milisegundos >= 1000) {
					milisegundos = 0;
					if (++segundos >= 60) {
						segundos = 0;
						if (++minutos >= 60) {
							minutos = 0;
							horas++;
						}
					}
				}
			}
		//}
	}
	
	/**
	 * Método para obtener una salida de datos de un objeto.
	 * 
	 * @return Una cadena de texto separada por tokens.
	 */
	public StringTokenizer mostrarDatos() {
		StringBuilder mensaje = new StringBuilder("#reloj#,");
		mensaje.append(getHoras());
		mensaje.append(",");
		mensaje.append(getMinutos());
		mensaje.append(",");
		mensaje.append(getSegundos());
		mensaje.append(",");
		mensaje.append(getMilisegundos());
		mensaje.append(",");
		mensaje.append(getImpulsos());
		
		return new StringTokenizer(mensaje.toString(), ",");
	}
	
	/**
	 * Metodo para obtener los milisegundos.
	 * @return  Los milisegundos
	 */
	public int getMilisegundos() {
		return milisegundos;
	}
	
	/**
	 * Metodo para obtener los segundos.
	 * @return  Los segundos.
	 */
	public int getSegundos() {
		return segundos;
	}
	
	/**
	 * Metodo para obtener los minutos.
	 * @return  Los minutos.
	 */
	public int getMinutos() {
		return minutos;
	}
	
	/**
	 * Metodo para obtener las horas.
	 * @return  Las horas.
	 */
	public int getHoras() {
		return horas;
	}
	
	/**
	 * @return
	 */
	public int getTotalImpulsos() {
		return totalImpulsos;
	}
	
	public String getIdentificadorSalidaDatos() {
		return "ruloj";
	}
	
	/**
	 * Main.
	 * 
	 * Pone un retraso para ir aumentando los impulsos.
	 */
//	public static void main(String[] args) {
//		Reloj reloj = Reloj.getInstance();
//		
//		for (int i=0; i < (100*60*60*60*60+1600000); i++) {
////		while (reloj.getMilisegundos() <= 2) {
//			reloj.nuevoImpulso();
//			
//			System.out.println("total impulsos: " + reloj.getTotalImpulsos());
//			System.out.println("impulsos: " + reloj.getImpulsos());
//			System.out.println("milisegundos: " + reloj.getMilisegundos());
//			System.out.println("segundos: " + reloj.getSegundos());
//			System.out.println("minutos: " + reloj.getMinutos());
//			System.out.println("horas: " + reloj.getHoras());
//		}
//		
//		try {
//			Thread.sleep(2);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//	}
}
