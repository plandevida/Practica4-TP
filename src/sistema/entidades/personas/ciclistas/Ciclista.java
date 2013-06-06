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
	
	private float periodo;
	
	private double tiempopedalada;
	
	// Número único del ciclista en la carrera
	private int numeromallot;
	
	private int contadorcandencia;
	
	// El cansancio del ciclista.
	private double fuerza;
	
	private boolean estrellado;
	
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
		
		calcularPeriodo();
		contadorcandencia = 1;
	}
	
	/**
	 * Método que contiene las acciones comprendidas
	 * por el iclista.
	 */
	@Override
	public void ejecuta() {
		
//		System.out.println("Ciclista: " + getIdentificadorSalidaDatos() + "Ejecutando...");
		
		pedalear();
		
//		System.out.println("Ciclista: " + getIdentificadorSalidaDatos() + "Ejecutado");
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
	 * Metodo que da pedaladas a la bicicleta y actualiza la fuerza consumida.
	 * Las pedaladas se dan en fuencion de la cadencia
	 */
	public void pedalear() {
		if(!estrellado){
			if (fuerza>0){
				if (reloj.getMilisegundos() != milisegundos) {
					
					
					if (contadorcandencia >= (periodo * 10)) {
						
						
						double fuerzagastada = (bicicletamontada.darPedalada(tiempopedalada, getPeso()))/10; 
						if (fuerza > 0) fuerza = (fuerza - fuerzagastada);
						
						contadorcandencia = 1;
					}
					
					contadorcandencia++;

					milisegundos = reloj.getMilisegundos();
				}
			}
		}
	}
	/**
	 * Calcula el periodo y se asegura de que el tiempo de pedalada no sea mayor del de el periodo;
	 */
	public void calcularPeriodo() {
		
		periodo = (float)60/cadencia;
		
		if (periodo < tiempopedalada) {
			tiempopedalada = periodo;
		}
	}
	
	/**
	 * Frena la bicicleta.
	 */
	public void frenar() {
		if (reloj.getMilisegundos() != milisegundos) {
			bicicletamontada.frenar();
		
			milisegundos = reloj.getMilisegundos();
		}
		
		
	}

	/**
	 * Aumentar el piñón actual al mayor adyacente.
	 * 
	 * @return El piñón al que se ha cambiado.
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
		
			bicicletamontada.setPinhonactual(indiceplato);
		}
		
		return bicicletamontada.getPlatoactual();
	}
	
	/**
	 * Obtiene la bicicleta que se esta montando.
	 *  
	 * @return la bicicleta que se esta montando
	 */
	public Bicicleta getBicicletamontada() {
		return bicicletamontada;
	}
	
	/**
	 * Cambia la bicicleta que se esta montando.
	 * 
	 * @param bicicletamontada Bicicleta que se esta montando.
	 */
	public void setBicicletamontada(Bicicleta bicicletamontada) {
		this.bicicletamontada = bicicletamontada;
	}
	
	/**
	 * Obtiene el reloj.
	 *  
	 * @return el reloj
	 */
	public Reloj getReloj() {
		return reloj;
	}
	
	/**
	 * Cambia el reloj.
	 * 
	 * @param reloj Reloj del ciclista.
	 */
	public void setReloj(Reloj reloj) {
		this.reloj = reloj;
	}
	
	/**
	 * Obtiene la cadencia del ciclista
	 *  
	 * @return La cadencia
	 */
	public int getCadencia() {
		return cadencia;
	}
	
	/**
	 * Cambia la candencia del ciclista.
	 * 
	 * @param cadencia Cadencia nueva.
	 */
	public void setCadencia(int cadencia) {
		this.cadencia = cadencia;
	}
	
//	/**
//	 * Obtiene el periodo del ciclista
//	 *  
//	 * @return La cadencia
//	 */
//	public double getPeriodo() {
//		return periodo;
//	}
//
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
	 * 
	 * @return Tiempo de pedalada.
	 */
	public double getTiempopedalada() {
		return tiempopedalada;
	}

	/**
	 * Cambia el tiempo de pedalada del ciclista.
	 * 
	 * @param tiempopedalada Nuevo tiempo de pedalada.
	 */
	public void setTiempopedalada(double tiempopedalada) {
		this.tiempopedalada = tiempopedalada;
	}

	/**
	 * Obtiene el estado de si el ciclista se ha estrellado.
	 * 
	 * @return True si se ha estrellado, false EOC.
	 */
	public boolean isEstrellado() {
		return estrellado;
	}

	/**
	 * Cambia el estado del ciclista si se ha estrellado.
	 * 
	 * @param estrellado
	 */
	public void setEstrellado(boolean estrellado) {
		this.estrellado = estrellado;
	}

	/**
	 * Obtiene el número del mallot del ciclista.
	 * 
	 * @return Número del mallot.
	 */
	public int getNumeromallot() {
		return numeromallot;
	}
	
	/**
	 * Método que devuelve el identificador del ciclista.
	 * 
	 * @return Identificador único.
	 */
	public String getIdentificadorSalidaDatos() {
		return numeromallot + " ciclista";
	}

	/**
	 * Obtiene la fuerza del ciclista.
	 * 
	 * @return La fuerza de 0 a 100
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
	 * 
	 * @param fuerza La nueva fuerza del ciclista.
	 */
	public void setFuerza(double fuerza) {
		this.fuerza = fuerza;
	}

	public float getPeriodo() {
		return periodo;
	}

	public void setPeriodo(float periodo) {
		this.periodo = periodo;
	}
}
