package src.tests.sistema.entidadesmock.personas;

import sistema.interfaces.ObjetosQueSeEjecutan;

public class PersonaMock implements ObjetosQueSeEjecutan {
	//nombre de la persona
	private String nombre;
	
	//peso de la persona
	private int peso;
	
	//cansancio de la persona
	private int cansancio;
	
	public PersonaMock(String nombrepersona, int pesopersona, int estadofisico) {
		nombre = nombrepersona;
		peso = pesopersona;
		cansancio = (estadofisico >= 0 && estadofisico < 100) ? estadofisico : 100;
	}
	
	// Este método tiene que hacer las veces del antiguo ejecuta.
	@Override
	public void ejecuta() { }
	
	/**
	 * Obtiene el peso de la persona.
	 *  
	 * @return El peso
	 */
	public int getPeso() {
		return peso;
	}
	/**
	 * Cambia el peso de la persona.
	 *  
	 *  @param peso Peso nuevo de la persona.
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}
	/**
	 * Obtiene el cansancio de la persona.
	 *  
	 * @return El cansancio
	 */
	public int getCansancio() {
		return cansancio;
	}
	/**
	 * Cambia el cansancio de la persona.
	 *  
	 *  @param cansancio Cansancio nuevo de la persona.
	 */
	public void setCansancio(int cansancio) {
		this.cansancio = cansancio;
	}
	/**
	 * Obtiene el nombre de la persona.
	 *  
	 * @return El nombre
	 */

	public String getNombre() {
		return nombre;
	}
	/**
	 * Cambia el nombre de la persona.
	 *  
	 *  @param nombre Nombre nuevo de la persona.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
