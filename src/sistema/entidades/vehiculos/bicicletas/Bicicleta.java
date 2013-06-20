package sistema.entidades.vehiculos.bicicletas;

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
public class Bicicleta extends Vehiculo implements ObjetosConSalidaDeDatos {

	// Continen el indice del pinhon que se esta utilizando
	/**
	 * @uml.property  name="platoactual"
	 */
	protected int platoactual;

	// Continen el indice del plato que se esta utilizando
	/**
	 * @uml.property  name="pinhonactual"
	 */
	protected int pinhonactual;
	
	/**
	 * @uml.property  name="radiorueda"
	 */
	protected double radiorueda;
	
	/**
	 * @uml.property  name="aceleracionpendiente"
	 */
	protected double aceleracionpendiente;
	
	/**
	 * @uml.property  name="aceleracionviento"
	 */
	protected double aceleracionviento;
	
	/**
	 * @uml.property  name="peso"
	 */
	protected int peso;
	
	/**
	 * @uml.property  name="impulso"
	 */
	protected int impulso;
	public Bicicleta() {
	
		setVelocidad(0);
		setEspacioRecorrido(0);
		setPinhonactual(0);
		setPlatoactual(2);
		radiorueda = VariablesDeContexto.RADIO_RUEDA;
		peso = 10;
		aceleracionpendiente = 0;
		aceleracionviento = 0;
		impulso = 1;
	}

	/**
	 * Relación entre el plato y pinhon que se están usando actualmente.
	 * 
	 * @return Un entero que es relación entre ambos valores.
	 */
	private double relacionDeTransmision() {

		double relaciondetrasminsion = redondear(VariablesDeContexto.PLATOS[platoactual] / VariablesDeContexto.PINHONES[pinhonactual],2);

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

		double aceleracion = espacioDePedalada() / (tiempopedalada*tiempopedalada);
		//System.out.println("esp "+ espacioDePedalada()+ " tiem " + tiempopedalada+" ac" +aceleracion);
		return aceleracion;
	}
	
	/**
	 * Método para obtener una salida de datos de un objeto.
	 * 
	 * @return Una cadena de texto separada por tokens.
	 */
	public StringTokenizer mostrarDatos() {
		StringBuilder mensaje = new StringBuilder("#bicicleta#,")
			.append(getVelocidad())
			.append(",")
			.append(getEspacioRecorrido())
			.append(",")
			.append(getPinhonactual())
			.append(",")
			.append(getPlatoactual());
		
		return new StringTokenizer(mensaje.toString(), ",");
	}
	
	/**
	 * Calcula la velocidad maxima a la que puede ir la bicicleta
	 * @param tiempopedalada : tiempo que tarda en dar una pedalada
	 * 
	 * @return La velocidad maxima a la que va la bicicleta
	 */
	private double velocidadmaxima(double tiempopedalada){
		double velocidadmax = espacioDePedalada() / tiempopedalada;
		return velocidadmax;
	}
	
	/**
	 * Realiza una pedalada en la bicicleta, aumentando su velocidad.
	 * 
	 * @param cadenciaciclista Frecuencia con la que el ciclista da pedaladas. 
	 * @return la fuerza gastada al pedalear
	 */
	public double darPedalada(double tiempopedalada, int pesociclista) {
		double aceleracion = calcularAceleracionTiempoPedalada(tiempopedalada);
		
		double aceleracionfactores = aceleracionpendiente + aceleracionviento;
		double velocidad = 0.00d;
		
//		if (velocidad > velocidadmaxima(tiempopedalada)){
//			velocidad = velocidadmaxima(tiempopedalada);
//		}
		if(aceleracion < aceleracionfactores){
			velocidad = (getVelocidad() + aceleracionfactores*impulso);
		}
		else {
			velocidad = (getVelocidad() + aceleracion *impulso);
			
			if (velocidad > velocidadmaxima(tiempopedalada)){
				velocidad = velocidadmaxima(tiempopedalada);
			}
			
			velocidad = (velocidad + aceleracionfactores*impulso);
			
			if (velocidad<0) velocidad = 0.00d;
		}

		setVelocidad(velocidad);
		setEspacioRecorrido((getVelocidad())/impulso);
		
		return ((peso/VariablesDeContexto.FUERZA_GRAVEDAD + pesociclista/VariablesDeContexto.FUERZA_GRAVEDAD )*(aceleracion + aceleracionfactores));
	}
	
	/**
	 * Decrementa la velocidad de la bicicleta.
	 */
	public void frenar(double decrementovelocidad) {
		System.out.println(decrementovelocidad);
		double velocidad = getVelocidad() - decrementovelocidad;
		
		if (velocidad < 0) velocidad = 0.00d; 

		setVelocidad(velocidad);
		setEspacioRecorrido(getVelocidad()/impulso);
	}
	
	/**
	 * Incrementa el pinhon de la bicicleta.
	 */
	public void incrementarPinhon() {
		if (pinhonactual < VariablesDeContexto.PINHONES.length - 1) {
			pinhonactual++;
		}
	}
	
	/**
	 * Decrementa el pinhon de la bicicleta.
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
	 * @return  El indice de la lista de platos del plato engranado.
	 * @uml.property  name="platoactual"
	 */
	public int getPlatoactual() {
		return platoactual;
	}
	
	/**
	 * Ajusta el plato engranado actualmente por otro.
	 * @param platoactual  El nuevo plato a engranar.
	 * @uml.property  name="platoactual"
	 */
	public void setPlatoactual(int platoactual) {
		if ( platoactual <= VariablesDeContexto.PLATOS.length - 1 || platoactual >= 0) {
			this.platoactual = platoactual;
		}
	}
	
	/**
	 * Obtiene el pinhon engranado actualmente.
	 * @return  El indice de la lista de piñones del pinhon engranado
	 * @uml.property  name="pinhonactual"
	 */
	public int getPinhonactual() {
		return pinhonactual;
	}
	
	/**
	 * Ajusta el pinhon engranado actualmente por otro.
	 * @param pinhonactual  El nuevo pinhon a engranar.
	 * @uml.property  name="pinhonactual"
	 */
	public void setPinhonactual(int pinhonactual) {
		if ( pinhonactual <= VariablesDeContexto.PINHONES.length - 1 || pinhonactual >= 0) {
			this.pinhonactual = pinhonactual;
		}
	}
	
	/**
	 * Obtiene el radio de la rueda.
	 * @return  Valor del radio de la rueda.
	 * @uml.property  name="radiorueda"
	 */
	public double getRadiorueda() {
		return radiorueda;
	}
	
	// identificador de la bicicleta
	/**
	 * @uml.property  name="id"
	 */
	private int id;
	
	/**
	 * @param numerocorredor
	 * @uml.property  name="id"
	 */
	public void setId(int numerocorredor) {
		id = numerocorredor;
	}
	
	/**
	 * @return
	 * @uml.property  name="id"
	 */
	public int getId() {
		return id;
	}
	
	@Override
	public String getIdentificadorSalidaDatos() {
		return id + " bicicleta";
	}

	public double getPendiente() {
		return aceleracionpendiente;
	}

	public void setPendiente(double pendiente) {
		this.aceleracionpendiente = pendiente;
	}

	public double getViento() {
		return aceleracionviento;
	}

	public void setViento(double viento) {
		this.aceleracionviento = viento;
	}
}
