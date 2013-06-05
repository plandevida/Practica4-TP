package src.tests.sistema.entidadesmock.vehiculos.bicicletas;

import java.util.StringTokenizer;

import sistema.entidades.vehiculos.Vehiculo;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.manager.VariablesDeContexto;


/**
 * Esta clase representa una bicicleta, con los elementos y acciones comunes a estas.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class BicicletaMock extends Vehiculo implements ObjetosConSalidaDeDatos {

	// Continen el indice del piñón que se esta utilizando
	protected int platoactual;

	// Continen el indice del plato que se esta utilizando
	protected int pinhonactual;
	
	protected double radiorueda;
	
	protected double factorpendiente;
	
	protected double factorviento;
	
	public BicicletaMock() {
	
		setVelocidad(0);
		setEspacioRecorrido(0);
		setPinhonactual(0);
		setPlatoactual(0);
		radiorueda = 0.2d;
		
		factorpendiente = 1;
		factorviento = 0;
	}

	/**
	 * Relación entre el plato y piñón que se están usando actualmente.
	 * 
	 * @return Un entero que es relación entre ambos valores.
	 */
	private int relacionDeTransmision() {

		int relaciondetrasminsion = VariablesDeContexto.PLATOS[platoactual] / VariablesDeContexto.PINHONES[pinhonactual];

		return relaciondetrasminsion;
	}
	
	/**
	 * Devuelve el la longitud de la rueda.
	 * 
	 * @return El valor de la longitud de la rueda.
	 */
	private double recorridoLinealDeLaRueda() {

		double recorridolinealdelarueda = Math.PI * radiorueda;

		return recorridolinealdelarueda;

	}
	
	/**
	 * Calcula el espacio recorrido por cada pedalada que se da.
	 * 
	 * @return El valor del espacio recorrido.
	 */
	private double espacioDePedalada() {

		double espaciodepedalada = recorridoLinealDeLaRueda() * relacionDeTransmision();

		return espaciodepedalada;
	}
	
	/**
	 * Calcula la velocidad de la bicicleta en función de la cadencia del ciclista.
	 * 
	 * @param cadenciaciclista Frecuencia con la que el ciclista da pedaladas.
	 * @return La velocidad de la bicicleta.
	 */
	private double calcularAceleracionTiempoPedalada(double tiempopedalada) {

		double aceleracion = espacioDePedalada() / (tiempopedalada)*(tiempopedalada);

		return aceleracion;
	}
	
	/**
	 * Método para obtener una salida de datos de un objeto.
	 * 
	 * @return Una cadena de texto separada por tokens.
	 */
	public StringTokenizer mostrarDatos() {
		StringBuilder mensaje = new StringBuilder("#bicicleta#,");
		mensaje.append(getVelocidad());
		
		return new StringTokenizer(mensaje.toString(), ",");
	}
	
	/**
	 * Realiza una pedalada en la bicicleta, aumentando su velocidad.
	 * 
	 * @param cadenciaciclista Frecuencia con la que el ciclista da pedaladas. 
	 */
	public void darPedalada(double tiempopedalada) {
		double aceleracion = calcularAceleracionTiempoPedalada(tiempopedalada);
		
		//velocidad = velocidad * factorpendiente;
		//velocidad = velocidad + velocidad*factorviento;
		setEspacioRecorrido(espacioDePedalada());
		double velocidad = getVelocidad() + aceleracion*1; 
		setVelocidad(velocidad);
	}
	
	/**
	 * Decrementa la velocidad de la bicicleta.
	 */
	public void frenar() {
		double velocidad = getVelocidad();
		
		setEspacioRecorrido(espacioDePedalada());
		
		double decrementovelocidad = velocidad * 0.2;
		
		setVelocidad(getVelocidad()-decrementovelocidad);
	}
	
	/**
	 * Incrementa el piñón de la bicicleta.
	 */
	public void incrementarPinhon() {
		if (pinhonactual < VariablesDeContexto.PINHONES.length - 1) {
			pinhonactual++;
		}
	}
	
	/**
	 * Decrementa el piñón de la bicicleta.
	 */
	public void decrementarPinhon() {
		if (pinhonactual > 0) {
			pinhonactual--;
		}
	}
	
	/**
	 * Incrementa el plato de la bicicleta.
	 */
	public void incrementarPlato() {
		if (platoactual < VariablesDeContexto.PLATOS.length - 1) {
			platoactual++;
		}
	}
	
	/**
	 * Decrementa el plato de la bicicleta.
	 */
	public void decrementarPlato() {
		if (platoactual > 0) {
			platoactual--;
		}
	}
	
	/**
	 * Obtiene los piñones y sus dientes.
	 * 
	 * @return Una lista de los piñones y sus dientes.
	 */
	public int[] getPinhones() {
		return VariablesDeContexto.PINHONES;
	}
	
	/**
	 * Obtiene los platos y sus dientes.
	 * 
	 * @return Una lista de los platos y sus dientes.
	 */
	public int[] getPlatos() {
		return VariablesDeContexto.PLATOS;
	}
	
	/**
	 * Obtiene el plato engranado actualmente.
	 *  
	 * @return El indice de la lista de platos del plato engranado.
	 */
	public int getPlatoactual() {
		return platoactual;
	}
	
	/**
	 * Ajusta el plato engranado actualmente por otro.
	 * 
	 * @param platoactual El nuevo plato a engranar.
	 */
	public void setPlatoactual(int platoactual) {
		if ( platoactual <= VariablesDeContexto.PLATOS.length - 1 || platoactual >= 0) {
			this.platoactual = platoactual;
		}
	}
	
	/**
	 * Obtiene el piñón engranado actualmente.
	 * 
	 * @return El indice de la lista de piñones del piñón engranado
	 */
	public int getPinhonactual() {
		return pinhonactual;
	}
	
	/**
	 * Ajusta el piñón engranado actualmente por otro.
	 * 
	 * @param pinhonactual El nuevo piñón a engranar.
	 */
	public void setPinhonactual(int pinhonactual) {
		if ( pinhonactual <= VariablesDeContexto.PINHONES.length - 1 || pinhonactual >= 0) {
			this.pinhonactual = pinhonactual;
		}
	}
	
	/**
	 * Obtiene el radio de la rueda.
	 * 
	 * @return Valor del radio de la rueda.
	 */
	public double getRadiorueda() {
		return radiorueda;
	}
	
	private int id;
	
	public void setId(int numerocorredor) {
		id = numerocorredor;
	}
	
	public String getIdentificadorSalidaDatos() {
		return "bicicleta " + id;
	}

	public double getPendiente() {
		return factorpendiente;
	}

	public void setPendiente(double pendiente) {
		this.factorpendiente = pendiente;
	}

	public double getViento() {
		return factorviento;
	}

	public void setViento(double viento) {
		this.factorviento = viento;
	}

//	 private enum Pinhones {
//		 UNO(10), //0
//		 DOS(20), //1
//		 TRES(30), //2
//		 CUATRO(40); //3
//		
//		 private int numerodedientes;
//		
//		 Pinhones(int numerodientes) {
//			 numerodedientes = numerodientes;
//		 }
//		
//		 public int getNumeroDientes() {
//			 return numerodedientes;
//		 }
//		
//		 public Pinhones desdeIndice(int indice) {
//			 return ( indice < Pinhones.values().length) ? Pinhones.values()[indice] : null;
//		 }
//	 }
}