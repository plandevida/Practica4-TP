package sistema.entidades.personas.ciclistas;

import java.util.StringTokenizer;

import sistema.entidades.personas.Persona;
import sistema.entidades.tiempo.Reloj;
import sistema.entidades.vehiculos.bicicletas.Bicicleta;
import sistema.interfaces.ObjetosConSalidaDeDatos;
import sistema.manager.VariablesDeContexto;

/**
 * Clase que representa un ciclista.
 * 
 * @author Daniel Serrano Torres
 * @author Alvaro Quesada Pimentel
 */
public class Ciclista extends Persona implements ObjetosConSalidaDeDatos {
	
	// La bicicleta que va a montar.
	private Bicicleta bicicletamontada;
	
	// El reloj del ciclista.
	private Reloj reloj;
	
	// El tiempo desde que dió la última pedalada.
	private int milisegundos;
	
	// La candencia de la pedalada del ciclista.
	private int cadencia;
	
	private double periodo;
	
	private double tiempopedalada;
	
	// Número único del ciclista en la carrera
	private int numeromallot;
	
	private int contadorcadencia;
	
	private int contadortiempofrenado;
	
	// El cansancio del ciclista.
	private double fuerza;
	
	private boolean estrellado;
	
	private boolean ganador;
	
	private boolean frenando;
	
	private double tiempofrenado;
	
	private double cantidadfrenado;
	
	/**
	 * Crea un ciclista.
	 * 
	 * @param nombre
	 * @param nummallot
	 * @param cadenciaCiclista
	 * @param bicicletaamontar
	 * @param tiempopedalada
	 * @param relojCiclista
	 * @param peso
	 * @param fuerza
	 */
	public Ciclista(String nombre, int nummallot, int cadenciaCiclista, Bicicleta bicicletaamontar, double tiempopedalada, Reloj relojCiclista, int peso, double fuerza) {
		
		super(nombre, peso);
		cadencia = cadenciaCiclista;
		bicicletamontada = bicicletaamontar;
		reloj = relojCiclista;
		numeromallot = nummallot;
		this.tiempopedalada = tiempopedalada;	
		this.fuerza = fuerza;
		bicicletamontada.setId(numeromallot);
		estrellado = false;
		ganador = false;
		frenando = false;
		tiempofrenado = 0d;
		cantidadfrenado = 0d;
		calcularPeriodo();
		contadorcadencia = 1;
		contadortiempofrenado = 1;
	}


	public void setContadorCadencia(int contador) {
		contadorcadencia = contador;
	}
	/**
	 * Metodo que contiene las acciones comprendidas
	 * por el iclista.
	 */
	@Override
	public void ejecuta() {
		
		pedalear();
		
//		System.out.println(getIdentificadorSalidaDatos() + " " + bicicletamontada.getEspacioRecorrido());
	}
	
	/**
	 * Metodo para obtener una salida de datos de un objeto.
	 * 
	 * @return Una cadena de texto separada por tokens.
	 */
	public StringTokenizer mostrarDatos() {
		StringBuilder mensaje = new StringBuilder("#ciclista#,")
										.append(",")
										.append(getNumeromallot())
										.append(",")
										.append(getNombre())
										.append(",")
										.append(getFuerzaAsInt())
										.append(",")
										.append(getCadencia())
										.append(",")
										.append(getTiempopedalada());

		StringTokenizer stringTokenizer = new StringTokenizer(mensaje.toString(), ",");

		return stringTokenizer;
	}
	/**
	 * 
	 */

	
	public boolean ciclistaPuedeAvanzar(){
		boolean avanza = false;
		if(VariablesDeContexto.CARRERA){

			if(bicicletamontada.getEspacioRecorrido() < VariablesDeContexto.META){
				if( !ganador){
				
					if( !estrellado){
						
						if (fuerza>0){
							
							avanza = true;
						}
					}
				}
			}
			else if( !ganador){
				VariablesDeContexto.CARRERA = false;
				ganador = true;
//				VariablesDeContexto.ALGUN_GANADOR = ganador;
				System.out.println("¡El ciclista "+numeromallot+ " ha ganado!");
			}
		}
		return avanza;
	}
	/**
	 * Metodo que da pedaladas a la bicicleta y actualiza la fuerza consumida.
	 * Las pedaladas se dan en fuencion de la cadencia
	 */
	public void pedalear() {
		if (ciclistaPuedeAvanzar()){
			if (reloj.getMilisegundos() != milisegundos) {
				
				if (contadorcadencia >= (periodo * VariablesDeContexto.VELOCIDAD_PERIODO)) {
					if (!frenando){				
						double fuerzagastada = redondear ((bicicletamontada.darPedalada(tiempopedalada, getPeso()))/VariablesDeContexto.REDUCIR_FUERZA_GASTADA,2);
								
						if (fuerza > 0) fuerza = redondear((fuerza - fuerzagastada),2);
					}
					
					else frenar();
					
					contadorcadencia = 1;
				}
				else contadorcadencia++;
			
				milisegundos = reloj.getMilisegundos();
			}
		
		}
	}
	/**
	 * Calcula el periodo y se asegura de que el tiempo de pedalada no sea mayor del de el periodo;
	 */
	public void calcularPeriodo() {
		
		periodo = (double)60/cadencia;
		periodo = redondear(periodo, 2);
		if (periodo < tiempopedalada) {
			tiempopedalada = periodo;
		}
	}
	
