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
	/**
	 * @uml.property  name="bicicletamontada"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Bicicleta bicicletamontada;
	
	// El reloj del ciclista.
	/**
	 * @uml.property  name="reloj"
	 * @uml.associationEnd  multiplicity="(1 1)"
	 */
	private Reloj reloj;
	
	// El tiempo desde que dió la última pedalada.
	/**
	 * @uml.property  name="milisegundos"
	 */
	private int milisegundos;
	
	// La candencia de la pedalada del ciclista.
	/**
	 * @uml.property  name="cadencia"
	 */
	private int cadencia;
	
	/**
	 * @uml.property  name="periodo"
	 */
	private double periodo;
	
	/**
	 * @uml.property  name="tiempopedalada"
	 */
	private double tiempopedalada;
	
	// Número único del ciclista en la carrera
	/**
	 * @uml.property  name="numeromallot"
	 */
	private int numeromallot;
	
	/**
	 * @uml.property  name="contadorcadencia"
	 */
	private int contadorcadencia;
	
	/**
	 * @uml.property  name="contadortiempofrenado"
	 */
	private int contadortiempofrenado;
	
	// El cansancio del ciclista.
	/**
	 * @uml.property  name="fuerza"
	 */
	private double fuerza;
	
	/**
	 * @uml.property  name="estrellado"
	 */
	private boolean estrellado;
	
	/**
	 * @uml.property  name="ganador"
	 */
	private boolean ganador;
	
	/**
	 * @uml.property  name="frenando"
	 */
	private boolean frenando;
	
	/**
	 * @uml.property  name="tiempofrenado"
	 */
	private double tiempofrenado;
	
	/**
	 * @uml.property  name="cantidadfrenado"
	 */
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
				VariablesDeContexto.ALGUN_GANADOR = ganador;
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
	 * @uml.property  name="bicicletamontada"
	 */
	public Bicicleta getBicicletamontada() {
		return bicicletamontada;
	}
	
	/**
	 * Cambia la bicicleta que se esta montando.
	 * @param bicicletamontada  Bicicleta que se esta montando.
	 * @uml.property  name="bicicletamontada"
	 */
	public void setBicicletamontada(Bicicleta bicicletamontada) {
		this.bicicletamontada = bicicletamontada;
	}
	
	/**
	 * Obtiene el reloj.
	 * @return  el reloj
	 * @uml.property  name="reloj"
	 */
	public Reloj getReloj() {
		return reloj;
	}
	
	/**
	 * Cambia el reloj.
	 * @param reloj  Reloj del ciclista.
	 * @uml.property  name="reloj"
	 */
	public void setReloj(Reloj reloj) {
		this.reloj = reloj;
	}
	
	/**
	 * Obtiene la cadencia del ciclista
	 * @return  La cadencia
	 * @uml.property  name="cadencia"
	 */
	public int getCadencia() {
		return cadencia;
	}
	
	/**
	 * Cambia la candencia del ciclista.
	 * @param cadencia  Cadencia nueva.
	 * @uml.property  name="cadencia"
	 */
	public void setCadencia(int cadencia) {
		this.cadencia = cadencia;
		calcularPeriodo();
	}
	
	/**
	 * Obtiene el periodo del ciclista
	 * @return  La cadencia
	 * @uml.property  name="periodo"
	 */
	public double getPeriodo() {
		return periodo;
	}

//	/**
//	 * Cambia el periodo del ciclista.
//	 * 
//	 * @param cadencia Cadencia nueva.
//	 */
//	public void setPeriodo(int periodo) {
//		this.periodo = periodo;
//	}

	/**
	 * Obtiene el tiempo de pedalada del ciclista.
	 * @return  Tiempo de pedalada.
	 * @uml.property  name="tiempopedalada"
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
	 * @uml.property  name="estrellado"
	 */
	public boolean isEstrellado() {
		return estrellado;
	}

	/**
	 * Cambia el estado del ciclista si se ha estrellado.
	 * @param  estrellado
	 * @uml.property  name="estrellado"
	 */
	public void setEstrellado(boolean estrellado) {
		this.estrellado = estrellado;
	}

	/**
	 * Obtiene el número del mallot del ciclista.
	 * @return  Número del mallot.
	 * @uml.property  name="numeromallot"
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
	 * @uml.property  name="fuerza"
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
	 * @uml.property  name="fuerza"
	 */
	public void setFuerza(double fuerza) {
		this.fuerza = fuerza;
	}
	/**
	 * @return
	 * @uml.property  name="ganador"
	 */
	public boolean isGanador() {
		return ganador;
	}

	/**
	 * @param ganador
	 * @uml.property  name="ganador"
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
