package sistema.entidades.personas;

import sistema.interfaces.ObjetosQueSeEjecutan;

public class Persona implements ObjetosQueSeEjecutan {
	//nombre de la persona
	/**
	 * @uml.property  name="nombre"
	 */
	private String nombre;
	
	//peso de la persona
	/**
	 * @uml.property  name="peso"
	 */
	private int peso;
	
	public Persona(String nombrepersona, int pesopersona) {
		nombre = nombrepersona;
		peso = pesopersona;
		
	}
	
	// Este método tiene que hacer las veces del antiguo ejecuta.
	@Override
	public void ejecuta() { }
	
	/**
	 * Obtiene el peso de la persona.
	 * @return  El peso
	 * @uml.property  name="peso"
	 */
	public int getPeso() {
		return peso;
	}
	/**
	 * Cambia el peso de la persona.
	 * @param peso  Peso nuevo de la persona.
	 * @uml.property  name="peso"
	 */
	public void setPeso(int peso) {
		this.peso = peso;
	}

	/**
	 * Obtiene el nombre de la persona.
	 * @return  El nombre
	 * @uml.property  name="nombre"
	 */

	public String getNombre() {
		return nombre;
	}
	/**
	 * Cambia el nombre de la persona.
	 * @param nombre  Nombre nuevo de la persona.
	 * @uml.property  name="nombre"
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}