	/**
	 * Frena la bicicleta.
	 */
	public void frenar() {

		if(contadortiempofrenado <= tiempofrenado ){
					
			bicicletamontada.frenar(cantidadfrenado);
					
			contadortiempofrenado++;
			System.out.println(contadortiempofrenado);
		}
				
		else {
			System.out.println(" estoy frenando");
			contadortiempofrenado = 1;
			frenando = false;
			tiempofrenado = 0d;
			cantidadfrenado = 0d;
		}
			
	}

	/**
	 * Aumentar el pinhon actual al mayor adyacente.
	 * 
	 * @return El pinhon al que se ha cambiado.
	 */
	public int cambiarPinhon(int indicepinhon) {
		
		if (indicepinhon >= 0 && indicepinhon < VariablesDeContexto.PINHONES.length) {
			
			bicicletamontada.setPinhonactual(indicepinhon);
		}
		
		return bicicletamontada.getPinhonactual();
	}
	
	/**
	 * Aumentar el plato actual al mayor adyacente.
	 * 
	 * @return El plato al que se ha cambiado.
	 */
	public int cambiarPlato(int indiceplato) {
		
		if (indiceplato >= 0 && indiceplato< VariablesDeContexto.PLATOS.length) {
		
			bicicletamontada.setPlatoactual(indiceplato);
		}
		
		return bicicletamontada.getPlatoactual();
	}
	
	/**
	 * Obtiene la bicicleta que se esta montando.
	 * @return  la bicicleta que se esta montando
	 */
	public Bicicleta getBicicletamontada() {
		return bicicletamontada;
	}
	
	/**
	 * Cambia la bicicleta que se esta montando.
	 * @param bicicletamontada  Bicicleta que se esta montando.
	 */
	public void setBicicletamontada(Bicicleta bicicletamontada) {
		this.bicicletamontada = bicicletamontada;
	}
	
	/**
	 * Obtiene el reloj.
	 * @return  el reloj
	 */
	public Reloj getReloj() {
		return reloj;
	}
	
	/**
	 * Cambia el reloj.
	 * @param reloj  Reloj del ciclista.
	 */
	public void setReloj(Reloj reloj) {
		this.reloj = reloj;
	}
	
	/**
	 * Obtiene la cadencia del ciclista
	 * @return  La cadencia
	 */
	public int getCadencia() {
		return cadencia;
	}
	
	/**
	 * Cambia la candencia del ciclista.
	 * @param cadencia  Cadencia nueva.
	 */
	public void setCadencia(int cadencia) {
		this.cadencia = cadencia;
		calcularPeriodo();
	}
	
	/**
	 * Obtiene el periodo del ciclista
	 * @return  La cadencia
	 */
	public double getPeriodo() {
		return periodo;
	}

	/**
	 * Obtiene el tiempo de pedalada del ciclista.
	 * @return  Tiempo de pedalada.
	 */
	public double getTiempopedalada() {
		return tiempopedalada;
	}

	/**
	 * Cambia el tiempo de pedalada del ciclista.
	 * 
	 * @param tiempopedalada Nuevo tiempo de pedalada.
	 */
	public void setTiempopedalada(Double tiempopedalada) {
		this.tiempopedalada = tiempopedalada;
	}

	/**
	 * Obtiene el estado de si el ciclista se ha estrellado.
	 * @return  True si se ha estrellado, false EOC.
	 */
	public boolean isEstrellado() {
		return estrellado;
	}

	/**
	 * Cambia el estado del ciclista si se ha estrellado.
	 * @param  estrellado
	 */
	public void setEstrellado(boolean estrellado) {
		this.estrellado = estrellado;
	}

	/**
	 * Obtiene el número del mallot del ciclista.
	 * @return  Número del mallot.
	 */
	public int getNumeromallot() {
		return numeromallot;
	}
	
	/**
	 * Metodo que devuelve el identificador del ciclista.
	 * 
	 * @return Identificador único.
	 */
	public String getIdentificadorSalidaDatos() {
		return numeromallot + " ciclista";
	}

	/**
	 * Obtiene la fuerza del ciclista.
	 * @return  La fuerza de 0 a 100
	 */
	public double getFuerza() {
		return fuerza;
	}
	
	/**
	 * Obtiene la fuerza del ciclista.
	 * 
	 * @return La fuerza de 0 a 100
	 */
	public int getFuerzaAsInt() {
		return (int)fuerza;
	}

	/**
	 * Asigna la fuerza del ciclista.
	 * @param fuerza  La nueva fuerza del ciclista.
	 */
	public void setFuerza(double fuerza) {
		this.fuerza = fuerza;
	}
	/**
	 * @return
	 */
	public boolean isGanador() {
		return ganador;
	}

	/**
	 * @param ganador
	 */
	public void setGanador(boolean ganador) {
		this.ganador = ganador;
	}
	/**
	 * Metodo para inicializar las variables para que empiece a frenar
	 * @param tiempofrenado tiempo que estara frenando
	 * @param cantidad	la cantidad q frenara
	 */
	public void setFrenando(double tiempofrenado, double cantidad) {
		frenando = true;
		this.tiempofrenado = tiempofrenado;
		cantidadfrenado = cantidad/tiempofrenado;
	}
	/**
	 * Metodo que redondea decimales
	 *
	 * 
	 * @param numero
	 * @param decimales
	 * @return numero redondeado
	 */
	public double redondear( double numero, int decimales ) {
	    return Math.round(numero*Math.pow(10,decimales))/Math.pow(10,decimales);
	  }
}
